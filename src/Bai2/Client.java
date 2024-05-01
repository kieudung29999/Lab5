package Bai2;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends JFrame {
    private JLabel timeLabel;

    public Client() {
        setTitle("Clock");
        setSize(300, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timeLabel, BorderLayout.CENTER);

        setVisible(true);

        updateTime();
    }

    private void updateTime() {
        try {
            Socket socket = new Socket("127.0.0.1", 5555);

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                // Gửi yêu cầu "time" đến server
                clientOutput.println("time");

                // Nhận thời gian từ server và cập nhật lên giao diện đồng hồ
                String serverMessage = serverInput.readLine();
                timeLabel.setText(serverMessage);

                Thread.sleep(1000); // Đợi 1 giây trước khi gửi yêu cầu mới
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}

