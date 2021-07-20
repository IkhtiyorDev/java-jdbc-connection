package dev.ikhtiyor.statement;

import dev.ikhtiyor.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bu classda database bilan bog'lanib ma'lumotlar almashish uchun ishlatiladigan methodlarni yozamiz
 * Statement
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

            String query = "INSERT INTO book(name, author, pages, published_date) " +
                    "values('" + book.getName() + "','" + book.getAuthor() + "','" + book.getPages() + "','" + book.getPublishedDate() + "');";

            statement.execute(query);
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();

        try {
            // Bu yerda database bilan bog'lanish hosil qildik
            Connection connection = DriverManager.getConnection(
                    dbUrl, dbUser, dbPassword
            );
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM book;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String pages = resultSet.getString("pages");
                String publishedDate = resultSet.getString("published_date");

                Book book = new Book(
                        id,
                        name,
                        author,
                        pages,
                        publishedDate
                );

                bookList.add(book);
            }
            statement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookList;
    }

    public void deleteBook(Integer id) {
        // Bu yerda database bilan bog'lanish hosil qildik
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    dbUrl, dbUser, dbPassword
            );
            Statement statement = connection.createStatement();

            String query = "DELETE FROM book WHERE id =" + id + ";";

            statement.execute(query);
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void editBook(Integer id, Book book) {
        try {
            Connection connection = DriverManager.getConnection(
                    dbUrl,
                    dbUser,
                    dbPassword
            );
            Statement statement = connection.createStatement();
            String query = "UPDATE book set ";

            if (!book.getName().isEmpty()) {
                query = query + " name = '" + book.getName() + "',";
            } else if (!book.getPages().isEmpty()) {
                query = query + " pages = '" + book.getPages() + "',";
            } else if (!book.getAuthor().isEmpty()) {
                query = query + " author = '" + book.getAuthor() + "',";
            } else if (!book.getPublishedDate().isEmpty()) {
                query = query + " published_date = '" + book.getPublishedDate() + "',";
            }

            if (!query.equals("UPDATE book set ")) {
                if (query.endsWith(",")) {
                    query = query.substring(0, query.length() - 1);
                    query = query + "WHERE id=" + id + ";";
                    statement.execute(query);
                }
            }

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
