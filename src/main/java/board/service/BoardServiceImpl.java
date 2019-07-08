package board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import board.dao.BoardDao;
import board.dto.Bbs;

@Service
public class BoardServiceImpl implements BoardService{
	private BoardDao boardDao;
	
	public BoardDao getBoardDao() {
		return boardDao;
	}
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public void write(Bbs bbs) {
		boardDao.insertArticle(bbs);
	}
	
	@Override
	public List<Bbs> list(int startNum, int endNum){
		return boardDao.getArticleList(startNum, endNum);
	}
	
	@Override
	public int getCount() {
		return boardDao.countArticle();
	}
	
	@Override
	public Bbs read(int num) {
		return boardDao.selectArticleByNum(num);
	}
	@Override
	public Bbs selectUpdateArticleByNum(int num) {
		return boardDao.selectUpdateArticleByNum(num);
	}
	@Override
	public void updateArticle(Bbs bbs) {
		boardDao.updateArticle(bbs);
	}
	@Override
	public void deleteArticle(int num) {
		boardDao.deleteArticle(num);
	}
}
