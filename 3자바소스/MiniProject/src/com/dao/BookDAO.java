package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.BookDTO;

public class BookDAO {

	// 도서 전체 조회
	public List<BookDTO> findAll(SqlSession session) {
		List<BookDTO> list = session.selectList("com.config.BookMapper.findAllBook");
		return list;
	}

	// 도서 추가
	public int create(SqlSession session, BookDTO dto) {
		return session.insert("com.config.BookMapper.createBook", dto);
	}

	// 도서 정보 수정
	public int updateBook(SqlSession session, BookDTO dto) {
		return session.update("com.config.BookMapper.updateBook", dto);
	}

	// 도서 삭제
	public int deleteByBookIdx(SqlSession session, int book_idx) {
		return session.delete("com.config.BookMapper.deleteByBookIdx", book_idx);
	}
	
	// 책 번호로 책 조회
    public BookDTO selectByBookIdx(SqlSession session, int book_idx) {
        return session.selectOne("com.config.BookMapper.selectByBookIdx", book_idx);
    }
    
 // 도서 정보 수정
 	public int updateBookRent(SqlSession session, BookDTO dto) {
 		return session.update("com.config.BookMapper.updateBookRent", dto);
 	}
}
