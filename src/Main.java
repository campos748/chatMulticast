import app.FachadaApp;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    //Creacion del hilo de JavaFX
    @Override
    public void start(Stage primaryStage) throws Exception{
        FachadaApp fa = new FachadaApp();
        fa.iniciarGUI();
    }

}
