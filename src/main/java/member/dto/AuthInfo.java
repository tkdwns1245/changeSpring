package member.dto;

public class AuthInfo {
	private long num;
	private String id;
	private String name;
	
	public AuthInfo(long num, String id, String name) {
		this.num = num;
		this.id = id;
		this.name = name;
	}
	
	public long getNum() {
		return num;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
