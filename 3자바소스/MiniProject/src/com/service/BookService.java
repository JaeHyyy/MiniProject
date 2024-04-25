package com.service;

import java.util.List;

import com.dao.BookDAO;
import com.dto.BookDTO;

public interface BookService {
	
	public void setDAO(BookDAO dao);
	public int createBook(BookDTO dto);
	public int updateBook(BookDTO dto);
	public List<BookDTO> findAllBook();
	public int deleteByBookIdx(int book_idx);
	public BookDTO selectByBookIdx(int book_idx);
	public int updateBookRent(BookDTO dto);
	
}
