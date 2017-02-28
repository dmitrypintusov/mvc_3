package by.pvt.pintusov.courses.filters;


import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * Encoding filter
 * @author pintusov
 * @version 1.2
 */

public class EncodingFilter implements Filter {
	private Logger logger = Logger.getLogger(EncodingFilter.class);
	private String initParameterEncoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		initParameterEncoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String requestEncoding = request.getCharacterEncoding();
		if (initParameterEncoding != null && !initParameterEncoding.equalsIgnoreCase(requestEncoding)) {
			request.setCharacterEncoding(initParameterEncoding);
			response.setCharacterEncoding(initParameterEncoding);
		}
		response.setContentType("UTF-8");
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}
