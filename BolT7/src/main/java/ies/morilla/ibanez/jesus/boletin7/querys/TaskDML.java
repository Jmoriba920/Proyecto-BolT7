package ies.morilla.ibanez.jesus.boletin7.querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ies.morilla.ibanez.jesus.boletin7.models.Task;
import ies.morilla.ibanez.jesus.boletin7.sqliteconnection.SQLiteConnection;

// Clase para realizar operaciones DML en la tabla de tareas (tasks) en la base de datos
public class TaskDML {

    // Constructor por defecto
    public TaskDML() {
    }

    // Método para insertar una tarea en la base de datos
    public static void insertTask(Task task) {
        // Crear una conexión a la base de datos SQLite
        SQLiteConnection con = new SQLiteConnection();

        try {
            // Consulta SQL para la inserción de una tarea en la tabla tasks
            String query = "INSERT INTO tasks (nombre, descripcion, fecha, status) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.getConnection().prepareStatement(query);

            // Establecer los parámetros de la consulta con los valores de la tarea
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getDate());
            preparedStatement.setString(4, task.getStatus());

            // Ejecutar la consulta de inserción
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("\n\u001B[33m" + rowsAffected + " fila(s) insertada(s) en la tabla tasks.\u001B[0m\n");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Desconectar al finalizar la operación
            con.disconnect();
        }
    }

    // Método para obtener todas las tareas de la base de datos
    public static List<Task> getAllTasks() {
        // Crear una conexión a la base de datos SQLite
        SQLiteConnection con = new SQLiteConnection();
        List<Task> tasks = new ArrayList<>();

        try {
            // Consulta SQL para obtener todas las tareas de la tabla tasks
            String query = "SELECT * FROM tasks";
            PreparedStatement preparedStatement = con.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Recorrer el conjunto de resultados y crear objetos Task
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("nombre");
                String description = resultSet.getString("descripcion");
                String date = resultSet.getString("fecha");
                String status = resultSet.getString("status");

                Task task = new Task(id, title, description, date, status);

                tasks.add(task);
            }

            System.out.println("\n\u001B[33mCargando tareas en la lista.\u001B[0m\n");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Desconectar al finalizar la operación
            con.disconnect();
        }

        return tasks;
    }

    // Método para eliminar una tarea por su ID
    public static void deleteTaskById(int taskId) {
        // Crear una conexión a la base de datos SQLite
        SQLiteConnection con = new SQLiteConnection();

        try {
            // Consulta SQL para eliminar una tarea de la tabla tasks por su ID
            String query = "DELETE FROM tasks WHERE id = ?";
            PreparedStatement preparedStatement = con.getConnection().prepareStatement(query);

            // Establecer el parámetro de la consulta con el valor del ID de la tarea
            preparedStatement.setInt(1, taskId);

            // Ejecutar la consulta de eliminación
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("\n\u001B[33m" + rowsAffected + " fila(s) eliminada(s) en la tabla tasks.\u001B[0m\n");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Desconectar al finalizar la operación
            con.disconnect();
        }
    }

    // Método para actualizar una tarea en la base de datos
    public static void updateTask(Task task) {
        // Crear una conexión a la base de datos SQLite
        SQLiteConnection con = new SQLiteConnection();

        try {
            // Consulta SQL para actualizar una tarea en la tabla tasks por su ID
            String query = "UPDATE tasks SET nombre=?, descripcion=?, fecha=?, status=? WHERE id=?";
            PreparedStatement preparedStatement = con.getConnection().prepareStatement(query);

            // Establecer los parámetros de la consulta con los nuevos valores de la tarea
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getDate());
            preparedStatement.setString(4, task.getStatus());
            preparedStatement.setInt(5, task.getId());

            // Ejecutar la consulta de actualización
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("\n\u001B[33m" + rowsAffected + " fila(s) actualizada(s) en la tabla tasks.\u001B[0m\n");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Desconectar al finalizar la operación
            con.disconnect();
        }
    }

}


