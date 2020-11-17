package kr.sejin.footprints.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDAO memberDAO;
	
	//회원가입 되어있는지 확인
	@Override
	public String signUpCheck(int kakao_id) {
		return memberDAO.signUpCheck(kakao_id);
	}
	//회원가입
	@Override
	public int signUp(MemberDTO member) {
		return memberDAO.signUp(member);
	}
	//회원정보 가져오기
	@Override
	public MemberDTO getMemberInfo(String mem_id) {
		return memberDAO.getMemberInfo(mem_id);
	}
	//이동거리를 기록 중인 것이 있는지 확인
	@Override
	public String checkWalkingState(String mem_id) {
		return memberDAO.checkWalkingState(mem_id);
	}
	//기록 시작 시 walking 테이블에 insert
	@Override
	public int walkingInfoInsert(String mem_id) {
		return memberDAO.walkingInfoInsert(mem_id);
	}
	//walkingRecord에 데이터 insert
	@Override
	public int walkingRecordInsert(MemberDTO member) {
		return memberDAO.walkingRecordInsert(member);
	}
	//기록 시작 시 insert들
	@Override
	public int walkingStartInsert(MemberDTO member) {
		int result = 0;
		//walking 테이블에 insert
		String mem_id = member.getMem_id();
		int walkingResult = memberDAO.walkingInfoInsert(mem_id);
		
		return result;
	}
}
