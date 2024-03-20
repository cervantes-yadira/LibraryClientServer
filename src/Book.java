public class Book {

    private int bookID;
    private String bookTitle;

    Book(int bookID, String title) {
        this.bookID = bookID;
        this.bookTitle = title;
    }

    /**
     * Retrieves the book ID.
     * @return the ID of the book
     */
    public int getID() {
        return this.bookID;
    }

    /**
     * Retrieves the title.
     * @return the title of the book
     */
    public String getTitle() {
        return this.bookTitle;
    }
}
