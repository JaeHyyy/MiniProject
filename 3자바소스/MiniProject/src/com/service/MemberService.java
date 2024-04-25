package com.service;

import com.dao.MemberDAO;
import com.dto.MemberDTO;

public interface MemberService {

    public void setDao(MemberDAO dao);

    public int join(MemberDTO dto);
    
    public int existsId(String id);
    
    public MemberDTO login(MemberDTO dto);
    
    public MemberDTO findUser(MemberDTO dto);
    
    public int changePw(MemberDTO dto);
    
    public int userEdit(MemberDTO dto);
    
    public int deleteUser(MemberDTO dto);
    
	public MemberDTO selectByMemberIdx(int Member_idx);

}
