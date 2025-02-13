package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.ItemPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDao {

    public void save(ItemPedido itemPedido) {
        String sql = "INSERT INTO ITEM_PEDIDO (ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR) VALUES (?, ?, ?, ?);";

        try (Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, itemPedido.getIdPedido());
            pst.setInt(2, itemPedido.getIdProduto());
            pst.setDouble(3, itemPedido.getQuantidade());
            pst.setDouble(4, itemPedido.getValor());

            pst.execute();

            pst.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemPedido> findAll() {
        String sql = "SELECT * FROM ITEM_PEDIDO;";
        List<ItemPedido> itens = new ArrayList<>();

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int idPedido = rs.getInt("ID_PEDIDO_FK");
                int idProduto = rs.getInt("ID_PRODUTO_FK");
                int quantidade = rs.getInt("QUANTIDADE");
                double valor = rs.getDouble("VALOR");

                ItemPedido item = new ItemPedido(id, idPedido, idProduto, quantidade, valor);
                itens.add(item);
            }

            rs.close();
            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return itens;
    }

    public ItemPedido findById(int id) {
        String sql = "SELECT * FROM ITEM_PEDIDO WHERE ID = ?;";
        ItemPedido itemPedido = null;

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int idPedido = rs.getInt("ID_PEDIDO_FK");
                int idProduto = rs.getInt("ID_PRODUTO_FK");
                int quantidade = rs.getInt("QUANTIDADE");
                double valor = rs.getDouble("VALOR");

                itemPedido = new ItemPedido(id, idPedido, idProduto, quantidade, valor);
            }

            rs.close();
            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return itemPedido;
    }

    public void update(ItemPedido itemPedido) {
        String sql = "UPDATE ITEM_PEDIDO SET ID_PEDIDO = ?, ID_PRODUTO = ?, QUANTIDADE = ?, VALOR = ? WHERE ID = ?;";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, itemPedido.getIdPedido());
            pst.setInt(2, itemPedido.getIdProduto());
            pst.setDouble(3, itemPedido.getQuantidade());
            pst.setDouble(4, itemPedido.getValor());
            pst.setInt(5, itemPedido.getId());

            pst.executeUpdate();

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM ITEM_PEDIDO WHERE ID = ?;";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}