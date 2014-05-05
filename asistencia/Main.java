package asistencia;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		if ((Globals.DB = new Database()) != null) {
			try {
				Display display = Display.getDefault();
				MainView shell = new MainView(display);
				shell.open();
				shell.layout();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			MessageBox error = new MessageBox(new Shell(Display.getDefault()), SWT.OK);
			error.setText("Error");
			error.setMessage("Hubo un error al conectar a la base de datos." +
					" Cierre la aplicación y vuelva a intentarlo.");
			error.open();
		}
	}

}
