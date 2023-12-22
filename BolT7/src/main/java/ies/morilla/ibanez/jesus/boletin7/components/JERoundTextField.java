package ies.morilla.ibanez.jesus.boletin7.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// Clase JERoundTextField que extiende JTextField y personaliza su apariencia
public class JERoundTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	private int arcw = 20; // Ancho del arco de la esquina redondeada
	private int arch = 20; // Altura del arco de la esquina redondeada
	private Image image = null; // Imagen asociada al componente
	private Icon icon; // Icono asociado al componente

	// Constructor que configura algunas propiedades del componente
	public JERoundTextField() {
		setOpaque(false);
		setBorder(new EmptyBorder(0, 5, 0, 2));
		setPreferredSize(new Dimension(100, 20));
	}

	// Método para pintar el componente
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create(); // Crear un objeto Graphics2D
		Paint oldPaint = g2.getPaint();

		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcw, arch); // Utilizar
																											// ancho y
																											// alto
																											// completos
		g2.clip(r2d);

		// Pintar el fondo con un degradado
		g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(), 0.0f, getHeight(), getBackground()));
		g2.fillRect(0, 0, getWidth(), getHeight());

		// Si hay una imagen asociada, dibujarla
		if (getImage() != null) {
			g2.drawImage(getImage(), 5, 2, getHeight() - 3, getHeight() - 3, null);
			setBorder(new EmptyBorder(0, (int) (getHeight() * 1.2), 0, 2));
		}

		// Pintar el contorno del componente
		g2.setPaint(new GradientPaint(0.0f, 0.0f, Color.BLACK, 0.0f, getHeight(), Color.BLACK));
		g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcw, arch);

		g2.setPaint(oldPaint);
		g2.dispose(); // Liberar el objeto Graphics2D
		super.paintComponent(g);
	}

	// Métodos getter y setter para las propiedades de las esquinas redondeadas
	public int getArcw() {
		return arcw;
	}

	public void setArcw(int arcw) {
		this.arcw = arcw;
	}

	public int getArch() {
		return arch;
	}

	public void setArch(int arch) {
		this.arch = arch;
	}

	// Métodos getter y setter para la imagen
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	// Métodos getter y setter para el icono
	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
		setImage(((ImageIcon) icon).getImage());
	}
}