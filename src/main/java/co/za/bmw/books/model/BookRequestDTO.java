package co.za.bmw.books.model;

public class BookRequestDTO {
    private long id;
    private  String bookName;
    private String author;

    public BookRequestDTO() {
    }

    public BookRequestDTO(String bookName, String author, long price) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }

    public BookRequestDTO(long id) {
        this.id = id;
    }

    private long price;

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
