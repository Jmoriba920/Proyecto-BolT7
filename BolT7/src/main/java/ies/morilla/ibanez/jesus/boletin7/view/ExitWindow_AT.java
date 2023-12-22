package ies.morilla.ibanez.jesus.boletin7.view;

import javax.swing.JDialog;
import ies.morilla.ibanez.jesus.boletin7.components.Button;
import ies.morilla.ibanez.jesus.boletin7.utils.Utils;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Point;

// Clase para la ventana de salida de la aplicación
public class ExitWindow_AT extends JDialog {

    // Número de versión de la serialización
    private static final long serialVersionUID = 1L;

    // Constructor de la ventana de salida
    public ExitWindow_AT() {
        // Configurar propiedades de la ventana
        getContentPane().setBackground(new Color(240, 237, 255));
        setTitle("Exit AT");
        setBackground(new Color(240, 237, 255));
        setIconImage(Toolkit.getDefaultToolkit().getImage(ExitWindow_AT.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/icon.png")));
        getContentPane().setLayout(null);

        // Botón de salida
        Button exitBtn = new Button();
        exitBtn.setToolTipText("Salir de la Aplicación");
        exitBtn.setIcon(new ImageIcon(ExitWindow_AT.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/exit_icon.png")));
        exitBtn.setText("Salir");
        exitBtn.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
        exitBtn.setBounds(199, 90, 130, 54);

        // Acción del botón de salida
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitBtn) {
                    System.exit(0);
                }
            }
        });

        getContentPane().add(exitBtn);

        // Botón de cancelar
        Button cancelBtn = new Button();
        cancelBtn.setToolTipText("Cancelar operación");
        cancelBtn.setIcon(new ImageIcon(ExitWindow_AT.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/cancel_icon.png")));
        cancelBtn.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
        cancelBtn.setText("Cancelar");
        cancelBtn.setBounds(60, 90, 130, 54);

        // Acción del botón de cancelar
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancelBtn) {
                    dispose();
                }
            }
        });

        getContentPane().add(cancelBtn);

        // Etiqueta de mensaje de salida
        JLabel lblMessageExit = new JLabel("¿Está seguro de que desea salir?");
        lblMessageExit.setBounds(70, 20, 240, 40);
        lblMessageExit.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
        getContentPane().add(lblMessageExit);

        setSize(new Dimension(400, 200));

        // Centrar la ventana en la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(new Point(x, y));
    }
}