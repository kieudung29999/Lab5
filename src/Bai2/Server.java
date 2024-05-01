package Bai2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("[Server] Listening on port 5555...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[Server] Accepted connection from " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);

                String clientMessage = clientInput.readLine();
                if (clientMessage != null && clientMessage.equals("time")) {
                    // Phản hồi với thời gian hiện tại
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTime = dateFormat.format(new Date());
                    serverOutput.println(currentTime);
                }

                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

