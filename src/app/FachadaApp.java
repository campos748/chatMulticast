package app;
import gui.FachadaGui;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class FachadaApp {

    private final FachadaGui fgui;

    public FachadaApp(){
        this.fgui = new FachadaGui(this);
    }

    //Funcion para iniciar la interfaz grafica
    public void iniciarGUI() throws IOException {
        fgui.iniciarVChat();
    }
}
