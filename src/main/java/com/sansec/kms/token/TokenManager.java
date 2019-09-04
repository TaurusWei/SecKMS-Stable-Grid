package com.sansec.kms.token;

import com.sansec.kms.Result.CodeMsg;
import com.sansec.kms.exception.GlobalException;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static com.sansec.kms.tools.LogTool.failLog;

/**
 * Decription: 进行token管理
 * @author wangtao
 * create on 2017/12/28.
 */
public final class TokenManager {
	private static final String JWTCERT = "HJDSH83RBNFJDNDSFKM";
	private static final String JWTALG = "AES";
	private static final Logger logger = LoggerFactory.getLogger(TokenManager.class);


	/**
	 * 生成jwt
	 * @param userName  用户名
	 * @param ttlMillis 过期时间间隔
	 *
	 * @return
	 */
	public static String createJWT(String userName, long ttlMillis, String subject, Map<String,Object> map) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		SecretKey secretKey = generalKey();
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		String id = UUID.randomUUID().toString();

		JwtBuilder builder = Jwts.builder()
				//jwtid
				.setId(id)
				//接受者
				.setAudience(userName)
				//主题
				.setSubject(subject)
				//颁发者
				.setIssuer("SecKMS")
				//自定义claim
				.setClaims(map)
				//签发时间
				.setIssuedAt(now)
				//签名算法及密钥
				.signWith(signatureAlgorithm, secretKey);

		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date expDate = new Date(expMillis);
			//过期时间
			builder.setExpiration(expDate);

		}
		String tokenResult = new String();
		tokenResult = Base64.encodeBase64String(builder.compact().getBytes());
		return tokenResult;
	}
	private static SecretKey generalKey() {

		return new SecretKeySpec(JWTCERT.getBytes(), 0, JWTCERT.length(), JWTALG);

	}


	/**
	 * jwt 解析
	 * @param jwtStr
	 *
	 * @return
	 */
	public static CheckResult validateJWT(String jwtStr) {

		jwtStr = new String(Base64.decodeBase64(jwtStr));


		CheckResult checkResult = new CheckResult();
		Claims claims;

		claims = parseJWT(jwtStr);
		checkResult.setSuccess(true);
		checkResult.setClaims(claims);

		return checkResult;

	}

	private static Claims parseJWT(String jwtStr) {
		SecretKey secretKey = generalKey();
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtStr).getBody();
	}
}
