package ies.morilla.ibanez.jesus.boletin7;

import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;
import ies.morilla.ibanez.jesus.boletin7.controllers.Controller_MainWindowAT;
import ies.morilla.ibanez.jesus.boletin7.view.MainWindow_AT;

// Clase de prueba para MainWindow_AT
public class MainWindow_ATTest {

    // Variables de instancia para las pruebas
    private MainWindow_AT mainWindow;
    private Controller_MainWindowAT mockController;

    // Método de configuración ejecutado antes de cada prueba
    @Before
    public void setUp() {
        // Inicializar MainWindow_AT y mock del Controller
        mainWindow = new MainWindow_AT();
        mockController = mock(Controller_MainWindowAT.class);
        mainWindow.setController(mockController);
    }

    // Método de prueba para simular el evento de clic en el botón "Login"
    @Test
    public void testLoginButtonClicked() {
        // Obtener el botón "Login" y crear un mock de ActionEvent
        JButton loginBtn = mainWindow.getLoginBtn();
        ActionEvent mockEvent = new ActionEvent(loginBtn, ActionEvent.ACTION_PERFORMED, "command");

        // Simular el clic en el botón y verificar si se llama al método actionPerformed del controlador
        loginBtn.getActionListeners()[0].actionPerformed(mockEvent);
        verify(mockController).actionPerformed(any(ActionEvent.class));
    }

    // Método de prueba para simular el evento de clic en el botón "Exit"
    @Test
    public void testExitButtonClicked() {
        // Obtener el botón "Exit" y crear un mock de ActionEvent
        JButton exitBtn = mainWindow.getExitBtn();
        ActionEvent mockEvent = new ActionEvent(exitBtn, ActionEvent.ACTION_PERFORMED, "command");

        // Simular el clic en el botón y verificar si no se llama al método actionPerformed del controlador
        exitBtn.getActionListeners()[0].actionPerformed(mockEvent);
        verify(mockController, never()).actionPerformed(any(ActionEvent.class));
    }
}
