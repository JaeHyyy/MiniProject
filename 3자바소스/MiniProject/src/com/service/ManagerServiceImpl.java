package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.ManagerDAO;
import com.dto.ManagerDTO;

public class ManagerServiceImpl implements ManagerService {

	private ManagerDAO dao;
	
	@Override
	public void setDAO(ManagerDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<ManagerDTO> findAllMember() {
		
		List<ManagerDTO> memberlist = null; //리턴값의 변수 생성
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.getSession();
			
			// DAO 연동코드
			memberlist=dao.findAllMember(session);	
			
			} catch (Exception e) {
			e.printStackTrace();
			} finally {
			session.close();
			}
		
		return memberlist;
	}

	@Override
	public int deleteByMemberIdx(int member_idx) {
		int n = 0;
		SqlSession session = null;
	      try {
			session = MySqlSessionFactory.getSession();
			//DAO 연동코드
			n = dao.deleteByMemberIdx(session, member_idx);
			session.commit();
	      }finally {
			session.close();
	      }
		
		return n;
	}

	@Override
	public int updateMemberRole(ManagerDTO dto) {
		int n = 0;
		SqlSession session = null;
		try {
			session = MySqlSessionFactory.getSession();
			n = dao.updateMemberRole(session, dto);
			session.commit();
		}finally {
			session.close();
		}
		return n;
	}
	
}
