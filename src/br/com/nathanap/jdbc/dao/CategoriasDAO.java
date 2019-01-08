package br.com.nathanap.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.nathanap.jdbc.modelo.Categoria;
import br.com.nathanap.jdbc.modelo.Produto;

public class CategoriasDAO
{
    private final Connection con;
	public CategoriasDAO(Connection con)
	{
		this.con = con;
	}
	public List<Categoria> lista() throws SQLException
	{
		List<Categoria> categorias = new ArrayList<>();
		String sql = "select * from Categoria";
		try (PreparedStatement preparedStatement = con.prepareStatement(sql))
		{
			preparedStatement.execute();
			
			try (ResultSet rs = preparedStatement.getResultSet())
			{
				while (rs.next())
				{
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					Categoria categoria = new Categoria (id, nome);
					categorias.add(categoria);
					
				}
			}
		}
		return categorias;
	}
	public List<Categoria> listaComProdutos() throws SQLException
	{
		List<Categoria> categorias = new ArrayList<>();
		Categoria ultima = null;
		String sql = "select * from Categoria as c join Produto as p on p.categoria_id = c.id";
		try (PreparedStatement preparedStatement = con.prepareStatement(sql))
		{
			preparedStatement.execute();
			
			try (ResultSet rs = preparedStatement.getResultSet())
			{
				while (rs.next())
				{
					int id = rs.getInt("c_id");
					String nome = rs.getString("c_nome");
					if(ultima==null || !ultima.getNome().equals(nome))
					{
						Categoria categoria = new Categoria (id, nome);
						categorias.add(categoria);
						ultima = categoria;
					}
					String nomeDoProduto = rs.getString("p_nome");
					String descricaoDoProduto = rs.getString("p_descricao");
					int idDoProduto = rs.getInt("p_id");
					Produto p = new Produto(nomeDoProduto, descricaoDoProduto);
					p.setId(idDoProduto);
					ultima.adiciona(p);
					
					
				}
			}
		}
		return categorias;
	}
  
}
