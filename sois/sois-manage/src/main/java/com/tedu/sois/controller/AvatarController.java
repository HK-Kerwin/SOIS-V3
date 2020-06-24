package com.tedu.sois.controller;

import com.tedu.sois.common.config.StatusCodeConfig;
import com.tedu.sois.controller.ex.*;
import com.tedu.sois.common.pojo.stu.StuBaseInfo;
import com.tedu.sois.common.util.ShiroUtils;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.stu.service.StuBaseInfoService;
import com.tedu.sois.common.pojo.sys.SysUser;
import com.tedu.sois.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("user/")
public class AvatarController implements StatusCodeConfig {


    @Value("${sois.profile}")
    private String uploadFolder;


    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private StuBaseInfoService stuBaseInfoService;

    /**
     * 允许上传的文件大小的上限值，以字节为单位
     */
    private static final long AVATAR_MAX_SIZE = 3 * 1024 * 1024;
    /**
     * 允许上传的文件类型的集合
     */
    private static final List<String> AVATAR_TYPES = new ArrayList<>();

    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/gif");
        AVATAR_TYPES.add("image/bmp");
    }

    /**
     * 格式化日期的对象
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 头像上传功能
     * @param avatarImg 头像文件信息
     * @param stuId 学员ID
     * @param userId 用户ID
     * @param session 备用
     * @return
     */
    @PostMapping("avatarChange")
    public JsonResult changeAvatar(@RequestParam("avatarImg") MultipartFile avatarImg,Long stuId, Long userId,HttpSession session) {
        // 日志
        System.err.println("UserController.changeAvatar()");

        if (avatarImg == null) {
            // 上传的文件为空，则抛出异常
            throw new FileEmptyException("上传失败！请选择您要上传的文件！");
        }
        // 判断文件是否为空
        boolean isEmpty = avatarImg.isEmpty();
        if (isEmpty) {
            // 上传的文件为空，则抛出异常
            throw new FileEmptyException("上传失败！请选择您要上传的文件！");
        }

        // 获取文件大小
        long size = avatarImg.getSize();
        System.err.println("size=" + size);
        if (size > AVATAR_MAX_SIZE) {
            throw new FileSizeException("上传失败！不允许上传超过" +(AVATAR_MAX_SIZE / (1024*1024)) + "MB的头像！");
        }

        // 获取文件的MIME类型
        String contentType = avatarImg.getContentType();
        System.err.println("contentType=" + contentType);
        // 判断上传的文件类型是否符合：image/jpeg，image/png，image/gif，image/bmp
        if (!AVATAR_TYPES.contains(contentType)) {
            throw new FileTypeException("上传失败！仅允许上传以下类型的文件：" + AVATAR_TYPES);
        }


        System.err.println("stuId = " + stuId);
        System.err.println("userId = " + userId);
        // 1. 将客户端上传的文件保存下来
        // 确定将客户端上传的文件保存到的文件夹的名称
        String dirName = "upload/avatar/stu";
        String parent;
        SysUser shiroUser = ShiroUtils.getUser();
        StuBaseInfo stuInfo = null;
        SysUser user = null;
        boolean checkStuId = stuId != null;
        boolean checkUserId = userId != null;
        if(checkStuId){
            parent = uploadFolder + "avatar/stu";
            stuInfo = stuBaseInfoService.findStuInfoById(stuId);
        }else if(shiroUser != null && shiroUser.getDeptId() == STU_DEPT_CODE){
            parent = uploadFolder + "avatar/stu";
            user = sysUserService.findUserInfoById(userId);
        } else if(checkUserId){
            parent = uploadFolder + "avatar/user";
            dirName = "upload/avatar/user";
            user = sysUserService.findUserInfoById(userId);
        }else{
            parent = uploadFolder + "avatar";
            dirName = "upload/avatar";
        }

        // 确定将客户端上传的文件保存到的文件夹的路径
        //String parent = session.getServletContext().getRealPath(dirName);
        System.err.println("将文件保存到=" + parent);
        // 确保保存上传文件的文件夹是存在的
        File parentFile = new File(parent);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String child = "";
        if (checkStuId){
            StuBaseInfo data = stuBaseInfoService.findStuInfoById(stuId);
            String avatar = data.getAvatar();
            if (avatar != null && !"".equals(avatar)) {
                child = avatar.split("/")[avatar.split("/").length-1];
            }
        }

        if (checkUserId){
            SysUser data = sysUserService.findUserInfoById(userId);
            String avatar = data.getAvatar();
            if (avatar != null && !"".equals(avatar)) {
                child = avatar.split("/")[avatar.split("/").length-1];
            }
        }

        // 确定将客户端上传的文件保存时使用什么文件名
        //查询数据如果存在头像地址,则会使用数据库里的地址
        if(child == null || "".equals(child)){
            Date now = new Date();
            String filename = sdf.format(now) + System.nanoTime();
            // 获取客户端上传的文件的原始名称
            String originalFilename = avatarImg.getOriginalFilename();
            System.err.println("originalFilename=" + originalFilename);
            // 确定将客户端上传的文件保存时使用什么扩展名
            String suffix = "";
            int beginIndex = originalFilename.lastIndexOf(".");
            if (beginIndex > 0) {
                suffix = originalFilename.substring(beginIndex);
            }
            // 确定将客户端上传的文件保存时使用什么文件全名
            String userName;
            if(checkStuId && stuInfo != null){
                userName = stuInfo.getStuName();
            } else {
                userName = user.getUserName();
            }
            child = userName + filename + suffix;
        }

        System.err.println("保存的文件名=" + child);

        // 确定将客户端上传的文件保存到哪里
        File dest = new File(parent, child);
        System.err.println("dest = " + dest);
        System.err.println("dest.exists() = " + dest.exists());
        try {
            // 执行保存客户端上传的文件,同名文件会自动删除
            avatarImg.transferTo(dest);
        } catch (IllegalStateException e) {
            throw new FileStateException("上传头像失败！请检查头像文件是否存在，并再次尝试！");
        } catch (IOException e) {
            throw new FileIOException("上传头像失败！读写头像文件时出现错误，请稍后再次尝试！");
        }

        // 将上传后保存的文件的路径记录在数据库中
        String avatarImgPath= "/" + dirName + "/" + child;
        System.err.println("avatar=" + avatarImgPath);

        // stu不为空表示是在学生信息编辑界面,则通过ID查询之前的头像地址进行删除
        if(checkStuId){
            System.err.println("修改学生信息头像路径");
            StuBaseInfo StuBaseInfo = new StuBaseInfo();
            StuBaseInfo.setStuId(stuId);
            StuBaseInfo.setAvatar(avatarImgPath);
            //通过id进行查询,分两种情况.
            stuBaseInfoService.modifyStuBaseInfo(StuBaseInfo);
        }

        //用户上传头像,通过ID查询之前的头像地址进行删除
        if(shiroUser != null && checkUserId && user != null){
            System.err.println("用户修改头像");
            sysUserService.changeAvatar(user,avatarImgPath);
        }

        Map<String,String> map = new HashMap<>();
        map.put("src",avatarImgPath);
        // 返回
        return new JsonResult("上传成功,可以直接退出编辑页面", map);
    }
}
