package member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.command.EditCommand;
import member.command.JoinCommand;
import member.command.LoginCommand;
import member.dto.AuthInfo;
import member.exception.DatabaseException;
import member.exception.IdPasswordNotMatchingException;
import member.service.AuthService;
import member.service.MemberService;
import member.validator.LoginCommandValidator;


@Controller
public class MemberController {
	private MemberService memberService;
	private AuthService authService;
	
	public void setMemberService(
			MemberService memberService) {
		this.memberService = memberService;
	}
	public void setAuthService(
			AuthService authService) {
		this.authService = authService;
	}
	
	@RequestMapping("/member/join")
	public String handleJoin(Model model) {
		model.addAttribute("formData", new JoinCommand());
		return "member/join";
	}
	@RequestMapping(value="/member/register", method=RequestMethod.POST)
	public String handleRegister(@ModelAttribute("formData")JoinCommand jcmd, HttpSession session) {
		try {
		memberService.regist(jcmd);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		AuthInfo authInfo = authService.authenticate(jcmd.getId(), jcmd.getPassword());
		session.setAttribute("authInfo", authInfo);
		return "member/register";
	}
	@RequestMapping("/member/logout")
	public String handleLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() {
		System.out.println("test");
		return "main";
	}
	

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginSubmit(LoginCommand loginCommand, Errors errors,
							HttpSession session, HttpServletRequest request,
							HttpServletResponse response)
	{
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "main";
		}
		try {
			AuthInfo authInfo = authService.authenticate(
					loginCommand.getId(),
					loginCommand.getPassword());
			
			session.setAttribute("authInfo", authInfo);
			Cookie rememberCookie = 
					new Cookie("REMEMBER", loginCommand.getId());
			rememberCookie.setPath("/");
			if(loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60*60*24*30);
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			return "member/loginSuccess";
		}catch (IdPasswordNotMatchingException e) {
			errors.reject("IdPasswordMatching");
			return "main";
		}
	}
	@RequestMapping(value="/member/idCheck")
	public String idCheck(String id,Model model) {
		int result = memberService.idCheck(id);
		model.addAttribute("result",result);
		model.addAttribute("id",id);
		return "member/idCheck";
	}
	@RequestMapping(value="/member/edit", method=RequestMethod.GET)
	public String editForm(EditCommand editCommand) {
		return "member/edit";
	}
	@RequestMapping(value="/member/editProcess", method=RequestMethod.POST)
	public String editProcess(EditCommand editCommand,HttpSession session,
								Errors errors) {
		try {
			memberService.edit(editCommand);
			}catch(Exception e)
			{
				e.printStackTrace();
				throw new DatabaseException();
			}
		return "member/editSuccess";
	}
	@ExceptionHandler(DatabaseException.class)
	public String handleDatabaseException() {
		return "error/databaseError";
	}
}
