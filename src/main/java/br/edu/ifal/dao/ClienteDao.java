package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDao {

    public void save(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE VALUES (?,?,?,?);";

        try (Connection connection = ConnectionHelper.getConnection()) {
            try (PreparedStatement pst = connection.prepareStatement(sql)) {

                pst.setString(1, cliente.getCpf());
                pst.setString(2, cliente.getNome());
                pst.setString(3, cliente.getEndereco());
                pst.setString(4, cliente.getTelefone());

                pst.execute();
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
