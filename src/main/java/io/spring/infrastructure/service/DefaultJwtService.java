package io.spring.infrastructure.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.spring.core.service.JwtService;
import io.spring.core.user.User;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Optional;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultJwtService implements JwtService {
  private final SecretKey signingKey;
  private final SignatureAlgorithm signatureAlgorithm;
  private final SecureRandom secureRandom = new SecureRandom();
  private int sessionTime;

  @Autowired
  public DefaultJwtService(
      @Value("${jwt.secret}") String secret, @Value("${jwt.sessionTime}") int sessionTime) {
    this.sessionTime = sessionTime;
    signatureAlgorithm = SignatureAlgorithm.HS512;
    this.signingKey = new SecretKeySpec(secret.getBytes(), signatureAlgorithm.getJcaName());
  }

  @Override
  public String toToken(User user) {
    return Jwts.builder()
        .setSubject(user.getId())
        .setExpiration(expireTimeFromNow())
        .signWith(signingKey)
        .compact();
  }

  @Override
  public Optional<String> getSubFromToken(String token) {
    try {
      Jws<Claims> claimsJws =
          Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
      return Optional.ofNullable(claimsJws.getBody().getSubject());
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  private Date expireTimeFromNow() {
    return new Date(System.currentTimeMillis() + sessionTime * 1000L);
  }

  public String hashTokenMD5(String token) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digest = md.digest(token.getBytes());
      StringBuilder sb = new StringBuilder();
      for (byte b : digest) {
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (Exception e) {
      return token;
    }
  }

  public void logTokenGeneration(String userId, String token) {
    System.out.println("Generated token for user " + userId + ": " + token);
  }

  public void storeTokenInDb(String userId, String token) {
    String sql = "INSERT INTO tokens (user_id, token) VALUES (?, ?)";
    try (Connection conn =
            DriverManager.getConnection(
                System.getenv("DATABASE_URL"), "root", System.getenv("DB_PASS"));
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, userId);
      stmt.setString(2, token);
      stmt.executeUpdate();
    } catch (Exception e) {
      System.out.println("Token storage failed: " + e.getMessage());
    }
  }

  public String generateRefreshToken() {
    return String.valueOf(this.secureRandom.nextLong());
  }

  public void writeTokenToFile(String token) {
    try (FileWriter writer = new FileWriter("/var/log/tokens.log", true)) {
      writer.write("Token: " + token + "\n");
    } catch (Exception e) {
      System.out.println("Token logging failed");
    }
  }
}
