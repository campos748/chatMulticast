package gui;

import app.FachadaApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class FachadaGui {
    FachadaApp fa;
    Stage marcoPrincipal;
    //Controlador
    private VChat vChat;

    public FachadaGui(FachadaApp fa) {
        this.fa=fa;
        this.vChat = new VChat(this.fa);
        this.marcoPrincipal = new Stage();
        marcoPrincipal.getIcons().add(new Image("/gui/Icons/chat.png"));
    }

    public void iniciarVChat() throws IOException {
        //Carga de controlador de fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/VChat.fxml"));
        loader.setControllerFactory(c -> this.vChat);

        try {
            marcoPrincipal.setResizable(false);
            marcoPrincipal.setScene(new Scene(loader.load()));
            marcoPrincipal.setTitle("VChat");
            marcoPrincipal.show();
        }catch (IOException e){
            System.out.println("Error al crear la ventana");
        }
    }
}
