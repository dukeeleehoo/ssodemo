package com.company;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.entity.User;

import javax.servlet.http.HttpServlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AuthcationServlet extends HttpServlet {



    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if(request.getSession().getAttribute("user")!=null){
            String redirctUrl = "";
            if(null!=redirctUrl&&!"".equals(redirctUrl)){
                response.sendRedirect(redirctUrl);
            }else{
                //返回首页
                request.getRequestDispatcher("/success.jsp").forward(request, response);
            }

        }

        try {
            String code = request.getParameter("code");
            //解密
            String result = RSAUtils.decryptByPublicKey(code,Constant.PUBLICKEY_PATH);
            JSONObject jsonObject = JSON.parseObject(result);
            Map paraMap = new HashMap<String,String>();
            //构建查询用户参数
            paraMap.put("token", jsonObject.getString("token"));
            paraMap.put("account", jsonObject.getString("account"));
            String paramter = JSON.toJSONString(paraMap);
            paraMap.clear();
            //加密请求参数  查询用户详情  构建session
            paraMap.put("code", RSAUtils.encryptByPublicKey(paramter,Constant.PUBLICKEY_PATH));
            //appid不需要加密
            paraMap.put("appid", Constant.APPID);
            //查詢后結果
            String resu =  HttpUtil.post(Constant.QUERY_USER_URL, paraMap);
            JSONObject rejson = JSON.parseObject(resu);
            //返回参数
            String rsutle = RSAUtils.decryptByPublicKey(rejson.getString("data"),Constant.PUBLICKEY_PATH);
            JSONObject json = JSON.parseObject(rsutle);
            if(rejson.getString("code").equals("10000")) {
                User user = new User();
                user.setAccount(json.getString("account"));
                user.setPassword(json.getString("password"));
                //可以在添加用户更多信息 放入session中
                //...........
                //创建用户session
                request.getSession().setAttribute("token",jsonObject.getString("token"));
                request.getSession().setAttribute("user", user);

                String redirectUrl = (String) jsonObject.get("redirctUrl");
                String redirectUrl1 = request.getParameter("redirctUrl");
                /**
                 * 如果有redirectUrl 返回redirectUrl
                 */
                if(redirectUrl!=null&&!"".equals(redirectUrl)){
                    response.sendRedirect(redirectUrl);
                }else if(redirectUrl1!=null&&!"".equals(redirectUrl1)){
                    response.sendRedirect(redirectUrl1);
                }else{
                    //轉發子系統成功頁面
                    request.getRequestDispatcher("/success.jsp").forward(request, response);
                }


            }else{


            }


        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }

    public static void main(String[] args) throws Exception {
        String code = "S8xPc2O3r6TF0o4wd70lrdgK3xuyFjbut1bVfzwRTCHU1PKM1KGqlSNjrw9CxKPoeFVtPL9fDwx/HYI+27wKVQhk0kdfbYs0fo0L9BFGJiePQUiGt1CQAhEZq7bJ7FePYurGrSJGKjdm2+8ZDu+tASQmWTQJsm5qQRsjrWqP3fVX6+BokGVVBkSKiXSVWK4ALhfN6rKCU17/lD1+v47k6lz25hjwY2lw8r87uJ/HjqThq2DzwPWIBUYDh2zegZJSBKkr29RNNXpaKkg3XTue3lsSmV58G18rqxA/hgT/RFas7DrRjoM/OnB6U5ZsgGjFINGkHXmTMXTQJRs2bf1TBg==";
        String result = RSAUtils.decryptByPublicKey(code,Constant.PUBLICKEY_PATH);
        System.out.println(result);
    }
}
