package com.tedu.sois.common.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.tedu.sois.common.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 拦截器对象,检查请求是否携带token
 * @author LYS
 */
public class TimeAccessInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(TimeAccessInterceptor.class);


    /**
     * token 校验
     *
     * @param req, resp, o
     * @return boolean
     * @methodName preHandle
     * @author fusheng
     * @date 2019/1/3 0003
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        LOG.info("come in preHandle");

        Map<String, String[]> mapIn = req.getParameterMap();
        JSON jsonObject = JSONUtil.parseObj(mapIn);
        StringBuffer stringBuffer = req.getRequestURL();

        //跨域问题解决
        resp.setHeader("Access-Control-Allow-Origin", resp.getHeader("Origin"));
        resp.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, X-Requested-With, Content-Type, Accept, Access-Token, If-Modified-Since");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Credentials","true");
        resp.setHeader("P3P", "CP=CAO IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT");

        LOG.info("Request Path:" + stringBuffer + ",入参:" + JSONUtil.toJsonStr(jsonObject));

        //校验APP的登陆状态，如果token 没有过期
        String oldToken = req.getHeader("token");
        LOG.info("token:" + oldToken);
        /*刷新token，有效期延长2小时*/
        if (StringUtils.isNotBlank(oldToken)) {
            Claims claims = null;
            try {
                claims = TokenUtil.parseJWT(oldToken);
            } catch (Exception e) {
                e.printStackTrace();
                String str = "{\"code\":801,\"msg\":\"登陆失效,请重新登录!!!\"}";
                dealErrorReturn(req, resp, str);
                return false;
            }
            if (claims.getExpiration().getTime() > DateUtil.date().getTime()) {
                String userId = claims.getId();
                try {
                    String newToken = TokenUtil.createJWT(claims.getId(), claims.getSubject(), TokenUtil.EFFECTIVE_TIME);
                    LOG.info("new TOKEN:{}", newToken);
                    req.setAttribute("userId", userId);
                    resp.setHeader("token", newToken);
                    LOG.info("flush token success ,{}", oldToken);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    String str = "{\"code\":801,\"msg\":\"登陆失效,请重新登录.\"}";
                    dealErrorReturn(req, resp, str);
                    return false;
                }
            }
        }
        String str = "{\"code\":801,\"msg\":\"验证失效!请重新登录!\"}";
        dealErrorReturn(req, resp, str);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object o, Exception e) throws Exception {
    }

    /**
     * 返回错误信息给WEB
     *
     * @param req, resp, obj
     * @return void
     * @methodName dealErrorReturn
     * @author fusheng
     * @date 2019/1/3 0003
     */
    public void dealErrorReturn(HttpServletRequest req, HttpServletResponse resp, Object obj) {
        String json = (String) obj;
        PrintWriter pw = null;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        try {
            pw = resp.getWriter();
            pw.print(json);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
