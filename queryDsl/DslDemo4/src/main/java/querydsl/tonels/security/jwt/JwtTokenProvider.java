package querydsl.tonels.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {
    private static final String CLAIM_KEY_USERNAME = "sub";

    /**
     * 1小时(毫秒)
     */
    private static final long EXPIRATION_TIME = 3600000;
    /**
     * JWT密码
     */
    private static final String SECRET = "secret";
    
    /**
     * 签发JWT
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        return Jwts.builder().setClaims(claims).setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    /**
     * 验证JWT
     */
    public Boolean validateToken(String token, String usernameDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(usernameDetails) && !isTokenExpired(token));
    }

    /**
     * 获取token是否过期
     */
    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 根据token获取username
     */
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 获取token的过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * 解析JWT
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

}
