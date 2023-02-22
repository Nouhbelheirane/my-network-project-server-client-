package MYTP;
	import java.io.*;
import java.net.*;

public class serverA {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected from " + socket.getInetAddress().getHostAddress());

                DataInputStream input = new DataInputStream(socket.getInputStream());
                String str1 = input.readUTF();
                String str2 = input.readUTF();

                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                if (str1.contains(str2)) {
                    output.writeUTF(str1 + " contains " + str2);
                } else {
                    output.writeUTF(str1 + " does not contain " + str2);
                }
                
                // close the socket after processing the client request
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
