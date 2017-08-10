package com.eeit9728;

import java.sql.*;

public class HW1 {
	public static void main(String[] args) {
		Connection conn = null;

		try {
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
			conn = DriverManager.getConnection(connUrl, "sa", "P@ssw0rd");

			String qryStmt = "SELECT empno, salary FROM employee";
			PreparedStatement pstmt = conn.prepareStatement(qryStmt);
			ResultSet rs = pstmt.executeQuery(); // 回傳符合select指令的資料

			String updateStmt = "UPDATE employee SET salary = ? WHERE empno = ?";
			pstmt = conn.prepareStatement(updateStmt);

			conn.setAutoCommit(false);//交易開始
			final int batchsize = 3;
			int count = 0;

			while (rs.next()) {
				pstmt.setDouble(1, rs.getDouble(2) * 1.1);
				pstmt.setInt(2, rs.getInt(1));
				pstmt.addBatch();
				// count++;

				if (++count % batchsize == 0) { // 每三筆資料更新一次
					// if(count%batchsize==0){ }
					pstmt.executeBatch();
				}
			}
			pstmt.executeBatch(); // 未滿三筆 獨立更新
			conn.setAutoCommit(true);//交易結束
			
			
			qryStmt = "SELECT ename, salary FROM employee";
			pstmt = conn.prepareStatement(qryStmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("name = " + rs.getString("ename"));
				System.out.println("salary = " + rs.getDouble("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class BatchUpdateDemo
