package eai.devass.sinistreat.web.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogFilterImpl implements Filter {

    private FilterConfig filterConfig;
    private static final Logger log = Logger.getLogger(LogFilterImpl.class.getName());

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        log.warning("Log filter processed a " + getFilterConfig().getInitParameter("logType")
            + " request");
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        Enumeration<String> headerNames = httpRequest.getHeaderNames();
//        String subject = "";
//        if (headerNames != null) {
//               // while (headerNames.)) {
//                	   // String header = httpRequest.getHeader(headerNames.nextElement());
//                	    String authoriz = httpRequest.getHeader("Authorization");
//                	    System.out.println("authorization" + authoriz);
//                	    try {
//                	    	if(authoriz != null) {
//							 subject = getSubject(authoriz);
//							 System.out.println("subject" + subject);
//                	    	}
//							// httpRequest.getHeader("authorization").replace(authoriz, subject);
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//                        System.out.println("Header: " + httpRequest.getHeader(headerNames.nextElement()));
//                //}
//        }
        HttpServletRequest req = (HttpServletRequest) request;
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(req);
        //String user = request.getRemoteAddr();
       // requestWrapper.addHeader("user", subject);
        

        HttpServletResponse resp = (HttpServletResponse) response;

       // chain.doFilter(requestWrapper, response); // Goes to default servlet.
        //String clientOrigin = requestWrapper.getHeader("origin");

        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:3002/");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET,  DELETE, PUT");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type, Origin, Authorization, X-Auth-Token");
        resp.addHeader("Access-Control-Expose-Headers", "X-Auth-Token");       
        filterChain.doFilter(request, resp);
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {}
    
   
  

}
