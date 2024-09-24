package Friends;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainForm extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnCreate;
    private JButton btnRead;
    private JPanel panelMain;
    private JLabel txtName;
    private JLabel txtNumber;
    private JButton btnClear;

    private CRUD crud;

    public MainForm() {
        crud = new CRUD();

        setTitle("CRUD");
        setSize(300, 400);
        setContentPane(panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Botón para crear amigo
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = textField1.getText();
                    long number = Long.parseLong(textField2.getText());
                    crud.addFriend(name, number);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Botón para mostrar amigos
        btnRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    crud.displayFriends();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Botón para actualizar amigo
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = textField1.getText();
                    long number = Long.parseLong(textField2.getText());
                    crud.updateFriend(name, number);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Botón para eliminar amigo
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = textField1.getText();
                    crud.deleteFriend(name);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Botón para limpiar los campos de texto
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
}
