package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.ItemPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemPedidoDao {

    public void save(ItemPedido itemPedido) {
        String sql = "INSERT INTO ITEM_PEDIDO (ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR) VALUES (?, ?, ?, ?);";

        try (Connection connection = ConnectionHelper.getConnection()) {
            try (PreparedStatement pst = connection.prepareStatement(sql)) {

                pst.setInt(1, itemPedido.getIdPedido());
                pst.setInt(2, itemPedido.getIdProduto());
                pst.setDouble(3, itemPedido.getQuantidade());
                pst.setDouble(4, itemPedido.getValor());

                pst.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}