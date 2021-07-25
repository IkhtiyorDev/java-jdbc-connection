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
    private final static String dbUrl = "jdbc:postgresql://localhost:5432/java-jdbc-connection";
    //Database bilan bog'lanishimiz uchun user nomi.
    private final static String dbUser = "postgres";
    //Database bilan bog'lanishimiz uchun parol.
    private final static String dbPassword = "root123";


    private final static String SAVE_BOOK_SQL_QUERY = "INSERT INTO book(name, author, pages, published_date) values('%s','%s','%s','%s');";
    private final static String GET_BOOKS_LIST_SQL_QUERY = "SELECT * FROM book;";
    private final static String DELETE_BOOK_SQL_QUERY = "DELETE FROM book WHERE id = %d";
    private final static String UPDATE_BOOK_SQL_QUERY = "UPDATE book SET name = '%s', pages = '%s', author = '%s', published_date = '%s' WHERE id = %d;";
    private final static String GET_BOOK_SQL_QUERY = "SELECT * FROM book WHERE id = %d;";

    public void saveBook(Book book) {
        try {
            // Bu yerda database bilan bog'lanish hosil qildik
            Connection connection = DriverManager.getConnection(
                    dbUrl,
                    dbUser,
                    dbPassword
            );

            Statement statement = connection.createStatement();

            String query = String.format(
                    SAVE_BOOK_SQL_QUERY,
                    book.getName(),
                    book.getAuthor(),
                    book.getPages(),
                    book.getPublishedDate());

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
                    dbUrl,
                    dbUser,
                    dbPassword
            );
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(GET_BOOKS_LIST_SQL_QUERY);

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

        try {
            Connection connection = DriverManager.getConnection(
                    dbUrl,
                    dbUser,
                    dbPassword
            );
            Statement statement = connection.createStatement();

            String query = String.format(
                    DELETE_BOOK_SQL_QUERY,
                    id);

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

            Book dbBook = getBook(id);

            String query = String.format(
                    UPDATE_BOOK_SQL_QUERY,
                    book.getName() != null ? book.getName() : dbBook.getName(),
                    book.getPages() != null ? book.getPages() : dbBook.getPages(),
                    book.getAuthor() != null ? book.getAuthor() : dbBook.getAuthor(),
                    book.getPublishedDate() != null ? book.getPublishedDate() : dbBook.getPublishedDate(),
                    id);

            statement.execute(query);
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public Book getBook(Integer bookId) {
        Book book = new Book();
        try {
            Connection connection = DriverManager.getConnection(
                    dbUrl,
                    dbUser,
                    dbPassword
            );

            String query = String.format(
                    GET_BOOK_SQL_QUERY,
                    bookId);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String pages = resultSet.getString("pages");
                String publishedDate = resultSet.getString("published_date");

                book = new Book(
                        id,
                        name,
                        author,
                        pages,
                        publishedDate
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }

}
