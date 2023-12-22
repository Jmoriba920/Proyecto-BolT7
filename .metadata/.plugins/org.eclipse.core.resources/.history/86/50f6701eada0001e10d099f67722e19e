package ies.morilla.ibanez.jesus.boletin7.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import ies.morilla.ibanez.jesus.boletin7.models.Task;
import ies.morilla.ibanez.jesus.boletin7.querys.TaskDML;
import ies.morilla.ibanez.jesus.boletin7.view.SecondaryWindow_AT;

// Clase TaskRenderer que extiende de JLabel e implementa ListCellRenderer para personalizar la apariencia de las celdas en un JList
public class TaskRenderer extends JLabel implements ListCellRenderer<Task> {

	private static final long serialVersionUID = 1L;
	private SecondaryWindow_AT window;

	// Constructor que recibe la ventana secundaria como parámetro
	public TaskRenderer(SecondaryWindow_AT window) {
		setOpaque(true);
		this.window = window;
	}

	// Método implementado de la interfaz ListCellRenderer
	@Override
	public Component getListCellRendererComponent(JList<? extends Task> list, Task task, int index, boolean isSelected,
			boolean cellHasFocus) {
		// Crear una representación en formato HTML de la información de la tarea
		String taskInfo = "<html>" + "<b>" + task.getTitle() + "</b><br>" + task.getDescription() + "<br>"
				+ "<font color='gray'>" + task.getStatus() + " - " + task.getDate() + "</font>" + "</html>";

		// Establecer el texto y el tamaño preferido del componente
		setText(taskInfo);
		setPreferredSize(new Dimension(200, getPreferredSize().height));

		// Establecer el color de fondo y el color del texto según si la celda está
		// seleccionada o no
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		// Establecer otras propiedades del componente
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Agregar un listener de mouse para gestionar eventos de clic derecho
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int clickedIndex = list.locationToIndex(e.getPoint());
					list.setSelectedIndex(clickedIndex);

					// Crear un menú emergente
					JPopupMenu popupMenu = new JPopupMenu();
					popupMenu.setBorder(new LineBorder(new Color(65, 66, 113), 2, true));
					JMenuItem deleteItem = new JMenuItem("Eliminar Tarea");
					JMenuItem loadItem = new JMenuItem("Cargar Tarea");
					JMenuItem markComplete = new JMenuItem("Finalizar Tarea");

					// Listener para eliminar la tarea seleccionada
					deleteItem.addActionListener(new ActionListener() {
						@SuppressWarnings("unchecked")
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							DefaultListModel<Task> model = (DefaultListModel<Task>) list.getModel();
							Task taskToDelete = model.getElementAt(clickedIndex);

							TaskDML.deleteTaskById(taskToDelete.getId());

							model.removeElement(taskToDelete);
						}
					});

					// Listener para cargar la información de la tarea seleccionada en la ventana
					loadItem.addActionListener(new ActionListener() {
						@SuppressWarnings("unchecked")
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							DefaultListModel<Task> model = (DefaultListModel<Task>) list.getModel();
							Task taskToLoad = model.getElementAt(clickedIndex);

							window.setTitleTask(taskToLoad.getTitle());
							window.setDescription(taskToLoad.getDescription());
							window.setDate(taskToLoad.getDate());
							window.setStatus(taskToLoad.getStatus());
						}
					});

					// Listener para marcar como completada la tarea seleccionada
					markComplete.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							@SuppressWarnings("unchecked")
							DefaultListModel<Task> model = (DefaultListModel<Task>) list.getModel();
							Task taskToComplete = model.getElementAt(clickedIndex);

							taskToComplete.setStatus("Completada");

							TaskDML.updateTask(taskToComplete);
						}
					});

					// Agregar elementos al menú emergente
					popupMenu.add(markComplete);
					popupMenu.add(loadItem);
					popupMenu.add(deleteItem);

					// Mostrar el menú emergente en la posición del clic derecho
					popupMenu.show(list, e.getX(), e.getY());
				}
			}
		});

		// Devolver el componente personalizado
		return this;
	}
}