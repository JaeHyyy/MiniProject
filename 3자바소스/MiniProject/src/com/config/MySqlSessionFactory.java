package com.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {
  
	static SqlSessionFactory sqlSessionFactory;
	static {
		String resource = "com/config/Configuration.xml";
		  InputStream inputStream=null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}//end static 블럭
	
	// SqlSessionFactory 로부터 SqlSession 얻는 메서드
	public static SqlSession getSession() {
		// MyBatis는 명시적으로 commit 지정해야 된다.
		SqlSession session = sqlSessionFactory.openSession(); // openSession(false) 동일
		return session;
	}
	
	
	
}





