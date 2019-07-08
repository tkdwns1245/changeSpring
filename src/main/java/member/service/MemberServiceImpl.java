package member.service;

import member.command.EditCommand;
import member.command.JoinCommand;
import member.dao.MemberDao;
import member.dto.Member;
import member.exception.AlreadyExistingMemberException;

public class MemberServiceImpl implements MemberService{
	private MemberDao memberDao;
	
	public MemberServiceImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void regist(JoinCommand jc) {
		Member member = memberDao.selectById(jc.getId());
		if(member != null) {
			throw new AlreadyExistingMemberException("dup email " +jc.getId());
		}
		Member newMember = new Member(
				jc.getId(),
				jc.getEmail(),
				jc.getPassword(),
				jc.getName(),
				Integer.parseInt(jc.getGender()));
		memberDao.insert(newMember);
	}
	public void edit(EditCommand ec) {
		Member member = memberDao.selectById(ec.getId());
		Member newMember = new Member(
				ec.getId(),
				ec.getEmail(),
				ec.getPassword(),
				member.getName(),
				member.getGender());
		memberDao.update(newMember);
	}
	public int idCheck(String id) {
		Member member = memberDao.selectById(id);
		if(member !=null)
			return -1;
		else
			return 1;
	}
}
