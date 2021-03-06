package kr.sejin.footprints.member;

import java.util.List;
import java.util.Map;

public interface MemberDAO {
	//회원가입 되어있는지 확인
	public String signUpCheck(int kakao_id);
	//회원가입
	public int signUp(MemberDTO member);
	//회원정보 가져오기
	public MemberDTO getMemberInfo(String mem_id);
	//이동거리를 기록 중인 것이 있는지 확인
	public String checkWalkingState(String mem_id);
	//기록 시작 시 walking 테이블에 insert
	public int walkingInfoInsert(String mem_id);
	//walkingRecord에 데이터 insert
	public int walkingRecordInsert(MemberDTO member);
	//walkingRecord에 데이터 select
	public List<MemberDTO> walkingRecordInfo(String wk_info_id);
	//기록 종료 시 walkingInfo 테이블에 wk_info_state update
	public int endWalkingInfoStateUpdate(MemberDTO member);
}
