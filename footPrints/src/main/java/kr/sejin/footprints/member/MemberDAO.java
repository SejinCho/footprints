package kr.sejin.footprints.member;

public interface MemberDAO {
	//회원가입 되어있는지 확인
	public String signUpCheck(int kakao_id);
	//회원가입
	public int signUp(MemberDTO member);
	//회원정보 가져오기
	public MemberDTO getMemberInfo(String mem_id);
}
