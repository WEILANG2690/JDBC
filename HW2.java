package com.eeit9728;


import java.sql.*;
import java.io.*;

public class HW2 {
	public static void main(String[] args) throws IOException {
		Connection conn = null;
		
		FileReader fr = new FileReader("res/emp.txt");
        BufferedReader br =new BufferedReader(fr);
        String strLine  ;
        br.readLine();
//      final int batchsize = 3;
//		int count = 0;
		
		try {     
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
			conn = DriverManager.getConnection(connUrl, "sa", "P@ssw0rd");
			
//			String qryStmt = "SELECT * FROM employee";
//			ResultSet rs = pstmt.executeQuery();
			
			String insStmt = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt = conn.prepareStatement(insStmt);
			
			while ((strLine=br.readLine())!=null) {
				String[] array=strLine.split(",");
				System.out.println(strLine);
				for(int i=0;i<array.length;i++){
				}		
				
				pstmt.setInt(1,Integer.valueOf(array[0]));
				pstmt.setString(2,array[1]);
				pstmt.setDate(3,Date.valueOf(array[2]));			
				pstmt.setString(4,array[3]);
				pstmt.setInt(5,Integer.valueOf(array[4]));
				pstmt.setString(6,array[5]);
				pstmt.addBatch();			
			    pstmt.executeBatch();
			   
			}
			 
//			String qryStmt = "SELECT * FROM employee";
//		    qryStmt = "SELECT ename, salary FROM employee";
//			pstmt = conn.prepareStatement(qryStmt);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				System.out.println("name = " + rs.getString("ename"));
//				System.out.println("salary = " + rs.getDouble("salary"));
//			}
		} catch (SQLException e) {
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
}// end of class BatchUpdateDemo
