package gb.client;


import gb.server.AuthService;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChildrenThread implements Runnable {
    private final Controller controller;

    Socket socket;
    DataInputStream in;

    public ChildrenThread(Controller controller, Socket socket, DataInputStream in) {
        this.controller = controller;
        this.socket = socket;
        this.in = in;

    }


    @Override
    public void run() {
        while (true) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            System.out.println(str);
                            if (str.startsWith("/authok")) {
                                //Изменим статус в бд на онлайн
                                String login = controller.loginField.getText();
                                System.out.println("login: "+login);
                                //AuthService.updateStatus(login,true);
                                //


                                controller.setAuthorized(true);

                                break;
                            } else {
                                controller.chatArea.appendText(str + "\n");
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverclosed")) break;
                            controller.chatArea.appendText(str + "\n");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        controller.setAuthorized(false);
                    }
                }
            });

        }
    }
}