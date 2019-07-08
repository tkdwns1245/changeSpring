package board.dao;

import java.util.List;

import board.dto.Comment;

public interface CommentDao {
	public void insertComment(Comment comment);
	public List<Comment> getCommentList(int bnum);
	public void deleteComment(int cnum);
}
