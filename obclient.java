package MYTP;

import java.io.*;
import java.net.*;

public class obclient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port)) {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            int clientId = input.readInt();
            System.out.println("Connected as client " + clientId);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Enter string 1: ");
                String str1 = reader.readLine();
                System.out.print("Enter string 2: ");
                String str2 = reader.readLine();

                output.writeUTF(str1);
                output.writeUTF(str2);
                output.flush();

                String response = input.readUTF();
                System.out.println(response);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
