package com.nuclei.iam.security;

import com.nuclei.iam.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Security filter.
 */
@Slf4j
@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
  
  /**
   * The Jwt util.
   */
  private JwtUtils jwtUtil;
  /**
   * The User details service.
   */
  private UserDetailsService userDetailsService;
  
  /**
   * Do filter internal.
   *
   * @param request     the request
   * @param response    the response
   * @param filterChain the filter chain
   *
   * @throws ServletException the servlet exception
   * @throws IOException      the io exception
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // Reading Token from Authorization Header
    try {
      final String token = jwtUtil.extractToken(request);
      final String username = jwtUtil.getEmailIdFromToken(token);
      final UserDetails user = userDetailsService.loadUserByUsername(username);
      final boolean isValid = jwtUtil.validateToken(token, user.getUsername());
      if (isValid) {
        final UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                username, user.getPassword(), user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    } catch (Exception exception) {
      log.error(exception.getMessage());
    } finally {
      filterChain.doFilter(request, response);
    }
  }
}