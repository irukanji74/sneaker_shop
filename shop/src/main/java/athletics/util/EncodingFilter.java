package athletics.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private String encoding = "UTF-8";
	private boolean forceEncoding = false;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			              throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		if (forceEncoding) { // If force encoding is set then it means that set
								// response stream encoding as well ...
			response.setCharacterEncoding(encoding);
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String encodingParam = filterConfig.getInitParameter("encoding");
		String forceEncoding = filterConfig.getInitParameter("forceEncoding");
		if (encodingParam != null) {
			encoding = encodingParam;
		}
		if (forceEncoding != null) {
			this.forceEncoding = Boolean.valueOf(forceEncoding);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}