package com.service;

import com.config.MySqlSessionFactory;
import com.dao.MemberDAO;
import com.dto.MemberDTO;

import org.apache.ibatis.session.SqlSession;

public class MemberServiceImpl implements MemberService {

    private MemberDAO dao;

    public void setDao(MemberDAO dao) {
        this.dao = dao;
    }

    @Override
    public int join(MemberDTO dto) {
        SqlSession session = null;
        int result = 0;

        try {
            session = MySqlSessionFactory.getSession();
            
            result = dao.join(session, dto);
            session.commit();
        }finally {
        	if(session != null) {
        		session.close();
        	}
        }
        return result;
    }

	@Override
	public int existsId(String id) {
		SqlSession session = null;
		int valiId = 0;
		try {
            session = MySqlSessionFactory.getSession();
            valiId = dao.existsId(session, id);
        } finally {
        	if(session != null) {
        		session.close();
        	}
        }
		
		return valiId;
	}
    
	@Override
	public MemberDTO login(MemberDTO dto) {
		
		SqlSession session = null;
		MemberDTO member = new MemberDTO();
		
        try {
        	
            session = MySqlSessionFactory.getSession();
            member = dao.login(session, dto);
            session.commit();
            
        } finally {
        	if(session != null) {
        		session.close();
        	}
        }
		return member;
	}
	

	@Override
	public MemberDTO findUser(MemberDTO dto) {
		
		SqlSession session = null;
		MemberDTO member = new MemberDTO();
		
        try {
        	
            session = MySqlSessionFactory.getSession();
            member = dao.findUser(session, dto);
            session.commit();
            
        } finally {
        	if(session != null) {
        		session.close();
        	}
        }
		return member;
	}

	@Override
	public int changePw(MemberDTO dto) {
		SqlSession session = null;
		int result = 0;
		
        try {
        	
            session = MySqlSessionFactory.getSession();
            result = dao.changePw(session, dto);
            session.commit();
            
        } finally {
        	if(session != null) {
        		session.close();
        	}
        }
		return result;
	}

	@Override
	public int userEdit(MemberDTO dto) {
		SqlSession session = null;
        int result = 0;

        try {
            session = MySqlSessionFactory.getSession();
            
            result = dao.userEdit(session, dto);
            session.commit();
        }finally {
        	if(session != null) {
        		session.close();
        	}
        }
        return result;
	}

	@Override
	public int deleteUser(MemberDTO dto) {
		SqlSession session = null;
        int result = 0;

        try {
            session = MySqlSessionFactory.getSession();
            
            result = dao.deleteUser(session, dto);
            session.commit();
        }finally {
        	if(session != null) {
        		session.close();
        	}
        }
        return result;
	}

	@Override
	public MemberDTO selectByMemberIdx(int member_idx) {
	    MemberDTO memberDTO = null;
	    SqlSession session = null;
	    try {
	        session = MySqlSessionFactory.getSession();
	        memberDTO = dao.selectByMemberIdx(session, member_idx);
	    } finally {
	        session.close();
	    }
	    return memberDTO;
	}


}
