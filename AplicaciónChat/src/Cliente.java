
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cliente {
    JFrame ventanaChat = null;
    JButton btnEnviar = null;
    JTextField txtMensaje = null;
    JTextArea areaChat = null;
    JPanel contenedorAreaChat = null;
    JPanel contenedorBtnTxt = null;
    JScrollPane scroll = null;
    
    public Cliente() {
        hacerInterfaz();
    }
    
    public void hacerInterfaz() {
        ventanaChat = new JFrame("Cliente");
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
    
    public static void main(String[] args) {
        new Cliente();
    }
}
