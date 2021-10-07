package gui;

import app.FachadaApp;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class FachadaGui {
    FachadaApp fa;
    Stage marco;
    //Controlador
    private VChat vChat;

    public FachadaGui(FachadaApp fa) {
        this.fa=fa;
        this.vChat = new VChat(this.fa);
        this.marco = new Stage();
        marco.getIcons().add(new Image("/gui/Icons/chat.png"));

    }

    public void iniciarVChat() throws IOException {
        //Carga de controlador de fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/VChat.fxml"));
        loader.setControllerFactory(c -> this.vChat);

        try {
            marco.setResizable(false);
            marco.setScene(new Scene(loader.load()));
            marco.setTitle("MChat");
            marco.setOnCloseRequest(                    //Es necesario sobrescribir el metodo para matar a los 2 hilos
                    new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            System.exit(0);
                        }
                    });
            marco.show();

        }catch (IOException e){
            System.out.println("Error al crear la ventana");
        }
    }
}
