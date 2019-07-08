package error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import member.command.JoinCommand;

@Controller
public class ErrorController {
	@RequestMapping("/member/invalidId")
	public String invalidId() {
		return "error/invalidId";
	}
}
