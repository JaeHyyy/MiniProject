package com.dao;

import org.apache.ibatis.session.SqlSession;

import com.dto.BookDTO;
import com.dto.MemberDTO;


public class MemberDAO {

    public int join(SqlSession session, MemberDTO dto) {
        return session.insert("com.config.MemberMapper.join", dto);
    }
    
    public int existsId(SqlSession session, String id) {
		return session.selectOne("com.config.MemberMapper.existsId", id);
    }
    
    public MemberDTO login(SqlSession session, MemberDTO dto) {
    	return session.selectOne("com.config.MemberMapper.login", dto);
    }
    
    public MemberDTO findUser(SqlSession session, MemberDTO dto) {
    	return session.selectOne("com.config.MemberMapper.findUser", dto);
    }
    
    public int changePw(SqlSession session, MemberDTO dto) {
    	return session.update("com.config.MemberMapper.changePw", dto);
    }
    
    public int userEdit(SqlSession session, MemberDTO dto) {
    	return session.update("com.config.MemberMapper.userEdit", dto);
    }
    
    public int deleteUser(SqlSession session, MemberDTO dto) {
    	return session.delete("com.config.MemberMapper.deleteUser", dto);
    }
    
    public MemberDTO selectByMemberIdx(SqlSession session, int member_idx) {
        return session.selectOne("com.config.MemberMapper.selectByMemberIdx", member_idx);
    }
}
