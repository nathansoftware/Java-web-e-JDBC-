package br.com.nathanap.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem 
{
 public static void main(String[] args) throws SQLException
{
	Database database = new Database();
	  for (int i = 0; i < 100; i++)
	  {
	 Connection connection = database.getConnection();
	 Statement statement = connection.createStatement();
	 boolean resultado = statement.execute("select * from Produto");
	 ResultSet resultset = statement.getResultSet();
	 while(resultset.next())
	 {
	 String nome = resultset.getString("nome");
	 System.out.println(nome);
	 String descricao = resultset.getString("descricao");
	 System.out.println(descricao);
	 }
	 System.out.println("E ai Le, entendeu alguma coisa?");
	 resultset.close();
	 statement.close();
	 
	 connection.close();
	  }
}

private static Connection getConnection() throws SQLException {
	Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual","SA","");
	return connection;
}
}
