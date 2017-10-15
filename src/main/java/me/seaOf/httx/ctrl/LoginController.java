package me.seaOf.httx.ctrl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import me.seaOf.httx.pojo.User;
import me.seaOf.httx.service.UserService;

public class LoginController extends BaseController{
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		
		//应该跳转到用户的登陆页面
		return "/sysadmin/login/login";
	}
	
	
	/**
	 * 思路：
	 * 	1.判断用户名或密码是否为空或空串  如果为空应该给用户提示信息
	 *  2.根据用户名和密码查询用户信息，如果用户信息不为null 则证明用户名和密码正确
	 *    页面应该跳转到系统首页。
	 *    入股user对象为空，则证明用户输入的用户名和密码不正确，给用户提示信息。
	 *  
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public String Login(String username,String password,Model model,HttpSession session){
		
		//判断用户名和密码是否为空
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			//表示用户名和密码为空
			model.addAttribute("errorInfo", "用户名或密码不能为空");
			
			//跳转到用户登陆页面
			return "/sysadmin/login/login";
		}
		//根据用户名和密码查询数据
		User user = userService.findUserByU_P(username,password);
		
		if(user == null){
			//表示用户名和密码不正确
			model.addAttribute("errorInfo", "用户名或密码不正确");
			return "/sysadmin/login/login";
		}
		
		//将用户信息存入session域
		session.setAttribute("userSession", user);
		
		//表示用户名和密码正确
		return "redirect:/home.action";	
	}
	
	
	@RequestMapping("/logout")
	public String loginOut(HttpSession httpSession){
		
		//将user对象从session域中删除
		httpSession.removeAttribute("userSession");
		//页面应该跳转到登陆页面
		return "/sysadmin/login/login";
	}
}
