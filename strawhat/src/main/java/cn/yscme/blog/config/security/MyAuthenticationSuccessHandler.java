package cn.yscme.blog.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationSuccessHandler 
            extends SavedRequestAwareAuthenticationSuccessHandler {
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, 
                    HttpServletResponse response, 
                    Authentication authentication) 
                    throws ServletException, IOException {
	  response.setContentType("application/json;charset=UTF-8");
	  response.getWriter().write("true");
  }
}
