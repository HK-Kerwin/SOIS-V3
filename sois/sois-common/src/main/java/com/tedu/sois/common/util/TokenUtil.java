package com.tedu.sois.common.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TokenUtil {

    private static Logger LOG = LoggerFactory.getLogger(TokenUtil.class);

    /** 有效时间2小时*/
    public static final long EFFECTIVE_TIME = 2 * 60 * 60 * 1000;

    /**
     * 创建TOKEN
     *
     * @param id, issuer签发者(目前不写在参数中), subject, ttlMillis
     * @return java.lang.String
     * @methodName createJWT
     * @author fusheng
     * @date 2019/1/10
     * @update LYS
     */
    public static String createJWT(String id, String subject, long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        LOG.info("JWT CREATE_DATE:{}", new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(now));

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("StudentOnlineInformationSystem");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        /**
         * Jwts.builder() 这里其实就是new一个JwtBuilder，设置jwt的body
         *
         * jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
         * iat: jwt的签发时间
         * sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
         * 值是一个大小写敏感的字符串，包含一个StringOrURI值
         * signWith:设置签名使用的签名算法和签名使用的秘钥
         */
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);//设置过期时间
        }
        LOG.info("LOGIN TOKEN:{}", builder.compact());
        return builder.compact();
    }

    /**
     * 解密TOKEN
     *
     * @param jwt
     * @return io.jsonwebtoken.Claims
     * @methodName parseJWT
     * @author fusheng
     * @date 2019/1/10
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("StudentOnlineInformationSystem"))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}