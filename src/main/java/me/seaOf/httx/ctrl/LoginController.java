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
		
		//Ӧ����ת���û��ĵ�½ҳ��
		return "/sysadmin/login/login";
	}
	
	
	/**
	 * ˼·��
	 * 	1.�ж��û����������Ƿ�Ϊ�ջ�մ�  ���Ϊ��Ӧ�ø��û���ʾ��Ϣ
	 *  2.�����û����������ѯ�û���Ϣ������û���Ϣ��Ϊnull ��֤���û�����������ȷ
	 *    ҳ��Ӧ����ת��ϵͳ��ҳ��
	 *    ���user����Ϊ�գ���֤���û�������û��������벻��ȷ�����û���ʾ��Ϣ��
	 *  
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public String Login(String username,String password,Model model,HttpSession session){
		
		//�ж��û����������Ƿ�Ϊ��
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			//��ʾ�û���������Ϊ��
			model.addAttribute("errorInfo", "�û��������벻��Ϊ��");
			
			//��ת���û���½ҳ��
			return "/sysadmin/login/login";
		}
		//�����û����������ѯ����
		User user = userService.findUserByU_P(username,password);
		
		if(user == null){
			//��ʾ�û��������벻��ȷ
			model.addAttribute("errorInfo", "�û��������벻��ȷ");
			return "/sysadmin/login/login";
		}
		
		//���û���Ϣ����session��
		session.setAttribute("userSession", user);
		
		//��ʾ�û�����������ȷ
		return "redirect:/home.action";	
	}
	
	
	@RequestMapping("/logout")
	public String loginOut(HttpSession httpSession){
		
		//��user�����session����ɾ��
		httpSession.removeAttribute("userSession");
		//ҳ��Ӧ����ת����½ҳ��
		return "/sysadmin/login/login";
	}
}
