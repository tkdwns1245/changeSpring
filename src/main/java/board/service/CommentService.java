package board.service;

import java.util.List;

import board.dto.Comment;

public interface CommentService {
	public void write(Comment comment);
	public void delete(int cnum);
	public List<Comment> list(int bnum);
}
