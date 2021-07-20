package dev.ikhtiyor.statement;

import dev.ikhtiyor.Book;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static String menu = "0 => Chiqish, 1 => Kitob qo'shish, 2 => Kitobni tahrirlash, 3 => Kitoblar ro'yxati, 4 => Kitobni o'chirish";

    public static void main(String[] args) {
        ConnectionService connectionService = new ConnectionService();
        Scanner scanner = new Scanner(System.in);
        boolean bool = true;

        while (bool) {
            System.out.println(menu);
            int step = scanner.nextInt();
            switch (step) {
                case 0:
                    bool = false;
                    break;
                case 1:
                    scanner = new Scanner(System.in);
                    saveBook(scanner, connectionService);
                    break;
                case 2:
                    scanner = new Scanner(System.in);
                    getBookList(connectionService);
                    editBook(connectionService, scanner);
                    break;
                case 3:
                    getBookList(connectionService);
                    break;
                case 4:
                    scanner = new Scanner(System.in);
                    getBookList(connectionService);
                    deleteBook(connectionService, scanner);
                    break;
            }
        }
    }

    public static void saveBook(Scanner scanner, ConnectionService connectionService) {
        try {
            System.out.println("Kitob nomini kiriting");
            String name = scanner.nextLine();
            System.out.println("Kitob aftorini kiriting");
            String author = scanner.nextLine();
            System.out.println("Kitob betlar sonini kiriting");
            String pages = scanner.nextLine();
            System.out.println("Kitob chiqqan yilini kiriting");
            String publishedDate = scanner.nextLine();

            Book book = new Book(
                    name,
                    author,
                    pages,
                    publishedDate
            );
            connectionService.saveBook(book);
            System.out.println("Ma'lumot saqlandi");
        } catch (Exception e) {
            System.err.println("Xatolik");
        }
    }

    public static void getBookList(ConnectionService connectionService) {
        List<Book> bookList = connectionService.getBookList();
        for (Book book : bookList) {
            System.out.println(book);
        }
        System.out.println();
    }

    public static void deleteBook(ConnectionService connectionService, Scanner scanner) {
        try {
            System.out.println("O'chirmoqchi bo'lgan kitob id sini kiriting");
            int id = scanner.nextInt();
            connectionService.deleteBook(id);
            System.out.println("Ma'lumot o'chirildi");
        } catch (Exception e) {
            System.err.println("Xatolik");
        }

    }

    public static void editBook(ConnectionService connectionService, Scanner scanner) {
        try {
            System.out.println("Tahrirlamoqchi bo'lgan kitobingiz id sini kiriting");
            int id = scanner.nextInt();
            scanner = new Scanner(System.in);

            System.out.println("Kitob nomini kiriting");
            String name = scanner.nextLine();
            System.out.println("Kitob aftorini kiriting");
            String author = scanner.nextLine();
            System.out.println("Kitob betlar sonini kiriting");
            String pages = scanner.nextLine();
            System.out.println("Kitob chiqqan yilini kiriting");
            String publishedDate = scanner.nextLine();

            Book book = new Book(
                    name,
                    author,
                    pages,
                    publishedDate
            );
            connectionService.editBook(id, book);
            System.out.println("Tahrirlandi");
        } catch (Exception e) {
            System.err.println("Xatolik");
        }
    }
}
