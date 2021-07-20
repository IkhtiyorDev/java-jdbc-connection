package dev.ikhtiyor;

public class Book {

    private Integer id;
    private String name;
    private String author;
    private String pages;
    private String publishedDate;

    public Book() {
    }

    public Book(Integer id, String name, String author, String pages, String publishedDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.publishedDate = publishedDate;
    }

    public Book(String name, String author, String pages, String publishedDate) {
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.publishedDate = publishedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pages='" + pages + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                '}';
    }
}
