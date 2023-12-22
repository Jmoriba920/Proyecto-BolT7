package ies.morilla.ibanez.jesus.boletin7.view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JCalendar;

import ies.morilla.ibanez.jesus.boletin7.components.Button;
import ies.morilla.ibanez.jesus.boletin7.controllers.Controller_SecondaryWindowAT;
import ies.morilla.ibanez.jesus.boletin7.models.Task;
import ies.morilla.ibanez.jesus.boletin7.utils.Utils;
import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.UIManager;

// Clase para la ventana secundaria (SecondaryWindow_AT)
public class SecondaryWindow_AT extends JFrame {

	// Número de versión
	private static final long serialVersionUID = 1L;

	// Componentes de la interfaz gráfica
	private JPanel contentPane;
	private JMenuItem menuSaveFile, menuExit, menuVersion;
	private JMenu menuInformation, menuArchive;
	private JTextField titleField;
	private JTextArea taDescription;
	private JCalendar calendar;
	private Choice choiceStatus;
	private Button btnAddTask, btnEditTask;
	private JList<Task> taskList;
	private Controller_SecondaryWindowAT controller;

	// Constructor de la ventana secundaria
	public SecondaryWindow_AT() {
		// Inicializar el controlador
		controller = new Controller_SecondaryWindowAT(this);
		// Configuración de la ventana secundaria
		setTitle("AT Tareas");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SecondaryWindow_AT.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 710);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(new Color(240, 237, 255));
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiquetas y campos de texto para la entrada de datos
		JLabel lblTaskList = new JLabel("Lista de Tareas");
		lblTaskList.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
		lblTaskList.setBounds(140, 15, 109, 18);
		contentPane.add(lblTaskList);

		taskList = new JList<>();
		taskList.setToolTipText("Lista de Tareas");
		taskList.setBorder(new LineBorder(new Color(65, 66, 113), 2, true));
		taskList.setBounds(30, 44, 340, 570);
		contentPane.add(taskList);

		JLabel lblTitle = new JLabel("Título");
		lblTitle.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
		lblTitle.setBounds(460, 15, 50, 18);
		contentPane.add(lblTitle);

		titleField = new JTextField();
		titleField.setToolTipText("Introduzca el título");
		titleField.setBorder(new LineBorder(new Color(65, 66, 113), 2, true));
		titleField.setBounds(460, 45, 340, 40);
		titleField.setFont(Utils.loadFonts(Utils.ATKINSON_REGULAR, 15));
		contentPane.add(titleField);

		JLabel lblDescription = new JLabel("Descripción");
		lblDescription.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
		lblDescription.setBounds(460, 102, 90, 18);
		contentPane.add(lblDescription);

		taDescription = new JTextArea();
		taDescription.setToolTipText("Introduzca la descripción");
		taDescription.setBorder(new LineBorder(new Color(65, 66, 113), 2, true));
		taDescription.setBounds(460, 131, 340, 87);
		taDescription.setFont(Utils.loadFonts(Utils.ATKINSON_REGULAR, 15));
		contentPane.add(taDescription);

		JLabel lblDate = new JLabel("Fecha");
		lblDate.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
		lblDate.setBounds(460, 235, 50, 18);
		contentPane.add(lblDate);

		calendar = new JCalendar();
		calendar.getDayChooser().getDayPanel().setToolTipText("Seleccione una fecha");
		calendar.setBorder(new LineBorder(new Color(65, 66, 113), 2, true));
		calendar.setWeekOfYearVisible(false);
		calendar.getDayChooser().setForeground(new Color(117, 116, 150));
		calendar.setSundayForeground(new Color(255, 95, 9));
		calendar.setWeekdayForeground(new Color(240, 237, 255));
		calendar.setDecorationBackgroundColor(new Color(65, 66, 113));
		calendar.setBackground(new Color(255, 128, 0));
		calendar.setBounds(460, 265, 340, 180);
		contentPane.add(calendar);

		JLabel lblStatus = new JLabel("Estado");
		lblStatus.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
		lblStatus.setBounds(460, 460, 50, 18);
		contentPane.add(lblStatus);

		choiceStatus = new Choice();
		choiceStatus.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		choiceStatus.add("Completada");
		choiceStatus.add("Pediente");
		choiceStatus.add("En Proceso");
		choiceStatus.setFont(Utils.loadFonts(Utils.ATKINSON_REGULAR, 15));
		choiceStatus.setBounds(460, 485, 340, 40);
		contentPane.add(choiceStatus);

		btnAddTask = new Button();
		btnAddTask.setToolTipText("Tarea a añadir");
		btnAddTask.setText("Añadir Tarea");
		btnAddTask.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
		btnAddTask.setBounds(460, 530, 130, 40);
		contentPane.add(btnAddTask);

		// Asociar el controlador al botón de añadir tarea
		btnAddTask.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAddTask) {
					if (titleField.getText().equals("")) {
						controller.emptyTitle();
					} else if (taDescription.getText().equals("")) {
						controller.emptyDescription();
					} else {
						controller.actionPerformed(e);
					}
				}
			}
		});

		btnEditTask = new Button();
		btnEditTask.setToolTipText("Tarea a editar");
		btnEditTask.setText("Editar Tarea");
		btnEditTask.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 15));
		btnEditTask.setBounds(660, 530, 140, 40);
		contentPane.add(btnEditTask);

		// Asociar el controlador al botón de editar tarea
		btnEditTask.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnEditTask) {
					if (titleField.getText().equals("")) {
						controller.emptyTitle();
					} else if (taDescription.getText().equals("")) {
						controller.emptyDescription();
					} else {
						controller.actionPerformed(e);
					}
				}
			}
		});

		// Actualizar la lista de tareas
		controller.updateJList();

		// Crear y configurar la barra de menú
		JMenuBar menuBar = createMenuBar();
		setJMenuBar(menuBar);
	}

	// Método para crear la barra de menú
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(UIManager.getBorder("MenuBar.border"));
		menuBar.setBackground(new Color(117, 116, 150));

		menuBar.setPreferredSize(new Dimension(menuBar.getWidth(), 35));

		// Sección de inicialización del menú "Archivo"
		menuArchive = new JMenu("Archivo");
		menuArchive.setToolTipText("Acciones Archivo");
		menuArchive.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(240, 237, 255)));
		menuArchive.setBackground(new Color(65, 66, 113));
		menuArchive.setForeground(new Color(255, 255, 255));
		menuArchive.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 12));
		menuArchive.setOpaque(true);
		menuArchive.setIcon(new ImageIcon(
				SecondaryWindow_AT.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/archive_icon.png")));
		menuArchive.setMnemonic(KeyEvent.VK_A);

		// Sección de inicialización del menú "Guardar Archivo"
		menuSaveFile = new JMenuItem("Guardar Archivo");
		menuSaveFile.setToolTipText("Guardar Archivo");
		menuSaveFile.setFont(Utils.loadFonts(Utils.ATKINSON_REGULAR, 12));
		menuSaveFile.setIcon(new ImageIcon(
				SecondaryWindow_AT.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/save_icon.png")));
		menuSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuArchive.add(menuSaveFile);

		// Acción para guardar el archivo
		menuSaveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == menuSaveFile) {
					controller.saveFile();
				}
			}
		});

		// Atajo de teclado para guardar el archivo (Ctrl + S)
		menuSaveFile.registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveFile();
			}
		}, menuSaveFile.getAccelerator(), JComponent.WHEN_IN_FOCUSED_WINDOW);

		// Sección de inicialización del menú "Salir"
		menuExit = new JMenuItem("Salir");
		menuExit.setToolTipText("Salir de la Aplicación");
		menuExit.setFont(Utils.loadFonts(Utils.ATKINSON_REGULAR, 12));
		menuExit.setIcon(new ImageIcon(
				SecondaryWindow_AT.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/exit_icon.png")));
		menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		menuArchive.add(menuExit);

		// Se añade el menú "Salir" al menú principal
		menuBar.add(menuArchive);

		// Se añade un ActionListener al menú "Salir" para abrir la ventana de salida al
		// hacer clic
		menuExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == menuExit) {
					ExitWindow_AT exitWindow = new ExitWindow_AT();
					exitWindow.setVisible(true);
				}
			}
		});

		// Atajo de teclado para salir (Ctrl + E)
		menuExit.registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.exitApp();
			}
		}, menuExit.getAccelerator(), JComponent.WHEN_IN_FOCUSED_WINDOW);

		// Sección de inicialización del menú "Información"
		menuInformation = new JMenu("Información");
		menuInformation.setToolTipText("Información de la Aplicación");
		menuInformation.setMargin(new Insets(2, 5, 2, 2));
		menuInformation.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(240, 237, 255)));
		menuInformation.setFont(Utils.loadFonts(Utils.ATKINSON_BOLD, 12));
		menuInformation.setBackground(new Color(65, 66, 113));
		menuInformation.setForeground(new Color(255, 255, 255));
		menuInformation.setOpaque(true);
		menuInformation.setIcon(new ImageIcon(SecondaryWindow_AT.class
				.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/information_icon.png")));
		menuInformation.setMnemonic(KeyEvent.VK_V);

		// Sección de inicialización del menú "Versión" dentro del menú "Información"
		menuVersion = new JMenuItem("Versión");
		menuVersion.setToolTipText("Versión de la Aplicación");
		menuVersion.setFont(Utils.loadFonts(Utils.ATKINSON_REGULAR, 12));
		menuVersion.setIcon(new ImageIcon(
				SecondaryWindow_AT.class.getResource("/ies/morilla/ibanez/jesus/boletin7/assets/version_icon.png")));
		menuVersion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		// Se añade el menú "Versión" al menú "Información"
		menuInformation.add(menuVersion);
		
		// Se añade el menú "Información" al menú principal
		menuBar.add(menuInformation);

		// Se añade un ActionListener al menú "Versión" para mostrar información de la
		// aplicación al hacer clic
		menuVersion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.informationApp();
			}
		});
		
		// Atajo de teclado para la versión (Ctrl + I)
		menuVersion.registerKeyboardAction(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        controller.informationApp();
		    }
		},menuVersion.getAccelerator(), JComponent.WHEN_IN_FOCUSED_WINDOW);

		// Método que devuelve la barra de menú construida
		return menuBar;
	}

	// Métodos para obtener y establecer información de tareas y la interfaz de
	// usuario
	public String getTitleTask() {
		return titleField.getText();
	}

	public String getDescription() {
		return new String(taDescription.getText());
	}

	public String getDate() {
		Date utilDate = calendar.getDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = dateFormat.format(utilDate);
		return formattedDate;
	}

	public String getStatus() {
		String selectedStatus = choiceStatus.getSelectedItem();
		return selectedStatus;
	}

	public void setTitleTask(String title) {
		titleField.setText(title);
	}

	public void setDescription(String description) {
		taDescription.setText(description);
	}

	// Método para establecer la fecha en el calendario
	public void setDate(String date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parsedDate = dateFormat.parse(date);
			calendar.setDate(parsedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// Método para establecer la fecha actual en el calendario
	public void setTodayDate() {
		Date currentDate = new Date();
		calendar.setDate(currentDate);
	}

	// Método para establecer el estado en la lista desplegable de estados
	public void setStatus(String status) {
		choiceStatus.select(status);
	}

	// Métodos para obtener botones y lista de tareas
	public JButton getBtnAddTask() {
		return this.btnAddTask;
	}

	public JButton getbtnEditTask() {
		return this.btnEditTask;
	}

	public JList<Task> getTaskList() {
		return this.taskList;
	}

	// Método para establecer el controlador de la interfaz de usuario
	public void setController(Controller_SecondaryWindowAT controller) {
		this.controller = controller;
	}
}