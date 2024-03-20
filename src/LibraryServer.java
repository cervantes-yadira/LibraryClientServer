import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class LibraryServer {
    static final int SBAP_PORT = 8888;

    public static void main(String[] args) throws IOException {
        // creating private libraries with unique IDs and storing them in libraries[]
        PrivateLibrary[] libraries = new PrivateLibrary[5];

        PrivateLibrary lib1 = new PrivateLibrary(0);
        PrivateLibrary lib2 = new PrivateLibrary(1);
        PrivateLibrary lib3 = new PrivateLibrary(2);
        PrivateLibrary lib4 = new PrivateLibrary(3);
        PrivateLibrary lib5 = new PrivateLibrary(4);

        libraries[0] = lib1;
        libraries[1] = lib2;
        libraries[2] = lib3;
        libraries[3] = lib4;
        libraries[4] = lib5;


        // creating books with unique IDs and storing them in publicLib
        Library publicLib = new Library();

        Book book1 = new Book(0, "Frankenstein");
        Book book2 = new Book(1, "Pride and Prejudice");
        Book book3 = new Book(2, "Moby Dick");
        Book book4 = new Book(3, "Romeo and Juliet");
        Book book5 = new Book(4, "The Great Gatsby");
        Book book6 = new Book(5, "Little Women");
        Book book7 = new Book(6, "Great Expectations");
        Book book8 = new Book(7, "Alice's Adventures in Wonderland");
        Book book9 = new Book(8, "Dracula");
        Book book10 = new Book(9, "A Tale of Two Cities");

        publicLib.add(book1);
        publicLib.add(book2);
        publicLib.add(book3);
        publicLib.add(book4);
        publicLib.add(book5);
        publicLib.add(book6);
        publicLib.add(book7);
        publicLib.add(book8);
        publicLib.add(book9);
        publicLib.add(book10);

        // creates a new server
        try (ServerSocket server = new ServerSocket(SBAP_PORT)) {
            System.out.println("Waiting for clients to connect...");

            // allows program to keep running
            while (true) {
                // listens for a connection from the client socket
                Socket s = server.accept();
                System.out.println("Client connected.");
                LibraryService service = new LibraryService(s, publicLib, libraries);

                // a process, allows multiple things to run at once
                Thread t = new Thread(service);
                t.start();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}