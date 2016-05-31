package com.worms.worms;

import javax.swing.*;
import java.awt.event.*;

public class Logowanie extends JDialog {


    public String login;
    public String haslo;
    Dane G;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;

    public Logowanie()
    {

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
// add your code here
        login = textField1.getText();
        haslo = String.valueOf(passwordField1.getPassword());
        G.login = login;
        G.haslo = haslo;
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void odpalanie() {
        Logowanie dialog = new Logowanie();
        dialog.pack();
        dialog.setVisible(true);

       // System.exit(0);
    }
}
