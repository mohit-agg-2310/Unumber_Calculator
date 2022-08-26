
package calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*******
 * <p> Title: Calculator Class. </p>
 * date 4th April 2020
 * <p> Description: A JavaFX demonstrat4ion application and baseline for a sequence of projects </p>
 *
 * <p> Copyright: Lynn Robert Carter, Mohit © 2017 </p>
 *

 *
 *
 *  @version 4.01	2020-01-26 The JavaFX-based GUI for the implementation of a  integer calculator
 * @version 4.02	2020-02-16 The JavaFX-based GUI implementation of a double calculator with Square root
 * @version 4.0.3	2020-03-30 The JavaFX-based GUI implementation of a double calculator with FSM
 * @version 4.0.4	2020-04-24 The JavaFX-based GUI implementation of a double calculator with ErrorTerm
 * @version 4.0.5	2020-09-13 The JavaFX-based UNumber Calculator with square root.
 */


public class Calculator extends Application {

	public final static double WINDOW_WIDTH = 800;
	public final static double WINDOW_HEIGHT = 450;

	public UserInterface theGUI;

	/**********
	 * This is the start method that is called once the application has been loaded into memory and is
	 * ready to get to work.
	 *
	 * In designing this application I have elected to IGNORE all opportunities for automatic layout
	 * support and instead have elected to manually position each GUI element and its properties in
	 * order to exercise complete control over the user interface look and feel.
	 *
	 */
	@Override
	public void start(Stage theStage) throws Exception {

		theStage.setTitle("Lynn Robert Carter,Mohit");				// Label the stage (a window)
		Pane theRoot = new Pane();							// Create a pane within the window

		theGUI = new UserInterface(theRoot);					// Create the Graphical User Interface

		Scene theScene = new Scene(theRoot, WINDOW_WIDTH, WINDOW_HEIGHT);	// Create the scene

		theStage.setScene(theScene);							// Set the scene on the stage

		theStage.show();										// Show the stage to the user

		// When the stage is shown to the user, the pane within the window is visible.  This means that the
		// labels, fields, and buttons of the Graphical User Interface (GUI) are visible and it is now
		// possible for the user to select input fields and enter values into them, click on buttons, and
		// read the labels, the results, and the error messages.
	}



	/*******************************************************************************************************/

	/*******************************************************************************************************
	 * This is the method that launches the JavaFX application
	 *
	 */
	public static void main(String[] args) {						// This method may not be required
		launch(args);											// for all JavaFX applications using
	}															// other IDEs.
}
