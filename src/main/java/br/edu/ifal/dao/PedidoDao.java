package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    public int save(Pedido pedido) {
        String sql = "INSERT INTO PEDIDO (CPF_CLIENTE_FK, CPF_FUNCIONARIO_FK, VALOR_TOTAL) VALUES (?, ?, ?);";

        try (Connection connection = ConnectionHelper.getConnection()) {
            int id;
            try (PreparedStatement pst = connection.prepareStatement(sql)) {

                pst.setString(1, pedido.getCpfCliente());
                pst.setString(2, pedido.getCpfFuncionario());
                pst.setDouble(3, pedido.getValorTotal());

                pst.execute();

                pst.close();

                sql = "SELECT LAST_INSERT_ID()";

                try (PreparedStatement newPst = connection.prepareStatement(sql)) {
                    ResultSet rs = newPst.executeQuery();
                    rs.next();
                    id = rs.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return id;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pedido> findAll() {
        String sql = "SELECT * FROM PEDIDO;";

        List<Pedido> lista = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String cpfCliente = rs.getString("CPF_CLIENTE_FK");
                String cpfFuncionario = rs.getString("CPF_FUNCIONARIO_FK");
                double valorTotal = rs.getDouble("VALOR_TOTAL");

                Pedido pedido = new Pedido(id, cpfCliente, cpfFuncionario, valorTotal);
                lista.add(pedido);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}