package br.com.nathanap.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao
{
 public static void main(String[] args) throws SQLException
{
    
    try ( Connection connection = new Database().getConnection())
    {
     connection.setAutoCommit(false);
     
          String sql = "insert into Produto (nome, descricao) values(?, ?)";
	
	      try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
	      {
	       adiciona("MacBook", "Usado e com 50% de desconto", statement);
	       connection.commit();
	      adiciona("Notebook da Dell", "Usado com problemas nas teclas e com 90% de desconto", statement);
	   
     } catch (Exception e) 
     {
    	 e.printStackTrace();
		connection.rollback();
	}
     
    }

}

private static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
	if(nome.equals("Notebook da Dell"))
	{
		throw new IllegalArgumentException("Problema ocorrido.");
	}

		statement.setString(1, nome);
	 statement.setString(2, descricao);
	 boolean resultado = statement.execute();
	 System.out.println(resultado);
	 
	 ResultSet resultSet = statement.getGeneratedKeys();
	 while(resultSet.next())
	 {
		 String id = resultSet.getString("id");
		 System.out.println(id + " gerado");
		 
		 
	 }
	 
	 resultSet.close();
}
}
