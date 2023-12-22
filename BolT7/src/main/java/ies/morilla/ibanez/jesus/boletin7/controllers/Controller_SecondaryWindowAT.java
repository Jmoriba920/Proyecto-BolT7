package ies.morilla.ibanez.jesus.boletin7.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import ies.morilla.ibanez.jesus.boletin7.components.TaskRenderer;
import ies.morilla.ibanez.jesus.boletin7.models.Task;
import ies.morilla.ibanez.jesus.boletin7.querys.TaskDML;
import ies.morilla.ibanez.jesus.boletin7.view.SecondaryWindow_AT;

// Clase controladora para la ventana secundaria (SecondaryWindow_AT)
public class Controller_SecondaryWindowAT implements ActionListener {

    // Referencia a la ventana secundaria
    private SecondaryWindow_AT window;
    // Referencia a la lista de tareas en la interfaz gráfica
    private JList<Task> taskJList;

    // Constructor que recibe la ventana secundaria como parámetro
    public Controller_SecondaryWindowAT(SecondaryWindow_AT window) {
        super();
        this.window = window;
    }

    // Método que maneja los eventos de acción
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();

            // Verificar qué botón se ha presionado
            if (clickedButton == window.getBtnAddTask()) {
                // Crear una nueva tarea con los datos de la interfaz gráfica
                Task task = new Task();
                task.setTitle(window.getTitleTask());
                task.setDescription(window.getDescription());
                task.setDate(window.getDate());
                task.setStatus(window.getStatus());

                // Insertar la tarea en la base de datos
                TaskDML.insertTask(task);

                // Actualizar la lista de tareas en la interfaz gráfica
                updateJList();

                // Limpiar los campos de entrada de datos
                cleandFields();
            } else if (clickedButton == window.getbtnEditTask()) {
                // Obtener la tarea seleccionada en la lista
                Task task = taskJList.getSelectedValue();
                task.setId(task.getId());
                // Actualizar la tarea con los nuevos datos de la interfaz gráfica
                task.setTitle(window.getTitleTask());
                task.setDescription(window.getDescription());
                task.setDate(window.getDate());
                task.setStatus(window.getStatus());

                // Actualizar la tarea en la base de datos
                TaskDML.updateTask(task);

                // Actualizar la lista de tareas en la interfaz gráfica
                updateJList();

                // Limpiar los campos de entrada de datos
                cleandFields();
            }
        }
    }

    // Método para guardar la lista de tareas en un archivo de texto
    public void saveFile() {
        // Obtener la lista completa de tareas desde la base de datos
        List<Task> taskList = TaskDML.getAllTasks();

        // Nombre del archivo de texto
        String txtFileName = "tasks.txt";

        try (FileWriter fw = new FileWriter(txtFileName)) {
            // Escribir cada tarea en una línea del archivo
            for (Task task : taskList) {
                fw.write(task.toString() + "\n");
            }

            System.out.println("Archivo de texto creado con éxito: " + txtFileName);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al escribir el archivo de texto: " + e.getMessage());
        }

        // Mostrar un mensaje de éxito al usuario
        ImageIcon icono = new ImageIcon(Controller_SecondaryWindowAT.class
                .getResource("/ies/morilla/ibanez/jesus/boletin7/assets/save_icon.png"));
        JOptionPane.showMessageDialog(null, "Archivo guardado con éxito", "Archivo Guardado",
                JOptionPane.INFORMATION_MESSAGE, icono);
    }

    // Método para actualizar la lista de tareas en la interfaz gráfica
    public void updateJList() {
        // Obtener la lista completa de tareas desde la base de datos
        List<Task> taskList = TaskDML.getAllTasks();

        // Obtener la referencia a la lista de tareas en la interfaz gráfica
        taskJList = window.getTaskList();

        // Crear un modelo de lista y agregar todas las tareas
        DefaultListModel<Task> listModel = new DefaultListModel<>();
        for (Task task : taskList) {
            listModel.addElement(task);
        }

        // Establecer el modelo de lista en la interfaz gráfica
        taskJList.setModel(listModel);
        // Establecer un renderizador personalizado para las celdas de la lista
        taskJList.setCellRenderer(new TaskRenderer(window));
    }

    // Método para limpiar los campos de entrada de datos en la interfaz gráfica
    public void cleandFields() {
        window.setTitleTask("");
        window.setDescription("");
        window.setStatus("Completada");
        window.setTodayDate();
    }

    // Método para salir de la aplicación
    public void exitApp() {
        // Mostrar un cuadro de diálogo de confirmación al usuario
        ImageIcon icon = new ImageIcon(Controller_SecondaryWindowAT.class
                .getResource("/ies/morilla/ibanez/jesus/boletin7/assets/exit_icon.png"));
        int seleccion = JOptionPane.showOptionDialog(window, "¿Estás seguro de que deseas salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, new Object[] { "Salir", "Cancelar" },
                "Salir");

        // Realizar acciones en función de la opción seleccionada por el usuario
        if (seleccion == 0) {
            window.dispose();
            System.exit(0);
        } else if (seleccion == 1) {
            JOptionPane.getRootFrame().dispose();
        }
    }

    // Método para mostrar información sobre la aplicación
    public void informationApp() {
        // Mostrar un cuadro de diálogo informativo al usuario
        ImageIcon icono = new ImageIcon(Controller_SecondaryWindowAT.class
                .getResource("/ies/morilla/ibanez/jesus/boletin7/assets/welcome_icon.png"));
        JOptionPane.showMessageDialog(window, "Versión 1.0 de la aplicación\n Creada por: Jesús Morilla Ibáñez",
                "Administrador de Tareas - AT", JOptionPane.INFORMATION_MESSAGE, icono);
    }

    // Método para mostrar un mensaje de advertencia si el título de la tarea está vacío
    public void emptyTitle() {
        ImageIcon icono = new ImageIcon(Controller_SecondaryWindowAT.class
                .getResource("/ies/morilla/ibanez/jesus/boletin7/assets/welcome_icon.png"));

        JOptionPane.showMessageDialog(window, "Por favor, introduzca el título de la tarea",
                "Administrador de Tareas - AT", JOptionPane.INFORMATION_MESSAGE, icono);
    }

    // Método para mostrar un mensaje de advertencia si la descripción de la tarea está vacía
    public void emptyDescription() {
        ImageIcon icono = new ImageIcon(Controller_SecondaryWindowAT.class
                .getResource("/ies/morilla/ibanez/jesus/boletin7/assets/welcome_icon.png"));

        JOptionPane.showMessageDialog(window, "Por favor, introduzca la descripción de la tarea",
                "Administrador de Tareas - AT", JOptionPane.INFORMATION_MESSAGE, icono);
    }
}