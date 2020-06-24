package com.tedu.sois.sys.service;

import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.pojo.sys.SysUser;

import java.util.Map;

/**
 * 用户个人功能和管理员操作用户功能接口
 * @author LYS
 */
public interface SysUserService {

    /**
     * 修改密码
     * @param password 原密码
     * @param newPassword 新密码
     * @param cfgPassword 二次输入密码
     * @return 修改结果
     */
    int modifyUserPassword(String password,
                           String newPassword,
                           String cfgPassword);

    /**
     * 用户到个人界面修改个人信息
     * @param entity 用户信息
     * @return 受影响行数
     */
    void modifySingleUserInfoBySingle(SysUser entity);


    /**
     * 保存用户自身信息以及用户对应的角色关系数据
     *
     * @param entity
     * @param roleIds
     * @return
     */
    int saveSysUser(SysUser entity, Integer[] roleIds);

    /**
     * 更新用户自身信息以及用户对应的角色关系数据
     *
     * @param entity
     * @param roleIds
     * @return
     */
    void modifySysUserInfo(SysUser entity, Integer[] roleIds);

    /**
     * 禁用启用业务方法
     *
     * @param userId
     * @param status
     * @param modifiedUser
     * @return
     */
    void modifyStatusById(Long userId, Integer status, String modifiedUser);

    /**
     * 根据用户名分页查询
     *
     * @param username 用户名
     * @param page 页码
     * @param limit 查询数量
     * @return
     */
    JsonResult findUserDeptByUserName(String username, Integer page, Integer limit);

	/**
	 * 通过登录账号获取账号信息
	 * @param loginName 登录名
	 * @return 返回用户信息
	 */
    Map<String, Object> getDataByLoginName(String loginName);

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return
     */
    SysUser findUserInfoById(Long userId);
    /**
     * 基于用户id查询用户以及用户对应部门,角色相关信息
     *
     * @param userId
     * @return
     */
    Map<String, Object> findUserDeptVoById(Long userId);

    /**
     * 上传、修改头像图片
     * @param avatar 头像地址
     */
    void changeAvatar(SysUser user, String avatar);

    /**
     * 根据用户ID删除信息
     * @param userId
     */
    void removeSysUserInfoById(Long userId);
}
