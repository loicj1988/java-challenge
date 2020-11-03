package jp.co.axa.apidemo.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Utility service to manipulate JWT
 * 
 * @author Loic
 * @version 0.0.1
 *
 */
@Service
public class JwtUtil {

  private String SECRET_KEY = "secret";

  /**
   * Generate a JWT token
   * 
   * @param username The username to set in the claims
   * @return The jwt token
   */
  public String generateToken(String username) {
    Date date =
        Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());

    // Generate token
    return Jwts.builder().setSubject(username).setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
  }

  /**
   * Validate a JWT token
   * 
   * @param token The token to validate
   * @return true when the token is valid, false instead
   */
  public boolean validateToken(String token) {
    try {
      // Validate token
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      // Log something ...
    }
    return false;
  }

  /**
   * Get the username stored in JWT claims
   * 
   * @param token The token
   * @return The username
   */
  public String getUsernameFromToken(String token) {
    // Get username from the token
    Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

}
