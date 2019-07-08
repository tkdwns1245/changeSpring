package board.dao;
import java.util.List;

import board.dto.Bbs;
import board.dto.Comment;

public interface BoardDao {
	public void insertArticle(Bbs bbs);
	public int countArticle();
	public List<Bbs> getArticleList(int startNum, int endNum);
	public Bbs selectArticleByNum(int num);
	public Bbs selectUpdateArticleByNum(int num);
	public void updateArticle(Bbs article);
	public void deleteArticle(int num);
}
