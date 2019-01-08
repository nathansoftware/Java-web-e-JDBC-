package br.com.nathanap.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.nathanap.jdbc.dao.ProdutosDAO;
import br.com.nathanap.jdbc.modelo.Produto;

public class TestaDAODeProduto 
{
   
  public static void main(String[] args) throws SQLException 
  {
	  Produto mesa = new Produto("Mesa Azul", "Mesa com 4 pés de madeira");
	 try (Connection con = new Database().getConnection())
	 {
		ProdutosDAO dao = new ProdutosDAO(con);
		dao.salva(mesa);
		List<Produto> produtos = dao.lista();
		for (Produto produto : produtos)
		{
			System.out.println("Existe o produto: "+produto);
			
		}
	 }
	 
	 
	
  }


}
	

