package Bai1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 5555);

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage, clientMessage;
            while (true) {
                // Nhập dữ liệu từ bàn phím và gửi tới server
                clientMessage = userInput.readLine();
                clientOutput.println(clientMessage);

                // Nhận dữ liệu từ server
                serverMessage = serverInput.readLine();
                System.out.println("[Server]: " + serverMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
