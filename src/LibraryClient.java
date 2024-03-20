import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program uses the library server to perform commands
 */
public class LibraryClient {
    public static void main(String[] args) throws IOException {
        final int SBAP_PORT = 8888;

        // localhost means we're connecting to our own device (loopback)
        try (Socket s = new Socket("localhost", SBAP_PORT)) {

            // for user input and server output
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);

            // used by library service class
            String command = "GET 0 1";
            System.out.println("Executing: " + command);
            out.print(command + "\n");
            out.flush();

            // server responds to output
            if(in.hasNextLine()) {
                String response = in.nextLine();
                System.out.println("Checking out: " + response); // Frankenstein
            }

            command = "GET 5 1";
            System.out.println("Executing: " + command);
            out.print(command + "\n");
            out.flush();

            if(in.hasNextLine()) {
                String response = in.nextLine();
                System.out.println("Checking out: " + response); // Little Women
            }

            command = "SHOW 1";
            System.out.println("Executing: " + command);
            out.print(command + "\n");
            out.flush();

            if(in.hasNextLine()) {
                String response = in.nextLine();
                System.out.println("Displaying: " + response);
            }

            command = "RETURN 0 1";
            System.out.println("Executing: " + command);
            out.print(command + "\n");
            out.flush();

            if(in.hasNextLine()) {
                String response = in.nextLine();
                System.out.println("Returning: " + response); // Frankenstein
            }

            command = "DO SOMETHING";
            System.out.println("Executing: " + command);
            out.print(command + "\n");
            out.flush();

            if(in.hasNextLine()) {
                String response = in.nextLine();
                System.out.println("Result: " + response); // invalid command
            }

            command = "QUIT";
            System.out.println("Executing: " + command);
            out.print(command + "\n");
            out.flush();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}