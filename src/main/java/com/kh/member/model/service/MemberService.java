package com.kh.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.SqlSessionTemplate;
import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;

public class MemberService {

	MemberDAO mDao;
	
	public MemberService() {
		mDao = new MemberDAO();
	}
	
	public int insertMember(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = mDao.insertMember(session,member);
		if(result>0) {
			//성공
			session.commit();
		} else {
			//실패
			session.rollback();
		}
		session.close();
		return result;
	}

	public int deleteMember(String memberId) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = mDao.deleteMember(session, memberId);
		if(result>0) {
			//성공
			session.commit();
		} else {
			//실패
			session.rollback();
		}
		session.close();
		return result;
	}

	public int updateMember(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = mDao.updateMember(session, member);
		if(result>0) {
			//성공
			session.commit();
		} else {
			//실패
			session.rollback();
		}
		session.close();
		return result;
	}

	public Member selectCheckLogin(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Member mOne = mDao.selectCheckLogin(session, member);
		return mOne;
	}

	public Member selectOneById(String memberId) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Member member = mDao.selectOneById(session, memberId);
		return member;
	}

}
