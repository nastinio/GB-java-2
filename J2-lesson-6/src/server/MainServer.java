package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");

            socket = server.accept();
            System.out.println("Клиент подключился");

            final Scanner in = new Scanner(socket.getInputStream());
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            final Scanner console = new Scanner(System.in);

            //Отправляем зеркальные сообщения от сервера сообщения от сервера
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String msg = in.nextLine();
                            if (msg.equals("end")) {
                                break;
                            }
                            System.out.println("Сообщение от клиента: " + msg);

                        }
                    } catch (Exception e) {
                    }
                }
            });
            t1.start();


            //Отправляем самостоятельные сообщения от сервера
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println("Введите сообщение");
                            String msg = console.nextLine();

                            out.println(msg);
                        }
                    } catch (Exception e) {
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
            System.out.println("Ошибка инициализации сервера");
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
