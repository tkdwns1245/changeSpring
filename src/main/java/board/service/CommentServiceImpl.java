package board.service;

import java.util.List;

import board.dao.CommentDao;
import board.dto.Comment;

public class CommentServiceImpl implements CommentService{
	private CommentDao commentDao;
	
	public CommentDao getCommentDao() {
		return commentDao;
	}
	
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	@Override
	public void delete(int cnum) {
		commentDao.deleteComment(cnum);
	}@Override
	public void write(Comment comment) {
		commentDao.insertComment(comment);
	}@Override
	public List<Comment> list(int bnum) {
		return commentDao.getCommentList(bnum);
	}
}
