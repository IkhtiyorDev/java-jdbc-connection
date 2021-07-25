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
    private final static String dbUrl = "jdbc:postgresql://localhost:5432/java-jdbc-connection";
    //Database bilan bog'lanishimiz uchun user nomi.
    private final static String dbUser = "postgres";
    //Database bilan bog'lanishimiz uchun parol.
    private final static String dbPassword = "root123";


    private final static String SAVE_BOOK_SQL_QUERY = "INSERT INTO book(name, author, pages, published_date) values(?,?,?,?);";
    private final static String GET_BOOKS_LIST_SQL_QUERY = "SELECT * FROM book;";
    private final static String DELETE_BOOK_SQL_QUERY = "DELETE FROM book WHERE id = ?;";
    private final static String UPDATE_BOOK_SQL_QUERY = "UPDATE book SET  name = ?, pages = ?, author = ?, published_date = ? WHERE id= ?;";
    private final static String GET_BOOK_SQL_QUERY = "";

    public void saveBook(Book book) {
        try {
            // Bu yerda database bilan bog'lanish hosil qildik
            Connection connection = DriverManager.getConnection(
                    dbUrl, dbUser, dbPassword
            );

            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_BOOK_SQL_QUERY);
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

            PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOKS_LIST_SQL_QUERY);
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

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK_SQL_QUERY);
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

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_SQL_QUERY);

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getPages());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getPublishedDate());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
