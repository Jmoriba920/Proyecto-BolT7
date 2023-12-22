package ies.morilla.ibanez.jesus.boletin7;

import java.awt.EventQueue;

import ies.morilla.ibanez.jesus.boletin7.view.MainWindow_AT;

// Clase principal que inicia la aplicación
public class App {
	// Método main que inicia la interfaz gráfica de usuario
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Crear una instancia de la ventana principal (MainWindow_AT)
					MainWindow_AT frame = new MainWindow_AT();
					// Hacer visible la ventana
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}