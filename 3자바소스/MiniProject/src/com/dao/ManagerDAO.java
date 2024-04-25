package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.ManagerDTO;

public class ManagerDAO {
	
	// 회원 전체 조회
	public List<ManagerDTO> findAllMember(SqlSession session){
		List<ManagerDTO> list =
				session.selectList("com.config.ManagerMapper.findAllMember");
		return list;
	}
	
	// 회원 탈퇴
	public int deleteByMemberIdx(SqlSession session, int member_idx) {
		return session.delete("com.config.ManagerMapper.deleteByMemberIdx",member_idx);
	}
	
	// 회원 등급 수정
	public int updateMemberRole(SqlSession session, ManagerDTO dto) {
		return session.update("com.config.ManagerMapper.updateMemberRole", dto);
	}
}
