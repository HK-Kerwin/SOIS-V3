package com.tedu.sois.sys.service.realm;

import com.tedu.sois.sys.dao.SysMenuDao;
import com.tedu.sois.sys.dao.SysRoleMenuDao;
import com.tedu.sois.sys.dao.SysUserDao;
import com.tedu.sois.sys.dao.SysUserRoleDao;
import com.tedu.sois.common.pojo.sys.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShiroUserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

//	@Override
//	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//		// TODO Auto-generated method stub
//		//构建凭证匹配对象
//		HashedCredentialsMatcher cMatcher=
//				new HashedCredentialsMatcher();
//		//设置加密算法
//		cMatcher.setHashAlgorithmName("MD5");
//		//设置加密次数
//		cMatcher.setHashIterations(1);
//		super.setCredentialsMatcher(cMatcher);
//	}

    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        //构建凭证匹配对象
        HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
        //设置加密算法
        cMatcher.setHashAlgorithmName("MD5");
        //设置加密次数
        cMatcher.setHashIterations(5);
        return cMatcher;
    }

    /**
     * 通过此方法获取用户认证信息,并进行封装,然后返回给
     * SecurityManager对象
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户登录时提交用户信息
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        String loginName = uToken.getUsername();
        //2.基于用户名查找用户
        SysUser user = sysUserDao.findUserByLoginName(loginName);
        //3.判定用户是否存在
        if (user == null)
            throw new UnknownAccountException("用户不存在");
        //4.判定用户是否被禁用
        if ("1".equals(user.getStatus()))
            throw new LockedAccountException("账号违规禁用");
        //5.封装认证信息
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        SimpleAuthenticationInfo info =  new SimpleAuthenticationInfo(
                        user,//principal 用户身份
                        user.getPassword(),//hashedCredentials
                        credentialsSalt, //credentialsSalt
                        getName());//realmName
        return info;//返回给SecurityManager
    }

    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysMenuDao sysMenuDao;

    /**
     * 获取登录用户的权限信息并进行封装.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("==doGetAuthorizationInfo==");
        //1.获取登录用户信息
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        //2.基于登录用户id获取角色id并进行校验.
        List<Integer> roleIds = sysUserRoleDao.selectRoleIdsByUserId(user.getUserId());
        if (roleIds == null || roleIds.size() == 0)
            throw new AuthorizationException();
        //3.基于角色id获取对应菜单id并进行校验
        Integer[] array = {};
        List<Integer> menuIds = sysRoleMenuDao.selectMenuIdsByRoleIds(roleIds);
        if (menuIds == null || menuIds.size() == 0)
            throw new AuthorizationException();
        //4.基于菜单id获取对应的菜单权限标识(permission)
        List<String> permissions = sysMenuDao.findPermissions(menuIds.toArray(array));
        if (permissions == null || permissions.size() == 0)
            throw new AuthorizationException();
        //5.封装用户权限信息,并将此信息返回给SecurityManager
        Set<String> stringPermissions = new HashSet<>();
        for (String per : permissions) {
            if (!StringUtils.isEmpty(per)) {
                stringPermissions.add(per);
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(stringPermissions);
        return info;//返回给securityManager
    }

}









