package member.dao;
import java.util.Date;
import java.util.List;

import member.dto.Member;

public interface MemberDao {
	public void update(Member member);
	public void insert(Member member);
	public Member selectByEmail(String email);
	public List<Member> selectAll();
	public int count();
	public List<Member> selectByRegdate(Date from, Date to);
	public Member selectById(String id);
}
