package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import board.command.ArticleCommand;
import board.command.CommentCommand;
import board.command.PageCommand;
import board.dto.Bbs;
import board.dto.Comment;
import board.service.BoardService;
import board.service.CommentService;

@Controller
public class BoardController {
	BoardService boardService;
	CommentService commentService;
	public void setBoardService(BoardService boardService){
		this.boardService = boardService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@RequestMapping(value="/freeBoard/board")
	public String list(String pageNum, Model model) {
		PageCommand pageCommand = new PageCommand(pageNum);
		pageCommand.setBoardService(boardService);
		pageCommand.init();
		if(pageCommand.getCount() > 0 ){
			model.addAttribute("boardList",boardService.list(pageCommand.getStartRow(),pageCommand.getEndRow()));
		}
		model.addAttribute("pageCommand",pageCommand);
		return "board/freeBoard/board";
	}
	@RequestMapping(value="/freeBoard/content")
	public String read(int pageNum, int num, Model model){
		Bbs bbs = boardService.read(num);
		List<Comment> commentList = commentService.list(num);
		model.addAttribute("article",bbs);
		model.addAttribute("commentList",commentList);
		model.addAttribute("pageNum",pageNum);
		return "board/freeBoard/content";
	}
	@RequestMapping(value="/freeBoard/write", method=RequestMethod.GET)
	public String writeGET(ArticleCommand articleCommand,Model model) {
		model.addAttribute("command",articleCommand);
		return "board/freeBoard/write";
	}
	@RequestMapping(value="/freeBoard/write", method=RequestMethod.POST)
	public String writePOST( ArticleCommand articleCommand) {
		Bbs bbs = new Bbs();
		bbs.setId(articleCommand.getId());
		bbs.setTitle(articleCommand.getTitle());
		bbs.setContent(articleCommand.getContent());
		boardService.write(bbs);
		return "redirect:/freeBoard/board";
	}
	@RequestMapping(value="/freeBoard/update",method=RequestMethod.GET)
	public String updateGET(int num, int pageNum, ArticleCommand articleCommand,Model model){
		Bbs bbs = boardService.selectUpdateArticleByNum(num);
		model.addAttribute("article",bbs);
		return "board/freeBoard/update";
	}
	@RequestMapping(value="/freeBoard/update", method=RequestMethod.POST)
	public String updatePOST(int num, ArticleCommand articleCommand) {
		Bbs bbs = boardService.selectUpdateArticleByNum(num);
		bbs.setTitle(articleCommand.getTitle());
		bbs.setContent(articleCommand.getContent());
		boardService.updateArticle(bbs);
		return "redirect:/freeBoard/board";
	}
	@RequestMapping(value="/freeBoard/delete")
	public String deleteArticle(int num) {
		boardService.deleteArticle(num);
		return "redirect:/freeBoard/board";
	}
	@RequestMapping(value="/freeBoard/commentInsert")
	public String insertComment(CommentCommand commentCommand,HttpServletRequest request) {
		Comment comment = new Comment();
		comment.setBnum(commentCommand.getBnum());
		comment.setContent(commentCommand.getContent());
		comment.setId(commentCommand.getId());
		commentService.write(comment);
		String referer = request.getHeader("Referer");
		return "redirect:"+ referer;
	}
	@RequestMapping(value="/freeBoard/commentDelete")
	public String deleteComment(int cnum, HttpServletRequest request) {
		commentService.delete(cnum);
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
}
