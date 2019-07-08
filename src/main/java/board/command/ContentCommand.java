package board.command;

import java.util.List;

import board.dto.Bbs;
import board.dto.Comment;

public class ContentCommand {
	Bbs bbs;
	List<Comment> commentList;
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	int pageNum;
	int num;
	public Bbs getBbs() {
		return bbs;
	}
	public void setBbs(Bbs bbs) {
		this.bbs = bbs;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
