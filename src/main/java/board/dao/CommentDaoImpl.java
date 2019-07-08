package board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import board.dto.Comment;

public class CommentDaoImpl implements CommentDao{
	SqlSessionTemplate sqlSessionTemplate;
	private static final String namespace = "commentDao";
	
	public CommentDaoImpl(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	public void insertComment(Comment comment) {
		sqlSessionTemplate.insert(namespace+".insert",comment);
	}
	public List<Comment> getCommentList(int bnum){
		return sqlSessionTemplate.selectList(namespace+".list",bnum);
	}
	public void deleteComment(int cnum) {
		sqlSessionTemplate.delete(namespace+".delete",cnum);
	}
}
