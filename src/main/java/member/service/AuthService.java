package member.service;

import member.dao.MemberDao;
import member.dto.AuthInfo;
import member.dto.Member;
import member.exception.IdPasswordNotMatchingException;

public class AuthService {
	private MemberDao memberDao;
	
	public AuthService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public AuthInfo authenticate(String id, String password) {
		Member member = memberDao.selectById(id);
		if(member == null) {
			throw new IdPasswordNotMatchingException();
		}
		if(!member.matchPassword(password)) {
			throw new IdPasswordNotMatchingException();
		}
		return new AuthInfo(member.getNum(),member.getId(),member.getName());
	}
}
