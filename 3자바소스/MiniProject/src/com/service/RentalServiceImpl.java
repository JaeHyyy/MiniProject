package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.RentalDAO;
import com.dto.RentalDTO;

public class RentalServiceImpl implements RentalService{

	private RentalDAO dao;
	
	public void setDao(RentalDAO dao) {
		this.dao = dao;
	}
	
	
	public List<RentalDTO> findAll(){
		List<RentalDTO> list = null; //리턴할려고 변수 지정
		
		SqlSession session = null;
		try {
		session = MySqlSessionFactory.getSession();
		//DAO 연동코드
		list = dao.findAll(session);
		}finally {
		session.close();
		}
		return list;
	}
	
	public List<RentalDTO> rentSearch(){
		List<RentalDTO> list = null; //리턴할려고 변수 지정
		
		SqlSession session = null;
		try {
		session = MySqlSessionFactory.getSession();
		//DAO 연동코드
		list = dao.findAll(session);
		}finally {
		session.close();
		}
		return list;
	}
	
	@Override
	public int rentAdd(RentalDTO dto) {
		
		int n = 0; // 리턴할려고 변수 지정
		
		SqlSession session = null;
		try {
		session = MySqlSessionFactory.getSession();
		//DAO 연동코드
			n = dao.rentAdd(session, dto);
			session.commit();
		}finally {
		session.close();
		}
		
		return n;
	}
	
	public int rentUp(RentalDTO rentalDTO) {
        SqlSession session = null;
        int result = 0;
        try {
            session = MySqlSessionFactory.getSession();
            result = dao.rentUp(session, rentalDTO);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }


	@Override
	public int rentDelete(int rental_idx) {
		int n = 0;
		SqlSession session = null;
	      try {
			session = MySqlSessionFactory.getSession();
			//DAO 연동코드
			n = dao.rentDelete(session, rental_idx);
			session.commit();
	      }finally {
			session.close();
	      }
		
		return n;
	}

	@Override
	public int findBookIdxByRentalIdx(int rental_idx) {
	    int bookIdx = 0;
	    SqlSession session = null;
	    try {
	        session = MySqlSessionFactory.getSession();
	        //DAO 연동코드
	        bookIdx = dao.findBookIdxByRentalIdx(session, rental_idx);
	    } finally {
	        session.close();
	    }
	    return bookIdx;
	}



	
}
