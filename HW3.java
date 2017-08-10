package com.eeit9728;

import java.sql.*;
import java.io.*;

public class HW3 {
	public static void main(String[] args) {
		Connection conn = null;

		try {     
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
			conn = DriverManager.getConnection(connUrl, "sa", "P@ssw0rd");
			
			String qryStmt = "SELECT photo FROM employee WHERE photo = ?,empno = ?";
			PreparedStatement stmt = conn.prepareStatement(qryStmt);

			
			String insertStmt = "UPDATE employee SET photo = ? WHERE empno = ?";
			stmt = conn.prepareStatement(insertStmt);
			
			for(int i =1001;i<=1010;i++){
				
				File f = new File("res/"+i+".jpg");
				FileInputStream fis = new FileInputStream(f);
				
				stmt.setBinaryStream(1, fis, f.length());
				stmt.setInt(2,i);
				stmt.addBatch();	
				stmt.executeBatch();
			}
			
				
			
	

			

			 // end of if (rs.next()) 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class BLOBDemo 
