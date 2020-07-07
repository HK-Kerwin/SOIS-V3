package com.tedu.sois.sys.service.impl;

import com.tedu.sois.annotation.RequiredLog;
import com.tedu.sois.common.exception.ServiceException;
import com.tedu.sois.common.util.ShiroUtils;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.sys.dao.SysUserDao;
import com.tedu.sois.sys.dao.SysUserRoleDao;
import com.tedu.sois.common.pojo.sys.SysUser;
import com.tedu.sois.sys.service.SysUserService;
import com.tedu.sois.sys.vo.SysUserDeptVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

//1)@Service描述类表示要交给Spring管理
//2)Spring会创建此类对象,并将对象存储到map容器
//3)Spring Bean容器中的key默认为类名首字母小写.
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, //隔离级别
        rollbackFor = Throwable.class,//什么异常回滚
        timeout = 30,//单位:秒
        propagation = Propagation.REQUIRED)//传播特性
//readOnly = false,默认
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;


    @CacheEvict(value = "userCache", allEntries = true)
    @Override
    public int saveSysUser(SysUser entity, Integer[] roleIds) {
        //1.参数检验
        if (entity == null)
            throw new IllegalArgumentException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getUserName()))
            throw new IllegalArgumentException("用户名不能为空");
        if (StringUtils.isEmpty(entity.getLoginName()))
            throw new IllegalArgumentException("登录账号不能为空");
        SysUser userByLoginName = sysUserDao.findUserByLoginName(entity.getLoginName());
        if (userByLoginName != null)
            throw new ServiceException("登录账号已经存在");
        if (StringUtils.isEmpty(entity.getPassword()))
            throw new IllegalArgumentException("密码不能为空");
        if (roleIds == null || roleIds.length == 0)
            throw new ServiceException("必须为用户分配角色");
        //2.保存用户自身信息
        //2.1对密码进行md5盐值加密
        //获取一个盐值,这个值使用随机的字符串
        String salt = UUID.randomUUID().toString();

        /*
            借助shiro框架中的API对应密码进行盐值加密
            String algorithmName 算法名(MD5算法是一种消息摘要算法)
            Object source 未加密的密码
            @Nullable Object 盐
            int hashIterations表示加密次数
         */
        SimpleHash sh = new SimpleHash("MD5", entity.getPassword(), salt, 5);
        //将加密结果转成16进制
        String pwd = sh.toHex();
        //2.2将盐值和密码存储SysUser对象
        entity.setSalt(salt);
        entity.setPassword(pwd);
        SysUser user = ShiroUtils.getUser();
        if(user != null){
            entity.setCreatedUser(user.getUserName());
        }
        entity.setCreatedTime(new Date());
        //2.3将SysUser对象持久化到数据库
        int userRows = sysUserDao.insertSysUser(entity);
        if (userRows == 0)
            throw new ServiceException("保存用户信息失败");
        //3.保存用户和角色关系数据
        int userRoleRows = sysUserRoleDao.insertSysUserRole(entity.getUserId(), roleIds);
        if (userRows == 0)
            throw new ServiceException("保存用户和角色关系数据失败");
        return userRoleRows;
    }

    //@RequiresPermissions("sys:user:updatePassword")
    //@CacheEvict(value = "userCache")
    @Override
    public int modifyUserPassword(String password, String newPassword, String cfgPassword) {
        //1.判定新密码与密码确认是否相同
        if (StringUtils.isEmpty(newPassword))
            throw new IllegalArgumentException("新密码不能为空");
        if (StringUtils.isEmpty(cfgPassword))
            throw new IllegalArgumentException("确认密码不能为空");
        if (newPassword.length() < 6 || cfgPassword.length() > 16)
            throw new IllegalArgumentException("密码长度6-16");
        if (!newPassword.equals(cfgPassword))
            throw new IllegalArgumentException("两次输入的密码不相等");
        //2.判定原密码是否正确
        if (StringUtils.isEmpty(password))
            throw new IllegalArgumentException("原密码不能为空");

        //获取登陆用户
        SysUser user = ShiroUtils.getUser();

        SimpleHash sh = new SimpleHash("MD5",
                password, user.getSalt(), 5);
        if (!user.getPassword().equals(sh.toHex()))
            throw new IllegalArgumentException("原密码不正确");
        //3.对新密码进行加密
        String salt = UUID.randomUUID().toString();
        sh = new SimpleHash("MD5", newPassword, salt, 5);

        //4.将新密码加密以后的结果更新到数据库
        int rows = sysUserDao.updatePassword(sh.toHex(), salt, user.getUserId(), user.getUserName());
        if (rows == 0)
            throw new ServiceException("修改失败");
        return rows;
    }

    @RequiresPermissions("sys:user:delete")
    @CacheEvict(value = "userCache", allEntries = true)
    @RequiredLog("删除用户")
    @Override
    public void removeSysUserInfoById(Long userId) {
        int row = sysUserDao.deleteSysUserInfoById(userId);
        if (row == 0)
            throw new ServiceException("删除失败");
    }

    //@CacheEvict表示清空缓存
    //在本应用中清空cache中key为指定id的对象
    //#entity.userId表示获取参数entity对象的id值
    @RequiresPermissions("sys:user:update")
    @CacheEvict(value = "userCache", allEntries = true)
    @Override
    public void modifySysUserInfo(SysUser entity, Integer[] roleIds) {
        //1.参数检验
        if (entity == null)
            throw new IllegalArgumentException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getUserName()))
            throw new IllegalArgumentException("用户名不能为空");
        if (roleIds == null || roleIds.length == 0)
            throw new ServiceException("必须为用户分配角色");
        //2.保存用户自身信息
        updateSysUserInfoSQL(entity);
        //3.更改用户和角色关系数据
        sysUserRoleDao.deleteSysUserRoleByUserId(entity.getUserId());
        sysUserRoleDao.insertSysUserRole(entity.getUserId(), roleIds);
    }

    /**
     * Shiro框架通过@RequiresPermissions注解定义切入点
     * 在这里表示访问此方法需要进行授权,需要具备这个注解中的权限标识
     * 1、需要系统基于登录用户获取用户权限{"sys:user:update","...","..."}
     * 2、当用户权限中包含@RequiresPermissions注解中定义权限标识,就标识用户拥有访问这个方法的权限
     * 3、拥有权限时则可以由Shiro框架进行授权访问
     */
    @Async
    @RequiresPermissions("sys:user:status")
    @CacheEvict(value = "userCache", allEntries = true)
    @RequiredLog("禁用启用用户")
    @Override
    public void modifyStatusById(Long userId, Integer status, String modifiedUser) {
        //1.验证参数有效性
        if (userId == null || userId < 1)
            throw new IllegalArgumentException("id值无效");
        if (status == null || status != 1 && status != 0)
            throw new IllegalArgumentException("状态不正确");
        //...
        //2.更新用户状态,并对其结果进行判定
        updateStatusByIdSQL(userId, status, modifiedUser);
    }

    //@Cacheable描述方法时，用于告诉spring框架，此方法的返回结果要进行cache
    //userCache表示一个cache对象的名称
    //key默认为方法的参数的组合
    @Cacheable(value = "userCache")
    @Transactional(readOnly = true)
    @Override
    public JsonResult findUserDeptByUserName(String userName, Integer page, Integer limit) {
        //参数校验
        if (page < 1)
            throw new IllegalArgumentException("页码值不正确");
        //2.统计总记录数并校验
        int rowCount = sysUserDao.getRowCount(userName);
        if (rowCount == 0)
            throw new ServiceException("记录不存在");
        //3.查询当前页记录
        int startIndex = (page - 1) * limit;
        List<SysUserDeptVo> records = sysUserDao.findPageSysUserDeptVo(userName, startIndex, limit);
        if (records != null && records.size() > 0) {
            for (SysUserDeptVo data : records) {
                //如果没有头像路径则设置默认图片
                if ("".equals(data.getAvatar()) || data.getAvatar() == null) {
                    data.setAvatar("../dist/layuiadmin/img/defualt.png");
                }
            }
        }
        //4.封装查询结果
        return new JsonResult(page, limit, rowCount, records);
    }

    @Transactional(readOnly = true)
    @Override
    public SysUser findUserInfoById(Long userId) {
        //1.参数有效性校验
        if (userId == null || userId < 1)
            throw new IllegalArgumentException("id值无效");
        //2.基于id查询用户以及对应的部门信息
        SysUser user = sysUserDao.selectById(userId);
        if (user == null)
            throw new ServiceException("用户不存在");
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> findUserDeptVoById(Long userId) {
        //1.参数有效性校验
        if (userId == null || userId < 1)
            throw new IllegalArgumentException("id值无效");
        //2.基于id查询用户以及对应的部门信息
        SysUserDeptVo user = sysUserDao.selectUserDeptVoById(userId);
        if (user == null)
            throw new ServiceException("用户不存在");
        //3.查询用户对应的角色id
        List<Integer> roleIds = sysUserRoleDao.selectRoleIdsByUserId(userId);
        //4.封装两次查询结果
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roleIds", roleIds);

        return map;
    }

    @Override
    public Map<String, Object> getDataByLoginName(String loginName) {
        SysUser user = sysUserDao.findUserByLoginName(loginName);
        Map<String, Object> map = new HashMap<>();
        List<Integer> list = sysUserRoleDao.selectRoleIdsByUserId(user.getUserId());
        map.put("userData", user);
        map.put("roleData", list);
        return map;
    }

    @Override
    public void changeAvatar(SysUser user, String avatarPath) {
        SysUser data = new SysUser();
        String userName = user.getUserName();
        Date date = new Date();

        data.setUserId(user.getUserId());
        data.setAvatar(avatarPath);
        data.setModifiedUser(userName);
        data.setModifiedTime(date);

        updateSysUserInfoSQL(data);

        //更新Shiro中用户信息
        SysUser userData = ShiroUtils.getUser();
        userData.setAvatar(avatarPath);
        userData.setModifiedUser(userName);
        userData.setModifiedTime(date);
    }

    @Override
    public void modifySingleUserInfoBySingle(SysUser entity) {
        int row = updateSysUserInfoSQL(entity);
        if (row == 0)
            throw new ServiceException("提交失败,账号信息更新失败");
    }

    private int updateSysUserInfoSQL(SysUser entity) {
        int row;
        try {
            row = sysUserDao.updateSysUserInfo(entity);
            if (row == 0)
                throw new ServiceException("更新失败,请重新尝试或联系管理员");
            return row;
        } catch (Exception e) {
            System.err.println("updateStuBaseInfoSQL()存在问题 : " + e.getMessage());
        }
        return 0;
    }

    private int updateStatusByIdSQL(Long userId, Integer status, String modifiedUser) {
        int rows;
        try {
            rows = sysUserDao.updateStatusById(userId, status, modifiedUser);
            if (rows == 0)
                throw new ServiceException("记录可能已经不存在");
            return rows;
        } catch (Exception e) {
            System.err.println("updateStatusByIdSQL()存在问题 : " + e.getMessage());
        }
        return 0;
    }

}
