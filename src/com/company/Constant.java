package com.company;

public class Constant {

    //统一认证跳转接口
    public static final String SSO_LOGIN_URl = "http://220.173.143.194:9012/login";
    //应用编号
    public  static final String APPID = "d16df15b-342a-47ec-bd57-46c636aba846";
    //token
    public  static final String TOKEN = "b2cdf754-378b-4234-9384-e1606c491610";

    //公钥路径
    public static final String PUBLICKEY_PATH = "D:/publicKey.key";
    //认证跳转成功跳转地址
    public  static final String AUTHEN_SUCCESS_URL = "http://220.173.143.194:9012/main";

    //用户查询接口
    public static final String QUERY_USER_URL = "http://220.173.143.194:9012/unifiedInterface/findUnifiedUserByAccount";
    //获取部门列表服务
    public static final String FIND_DEPT_URL = "http://220.173.143.194:9012/unifiedInterface/findDept";
    //校验用户名是否唯一服务
    public static final String CHECK_ACCOUNT_URL = "http://220.173.143.194:9012/unifiedInterface/checkAccount";
    //修改密码服务
    public static final String UPDATE_PASSWORD_URL = "http://220.173.143.194:9012/unifiedInterface/updatePassword";
    //用户注册服务
    public static final String USER_REGISTER_URL = "http://220.173.143.194:9012/unifiedInterface/userRegister";
    //用户信息编辑服务
    public static final String USER_EDIT_URL = "http://220.173.143.194:9012/unifiedInterface/userEdit";
    //获取全部用户信息
    public static final String FIND_USERALL_URL="";
    public static final String FIND_USER_URL="";
    //sso  首页
    public static final String SSOBASEURL = "http://220.173.143.194:9012/main";

    //推送地址
    public static final String PUSH_URL = "http://220.173.143.194:9012/apppush/push";

}
