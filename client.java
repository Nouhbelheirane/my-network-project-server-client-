package MYTP;
 
import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to server at " + socket.getInetAddress().getHostAddress() + ",in port:" + port);

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            System.out.println("Enter the first string:");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String str1 = consoleReader.readLine();
            writer.println(str1);

            System.out.println("Enter the second string:");
            String str2 = consoleReader.readLine();
            writer.println(str2);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String response = reader.readLine();
            System.out.println(response);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
