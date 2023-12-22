package ies.morilla.ibanez.jesus.boletin7;

import static org.mockito.Mockito.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ies.morilla.ibanez.jesus.boletin7.controllers.Controller_SecondaryWindowAT;
import ies.morilla.ibanez.jesus.boletin7.models.Task;
import ies.morilla.ibanez.jesus.boletin7.view.SecondaryWindow_AT;

// Clase de prueba para SecondaryWindow_AT
public class SecondaryWindow_ATTest {

	// Variables de instancia para las pruebas
	private SecondaryWindow_AT secondaryWindow;
	private Controller_SecondaryWindowAT mockController;

	// Método de configuración ejecutado antes de cada prueba
	@Before
	public void setUp() {
		// Inicializar SecondaryWindow_AT y mock del Controller
		secondaryWindow = new SecondaryWindow_AT();
		mockController = Mockito.mock(Controller_SecondaryWindowAT.class);
		secondaryWindow.setController(mockController);
	}

	// Método de prueba para simular el evento de clic en el botón "Agregar Tarea"
	@Test
	public void testAddTaskButtonClicked() {
		// Configurar datos de prueba para título y descripción
		secondaryWindow.setTitleTask("Título de prueba");
		secondaryWindow.setDescription("Descripción de prueba");

		// Obtener el botón "Agregar Tarea" y crear un mock de ActionEvent
		JButton btnAddTask = secondaryWindow.getBtnAddTask();
		ActionEvent mockEvent = new ActionEvent(btnAddTask, ActionEvent.ACTION_PERFORMED, "command");

		// Simular el clic en el botón y verificar si se llama al método actionPerformed del controlador
		btnAddTask.getActionListeners()[0].actionPerformed(mockEvent);
		verify(mockController).actionPerformed(any(ActionEvent.class));
	}

	// Método de prueba para simular el evento de clic en el botón "Editar Tarea"
	@Test
	public void testEditTaskButtonClicked() {
		// Configurar una tarea existente con título, descripción, fecha y estado
		Task existingTask = new Task("Título existente", "Descripción existente", "2023-12-21", "Pendiente");

		// Establecer la tarea existente en la lista de tareas en SecondaryWindow_AT
		secondaryWindow.getTaskList().setListData(new Task[] { existingTask });

		// Configurar datos de prueba para título y descripción editados
		secondaryWindow.setTitleTask("Título editado");
		secondaryWindow.setDescription("Descripción editada");

		// Obtener el botón "Editar Tarea" y crear un mock de ActionEvent
		JButton btnEditTask = secondaryWindow.getbtnEditTask();
		ActionEvent mockEvent = new ActionEvent(btnEditTask, ActionEvent.ACTION_PERFORMED, "command");

		// Simular el clic en el botón y verificar si se llama al método actionPerformed del controlador
		btnEditTask.getActionListeners()[0].actionPerformed(mockEvent);
		verify(mockController).actionPerformed(any(ActionEvent.class));
	}
}

