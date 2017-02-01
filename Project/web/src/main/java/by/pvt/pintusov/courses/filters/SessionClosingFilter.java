package by.pvt.pintusov.courses.filters;

import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security filter
 * @author pintusov
 * @version 1.1
 */

public class SessionClosingFilter implements javax.servlet.Filter {
	public void init(FilterConfig fConfig) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Session session = HibernateUtil.getInstance().getSession();
		filterChain.doFilter(request, response);
		HibernateUtil.getInstance().releaseSession(session);
	}

	public void destroy() {}
}
