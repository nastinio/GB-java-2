package client;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends JFrame {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;


    public Client() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            /*in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Получаем зеркальные сообщения от сервера
        Thread threadSendMsgFromClient = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Scanner in = new Scanner(socket.getInputStream());
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    try {
                        while (true) {
                            //System.out.println("t1");
                            sendMsg(out);

                            String msgFromServer = in.nextLine();
                            if (msgFromServer.equals("/end")) break;
                            System.out.println("Получили зеркальное сообщение от сервера: " + msgFromServer);
                            System.out.println("-----------------------------------------------");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            out.println("end");
                            out.flush();
                            socket.close();
                            out.close();
                            in.close();
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        threadSendMsgFromClient.start();

        /*//Получаем самостоятельные сообщения от сервера
        Thread threadGetMsgFromClient = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Scanner in = new Scanner(socket.getInputStream());
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    try {
                        while (true) {
                            System.out.println("t2");

                            *//*String msgFromServer = in.nextLine();
                            if (msgFromServer.equals("/end")) break;
                            System.out.println("Получили самостоятельное сообщение от сервера: " + msgFromServer);
                            System.out.println("-----------------------------------------------");*//*

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            out.println("end");
                            out.flush();
                            socket.close();
                            out.close();
                            in.close();
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        threadGetMsgFromClient.start();


        try {
            threadSendMsgFromClient.join();
            threadGetMsgFromClient.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    public void sendMsg(PrintWriter out) {
        //Отправляет сообщение серверу
        System.out.println("Введите сообщение");
        Scanner sc = new Scanner(System.in);
        String msg = sc.next();

        out.println(msg);
        out.flush();
    }

    private void close(PrintWriter out,Scanner in) {
        try {
            out.println("end");
            out.flush();
            socket.close();
            out.close();
            in.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}