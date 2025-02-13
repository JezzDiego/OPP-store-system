package br.edu.ifal;

import br.edu.ifal.dao.ClienteDao;
import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Cliente;
import br.edu.ifal.domain.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu.start();
    }
}
