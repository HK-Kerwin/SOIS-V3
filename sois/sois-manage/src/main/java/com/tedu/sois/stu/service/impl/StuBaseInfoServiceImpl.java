package com.tedu.sois.stu.service.impl;

import com.tedu.sois.annotation.RequiredLog;
import com.tedu.sois.common.config.StatusCodeConfig;
import com.tedu.sois.common.exception.ServiceException;
import com.tedu.sois.common.util.ShiroUtils;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.stu.dao.StuBaseInfoDao;
import com.tedu.sois.common.pojo.stu.StuBaseInfo;
import com.tedu.sois.stu.service.StuBaseInfoService;
import com.tedu.sois.common.pojo.sys.SysUser;
import com.tedu.sois.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, //隔离级别
        rollbackFor = Throwable.class,//什么异常回滚
        propagation = Propagation.REQUIRED)//传播特性
public class StuBaseInfoServiceImpl implements StuBaseInfoService, StatusCodeConfig {


    @Autowired
    private StuBaseInfoDao stuBaseInfoDao;

    /**
     * 用户对象接口
     */
    @Autowired
    private SysUserService sysUserService;


    @CacheEvict(value = "stuInfoCache", allEntries = true)
    @Override
    public int saveStuBaseInfo(StuBaseInfo stuBaseInfo, int state) {

        if (stuBaseInfo == null)
            throw new ServiceException("请输入信息进行存储");

        if (!isPhoneNumber(stuBaseInfo.getPhoneNumber()))
            throw new ServiceException("自己的手机格式错误,请进行检查");
        if (!isPhoneNumber(stuBaseInfo.getP1PhoneNumber()))
            throw new ServiceException("关系人1手机格式错误,请进行检查");
        if (!isPhoneNumber(stuBaseInfo.getP2PhoneNumber()))
            throw new ServiceException("关系人2手机格式错误,请进行检查");

        if (!isIDCard(stuBaseInfo.getIdCard()))
            throw new ServiceException("身份证格式错误,请进行检查");

        StuBaseInfo phoneNumberToData = stuBaseInfoDao.selectStuBaseInfoByPhoneNumber(stuBaseInfo.getPhoneNumber());
        if (phoneNumberToData != null)
            throw new ServiceException("手机号码已经存在,或资料未进行修改,请到查询页面搜索");

        StuBaseInfo stuEmailToData = stuBaseInfoDao.selectStuBaseInfoByEmail(stuBaseInfo.getStuEmail());
        if (stuEmailToData != null)
            throw new ServiceException("邮箱信息已经存在,或资料未进行修改,请到查询页面搜索");

        StuBaseInfo idCardToData = stuBaseInfoDao.selectStuBaseInfoByIdCard(stuBaseInfo.getIdCard());
        if (idCardToData != null)
            throw new ServiceException("身份证信息已经存在,或资料未进行修改,请到查询页面搜索");

        if (stuBaseInfo.getNativePlace() != null && stuBaseInfo.getNativePlace().length() > 24)
            throw new ServiceException("籍贯文字个数不能超过24个");

        stuBaseInfo.setStuState("全日制");
        String stuClass = stuBaseInfo.getStuClass();
        if (stuClass != null
                && (stuClass.toUpperCase().contains("VN")
                || stuClass.toUpperCase().contains("VIP")
                || stuClass.toUpperCase().contains("业余")))
            stuBaseInfo.setStuState("VIP");


        stuBaseInfo.setDelFlag("0");
        stuBaseInfo.setCreatedTime(new Date());
        //判断是否有用户登录,如果登录就取登录用户名,如果未登录,就取注册用户名作为创建者
        SysUser user = ShiroUtils.getUser();
        if (user != null) {
            stuBaseInfo.setCreatedUser(user.getUserName());
        } else {
            stuBaseInfo.setCreatedUser(stuBaseInfo.getStuName());
        }
        //保存学生数据
        int rowStu = insertStuBaseInfoSQL(stuBaseInfo);

        int rowUser = 0;
        //首次注册创建用户
        if (state == SAVE) {
            SysUser sysUserAccount = getSysUser(stuBaseInfo, state);
            rowUser = sysUserService.saveSysUser(sysUserAccount, new Integer[]{51});
        }

        return rowStu + rowUser;
    }

    /**
     * 通过传入学生必填信息，设置到用户对象中
     *
     * @param data 学生基础信息对象
     * @return 用户对象信息
     */
    private SysUser getSysUser(StuBaseInfo data, int state) {
        SysUser regUser = new SysUser();
        regUser.setStuId(data.getStuId());
        regUser.setAvatar(data.getAvatar());
        regUser.setEmail(data.getStuEmail());
        regUser.setPhoneNumber(data.getPhoneNumber());
        regUser.setSex(data.getGender());
        if (state == SAVE) {
            String password = new StringBuilder(data.getIdCard().substring(14, 18))
                    .append(data.getPhoneNumber().substring(7, 11)).toString();
            regUser.setPassword(password);
            regUser.setDeptId(STU_DEPT_CODE);
            regUser.setLoginName(data.getStuEmail());
            regUser.setUserName(data.getStuName());
            regUser.setStatus("0");
            regUser.setDelFlag("0");
            regUser.setCreatedUser(data.getStuName());
        }
        if (state == MODIFY) {
            regUser.setModifiedTime(new Date());
            regUser.setModifiedUser(data.getStuName());
        }
        return regUser;
    }

    @RequiresPermissions("sys:stuInfo:delete")
    @CacheEvict(value = "stuInfoCache", allEntries = true)
    @RequiredLog("删除学生信息")
    @Override
    public void removeStuInfoById(Long[] stuIds) {
        if (stuIds == null || stuIds.length == 0)
            throw new ServiceException("请选择后再删除");
        int row = stuBaseInfoDao.deleteStuInfoById(stuIds);
        if (row == 0)
            throw new ServiceException("删除失败,请重新尝试或联系管理员");
    }

    @RequiresPermissions("stu:single:update")
    @CacheEvict(value = "stuInfoCache", allEntries = true)
    @RequiredLog("修改学生信息")
    @Override
    public void modifyStuBaseInfo(StuBaseInfo stuBaseInfo) {
        stuBaseInfo.setModifiedTime(new Date());
        SysUser user = ShiroUtils.getUser();
        if (user != null) {
            stuBaseInfo.setModifiedUser(user.getUserName());
        } else {
            stuBaseInfo.setModifiedUser(stuBaseInfo.getStuName());
        }
        updateStuBaseInfoSQL(stuBaseInfo);
    }

    @Override
    public List<String> showStuClassNumList() {
        return selectStuClassNumListSQL();
    }


    @RequiresPermissions("sys:stuInfo:view")
    @Cacheable(value = "stuInfoCache"
            ,key="#stuBaseInfo.stuClass+'-'+#stuBaseInfo.stuName+'-'+#stuBaseInfo.phoneNumber+'-'+#stuBaseInfo.educationBackground+'-'+#stuBaseInfo.classRoom+'-'+#stuBaseInfo.stuState+'-'+#stuBaseInfo.delFlag+'-'+#stuBaseInfo.idCard+'-'+#stuBaseInfo.nativePlace+'-'+#page+'-'+#pageSize")
    @Transactional(readOnly = true)
    @RequiredLog("分页查询学生信息")
    @Override
    public JsonResult showStuInfoList(StuBaseInfo stuBaseInfo, int page, int limit) {

        if (stuBaseInfo == null)
            throw new ServiceException("数据出现异常");
        //参数校验
        if (page < 1)
            throw new IllegalArgumentException("页码值不正确");
        //统计总记录数并校验
        int rowCount = getStuInfoRowCountSQL(stuBaseInfo);
        //查询当前页记录
        int startIndex = (page - 1) * limit;
        //封装查询结果
        List<StuBaseInfo> records = selectStuInfoListSQL(stuBaseInfo, startIndex, limit);
        if (records != null && records.size() > 0) {
            for (StuBaseInfo data : records) {
                //如果没有头像路径则设置默认图片
                if ("".equals(data.getAvatar()) || data.getAvatar() == null) {
                    data.setAvatar("../dist/layuiadmin/img/defualt.png");
                }
            }
        }

        //传入
        return new JsonResult(page, limit, rowCount, records);
    }


    @Transactional(readOnly = true)
    @Override
    public List<StuBaseInfo> findByAfterFour(String afterFour) {
        if (afterFour == null || "".equals(afterFour))
            throw new ServiceException("请输入查询信息");
        List<StuBaseInfo> list = selectByIdCardAndPhoneNumberSQL(afterFour.toUpperCase());
        if (list.size() == 0)
            throw new ServiceException("无信息.请重新输入身份证后四位手机号后四位.如需帮助请联系管理员");
        if (list != null && list.size() > 0) {
            for (StuBaseInfo data : list) {
                //如果没有头像路径则设置默认图片
                if ("".equals(data.getAvatar()) || data.getAvatar() == null) {
                    data.setAvatar("../dist/layuiadmin/img/defualt.png");
                }
            }
        }
        return list;
    }


    @Override
    public StuBaseInfo findStuInfoById(Long stuId) {
        return selectStuInfoByIdSQL(stuId);
    }



    /*
     * 以下是捕获数据库异常的私有方法
     * 包含姓名、手机、身份证正则验证
     */


    private int insertStuBaseInfoSQL(StuBaseInfo stuBaseInfo) {
        int row;
        try {
            row = stuBaseInfoDao.insert(stuBaseInfo);
            if (row == 0)
                throw new ServiceException("保存失败,请重新尝试或联系管理员");
            return row;
        } catch (Exception e) {
            System.out.println("insertStuBaseInfoSQL()存在问题 : " + e.getMessage());
        }
        return 0;
    }

    private void updateStuBaseInfoSQL(StuBaseInfo data) {
        try {
            int row = stuBaseInfoDao.updateById(data);
            if (row == 0)
                throw new ServiceException("更新失败,请重新尝试或联系管理员");
        } catch (Exception e) {
            System.out.println("updateStuBaseInfoSQL()存在问题 : " + e.getMessage());
        }
    }

    private List<String> selectStuClassNumListSQL() {
        List<String> list = null;
        try {
            list = stuBaseInfoDao.selectStuClassNumList();
            if (list == null || list.size() == 0)
                throw new ServiceException("查询没有班级数据");
        } catch (Exception e) {
            System.out.println("selectStuClassNumListSQL()存在问题 : " + e.getMessage());
        }
        return list;
    }

    private StuBaseInfo selectStuInfoByIdSQL(Long stuId) {
        StuBaseInfo data = null;
        try {
            data = stuBaseInfoDao.selectById(stuId);
            if (data == null)
                throw new ServiceException("查询没有学生数据");
        } catch (Exception e) {
            System.out.println("selectStuInfoByIdSQL()存在问题 : " + e.getMessage());
        }
        return data;
    }

    private List<StuBaseInfo> selectByIdCardAndPhoneNumberSQL(String afterFour) {
        List<StuBaseInfo> list = null;
        try {
            list = stuBaseInfoDao.selectByIdCardAndPhoneNumber(afterFour.toUpperCase());
            if (list == null || list.size() == 0)
                throw new ServiceException("查询没有数据");
        } catch (Exception e) {
            System.out.println("selectByIdCardAndPhoneNumberSQL()存在问题 : " + e.getMessage());
        }
        return list;
    }

    private List<StuBaseInfo> selectStuInfoListSQL(StuBaseInfo stuBaseInfo, int startIndex, int pageSize) {
        List<StuBaseInfo> list;
        try {
            list = stuBaseInfoDao.selectStuBaseInfoList(stuBaseInfo, startIndex, pageSize);
            if (list == null || list.size() == 0)
                throw new ServiceException("查询没有数据");
            return list;
        } catch (Exception e) {
            System.out.println("selectStuInfoListSQL()存在问题 : " + e.getMessage());
        }
        return null;
    }


    private int getStuInfoRowCountSQL(StuBaseInfo stuBaseInfo) {
        int row;
        try {
            row = stuBaseInfoDao.getStuInfoRowCount(stuBaseInfo);
            if (row == 0)
                throw new ServiceException("查询没有数据");
            return row;
        } catch (Exception e) {
            System.out.println("getStuInfoRowCountSQL()存在问题 : " + e.getMessage());
        }
        return 0;
    }



    /**
     * 验证姓名
     * @param name 姓名
     * @return 校验通过返回true，否则返回false
     */
    private boolean isName(String name) {
        return Pattern.matches(REGEX_NAME, name);
    }

    /**
     * 验证手机号码
     *
     * @param phoneNumber 手机号码
     * @return 校验通过返回true，否则返回false
     */
    public boolean isPhoneNumber(String phoneNumber) {
        return Pattern.matches(REGEX_PHONE_NUMBER, phoneNumber);
    }


    /**
     * 校验身份证
     *
     * @param idCard 身份证号码
     * @return 校验通过返回true，否则返回false
     */
    public boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验特殊字符
     * @param ex 特殊符号
     * @return 校验通过返回true，否则返回false
     */
    public boolean isEx(String ex) {
        return Pattern.matches(REGEX_EX, ex);
    }

}
