package com.kh.notice.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.SqlSessionTemplate;
import com.kh.notice.model.dao.NoticeDAO;
import com.kh.notice.model.vo.Notice;

public class NoticeService {
	
	private NoticeDAO nDao;
	
	public NoticeService() {
		nDao = new NoticeDAO();
	}

	public int insertNotice(Notice notice) {
		SqlSession session =  SqlSessionTemplate.getSqlSession();
		int result = nDao.insertNotice(session, notice);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public List<Notice> selectNoticeList(int currentPage) {
		//서비스, DAO, mapper.xml 순으로 코딩
		//서비스는 연결생성, DAO호출, 연결해제
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List<Notice> nList = nDao.selectNoticeList(session, currentPage);
		return nList;
	}

	public Notice selectOneById(int noticeNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Notice notice = nDao.selectOneById(session, noticeNo);
		return notice;
	}

	public int deleteNoticeByNo(int noticeNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = nDao.deleteNoticeByNo(session, noticeNo);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public int updateNotice(Notice notice) {
		SqlSession session =  SqlSessionTemplate.getSqlSession();
		int result = nDao.updateNotice(session, notice);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

}
