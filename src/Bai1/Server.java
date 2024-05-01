package Bai1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("[Server] Listening on port 5555...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("[Server] Accepted connection from " + clientSocket.getInetAddress().getHostAddress());

            BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;
            while (true) {
                // Nhận dữ liệu từ client
                clientMessage = clientInput.readLine();
                if (clientMessage == null) {
                    break;
                }
                System.out.println("[Client]: " + clientMessage);

                // Nhập dữ liệu từ bàn phím và gửi tới client
                serverMessage = serverInput.readLine();
                serverOutput.println(serverMessage);
            }

            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
