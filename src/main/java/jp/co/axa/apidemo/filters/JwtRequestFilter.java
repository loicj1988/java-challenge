package jp.co.axa.apidemo.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import jp.co.axa.apidemo.config.security.EmployeeUserDetailsService;
import jp.co.axa.apidemo.utils.JwtUtil;


/**
 * Jwt verification filter
 * 
 * Filter request to get the jwt header and authentify the user.
 * 
 * @author Loic
 * @version 0.0.1
 *
 */
@Component
public class JwtRequestFilter extends GenericFilterBean {

  private static final String AUTHORIZATION = "Authorization";

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private EmployeeUserDetailsService employeeUserDetailsService;


  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    // Get the token
    String token = getTokenFromRequest((HttpServletRequest) servletRequest);

    // Validate the token
    if (token != null && jwtUtil.validateToken(token)) {

      // Get username
      String username = jwtUtil.getUsernameFromToken(token);

      // Load user details
      UserDetails userDetails = employeeUserDetailsService.loadUserByUsername(username);

      // Set spring auth
      UsernamePasswordAuthenticationToken auth =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

      // Set context
      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    // Proceed next filter
    filterChain.doFilter(servletRequest, servletResponse);
  }

  /**
   * Get the token present in the http header request
   * 
   * @param request
   * @return The jwt token
   */
  private String getTokenFromRequest(HttpServletRequest request) {
    // Get authorization header
    String authorizationHeader = request.getHeader(AUTHORIZATION);

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      // Get the token
      return authorizationHeader.substring(7);
    }

    return null;
  }

}
