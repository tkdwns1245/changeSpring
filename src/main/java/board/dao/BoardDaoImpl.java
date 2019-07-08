package board.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import board.dto.Bbs;
import board.dto.Comment;

public class BoardDaoImpl implements BoardDao{
	private SqlSessionTemplate sqlSessionTemplate;
	private static final String namespace = "boardDao";
	
	public BoardDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	public void insertArticle(Bbs bbs){
		sqlSessionTemplate.insert(namespace +".insert",bbs);
	}
	public int countArticle() {
		return sqlSessionTemplate.selectOne(namespace +".count");
	}
	public List<Bbs> getArticleList(int startNum, int endNum){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum",startNum);
		map.put("endNum",endNum);
		return sqlSessionTemplate.selectList(namespace+".list",map);
	}
	public Bbs selectArticleByNum(int num) {
		sqlSessionTemplate.update(namespace+".updateReadCount",num);
		return sqlSessionTemplate.selectOne(namespace+".selectByNum",num);
	}
	public Bbs selectUpdateArticleByNum(int num) {
		return sqlSessionTemplate.selectOne(namespace+".selectByNum",num);
	}
	public void updateArticle(Bbs article) {
		sqlSessionTemplate.update(namespace+".update",article);
	}
	public void deleteArticle(int num) {
		sqlSessionTemplate.delete(namespace+".delete",num);
	}
}
