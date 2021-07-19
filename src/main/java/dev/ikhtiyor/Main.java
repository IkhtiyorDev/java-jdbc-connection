package dev.ikhtiyor;

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
            scanner = new Scanner(System.in);
            switch (step) {
                case 0:
                    bool = false;
                    break;

                case 1:
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
                    break;
                case 2:

                    System.out.println(step);
                    break;

                case 3:
                    System.out.println(step);
                    break;

                case 4:
                    System.out.println(step);
                    break;
            }
        }
    }
}
