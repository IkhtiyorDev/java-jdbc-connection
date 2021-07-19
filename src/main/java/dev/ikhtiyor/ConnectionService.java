package dev.ikhtiyor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Bu classda database bilan bog'lanib ma'lumotlar almashish uchun ishlatiladigan methodlarni yozamiz
 */
public class ConnectionService {

    //Database bilan bog'lanishimiz uchun url. Bunda { java-jdbc-connection } database nomi
    private String dbUrl = "jdbc:postgresql://localhost:5432/java-jdbc-connection";
    //Database bilan bog'lanishimiz uchun user nomi.
    private String dbUser = "postgres";
    //Database bilan bog'lanishimiz uchun parol.
    private String dbPassword = "root123";

    public void saveBook(Book book) {
        try {
            // Bu yerda database bilan bog'lanish hosil qildik
            Connection connection = DriverManager.getConnection(
                    dbUrl, dbUser, dbPassword
            );

            Statement statement = connection.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
