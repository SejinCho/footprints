package kr.sejin.footprints.member;

public class MemberDTO {
	//member
	private String mem_id ;
	private int kakao_id ;
	private String kakao_nickname ;
	private String gender ;
	private String kakao_profile_image ;
	private String join_date ;
	
	//walkingInfo
	private String wk_info_id ;
	private String wk_info_start_date ;
	private String wk_info_end_date ;
	private int wk_info_state ;
	
	//WalkingRecord
	private String wk_record_id ;
	private float wk_latitude ;
	private float wk_longitude ;
	private int wk_record_marker ;
	
	
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
	
	
	
	public String getWk_info_id() {
		return wk_info_id;
	}
	public void setWk_info_id(String wk_info_id) {
		this.wk_info_id = wk_info_id;
	}
	public String getWk_info_start_date() {
		return wk_info_start_date;
	}
	public void setWk_info_start_date(String wk_info_start_date) {
		this.wk_info_start_date = wk_info_start_date;
	}
	public String getWk_info_end_date() {
		return wk_info_end_date;
	}
	public void setWk_info_end_date(String wk_info_end_date) {
		this.wk_info_end_date = wk_info_end_date;
	}
	public int getWk_info_state() {
		return wk_info_state;
	}
	public void setWk_info_state(int wk_info_state) {
		this.wk_info_state = wk_info_state;
	}
	
	
	public String getWk_record_id() {
		return wk_record_id;
	}
	public void setWk_record_id(String wk_record_id) {
		this.wk_record_id = wk_record_id;
	}
	public float getWk_latitude() {
		return wk_latitude;
	}
	public void setWk_latitude(float wk_latitude) {
		this.wk_latitude = wk_latitude;
	}
	public float getWk_longitude() {
		return wk_longitude;
	}
	public void setWk_longitude(float wk_longitude) {
		this.wk_longitude = wk_longitude;
	}
	public int getWk_record_marker() {
		return wk_record_marker;
	}
	public void setWk_record_marker(int wk_record_marker) {
		this.wk_record_marker = wk_record_marker;
	}
	@Override
	public String toString() {
		return "MemberDTO [mem_id=" + mem_id + ", kakao_id=" + kakao_id + ", kakao_nickname=" + kakao_nickname
				+ ", gender=" + gender + ", kakao_profile_image=" + kakao_profile_image + ", join_date=" + join_date
				+ ", wk_info_id=" + wk_info_id + ", wk_info_start_date=" + wk_info_start_date + ", wk_info_end_date="
				+ wk_info_end_date + ", wk_info_state=" + wk_info_state + ", wk_record_id=" + wk_record_id
				+ ", wk_latitude=" + wk_latitude + ", wk_longitude=" + wk_longitude + ", wk_record_marker="
				+ wk_record_marker + "]";
	}
	
	
}
