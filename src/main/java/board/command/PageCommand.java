package board.command;

import board.service.BoardService;

public class PageCommand {
	BoardService boardService;
	
	int pageSize;
	String pageNum;
	int currentPage;
	int startRow, endRow;
	int count;
	int number;
	int pageBlock;
	int imsi;
	int pageCount;
	int startPage,endPage;
	public PageCommand() {}
	public PageCommand(String pageNum) {
		this.pageNum = pageNum;
		if(pageNum == null) {
			this.pageNum = "1";
		}
	}
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	public void init() {
		setPageSize(10);
		setCurrentPage(Integer.parseInt(getPageNum()));
		setStartRow((getCurrentPage() - 1) * getPageSize() + 1);
		setEndRow(getCurrentPage() * getPageSize());
		setCount(boardService.getCount());
		setPageBlock(2);
		setNumber(count - (currentPage-1) * pageSize);	//게시판 표시번호
		setImsi(count % pageSize == 0 ? 0 : 1);
		setPageCount(count / pageSize + imsi);	//총 페이지 수
		setStartPage((int)((currentPage-1) / pageBlock) * pageBlock + 1);//현제 페이지가 1+5의 배수일 때마다 시작 페이지 갱신
		setEndPage(startPage + pageBlock - 1);
		if( endPage > pageCount)
			setEndPage(pageCount);//페이지 마지막번호가 pageCount보다 작으면 endpage = pagecount
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getImsi() {
		return imsi;
	}
	public void setImsi(int imsi) {
		this.imsi = imsi;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
