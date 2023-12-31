package com.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionMgr {
	/*선언부*/
	static DBConnectionMgr dbMGR = null;
	Connection con                        = null;  // java.sql.Connection -> 특정 데이터베이스와의 연결
	PreparedStatement pstmt           = null;  //java.sql.PreparedStatement -> 미리 컴파일된 SQL 문
	ResultSet rs                              = null;  //java.sql.ResultSet
	public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String _URL= "jdbc:oracle:thin:@127.0.0.1:1521:orcl11";
	public static final String _USER = "scott";
	public static final String _PW = "tiger";
	/*정의메소드*/
	//Calendar 클래스 처럼 메소드를 통해 객체 주입 받기
	//메소드를 통해 객체생성을 하므로 if문 사용해서 조건별 객체 생성이 가능해짐. 
	public static DBConnectionMgr getInstance() {
		//DB연결이 없는 null값이면 객체를 생성하게 하는 메소드
		if(dbMGR ==null) dbMGR = new DBConnectionMgr();
		return dbMGR;
	}
	//리턴타입으로 연결통로를 확보한 con을 얻는다
	//Connection(물리적으로 떨어져있는 것 사이 연결통로 확보, url 계정 정보 일치) 객체로 
	//앞에 객체가 주입되지 않으면 나머지 뒤에는 모두 null인 상태에 놓인다. 
	public Connection getConnection()	{
		/*예외처리 시 사용되는 try catch
		 *멀티블록이 가능함. 단, 하위에서 상위클래스로 처리함 */
		//각 제조사의 드라이버 클래스를 로딩하기 = ojdbc6.jar 문자열로서 객체 주입 받아냄. - 둘을 연결하기 위해 인터페이스 필요
		//인터페이스는 제조사가 제공해야 한다. 노출 시 핵심기술 유출됨. 
		try { //아래의 DB를 찾아서 연결해줘
			Class.forName("oracle.jdbc.driver.OracleDriver");  //java.lang.Class<T> -> java reflection API 공부, F/W만들 수 있음. (https://jeongkyun-it.tistory.com/225)
			//getConnection throws SQLException(classnotFoundException 나올 경우, 빌드패스하지 않은 것(~jar파일)
			//try catch를 통해 사이드 이펙트가 발생하지 않도록 예방하는 코드 신뢰도 ->지변, 람다식, 화살표함수 등 사용
 			con = DriverManager.getConnection(_URL,_USER,_PW); //파라미터값(String url, String user, String password)
		/*그런데!! 혹시 클래스를 못 찾을 때*/
		}catch (ClassNotFoundException e) { //클래스 못 찾을 때 
			System.out.println("ojdbc6.jar를 설정하지 않았다. 그래서 클래스를 못 찾는다.");
			/*그런데!! 혹시 비번이 맞지 않을 때*/
		}catch (Exception e) {                     //비번이 맞지 않을 때 
			e.printStackTrace();
		}
		return con;
	}
	public static void freeConnection(ResultSet rs, PreparedStatement pstmt, Connection con){
		try {
			if(rs !=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*위 코드에서 22번과 24번 호출 시 문제가 없다면 catch문은 사용x */
	public static void freeConnection(PreparedStatement pstmt, Connection con){
		try {
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void freeConnection(ResultSet rs, CallableStatement cstmt, Connection con){
		try {
			if(rs !=null) rs.close();
			if(cstmt !=null) cstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void freeConnection(CallableStatement cstmt, Connection con){
		try {
			if(cstmt !=null) cstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

