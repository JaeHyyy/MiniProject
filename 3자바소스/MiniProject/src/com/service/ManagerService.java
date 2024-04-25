package com.service;

import java.util.List;

import com.dao.ManagerDAO;
import com.dto.ManagerDTO;

public interface ManagerService {

	public void setDAO(ManagerDAO dao);
	public List<ManagerDTO> findAllMember();
	public int deleteByMemberIdx(int member_idx);
	public int updateMemberRole(ManagerDTO dto);
}
