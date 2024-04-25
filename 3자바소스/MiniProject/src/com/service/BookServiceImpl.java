package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.BookDAO;
import com.dto.BookDTO;

public class BookServiceImpl implements BookService{

	private BookDAO dao;
	
	@Override
	public void setDAO(BookDAO dao) {
		this.dao = dao;
	}

	@Override
	public int createBook(BookDTO dto) {
		int n = 0;
		SqlSession session = null;
		try {
			session = MySqlSessionFactory.getSession();
			n = dao.create(session, dto);
			session.commit();
		}finally {
			session.close();
		}
		return n;
	}

	@Override
	public int updateBook(BookDTO dto) {
		int n = 0;
		SqlSession session = null;
		try {
			session = MySqlSessionFactory.getSession();
			n = dao.updateBook(session, dto);
			session.commit();
		}finally {
			session.close();
		}
		return n;
	}
	
	@Override
	public List<BookDTO> findAllBook() {
		
		List<BookDTO> booklist = null; //리턴값의 변수 생성
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.getSession();
			
			// DAO 연동코드
			booklist=dao.findAll(session);	
			
			} catch (Exception e) {
			e.printStackTrace();
			} finally {
			session.close();
			}
		
		return booklist;
	}

	@Override
	public int deleteByBookIdx(int book_idx) {
		int n = 0;
		SqlSession session = null;
	      try {
			session = MySqlSessionFactory.getSession();
			//DAO 연동코드
			n = dao.deleteByBookIdx(session, book_idx);
			session.commit();
	      }finally {
			session.close();
	      }
		
		return n;
	}

	@Override
	public BookDTO selectByBookIdx(int book_idx) {
	    BookDTO bookDTO = null;
	    SqlSession session = null;
	    try {
	        session = MySqlSessionFactory.getSession();
	        bookDTO = dao.selectByBookIdx(session, book_idx);
	    } finally {
	        session.close();
	    }
	    return bookDTO;
	}

	@Override
	public int updateBookRent(BookDTO bookDTO) {
	    int n = 0;
	    SqlSession session = null;
	    try {
	        session = MySqlSessionFactory.getSession();
	        n = dao.updateBookRent(session, bookDTO);
	        session.commit();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return n;
	}




	
}
