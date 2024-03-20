import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 This program tests the bank server.
 */
public class LibraryClient {
    public static void main(String[] args) throws IOException {
        final int SBAP_PORT = 8888;

        // localhost means we're connecting to our own device (loopback)
        // localhost = 127.0.0.1
        try (Socket s = new Socket("localhost", SBAP_PORT)){

            // for user input and server output
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);

            // server responds to output
            String command = "GET 0 1";
            System.out.println("Executing: " + command);
            out.print(command + "\n");
            out.flush();
            if(in.hasNext()) {
                String response = in.next();
                System.out.println("Checking out: " + response);
            }

            command = "SHOW 1";
            System.out.println("Executing: " + command);
            out.print(command + "\n");
            out.flush();
            if(in.hasNext()) {
                String response = in.next();
                System.out.println("Displaying: " + response);
            }

            command = "RETURN 0 1";
            System.out.println("Executing: " + command);
            out.print(command + "\n");
            out.flush();
            if(in.hasNext()) {
                String response = in.next();
                System.out.println("Returning: " + response);
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