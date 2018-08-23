package gb.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller {
    @FXML
    TextField msgField;

    @FXML
    TextArea chatArea;

    @FXML
    HBox bottomPanel;

    @FXML
    HBox upperPanel;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    private boolean isAuthorized;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    //Отрисовка намертво виснет, придется тестить так
    Scanner inConsole;
    PrintWriter outConsole;
    Scanner console;

    final String IP_ADDRESS = "localhost";
    final int PORT = 8189;

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if(!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
        }
    }


    void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            inConsole = new Scanner(socket.getInputStream());
            outConsole = new PrintWriter(socket.getOutputStream(), true);
            console = new Scanner(System.in);

            //new Thread(new ChildrenThread(this,socket,in)).start();
            new Thread(new Runnable() {
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
                                            String login = loginField.getText();
                                            System.out.println("login: "+login);
                                            //AuthService.updateStatus(login,true);
                                            //


                                            setAuthorized(true);

                                            break;
                                        } else {
                                            chatArea.appendText(str + "\n");
                                        }
                                    }

                                    while (true) {
                                        String str = in.readUTF();
                                        if (str.equals("/serverclosed")) break;
                                        chatArea.appendText(str + "\n");
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        socket.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    setAuthorized(false);
                                }
                            }
                        });

                    }
                }
            }).start();
            /*new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        System.out.println(str);
                        if(str.startsWith("/authok")) {
                            setAuthorized(true);
                            break;
                        } else {
                            chatArea.appendText(str + "\n");
                        }
                    }

                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/serverclosed")) break;
                        chatArea.appendText(str + "\n");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setAuthorized(false);
                }
            }).start();*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if(socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            System.out.println("tryToAuth: login: "+loginField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
