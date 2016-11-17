import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;

public class Servidor {
    JFrame ventanaChat = null;
    JButton btnEnviar = null;
    JTextField txtMensaje = null;
    JTextArea areaChat = null;
    JPanel contenedorAreaChat = null;
    JPanel contenedorBtnTxt = null;
    JScrollPane scroll = null;
    ServerSocket servidor = null;
    Socket socket = null;
    BufferedReader lector = null;
    PrintWriter escritor = null;
    
    public Servidor() {
        hacerInterfaz();
    }
    
    public void hacerInterfaz() {
        ventanaChat = new JFrame("Servidor");
        btnEnviar = new JButton("Enviar");
        txtMensaje = new JTextField(4);
        areaChat = new JTextArea(10, 12);
        scroll = new JScrollPane(areaChat);
        contenedorAreaChat = new JPanel();
        contenedorAreaChat.setLayout(new GridLayout(1, 1));
        contenedorAreaChat.add(scroll);
        contenedorBtnTxt = new JPanel();
        contenedorBtnTxt.setLayout(new GridLayout(1, 2));
        contenedorBtnTxt.add(txtMensaje);
        contenedorBtnTxt.add(btnEnviar);
        ventanaChat.setLayout(new BorderLayout());
        ventanaChat.add(contenedorAreaChat, BorderLayout.NORTH);
        ventanaChat.add(contenedorBtnTxt, BorderLayout.SOUTH);
        ventanaChat.setSize(300, 220);
        ventanaChat.setVisible(true);
        ventanaChat.setResizable(false);
        ventanaChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void leer() {
        Thread leerHilo = new Thread(new Runnable() {
            public void run() {
                try {
                    lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
                    while(true) {
                        String mensajeRecibido = lector.readLine();
                        areaChat.append("Cliente dice: " + mensajeRecibido);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        leerHilo.start();
    }
    
    public void escribir() {
        Thread escribirHilo = new Thread(new Runnable() {
            public void run() {
                try {
                    escritor = new PrintWriter(socket.getOutputStream(), true);
                    btnEnviar.addActionListener(new ActionListener() {
                        
                    public void actionPerformed(ActionEvent e) {
                        String enviarMensaje = txtMensaje.getText();
                        escritor.println(enviarMensaje);
                    }
                });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        escribirHilo.start();
    }
    
    public static void main(String[] args) {
        new Servidor();
    }
}
