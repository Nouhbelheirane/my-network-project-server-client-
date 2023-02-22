package MYTP;
	import java.io.*;
import java.net.*;

public class clientA {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port)) {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF("Hello");
            output.writeUTF("day");

            DataInputStream input = new DataInputStream(socket.getInputStream());
            String response = input.readUTF();
            System.out.println(response);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
