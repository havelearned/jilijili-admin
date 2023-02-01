package wang.jilijili.music.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import wang.jilijili.music.config.SecurityConfig;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Auther: Amani
 * @Date: 2023/1/28 16:38
 * @Description: 授权
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws IOException, ServletException {
    /*鉴权,校验解析token*/
    String header = request.getHeader(SecurityConfig.HEADER_STRING);
    if (header == null) {
      chain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(String header) {
    // 解析token
    String username = JWT.require(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()))
        .build().verify(header.replace(SecurityConfig.TOKEN_PREFIX, ""))
        .getSubject();

    if (username != null)
      return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

    return null;
  }
}
