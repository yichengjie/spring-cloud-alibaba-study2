package com.yicj.study.sentinel.component;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CustomUrlBlockHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        // {"code":999, "msg":"访问人数过多"}
        String msg = "{\"code\":999, \"msg\":\"访问人数过多\"}" ;
        response.getWriter().write(msg);
    }
}
