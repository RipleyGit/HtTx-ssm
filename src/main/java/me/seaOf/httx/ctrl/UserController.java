package me.seaOf.httx.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import me.seaOf.httx.pojo.User;
import me.seaOf.httx.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("userList.do")
	public String findAll(Model model){
        List<User> list = userService.getUserList();
        model.addAttribute("userList", list);
        return "userList";
    }
}
