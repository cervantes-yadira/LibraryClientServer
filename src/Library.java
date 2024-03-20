import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Library {
    //fields
    private Book[] books;
    private int size;
    private Lock getBookLock;

    // constructor
    public Library() {
        this.books = new Book[10];
        this.size = 0;
        getBookLock = new ReentrantLock();
    }

    // methods
    public void viewCollection() {
        int i = 0;

        while(books[i] != null) {
            System.out.print(books[i].getTitle() + " ");
            i++;
        }
    }

    public boolean hasBook(Book b){
        for (Book book : books) {
            if (book == b) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a book to the first empty space in books[]
     * if the array isn't at capacity
     * @param b a book that is added to books[]
     */
    public void add(Book b) {
        getBookLock.lock();
        try {
            if (this.size != this.books.length) {
                int i = 0;

                while(books[i] != null) {
                    i++;
                }

                books[i] = b;
                size++;
            } else {
                System.out.println("Library is full!");
            }
        } finally {
            getBookLock.unlock();
        }
    }

    /**
     * Fetches a book from the library with the given ID
     * or displays an error message
     * @param bookID the ID that is searched for
     * @return a book with a matching ID
     */
    public Book get(int bookID) {
        getBookLock.lock();
        try {
            for (int i = 0; i < books.length; i++) {
                System.out.println(books[i].getTitle());
                if(books[1] != null) {
                    if (books[i].getID() == bookID) {
                        return books[i];
                    }
                }
            }
        } finally {
            getBookLock.unlock();
        }

        throw new NoSuchElementException("Book not found");
    }

    /**
     * Removes a book from the library
     * or throws an error
     * @param b the book that is removed
     * @return the removed book
     */
    public Book remove(Book b) {
        getBookLock.lock();

        try {
            for (Book book : books) {
                if (book == b) {
                    books[b.getID()] = null;

                    size--;
                    return b;
                }
            }
        } finally {
            getBookLock.unlock();
        }

        throw new NoSuchElementException("Book not found");
    }

}