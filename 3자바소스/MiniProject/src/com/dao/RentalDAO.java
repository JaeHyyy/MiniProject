package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.RentalDTO;

public class RentalDAO {

	public List<RentalDTO> findAll(SqlSession session) {
		List<RentalDTO> list = session.selectList("com.config.RentalMapper.findAll");
		return list;
	}

	public List<RentalDTO> rentSearch(SqlSession session) {
		List<RentalDTO> list = session.selectList("com.config.RentalMapper.rentSearch");
		return list;
	}

	public int rentAdd(SqlSession session, RentalDTO dto) {

		return session.insert("com.config.RentalMapper.rentAdd", dto);
	}

	public int rentUp(SqlSession session, RentalDTO rentalDTO) {
		return session.update("com.config.RentalMapper.rentUp", rentalDTO);
	}

	public int rentDelete(SqlSession session, int rental_idx) {
		return session.update("com.config.RentalMapper.rentDelete", rental_idx);
	}

	public int findBookIdxByRentalIdx(SqlSession session, int rental_idx) {
		return session.selectOne("com.config.RentalMapper.findBookIdxByRentalIdx", rental_idx);
	}

	public RentalDTO selectByRentalIdx(SqlSession session, int rentalIdx) {
		return session.selectOne("com.config.RentalMapper.selectByRentalIdx", rentalIdx);
	}
}
