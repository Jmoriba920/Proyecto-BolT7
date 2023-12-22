package ies.morilla.ibanez.jesus.boletin7.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;

public class Utils {

	public static final String ATKINSON_BOLD_ITALIC = "Atkinson/Atkinson-Hyperlegible-BoldItalic-102.otf";
	public static final String ATKINSON_BOLD = "Atkinson/Atkinson-Hyperlegible-Bold-102.otf";
	public static final String ATKINSON_REGULAR = "Atkinson/Atkinson-Hyperlegible-Regular-102.otf";
	public static final String ATKINSON_ITALIC = "Atkinson/Atkinson-Hyperlegible-Italic-102.otf";

	/**
	 * Método para cargar fuentes personalizadas.
	 *
	 * @param nombreFuente Nombre del archivo de la fuente (ubicado en ../assets/).
	 * @param tamanyo      Tamaño de la fuente.
	 * @return Objeto de tipo Font cargado desde el archivo especificado.
	 */
	public static Font loadFonts(String fontname, int size) {
		// Inicializar la fuente como nula
		Font font = null;

		try {
			// Ruta al archivo de la fuente descargada
			URL fontUrl = Utils.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/" + fontname);
			// Crear un objeto de fuente personalizada desde la URL
			// Derivar la fuente con tamaño especificado
			font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			// Imprimir detalles del error en caso de FontFormatException o IOException
			e.printStackTrace();
			// En caso de error, usar una fuente predeterminada
			return new Font("Arial", Font.PLAIN, size);
		}

		// Devolver la fuente cargada
		return font;
	}

}
