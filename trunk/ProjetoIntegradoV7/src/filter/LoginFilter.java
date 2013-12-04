package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//@WebFilter(servletNames = {"Faces Servlet"})
public class LoginFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Get the loginBean from session attribute
		String usuarioNaSessao = (String)((HttpServletRequest)request).getSession().getAttribute("usuario");
		
		// For the first application request there is no loginBean in the session so user needs to log in
		// For other requests loginBean is present but we need to check if user has logged in successfully
		// if (usuarioNaSessao != null) {
		// 			String contextPath = ((HttpServletRequest)request).getContextPath();
		// 			((HttpServletResponse)response).sendRedirect(contextPath + "/main_logado.xhtml");
		// }
		
		chain.doFilter(request, response);
			
	}

	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}	
	
}