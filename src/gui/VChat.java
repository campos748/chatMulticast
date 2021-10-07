package gui;
import app.FachadaApp;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;


public class VChat {

    private final FachadaApp fa;
    private MulticastSocket s;
    private InetAddress group;



    @FXML
    private TextArea mensaje;
    @FXML
    private TextArea chat;
    @FXML
    private Button btnEnviar;
    @FXML
    private TextField name;

    //Constructor
    public VChat(FachadaApp fa){
        this.fa=fa;
        try {
            this.group = InetAddress.getByName("224.0.0.100"); //Asignación IP multicast
            this.s = new MulticastSocket(6703);           //Especificación del puerto
            s.joinGroup(group);                                //Unión al grupo multicast
            this.recibir();                                    //Funcion encargada de generar el hilo de escucha
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Función que envia el mensaje por multicast al presionar el botón Send
    public void enviarMensaje(){

        //El mensaje enviado puede ser de un usuario invisible (no escribió ningún nombre) --> ?
        //O bien puede contener el nombre que el usuario a escrito
        StringBuffer men = new StringBuffer();
        if(name.getText().equals("")){
            men.append("(?)>" + mensaje.getText() + "\n");
        }
        else{
            men.append("(" + name.getText() + ")> " + mensaje.getText() + "\n");
        }


        byte[] m = men.toString().getBytes(StandardCharsets.UTF_8);
        DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6703);   //Preparación del paquete
        try {
            s.send(messageOut);                                                          //Envio del mensaje
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al mandar el mensaje");
        }
        mensaje.clear();
    }

    public void recibir(){
        Task tarea = new Task<Void>(){   //Esta clase, propia de javaFX y esta destinada a tareas largas y complejas
            @Override
            protected Void call() throws Exception {

                while (true) {                      //Bucle infinito a la espera de recepcionar mensajes
                    this.preparePakage();           //Llamo a una funcion para controlar el crecimiento del buffer
                }
            }

            private void preparePakage() {
                byte[] buffer = new byte[1000];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);//Preparación del paquete
                try {
                    s.receive(messageIn);                                            //Recepción del mensaje
                    chat.appendText(new String(messageIn.getData()));                //Se escribe el mensaje en el cuadro
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        (new Thread(tarea)).start(); //Nuevo hilo que ejecuta la tarea
    }


}
