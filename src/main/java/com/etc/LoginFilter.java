package com.etc;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        HttpSession session=request.getSession();
        Object user=session.getAttribute("user");
        String url=request.getRequestURL().toString();
        logger.info("session.getAttribute('user'):" + user);
        logger.info("--->>> LoginFilter {},{}", request.getMethod(), request.getRequestURL().toString());
        if(user!=null){
            requestContext.setSendZuulResponse(true);
            requestContext.setResponseStatusCode(200);
            requestContext.set("isSuccess",true);
        }else{
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(400);
            requestContext.getResponse().setCharacterEncoding("UTF-8");
            requestContext.getResponse().setContentType("text/html;charset=UTF-8 ");
            requestContext.setResponseBody("<a href='/index?url="+url+"'>请先登录！</a>");
            requestContext.set("isSuccess",false);
        }
        return null;
    }
}
