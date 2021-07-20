package dev.ikhtiyor.prepareStatement;

import dev.ikhtiyor.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bu classda database bilan bog'lanib ma'lumotlar almashish uchun ishlatiladigan methodlarni yozamiz
 * PreparedStatement
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

            String query = "INSERT INTO book(name, author, pages, published_date) values(?,?,?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPages());
            preparedStatement.setString(4, book.getPublishedDate());

            preparedStatement.executeUpdate();
            preparedStatement.close();
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

            String query = "SELECT * FROM book;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

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
            preparedStatement.close();
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

            String query = "DELETE FROM book WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
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
            String query = "UPDATE book set ";

            if (!book.getName().isEmpty()) {
                query = query + " name = ?,";
            } else if (!book.getPages().isEmpty()) {
                query = query + " pages = ?,";
            } else if (!book.getAuthor().isEmpty()) {
                query = query + " author = ?,";
            } else if (!book.getPublishedDate().isEmpty()) {
                query = query + " published_date = ?,";
            }

            if (!query.equals("UPDATE book set ")) {
                if (query.endsWith(",")) {
                    query = query.substring(0, query.length() - 1);
                }
                query = query + "WHERE id= ? ;";

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getPages());
                preparedStatement.setString(3, book.getAuthor());
                preparedStatement.setString(4, book.getPublishedDate());
                preparedStatement.setInt(5, id);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
