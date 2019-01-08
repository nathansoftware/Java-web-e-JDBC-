package br.com.nathanap.jdbc;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.nathanap.jdbc.dao.CategoriasDAO;
import br.com.nathanap.jdbc.dao.ProdutosDAO;
import br.com.nathanap.jdbc.modelo.Categoria;
import br.com.nathanap.jdbc.modelo.Produto;

public class TestaCategorias
{
  public static void main(String[] args) throws SQLException
{
	 
	try (Connection con = new Database().getConnection())
	{
		List<Categoria> categorias = new CategoriasDAO(con).listaComProdutos();
		for (Categoria categoria : categorias)
		{
			System.out.println(categoria.getNome());
			for (Produto produto : categoria.getProdutos())
			{
				System.out.println(categoria.getNome() + " - " + produto.getNome());
			}
		}
		
		
	}
}
}
