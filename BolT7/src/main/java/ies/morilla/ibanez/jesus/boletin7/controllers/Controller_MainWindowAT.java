package ies.morilla.ibanez.jesus.boletin7.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ies.morilla.ibanez.jesus.boletin7.view.MainWindow_AT;
import ies.morilla.ibanez.jesus.boletin7.view.SecondaryWindow_AT;
import ies.morilla.ibanez.jesus.boletin7.view.WelcomeWindow_AT;

// Definición de la clase Controller_MainWindowAT que implementa la interfaz ActionListener
public class Controller_MainWindowAT implements ActionListener {

    // Atributo que representa la ventana principal de la aplicación
    private MainWindow_AT window;

    // Constructor que recibe la ventana principal como parámetro
    public Controller_MainWindowAT(MainWindow_AT window) {
        super();
        this.window = window;
    }

    // Método actionPerformed que se ejecuta al interactuar con algún componente que tiene asignado este controlador
    @Override
    public void actionPerformed(ActionEvent e) {
        // Verificar si el evento proviene de un JButton
        if (e.getSource() instanceof JButton) {
            // Obtener el nombre de usuario y la contraseña ingresados en la ventana principal
            String username = window.getUsername();
            String password = window.getPassword();

            // Verificar si el nombre de usuario y la contraseña son correctos
            if (username.equals("usuario") && password.equals("123456")) {
                // Cerrar la ventana principal
                window.dispose();

                // Crear una instancia de la ventana secundaria (SecondaryWindow_AT) y hacerla visible
                SecondaryWindow_AT frame = new SecondaryWindow_AT();
                frame.setVisible(true);

                // Crear una instancia de la ventana de bienvenida (WelcomeWindow_AT) y hacerla visible
                WelcomeWindow_AT welcomeWindow = new WelcomeWindow_AT();
                welcomeWindow.setVisible(true);
            } else {
                // Mostrar un mensaje de error en caso de credenciales incorrectas
                JOptionPane.showMessageDialog(window,
                        "Usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.", "Error de Autenticación",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}