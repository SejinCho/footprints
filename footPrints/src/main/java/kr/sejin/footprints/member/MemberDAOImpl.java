package kr.sejin.footprints.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	SqlSession sqlSession;
	
	//회원가입 되어있는지 확인 
	@Override
	public String signUpCheck(int kakao_id) {
		return sqlSession.selectOne("kr.sejin.footprints.member.signUpcheck",kakao_id);
	}
	//회원가입
	@Override
	public int signUp(MemberDTO member) {
		return sqlSession.insert("kr.sejin.footprints.member.signUp", member);
	}
	//회원정보 가져오기
	@Override
	public MemberDTO getMemberInfo(String mem_id) {
		return sqlSession.selectOne("kr.sejin.footprints.member.getMemberInfo", mem_id);
	}
	//이동거리를 기록 중인 것이 있는지 확인
	@Override
	public String checkWalkingState(String mem_id) {
		return sqlSession.selectOne("kr.sejin.footprints.member.checkWalkingState", mem_id);
	}
	//기록 시작 시 walking 테이블에 insert
	@Override
	public int walkingInfoInsert(String mem_id) {
		return sqlSession.insert("kr.sejin.footprints.member.walkingInfoInsert", mem_id);
	}
	//walkingRecord에 데이터 insert
	@Override
	public int walkingRecordInsert(MemberDTO member) {
		return sqlSession.insert("kr.sejin.footprints.member.walkingRecordInsert", member);
	}
	//walkingRecord에 데이터 select
	@Override
	public List<MemberDTO> walkingRecordInfo(String wk_info_id) {
		return sqlSession.selectList("kr.sejin.footprints.member.walkingRecordInfo", wk_info_id);
	}
}
