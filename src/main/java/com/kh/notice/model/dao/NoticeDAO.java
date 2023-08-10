package com.kh.notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.notice.model.vo.Notice;

public class NoticeDAO {

	public int insertNotice(SqlSession session, Notice notice) {
		//JDBC랑 다르게 한줄이면 코드 끝
		//insert를 할거면 session에서 insert() 메소드 호출 후 
		//mapper.xml의 name값(NoticeMapper)과 쿼리문의 id값(isnertNotice)을 호출!
		//한줄 쓰고 끝나는게 아니라 notice-mapper.xml을 만들고 태그를 이용해서 쿼리문 작성해주고
		//mapper.xml을 사용한다고 mybatis-config.xml에 신고/등록 해야함
		int result = session.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

	

	public int deleteNoticeByNo(SqlSession session, int noticeNo) {
		//한줄 쓰고 끝나는게 아니라 notice-mapper.sml에 태그를 이용해서 쿼리문 작성해줘야함
		int result = session.delete("NoticeMapper.deleteNoticeByNo", noticeNo);
		return result;
	}



	public List<Notice> selectNoticeList(SqlSession session, int currentPage) {
		//select를 할거면 session에서 selectList, selectOne 메소드 중에서 필요에 맞게 호출
		//mapper.xml의 name값(NoticeMapper)과 커리문의 id값(selectNoticeList)을 호출
		//넘겨주는 값(매개변수)은 없으므로 name.id값만 selectList() 메소드의 전달값으로 넘겨줌
		//mapper.xml에서는 select이기 때문에 restToNotice에 해당하는 ResultMap을 작성해줘야함
		
		
		/*
		 * RowBounds는 쿼리문을 변경하지 않고도 페이징을 처리할 수 있게 해주는 클래스
		 * RowBounds의 동작은 offset값과 limit값을 이용해서 동작함
		 * limit 값은 한 페이지당 보여주고 싶은 게시물의 갯수
		 * offset값은 시작값, 변하는 값임.
		 * 1페이지는 (0*10) 0부터 시작해서 10개를 가져오고   1 ~ 10
		 * 2페이지는 (1*10)10부터 시작해서 10개를 가져온다.  10 ~ 20
		 * 3페이지는 (2*10)20부터 시작해서 10개를 가져온다.   20 ~ 30
		 */

		int limit = 10;
		int offset = (currentPage-1)*limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}



	public Notice selectOneById(SqlSession session, int noticeNo) {
		Notice notice = session.selectOne("NoticeMapper.selectOneById", noticeNo);
		return notice;
	}



	public int updateNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.updateNotice", notice);
		return result;
	}
	
	
	
	public String generatePageNavi(SqlSession session,int currentPage) {
		int totalCount = getTotalCount(session);   //전체 게시물의 갯수
		int recordCountPerPage = 10; //한페이지당 몇개의 게시물을 보여줄지
		int naviCountPerPage = 5;  ///한페이지당 네비바의 개수...1 2 3 4 5 
		int totalNaviCount;
		if(totalCount % recordCountPerPage > 0) {
			totalNaviCount = totalCount / recordCountPerPage + 1;
		} else {
			totalNaviCount = totalCount / recordCountPerPage;
		}
		// currentPage                      startNavi
		// 1,2,3,4,5                           1
		// 6,7,8,9,10                          6
		// 11,12,13,14,15                      11
		
		// currentPage                      endNavi
		// 1,2,3,4,5                           5
		// 6,7,8,9,10                          10
		// 11,12,13,14,15                      15
		
		
		
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage+ 1;
		int endNavi = startNavi + naviCountPerPage -1;
		
		if(endNavi > totalNaviCount) {
			endNavi = totalNaviCount;
		}
		
		StringBuilder result = new StringBuilder();
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi != 1) {
			result.append("<a href='/notice/list.do?currentPage="+(startNavi-1)+"'>[이전]</a>&nbsp;&nbsp;");
		}
		
		for(int i = startNavi; i <= endNavi; i++) {
			result.append("<a href='/notice/list.do?currentPage="+i+"'>"+i+"</a>&nbsp;&nbsp;");
		}
		
		if(startNavi != totalNaviCount) {
			result.append("<a href='/notice/list.do?currentPage="+(endNavi+1)+"'>[다음]</a>&nbsp;&nbsp;");
		}
		
		return result.toString();
	}



	private int getTotalCount(SqlSession session) {
		int totalCount = session.selectOne("NoticeMapper.getTotalCount");
		return totalCount;
	}
	
}


