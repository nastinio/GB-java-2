package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8189);

            final Scanner in = new Scanner(socket.getInputStream());
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            final Scanner console = new Scanner(System.in);

            //Получаем зеркальные сообщения от сервера
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String msg = in.nextLine();
                            if (msg.equals("end")) {
                                break;
                            }
                            System.out.println("Сообщение от сервера: " + msg);
                        }
                    } catch (Exception e) {
                    }
                }
            });
            t1.start();

            //Получаем самостоятельные сообщения от клиента
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println("Введите сообщение");
                            String msg = console.nextLine();

                            //System.out.println("Отправим сообщение от клиента: " + msg);
                            out.println(msg);
                            //System.out.println("-----------------------------------------------");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.setDaemon(true);
            t2.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
