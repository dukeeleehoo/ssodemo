package com.company;


import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class LoginFilter implements Filter {


    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
         if(session!=null&&session.getAttribute("user")!=null){
             String redirectUrl1 = request.getParameter("redirctUrl");
             if(null!=redirectUrl1&&!"".equals(redirectUrl1)){
                 response.sendRedirect(redirectUrl1);
             }else{
                 chain.doFilter(req, resp);
             }

        }else{
            String redirctUrl = request.getRequestURL().toString();

            try {
                response.sendRedirect(Constant.SSO_LOGIN_URl + "?appid=" + Constant.APPID+"&redirctUrl="+redirctUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
