package member.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import member.dto.Member;

public class MemberDaoImpl implements MemberDao{
	private SqlSessionTemplate sqlSessionTemplate;
	private static final String namespace = "memberDao";
	public MemberDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	@Override
	public void update(Member member) {
		sqlSessionTemplate.update(namespace+".update",member);
	}
	@Override
	public void insert(Member member) {
		sqlSessionTemplate.insert(namespace+".insert",member);
	}
	@Override
	public Member selectByEmail(String email) {
		return sqlSessionTemplate.selectOne(namespace+".selectByEmail", email);
	}
	@Override
	public List<Member> selectAll() {
		return sqlSessionTemplate.selectList(namespace+".list");
	}
	@Override
	public int count() {
		return sqlSessionTemplate.selectOne(namespace+".count");
	}
	@Override
	public List<Member> selectByRegdate(Date from, Date to) {
		HashMap<String, Date> map = new HashMap<String, Date>();
		map.put("from", from);
		map.put("to", to);
		return sqlSessionTemplate.selectList(namespace+".selectByRegdate",map);
	}
	@Override
	public Member selectById(String id) {
		List<Member> results = sqlSessionTemplate.selectList(namespace+".selectById", id);
		return results.isEmpty() ? null : results.get(0);
	}
}
