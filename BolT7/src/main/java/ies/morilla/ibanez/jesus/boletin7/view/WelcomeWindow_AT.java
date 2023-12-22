package ies.morilla.ibanez.jesus.boletin7.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import ies.morilla.ibanez.jesus.boletin7.components.Button;
import ies.morilla.ibanez.jesus.boletin7.utils.Utils;

// Clase para la ventana de bienvenida
public class WelcomeWindow_AT extends JDialog {

	// Número de versión de la serialización
	private static final long serialVersionUID = 1L;

	// Constructor de la ventana de bienvenida
	public WelcomeWindow_AT() {
		// Configurar el fondo y el título de la ventana
		getContentPane().setBackground(new Color(240, 237, 255));
		setTitle("Welcome AT");
		setBackground(new Color(240, 237, 255));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ExitWindow_AT.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/icon.png")));

		// Configurar el diseño nulo (null layout)
		getContentPane().setLayout(null);

		// Crear y configurar el botón "Continuar"
		Button continueBtn = new Button();
		continueBtn.setIcon(new ImageIcon(
				WelcomeWindow_AT.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/continue_icon.png")));
		continueBtn.setText("Continuar");
		continueBtn.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
		continueBtn.setBounds(120, 100, 140, 54);

		// Configurar el ActionListener para cerrar la ventana al hacer clic en
		// "Continuar"
		continueBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == continueBtn) {
					dispose(); // Cerrar la ventana
				}
			}
		});

		// Agregar el botón a la ventana
		getContentPane().add(continueBtn);

		// Crear y configurar la etiqueta de mensaje
		JLabel lblMessageExit = new JLabel("Bienvenido a AT tu Administrador de Tareas");
		lblMessageExit.setBounds(30, 30, 325, 40);
		lblMessageExit.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
		getContentPane().add(lblMessageExit);

		// Configurar el tamaño de la ventana
		setSize(new Dimension(400, 200));

		// Obtener el tamaño de la pantalla y centrar la ventana en la pantalla
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(new Point(x, y));
	}
}
