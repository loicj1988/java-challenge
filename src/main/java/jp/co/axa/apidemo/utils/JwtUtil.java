package jp.co.axa.apidemo.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;

@Service
@Log
public class JwtUtil {

  private String SECRET_KEY = "secret";

  public String generateToken(String username) {
    Date date =
        Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());

    // Generate token
    return Jwts.builder().setSubject(username).setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
  }

  public boolean validateToken(String token) {
    try {
      // Validate token
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      log.severe("invalid token");
    }
    return false;
  }

  public String getUsernameFromToken(String token) {
    // Get username from the token
    Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

}
