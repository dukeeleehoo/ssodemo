package com.company;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.http.HttpUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 接口请求demo
 * @author zp
 *
 */
public class UnifiedInterface {

	public static void main(String []args)throws Exception {
//		String result = userRegister("111111","111111","张三","18189121202","12","812902521@qq.com","1","029-88888888");
//		String result = findAllUser();
//		String result = userEdit("接接","测试账号","18189121722","11","81296521@qq.com","2","029-88888888");
//		String result = findDept();
//		String result = findUnifiedUserByAccount("admin");
//		String result = updatePassword("接口测试账号","222222","111111");
//		String result = findUser(2,1);
//		String result = findUnifiedUserByAccount("接");
		//对接口返回信息进行解析（查看接口文档）
//		JSONObject json = JSON.parseObject(result);
//		System.out.println(json.toJSONString());
//		String data = json.getString("data");
		//findAllUser屏蔽掉解密！
//		if(!data.equals("") && null != data){
//			data = RSAUtils.decryptByPublicKey(data,Constant.PUBLICKEY_PATH);
//
//		}
//		System.out.println("data="+data);
		String reslult = appPush("test1", Constant.APPID, "NBA", "莱纳德得到39分、14个篮板和5次助攻", "http://220.173.143.194:9020/GLMapGIS/appauthen");
//		String reslult1= appPush("admin", Constant.APPID, "NBA", "莱纳德得到39分、14个篮板和5次助攻", "http://220.173.143.194:9020/GLMapGIS/appauthen");
		System.out.println(reslult);
	}
	
	/**
	 * 获取全部用户列表服务
	 */
	public static String findAllUser() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//构建查询用户参数
		map.put("token", Constant.TOKEN);
        String paramter = JSON.toJSONString(map);
        //清空map
        map.clear();
		//对构建参数进行加密。存储到map集合
        map.put("code", RSAUtils.encryptByPublicKey(paramter,Constant.PUBLICKEY_PATH));
		map.put("appid", Constant.APPID);
		return HttpUtil.post(Constant.FIND_USERALL_URL, map);
	}
	
	/**
	 * 获取用户分页服务
	 */
	public static String findUser(Integer size,Integer current) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//构建查询用户参数
		map.put("token", Constant.TOKEN);
		map.put("size", size);
		map.put("current", current);
        String paramter = JSON.toJSONString(map);
        //清空map
        map.clear();
		//对构建参数进行加密。存储到map集合
        map.put("code", RSAUtils.encryptByPublicKey(paramter,Constant.PUBLICKEY_PATH));
		map.put("appid", Constant.APPID);
		return HttpUtil.post(Constant.FIND_USER_URL, map);
	}
	
	/**
	 * 根据用户名获取用户信息
	 * @param account
	 */
	public static String findUnifiedUserByAccount(String account) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//构建查询用户参数
		map.put("token", Constant.TOKEN);
		map.put("account", account);
        String paramter = JSON.toJSONString(map);
        //清空map
        map.clear();
		//对构建参数进行加密。存储到map集合
        map.put("code", RSAUtils.encryptByPublicKey(paramter,Constant.PUBLICKEY_PATH));
		map.put("appid", Constant.APPID);
		return HttpUtil.post(Constant.QUERY_USER_URL, map);
	}
	
	/**
	 * 获取部门列表服务
	 */
	public static String findDept() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//构建查询用户参数
		map.put("token", Constant.TOKEN);
        String paramter = JSON.toJSONString(map);
        //清空map
        map.clear();
		//对构建参数进行加密。存储到map集合
        map.put("code", RSAUtils.encryptByPublicKey(paramter,Constant.PUBLICKEY_PATH));
		map.put("appid", Constant.APPID);
		return HttpUtil.post(Constant.FIND_DEPT_URL, map);
	}
	
	/**
	 * 校验用户名是否唯一服务	
	 * @param account
	 */
	public static String checkAccount(String account) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//构建查询用户参数
		map.put("token", Constant.TOKEN);
		map.put("account", account);
        String paramter = JSON.toJSONString(map);
        //清空map
        map.clear();
		//对构建参数进行加密。存储到map集合
        map.put("code", RSAUtils.encryptByPublicKey(paramter,Constant.PUBLICKEY_PATH));
		map.put("appid", Constant.APPID);
		return HttpUtil.post(Constant.CHECK_ACCOUNT_URL, map);
	}
	
	/**
	 * 修改密码服务	
	 * @param account
	 * @param oldPassword  旧密码
	 * @param newPassword  新密码
	 */
	public static String updatePassword(String account,String oldPassword,String newPassword) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//构建查询用户参数
		map.put("token", Constant.TOKEN);
		map.put("account", account);
		map.put("oldPassword", oldPassword);
		map.put("newPassword", newPassword);
        String paramter = JSON.toJSONString(map);
        //清空map
        map.clear();
		//对构建参数进行加密。存储到map集合
        map.put("code", RSAUtils.encryptByPublicKey(paramter,Constant.PUBLICKEY_PATH));
		map.put("appid", Constant.APPID);
		return HttpUtil.post(Constant.UPDATE_PASSWORD_URL, map);
	}
	
	/**
	 * 用户注册服务	
	 * @param account	账号名(必须)
	 * @param password	密码(必须)
	 * @param name		姓名(必须)
	 * @param mobile	手机(必须)
	 * @param deptid	部门编号(必须)
	 * @param email		邮箱
	 * @param sex		性别（1男，2女）
	 * @param phone		座机
	 * @return
	 * @throws Exception
	 */
	public static String userRegister(String account,String password,String name,String mobile,String deptid,String email,String sex,String phone) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//构建查询用户参数
		map.put("token", Constant.TOKEN);
		map.put("account", account);
		map.put("password", password);
		map.put("name", name);
		map.put("mobile", mobile);
		map.put("deptid", deptid);
		map.put("email", email);
		map.put("sex", sex);
		map.put("phone", phone);
        String paramter = JSON.toJSONString(map);
        //清空map
        map.clear();
		//对构建参数进行加密。存储到map集合
        map.put("code", RSAUtils.encryptByPublicKey(paramter,Constant.PUBLICKEY_PATH));
		map.put("appid", Constant.APPID);
		return HttpUtil.post(Constant.USER_REGISTER_URL, map);
	}

	/**
	 * 用户信息编辑服务	
	 * @param account	账号名(必须)
	 * @param name
	 * @param mobile
	 * @param deptid
	 * @param email
	 * @param sex
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public static String userEdit(String account,String name,String mobile,String deptid,String email,String sex,String phone) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//构建查询用户参数
		map.put("token", Constant.TOKEN);
		map.put("account", account);
		map.put("name", name);
		map.put("mobile", mobile);
		map.put("deptid", deptid);
		map.put("email", email);
		map.put("sex", sex);
		map.put("phone", phone);
        String paramter = JSON.toJSONString(map);
        //清空map
        map.clear();
		//对构建参数进行加密。存储到map集合
        map.put("code", RSAUtils.encryptByPublicKey(paramter,Constant.PUBLICKEY_PATH));
		map.put("appid", Constant.APPID);
		return HttpUtil.post(Constant.USER_EDIT_URL, map);
	}


	/**
	 * app推送
	 * @param account	账号名(必须)
	 * @param appid  (必须)
	 * @param title (必须)
	 * @param content (必须)
	 * @param redirctUrl (必须)
	 * @return
	 * @throws Exception
	 */
	public static String appPush(String account,String appid,String title,String content,String redirctUrl) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//构建查询用户参数
		map.put("account", account);
		map.put("title", title);
		map.put("content", content);
		map.put("redirctUrl", redirctUrl);
		String paramter = JSON.toJSONString(map);
		//清空map
		map.clear();
		//对构建参数进行加密。存储到map集合
		map.put("code", RSAUtils.encryptByPublicKey(paramter,Constant.PUBLICKEY_PATH));
		map.put("appid", appid);
		return HttpUtil.post(Constant.PUSH_URL, map);

	}
}
