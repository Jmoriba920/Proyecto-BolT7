package ies.morilla.ibanez.jesus.boletin7.models;

//Clase que representa una tarea con nombre, descripción, fecha y estado.
public class Task {
	private Integer id;
	private String title; // Nombre de la tarea.
	private String description; // Descripción de la tarea.
	private String date; // Fecha de la tarea.
	private String status; // Estado actual de la tarea.

	// Constructor vacío
	public Task() {
		super();
	}

	// Constructor con parámetros
	public Task(String title, String description, String date, String status) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
		this.status = status;
	}

	// Constructor con parámetros, incluyendo el ID
	public Task(Integer id, String title, String description, String date, String status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.status = status;
	}

	// Métodos de acceso y modificación para los atributos de la tarea

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Método toString para obtener una representación en cadena de la tarea
	@Override
	public String toString() {
		return String.format("Title: %s\nDescription: %s\nDate: %s\nStatus: %s", title, description, date, status);
	}
}