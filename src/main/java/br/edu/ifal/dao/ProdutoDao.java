package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    public void save(Produto produto) {
        String sql = "INSERT INTO PRODUTO (NOME, VALOR_UNIT, QUANTIDADE) VALUES (?, ?, ?);";

        try (Connection connection = ConnectionHelper.getConnection()) {
            try (PreparedStatement pst = connection.prepareStatement(sql)) {

                pst.setString(1, produto.getNome());
                pst.setDouble(2, produto.getValorUnit());
                pst.setDouble(3, produto.getQuantidade());

                pst.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> findAll() {
        String sql = "SELECT * FROM PRODUTO;";

        List<Produto> lista = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("NOME");
                double valorUnit = rs.getDouble("VALOR_UNIT");
                double quantidade = rs.getDouble("QUANTIDADE");

                Produto produto = new Produto(id, nome, valorUnit, quantidade);
                lista.add(produto);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public Produto findById(int id) {
        String sql = "SELECT * FROM PRODUTO WHERE ID = ?;";
        Produto produto = null;

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NOME");
                double valorUnit = rs.getDouble("VALOR_UNIT");
                double quantidade = rs.getDouble("QUANTIDADE");

                produto = new Produto(id, nome, valorUnit, quantidade);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return produto;
    }

    public void update(Produto produto) {
        String sql = "UPDATE PRODUTO SET NOME = ?, VALOR_UNIT = ?, QUANTIDADE = ? WHERE ID = ?;";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, produto.getNome());
            pst.setDouble(2, produto.getValorUnit());
            pst.setDouble(3, produto.getQuantidade());
            pst.setInt(4, produto.getId());

            pst.executeUpdate();

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}