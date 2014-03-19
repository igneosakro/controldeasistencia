package asistencia;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CustomDialog extends Dialog {
	
	//Student data
	Label lblDni;
	GridData dataDni;
	Text txtDni;
	
	Label lblFirstName;
	GridData dataFirstName;
	Text txtFirstName;
	
	Label lblLastName;
	GridData dataLastName;
	Text txtLastName;
	
	Label lblMail;
	GridData dataMail;
	Text txtMail;
	
	//Subject data
	Label lblSubjectName;
	GridData dataSubjectName;
	Text txtSubjectName;
	
	private Composite container;
	private Student student = null;
	private String subject = null;
	private int style;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public CustomDialog(Shell parentShell, int selectedStyle) {
		super(parentShell);
		style = selectedStyle;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	protected Control createDialogArea(Composite parent) {
		container = (Composite) super.createDialogArea(parent);
		
		switch (style) {
		case Globals.ADD_STUDENT:
			configureShell(container.getShell(), "Add a new student");
			addStudentForm();
			break;
		case Globals.ADD_SUBJECT:
			configureShell(container.getShell(), "Add a new subject");
			addSubjectForm();
			break;
		}
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	protected void configureShell(Shell newShell, String title) {
		super.configureShell(newShell);
		newShell.setText(title);
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
	protected void addStudentForm() {
		//container.setLayoutData(new GridData(GridData.FILL_BOTH));
		//container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		container.setLayout(new GridLayout(4, false));
		
		//Blank space
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
	    
	    //Student's dni
		new Label(container, SWT.NONE);
	  	lblDni = new Label(container, SWT.NONE);
	  	lblDni.setLayoutData(new GridData(SWT.RIGHT));
	  	lblDni.setText("DNI");
	  	dataDni = new GridData();
	  	dataDni.grabExcessHorizontalSpace = true;
	  	dataDni.horizontalAlignment = GridData.FILL;
	  	txtDni = new Text(container, SWT.BORDER);
	  	txtDni.setLayoutData(dataDni);
	  	new Label(container, SWT.NONE);
	  	
	  	//Blank space
	  	new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		//Student's first name
		new Label(container, SWT.NONE);
		lblFirstName = new Label(container, SWT.NONE);
	    lblFirstName.setText("First name");
	    dataFirstName = new GridData();
	    dataFirstName.grabExcessHorizontalSpace = true;
	    dataFirstName.horizontalAlignment = GridData.FILL;
	    txtFirstName = new Text(container, SWT.BORDER);
	    txtFirstName.setLayoutData(dataFirstName);
	    new Label(container, SWT.NONE);
	    
	    //Blank space
	    new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
	    
	    //Student's last name
		new Label(container, SWT.NONE);
		lblLastName = new Label(container, SWT.NONE);
	    lblLastName.setText("Last name");
	    dataLastName = new GridData();
	    dataLastName.grabExcessHorizontalSpace = true;
	    dataLastName.horizontalAlignment = GridData.FILL;
	    txtLastName = new Text(container, SWT.BORDER);
	    txtLastName.setLayoutData(dataLastName);
	    new Label(container, SWT.NONE);
	    
	    //Blank space
	    new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
	    
	    //Student's mail
		new Label(container, SWT.NONE);
	  	lblMail = new Label(container, SWT.NONE);
	  	lblMail.setText("Mail");
	  	dataMail = new GridData();
	  	dataMail.grabExcessHorizontalSpace = true;
	  	dataMail.horizontalAlignment = GridData.FILL;
	  	txtMail = new Text(container, SWT.BORDER);
	  	txtMail.setLayoutData(dataMail);
	  	new Label(container, SWT.NONE);
	}
	
	protected void addSubjectForm() {
		//container.setLayoutData(new GridData(GridData.FILL_BOTH));
		//container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		container.setLayout(new GridLayout(4, false));
				
		//Blank space
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		//Subject name
		new Label(container, SWT.NONE);
		lblSubjectName = new Label(container, SWT.NONE);
		lblSubjectName.setLayoutData(new GridData(SWT.RIGHT));
		lblSubjectName.setText("Name");
		dataSubjectName = new GridData();
		dataSubjectName.grabExcessHorizontalSpace = true;
		dataSubjectName.horizontalAlignment = GridData.FILL;
		txtSubjectName = new Text(container, SWT.BORDER);
		txtSubjectName.setLayoutData(dataSubjectName);
		new Label(container, SWT.NONE);
	}

	public void okPressed() {
		boolean error = false;
		
		switch (style) {
		case Globals.ADD_STUDENT:
			student = new Student();
			if (txtDni.getText() == "" || txtFirstName.getText() == "" || txtLastName.getText() == "" || txtMail.getText() == "") {
				error = true;
			} else {
				student.setDni(txtDni.getText());
				student.setDni(txtFirstName.getText());
				student.setDni(txtLastName.getText());
				student.setDni(txtMail.getText());
			}
			break;
		case Globals.ADD_SUBJECT:
			subject = txtSubjectName.getText();
			break;
		}
		
		if (error) {
			
		} else {
			super.okPressed();
		}
	}
	
	public Student getStudent() {
		return student;
	}
	
	public String getSubject() {
		return subject;
	}
	
}
