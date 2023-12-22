package ies.morilla.ibanez.jesus.boletin7.sqliteconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;

public class SQLiteConnection {

    private static final String URL = "jdbc:sqlite:task.db";
    private Connection connection;

    public SQLiteConnection() {
        try {
            // Verificar si el archivo de la base de datos existe
            File databaseFile = new File("task.db");
            boolean databaseExists = databaseFile.exists();

            // Cargar el controlador JDBC
            Class.forName("org.sqlite.JDBC");

            // Establecer la conexión a la base de datos
            connection = DriverManager.getConnection(URL);

            // Si la base de datos no existía, crearla
            if (!databaseExists) {
                createDatabase();
            }

            System.out.println("Conexión a la base de datos establecida.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos SQLite", e);
        }
    }

    public void createDatabase() {
        // Ejecutar el script SQL directamente
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableScript());
            System.out.println("Base de datos y tabla creadas con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar el script SQL", e);
        } finally {
            // Cerrar la conexión después de ejecutar el script
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String createTableScript() {
        // Script SQL para crear la tabla
        return "CREATE TABLE IF NOT EXISTS tasks ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT NOT NULL,"
                + "descripcion TEXT,"
                + "fecha TEXT,"
                + "status TEXT"
                + ")";
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                // Cerrar la conexión
                connection.close();
                System.out.println("Conexión a la base de datos cerrada.\n");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cerrar la conexión a la base de datos", e);
            }
        }
    }
}
