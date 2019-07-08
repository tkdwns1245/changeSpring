package member.service;

import member.command.EditCommand;
import member.command.JoinCommand;

public interface MemberService {
	public void regist(JoinCommand jc);
	public void edit(EditCommand ec);
	public int idCheck(String id);
}
