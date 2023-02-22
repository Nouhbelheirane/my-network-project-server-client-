package MYTP;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class multiserver {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000...");
            AtomicInteger clientCount = new AtomicInteger(0);

            while (true) {
                Socket socket = serverSocket.accept();
                int clientId = clientCount.incrementAndGet();
                System.out.println("A new client has connected with id " + clientId + " from " + socket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(socket, clientId, clientCount);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket socket;
    private final int id;
    private final AtomicInteger clientCount;

    public ClientHandler(Socket socket, int id, AtomicInteger clientCount) {
        this.socket = socket;
        this.id = id;
        this.clientCount = clientCount;
    }

    @Override
    public void run() {
        try (DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            output.writeInt(clientCount.get());
            output.flush();

            while (true) {
                String str1 = input.readUTF();
                String str2 = input.readUTF();

                if (str1.contains(str2)) {
                    output.writeUTF(str1 + " contains " + str2);
                } else {
                    output.writeUTF(str1 + " does not contain " + str2);
                }

                output.flush();
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Client " + id + " disconnected: " + ex.getMessage());
        }
    }
}
