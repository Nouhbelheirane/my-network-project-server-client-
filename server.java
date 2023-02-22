package MYTP;
import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected from " + socket.getInetAddress().getHostAddress());

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                String str1 = reader.readLine();
                String str2 = reader.readLine();

                if (str1.contains(str2)) {
                    writer.println(str1 + " contains " + str2);
                } else {
                    writer.println(str1 + " does not contain " + str2);
                }
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
