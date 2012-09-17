package com.tenx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		execute(new CallBack() {
			
			public void doItinConnection(Connection connection) throws SQLException {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from book");
				while(resultSet.next()){
					System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+
							resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5));
				}
			}
		});

	}
	
	interface CallBack{
		void doItinConnection(Connection connection) throws SQLException;
	}
	
	static void execute(CallBack callBack){
		Connection connection = null ;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb","root","sa");
			callBack.doItinConnection(connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if ( connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

}
