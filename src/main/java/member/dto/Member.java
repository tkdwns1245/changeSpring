package member.dto;
import java.sql.Timestamp;

public class Member {
	private Long num;
	private String id;
	private String password;
	private String email;
	private int gender;
	private String name;
	private Timestamp regdate;
	public Member(){}
	public Member(
			String id, String email, String password, String name, int gender)
	{
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean matchPassword(String pwd) {
		return this.password.equals(pwd);
	}
}
