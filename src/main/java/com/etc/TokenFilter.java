package com.etc;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

public class TokenFilter extends ZuulFilter {
    private static final Logger logger= LoggerFactory.getLogger(TokenFilter.class);
    @Override
    public String filterType() { //表示过滤器的类型--pre表示路由前执行
        return "pre";
    }

    @Override
    public int filterOrder() { //过滤器的执行顺序，数字越大，优先级越低，越靠后执行
        return 1; //高优先级
    }

    @Override
    public boolean shouldFilter() { //过滤器的执行条件，是否执行过滤器，true表示过滤器执行
        return true;
    }

    @Override
    public Object run() throws ZuulException { //过滤器具体的执行代码
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        logger.info("--->> TokenFilter {},{}",request.getMethod(),request.getRequestURL().toString());
        String token=request.getParameter("token");//获取token请求的参数
        if(token!=null && !"".equals(token)){
            requestContext.setSendZuulResponse(true); //允许路由
            requestContext.setResponseStatusCode(200);
            requestContext.set("isSuccess",true);
        }else{
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(400);
            requestContext.setResponseBody("token is empty");
            requestContext.set("isSuccess",false);
        }
        return null;
    }
}
