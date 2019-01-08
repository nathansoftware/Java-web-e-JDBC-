package br.com.nathanap.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.nathanap.jdbc.modelo.Categoria;
import br.com.nathanap.jdbc.modelo.Produto;

public class ProdutosDAO
{
    private final Connection con;
	public ProdutosDAO(Connection con)
	{
		// TODO Auto-generated constructor stub
		this.con = con;
	}
	public  void salva(Produto produto) throws SQLException {
		String sql = "insert into Produto (nome, descricao) values (?,?)";
		 try (PreparedStatement preparedstatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
		 {
			 preparedstatement.setString(1, produto.getNome());
			 preparedstatement.setString(2, produto.getDescricao());
			 preparedstatement.execute();
			 
			 try (ResultSet rs = preparedstatement.getGeneratedKeys())
			 {
				 if(rs.next())
				 {
					 
				 
				int id =  rs.getInt("id");
				produto.setId(id);
				 }
			 }
		 }
	

	}
	public List<Produto> lista() throws SQLException 
	{
		List<Produto> produtos = new ArrayList<>();
		
		String sql = "select * from Produto";
		try (PreparedStatement preparedStatement = con.prepareStatement(sql))
		{
			preparedStatement.execute();
			transformaResultadoEmProdutos(produtos, preparedStatement);
		}
		return produtos;
	}
	private void transformaResultadoEmProdutos(List<Produto> produtos, PreparedStatement preparedStatement)
			throws SQLException {
		try (ResultSet rs = preparedStatement.getResultSet())
		{
			while (rs.next())
			{
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Produto produto = new Produto (nome, descricao);
				produto.setId(id);
				produtos.add(produto);
				
				
				
			}
		}
	}
	
	public List<Produto> busca(Categoria categoria) throws SQLException
	{
  List<Produto> produtos = new ArrayList<>();
		
		String sql = "select * from Produto where categoria_id = ?";
		try (PreparedStatement preparedStatement = con.prepareStatement(sql))
		{
			preparedStatement.setInt(1, categoria.getId());
			preparedStatement.execute();
			transformaResultadoEmProdutos(produtos, preparedStatement);
		}
		return produtos;           
	}
}
