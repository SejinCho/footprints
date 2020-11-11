package kr.sejin.footprints.member;

public class MemberDTO {
	private String mem_id ;
	private int kakao_id ;
	private String kakao_nickname ;
	private String gender ;
	private String kakao_profile_image ;
	private String join_date ;
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getKakao_id() {
		return kakao_id;
	}
	public void setKakao_id(int kakao_id) {
		this.kakao_id = kakao_id;
	}
	public String getKakao_nickname() {
		return kakao_nickname;
	}
	public void setKakao_nickname(String kakao_nickname) {
		this.kakao_nickname = kakao_nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getKakao_profile_image() {
		return kakao_profile_image;
	}
	public void setKakao_profile_image(String kakao_profile_image) {
		this.kakao_profile_image = kakao_profile_image;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	@Override
	public String toString() {
		return "MemberDTO [mem_id=" + mem_id + ", kakao_id=" + kakao_id + ", kakao_nickname=" + kakao_nickname
				+ ", gender=" + gender + ", kakao_profile_image=" + kakao_profile_image + ", join_date=" + join_date
				+ "]";
	}
	
	
	
}
