package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;

public class TestPowerOutagesDAO {
	
	static PowerOutageDAO dao;
	
	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			dao = new PowerOutageDAO() ;
			System.out.println(dao.selecData(6)) ;

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}

	}

}
