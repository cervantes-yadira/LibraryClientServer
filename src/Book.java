public class Book {

    private int bookID;
    private String bookTitle;

    Book(int bookID, String title) {
        this.bookID = bookID;
        this.bookTitle = title;
    }

    public int getID() {
        return this.bookID;
    }

    public String getTitle() {
        return this.bookTitle;
    }
}
