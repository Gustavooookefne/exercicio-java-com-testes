package org.example.repository;

import org.example.model.Produto;
import org.example.util.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class ProdutoRepositoryImpl implements ProdutoRepository{

    @Override
    public Produto save(Produto produto) throws SQLException{

        Produto produtoPersistency = null;

        String querySave = """
                INSERT INTO produto (nome, preco, quantidade, categoria) VALUES (?, ?, ?, ?);
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(querySave, RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    int id = rs.getInt(1);

                    produtoPersistency = new Produto(
                            id, produto.getNome(),
                            produto.getPreco(),
                            produto.getQuantidade(),
                            produto.getCategoria()
                    );
                }
            }
        }

        return produtoPersistency;
    }

    @Override
    public List<Produto> findAll() throws SQLException{

        List<Produto> produtos = new ArrayList<>();

        String query = """
                SELECT id,nome,preco,quantidade,categoria FROM produto
                """;

        try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next());

            Produto produto = new Produto(

                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("Quatidade"),
                    rs.getString("categoria")
            );
            produtos.add(produto);
        }
        return produtos;
    }



    @Override
    public Produto findById(int id) throws SQLException{
        String query = """
                SELECT id ,nome ,preco ,quantidade ,categoria FROM produto WHERE id = ?;
                """;

        try(Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {

                return new Produto(

                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("preco"),
                        rs.getInt("quantidade"),
                        rs.getString("categoria")
                );
            }
        }
        return null;

    }

    @Override
    public Produto update(Produto produto) throws SQLException{
       String query = """
               UPDATE produto SET nome = ? ,preco = ? ,quantidade = ? ,categoria = ? WHERE id = ?;
               """;

       try(Connection conn = ConexaoBanco.conectar();
       PreparedStatement stmt = conn.prepareStatement(query)){

           stmt.setString(1,produto.getNome());
           stmt.setDouble(2,produto.getPreco());
           stmt.setInt(3,produto.getQuantidade());
           stmt.setString(4,produto.getCategoria());

           stmt.setInt(5, produto.getId());
           stmt.executeUpdate();
       }
       return produto;
    }

    @Override
    public boolean deleteById(int id) throws SQLException{
        String query = """
                DELETE FROM produto WHERE id = ?;
                """;

        try(Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,id);

            int rs = stmt.executeUpdate();

            if(rs > 0){
                return true;
            }
        }
        return false;

    }
}
