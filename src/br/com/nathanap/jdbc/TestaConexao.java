package br.com.nathanap.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao
{
 public static void main(String[] args) throws SQLException
 
{
	 
	 Connection connection = new Database().getConnection();
	 System.out.println("Abrindo uma conexão com sucesso!");
	 connection.close();
	
}
}
