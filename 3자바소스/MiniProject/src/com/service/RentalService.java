package com.service;

import java.util.List;

import com.dao.RentalDAO;
import com.dto.RentalDTO;

public interface RentalService {

	public void setDao(RentalDAO dao);
	public List<RentalDTO> findAll();
	public List<RentalDTO> rentSearch();
	int rentAdd(RentalDTO dto);
	int rentUp(RentalDTO rentalDTO);
	int rentDelete(int rental_idx);
	public int findBookIdxByRentalIdx(int rental_idx);
}
