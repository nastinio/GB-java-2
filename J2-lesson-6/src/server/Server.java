package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Server {

    private Scanner in;
    private PrintWriter out;

    public Server() {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = server.accept();
            System.out.println("Клиент подключился");

            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());

            //Отправляем зеркальные сообщения от сервера сообщения от сервера
            while (true) {
                //Получили сообщение от клиента
                //Отправляем его назад и выводим на собственную консоль
                String msgFromClient = in.nextLine();
                if (msgFromClient.equals("end")) break;

                out.println(msgFromClient);
                out.flush();

                System.out.println("Получили сообщение от клиента и переправили его обратно: " + msgFromClient);
                System.out.println("--------------------------------------------------------------------------");

            }


            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                    } catch (Exception e) {
                    }
                }
            }).start();*/

            /*//Отправляем самостоятельные сообщения от сервера
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            sendMsg();
                            System.out.println("Отправили самостоятельное сообщение от сервера");
                            System.out.println("-----------------------------------------------");

                        }
                    } catch (Exception e) {
                    }
                }
            }).start();*/


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

    public void sendMsg() {
        //Отправляет сообщение серверу
        System.out.println("Введите сообщение клиету");
        Scanner sc = new Scanner(System.in);
        String msg = sc.next();

        out.println(msg);
        out.flush();
    }
}