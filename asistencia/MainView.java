package asistencia;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class MainView extends Shell {
	Shell shell;
	Menu menuBar, fileMenu;
	MenuItem fileMenuHeader, fileSaveItem, fileExitItem;
	Image activeIcon;
	Image inactiveIcon;
	boolean isScanning = false;
	Composite cmpScan;
	Composite cmpHome;
	Composite cmpManagement;
	Label camera;
	TabFolder tabFolder;
	TabItem tbtmHome;
	TabItem tbtmScan;
	TabItem tbtmManagement;
	TabItem tbtmReports;
	TabItem tbtmSettings;
	Button btnStopScan;
	Button btnStartScan;
	Label lblNewSession;
	Label lblOldSession;
	Table tblSubjects;
	TableColumn tbclSubjectName;
	TableColumn tbclSubjectPassword;
	TableColumn tbclSubjectEnrolments;
	Table tblStudents;
	TableColumn tbclStudentID;
	TableColumn tbclStudentName;
	TableColumn tbclStudentLastName;
	TableColumn tbclStudentMail;
	/**
	 * Create the shell.
	 * @param display
	 */
	public MainView(Display display) {
		super(display, SWT.SHELL_TRIM);
		shell = this;
		
		menuBar = new Menu(shell, SWT.BAR);
		
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("&File");
		
		fileMenu = new Menu(shell, SWT.DROP_DOWN);
		
		fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
		fileSaveItem.setText("&Save");
		
		fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
		fileExitItem.setText("E&xit");
		
		fileMenuHeader.setMenu(fileMenu);
		
		setMenuBar(menuBar);
		
		activeIcon = new Image(display, "activeicon.png");
		inactiveIcon = new Image(display, "inactiveicon.png");
		
		setLayout(new GridLayout(1, false));
		
		camera = new Label(shell, SWT.CENTER);
		camera.setLayoutData(new GridData(SWT.CENTER, SWT.BEGINNING, false, false, 1, 1));
	    camera.setBounds(0, 0, 100, 100);
	    camera.setImage(inactiveIcon);
		isScanning = false;
		
		tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		tbtmHome = new TabItem(tabFolder, SWT.NONE);
		tbtmHome.setText("Home");
		
		tbtmScan = new TabItem(tabFolder, SWT.NONE);
		tbtmScan.setText("Scan");
		
		tbtmManagement = new TabItem(tabFolder, SWT.NONE);
		tbtmManagement.setText("Management");
		
		tbtmReports = new TabItem(tabFolder, SWT.NONE);
		tbtmReports.setText("Reports");
		
		tbtmSettings = new TabItem(tabFolder, SWT.NONE);
		tbtmSettings.setText("Settings");
		
		//HOME
		cmpHome = new Composite(tabFolder, SWT.NONE);
		cmpHome.setLayout(new GridLayout(2, true));
		cmpHome.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Group grpData = new Group(cmpHome, SWT.BORDER);
		grpData.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpData.setText("Data:");
		
		Group grpData2 = new Group(cmpHome, SWT.BORDER);
		grpData2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpData2.setText("Data:");
		
		Group grpData3 = new Group(cmpHome, SWT.BORDER);
		grpData3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpData3.setText("Data:");
		
		Group grpData4 = new Group(cmpHome, SWT.BORDER);
		grpData4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpData4.setText("Data:");
		
		tbtmHome.setControl(cmpHome);
		
		//SCAN
		cmpScan = new Composite(tabFolder, SWT.NONE);
		cmpScan.setLayout(new GridLayout(1, true));
		cmpScan.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		btnStopScan = new Button(cmpScan, SWT.PUSH);
		btnStopScan.setText("Stop");
		btnStopScan.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				isScanning = false;
				setScanEnabled();
			}
		});
		
		lblNewSession = new Label(cmpScan, SWT.NONE);
		lblNewSession.setText("Start a new session:");
		
		lblOldSession = new Label(cmpScan, SWT.NONE);
		lblOldSession.setText("Continue from a previous session:");
		
		btnStartScan = new Button(cmpScan, SWT.PUSH);
		btnStartScan.setText("Start");
		btnStartScan.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				isScanning = true;
				setScanEnabled();
			}
		});
		
		setScanEnabled();
		
		tbtmScan.setControl(cmpScan);
		
		//MANAGEMENT
		cmpManagement = new Composite(tabFolder, SWT.NONE);
		cmpManagement.setLayout(new GridLayout(2, false));
		cmpManagement.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tblSubjects = new Table(cmpManagement, SWT.BORDER | SWT.V_SCROLL);
		tblSubjects.setHeaderVisible(true);
		tblSubjects.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tblSubjects.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event e) {
		          TableItem[] tbit = tblSubjects.getSelection();
		          System.out.println(tbit[0].getText(0));
		        }
		      });
		
		tbclSubjectName = new TableColumn(tblSubjects, SWT.NONE);
		tbclSubjectName.setText("Subject");
		tbclSubjectPassword = new TableColumn(tblSubjects, SWT.NONE);
		tbclSubjectPassword.setText("Password");
		tbclSubjectEnrolments = new TableColumn(tblSubjects, SWT.NONE);
		tbclSubjectEnrolments.setText("Nº Students");
		
		ResultSet rs = Globals.DB.query("SELECT s.name AS name, s.password AS password, COUNT(*) AS students " +
										"FROM register AS r, (SELECT name, password FROM subject) AS s " +
										"WHERE r.subject = s.name " +
										"GROUP BY s.name, s.password;");
		
		try {
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				TableItem tbit = new TableItem(tblSubjects, SWT.NONE);
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					tbit.setText(i, rs.getString(rsmd.getColumnName(i+1)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0, n = tblSubjects.getColumnCount(); i < n; i++) {
			tblSubjects.getColumn(i).pack();
		}
		
		tblStudents = new Table(cmpManagement, SWT.BORDER | SWT.V_SCROLL);
		tblStudents.setHeaderVisible(true);
		tblStudents.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tblStudents.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TableItem[] tbit = tblStudents.getSelection();
				System.out.println(tbit[0].getText(0) + " " + tbit[0].getText(1));
			}
		});
		
		tbclStudentID = new TableColumn(tblStudents, SWT.NONE);
		tbclStudentID.setText("ID");
		tbclStudentName = new TableColumn(tblStudents, SWT.NONE);
		tbclStudentName.setText("Name");
		tbclStudentLastName = new TableColumn(tblStudents, SWT.NONE);
		tbclStudentLastName.setText("Last name");
		tbclStudentMail = new TableColumn(tblStudents, SWT.NONE);
		tbclStudentMail.setText("Mail");
		
		ResultSet rs2 = Globals.DB.query("SELECT * FROM student;");
		
		try {
			while (rs2.next()) {
				ResultSetMetaData rsmd = rs2.getMetaData();
				TableItem tbit = new TableItem(tblStudents, SWT.NONE);
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					tbit.setText(i, rs2.getString(rsmd.getColumnName(i+1)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0, n = tblStudents.getColumnCount(); i < n; i++) {
			tblStudents.getColumn(i).pack();
		}
		
		tbtmManagement.setControl(cmpManagement);
		
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Control de asistencia");
		setSize(800, 600);
		setMinimumSize(800, 600);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	protected void setScanEnabled() {
		if (isScanning) {
			btnStartScan.setEnabled(false);
			btnStopScan.setEnabled(true);
			camera.setImage(activeIcon);
		} else {
			btnStartScan.setEnabled(true);
			btnStopScan.setEnabled(false);
			camera.setImage(inactiveIcon);
		}
	}
}
