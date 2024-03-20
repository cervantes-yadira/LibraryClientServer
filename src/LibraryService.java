import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 Executes Simple Library System Protocol commands
 from a socket.
 */
public class LibraryService implements Runnable {
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Library lib;
    private PrivateLibrary[] libraries;

    /**
     Constructs a service object that processes commands
     from a socket for a bank.
     @param aSocket the socket
     @param lib the library
     */
    public LibraryService(Socket aSocket, Library lib, PrivateLibrary[] libraries) {
        s = aSocket;
        this.lib = lib;
        this.libraries = libraries;
    }

    public void run() {
        try {
            try {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());

                doService();
            } finally {
                System.out.println("Disconnecting...");
                s.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     Executes all commands until the QUIT command or the
     end of input.
     */
    public void doService() throws IOException {
        while (true) {
            if (!in.hasNext()) {
                return;
            }

            String command = in.next();

            if (command.equals("QUIT")) {
                return;
            } else {
                executeCommand(command);
            }
        }
    }

    /**
     Executes a single command.
     @param command the command to execute
     */
    public void executeCommand(String command) {
        String value = "";

        if(command.equals("GET")) {
            int bookID = -1;
            int libraryID = 1;

            if(in.hasNextInt()) {
                bookID = in.nextInt();
            }

            if(in.hasNextInt()) {
                libraryID = in.nextInt();
            }

            if(bookID != -1  && libraryID != -1) {
                Book b = lib.remove(lib.get(bookID));
                libraries[libraryID].add(b);
                value = b.getTitle();
            }

        } else if (command.equals("RETURN")) {
            int bookID = -1;
            int libraryID = 1;

            if(in.hasNextInt()) {
                bookID = in.nextInt();
            }

            if(in.hasNextInt()) {
                libraryID = in.nextInt();
            }
            if(bookID != -1 && libraryID != -1) {
                value = libraries[libraryID].get(bookID).getTitle();
                lib.add(libraries[libraryID].remove(libraries[libraryID].get(bookID)));
            }
        } else if (command.equals("SHOW")) {
            int libraryID = -1;

            if(in.hasNextInt()) {
                libraryID = in.nextInt();
            }

            if(libraryID != -1) {
                PrivateLibrary library = libraries[libraryID];
                library.viewCollection();
            }
        } else {
            out.println("Invalid command");
            out.flush();
            return;
        }

        out.println(value);
        out.flush();

    }
}