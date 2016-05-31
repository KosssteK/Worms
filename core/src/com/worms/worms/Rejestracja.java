package com.worms.worms;

import javax.swing.*;
import java.awt.event.*;

public class Rejestracja extends JDialog {

    public String login;
    public String haslo;
    public String nick;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;

    Dane H;

    public Rejestracja() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        login = textField1.getText();
        haslo = String.valueOf(passwordField1.getPassword());
        nick = textField2.getText();
        H.login = login;
        H.haslo = haslo;
        H.nick = nick;
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void odpalanie() {
        Rejestracja dialog = new Rejestracja();
        dialog.pack();
        dialog.setVisible(true);

    }
}
