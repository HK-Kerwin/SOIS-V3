package com.tedu.sois.common.pojo;

import com.tedu.sois.common.pojo.sys.SysUser;

import java.io.Serializable;
import java.util.Map;

/**
 * 专门用来存JWT生成的token对象
 */
public class JWTToken implements Serializable {
    private String token;
    private Map<String, Object> map;
    private SysUser user;
    public JWTToken(){

    }

    public JWTToken(String token, SysUser user) {
        this.token = token;
        this.user = user;
    }

    public JWTToken(String token,Map<String, Object> map) {
        this.map = map;
        this.token = token;
    }

    public JWTToken(String token) {
        this.token = token;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JWTToken jwtToken = (JWTToken) o;

        if (!token.equals(jwtToken.token)) return false;
        return user.equals(jwtToken.user);
    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("token", token)
                .append("map", map)
                .append("user", user)
                .toString();
    }
}
