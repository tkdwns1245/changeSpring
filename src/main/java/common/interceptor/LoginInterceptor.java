package common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import member.command.LoginCommand;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception{
		System.out.println("inter");
		LoginCommand loginCommand = new LoginCommand();
		Cookie[] getCookie = request.getCookies();
		Cookie idCookie = null;
		if(getCookie != null){
			for(int i=0; i<getCookie.length; i++){
				if(getCookie[i].getName().equals("REMEMBER"))
				{
					idCookie = getCookie[i];
				}
			}
		}
		if(idCookie != null) {
			loginCommand.setId(idCookie.getValue());
			loginCommand.setRememberEmail(true);
			request.setAttribute("loginCommand", loginCommand);
			return true;
		}
		request.setAttribute("loginCommand", loginCommand);
		return true;
	}
}
