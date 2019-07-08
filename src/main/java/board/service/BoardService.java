package board.service;

import java.util.List;

import board.dto.Bbs;

public interface BoardService {
	public void write(Bbs bbs);
	public int getCount();
	public List<Bbs> list(int startNum, int endNum);
	public Bbs read(int num);
	public Bbs selectUpdateArticleByNum(int num);
	public void updateArticle(Bbs bbs);
	public void deleteArticle(int num);
}
