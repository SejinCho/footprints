package kr.sejin.footprints.member;

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
}
