
package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.util.StringTokenizer;
import calculator.UNumber;
import calculator.BusinessLogic;

/**
 * <p> Title: UserInterface Class. </p>
 * date 1st April 2020
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 *
 * <p> Copyright: Lynn Robert Carter, Mohit Â© 2017 </p>
 *

 * @version 4.01	2020-01-26 The JavaFX-based GUI for the implementation of a  integer calculator
 * @version 4.02	2020-02-16 The JavaFX-based GUI implementation of a double calculator with Square root
 * @version 4.0.3	2020-03-30 The JavaFX-based GUI implementation of a double calculator with FSM
 * @version 4.0.4	2020-04-24 The JavaFX-based GUI implementation of a double calculator with ErrorTerm
 * @version 4.0.5	2020-09-13 The JavaFX-based UNumber Calculator with square root.
 */

public class UserInterface {

	/**********************************************************************************************

	Attributes

	**********************************************************************************************/

	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 40;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 3;

	// These are the application values required by the user interface
	private Label label_DoubleCalculator = new Label("Double Calculator With Error Term");
	private Label label_Operand1 = new Label("First operand");
	private Label label_symbol1 = new Label("\u00B1");
	private Label label_symbol2 = new Label("±");
	private Label label_symbol3 = new Label("±");
	private TextField text_Operand1 = new TextField();
	private Label label_Operand1errorterm=new Label("Error term");
	private TextField text_Operand1errorterm = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();
	private Label label_Operand2errorterm=new Label("Error term");
	private TextField text_Operand2errorterm = new TextField();
	private Label label_Result = new Label("Result");
	private TextField text_Resulterrorterm = new TextField();
	private TextField text_Result = new TextField();
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("X");
	private Button button_Div = new Button("\u00F7");
	private Button button_sqrt = new Button("\u221A");
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand2 = new Label("");
	private Label label_errOperand1errorterm = new Label("");
	private Label label_errOperand2errorterm = new Label("");
	private Label label_Resulterrorterm = new Label("");
	private Label label_errResult = new Label("");
	private TextFlow errMeasuredValue1;
    private Text errMVPart1 = new Text();
    private Text errMVPart2 = new Text();
    private TextFlow errMeasuredValue2;
    private Text errMVPart3 = new Text();
    private Text errMVPart4 = new Text();
    private TextFlow errerrorterm1;
    private TextFlow errerrorterm2;
    private Text errETPart1 = new Text();
    private Text errETPart2 = new Text();
    private Text errETPart3 = new Text();
    private Text errETPart4 = new Text();

	// If the multiplication and/or division symbols do not display properly, replace the
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode  representation of those characters and will
	// work regardless of the underlying hardware being used.

	private double buttonSpace;		// This is the white space between the operator buttons.

	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();


	/**********************************************************************************************

	Constructors

	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {

		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;

		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_DoubleCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 40);

		setupLabelUI(label_symbol1, "Arial", 30, 150,  Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 80,  65);

		setupLabelUI(label_symbol2, "Arial", 30, 150, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 80,  165);

		setupLabelUI(label_symbol3, "Arial", 30,150,  Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 80,  285);


		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH/2 +30, Pos.BASELINE_LEFT, 10, 70, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		text_Operand1.setStyle("-fx-text-fill:black;");
		// Move focus to the second operand when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus(); });


		// Establish an error message for the first operand just above it with, left aligned
		label_errOperand1.setTextFill(Color.RED);
		label_errOperand1.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1, "Arial", 14, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 126);
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 145);

		// Establish the Error Term textfield for the first operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1errorterm, "Arial", 18, 150, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2+140, 70, true);
		text_Operand1errorterm.textProperty().addListener((observable, oldValue, newValue)
				-> {setOperand1(); });

		// Establish an error message for the first operand Error Term, left aligned
		label_errOperand1errorterm.setTextFill(Color.RED);
		label_errOperand1errorterm.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1errorterm, "Arial", 14, Calculator.WINDOW_WIDTH-10-10, Pos.BASELINE_LEFT,
					    Calculator.WINDOW_WIDTH/2- 150, 126);



		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH/2 +30, Pos.BASELINE_LEFT, 10, 170, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		text_Operand2.setStyle("-fx-text-fill:black;");
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });

		// Establish an error message for the second operand just above it with, left aligned
		label_errOperand2.setTextFill(Color.RED);
		label_errOperand2.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand2, "Arial", 14, Calculator.WINDOW_WIDTH/150-10, Pos.BASELINE_LEFT, 22, 220);

		// Establish the Error Term textfield for the second operand.  If anything changes, process
				// all fields to ensure that we are ready to perform as soon as possible.
				setupTextUI(text_Operand2errorterm, "Arial", 18, 150, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2+140, 170, true);
				text_Operand2errorterm.textProperty().addListener((observable, oldValue, newValue)
						-> {setOperand2(); });

				// Establish an error message for the first operand Error Term, left aligned
						label_errOperand2errorterm.setTextFill(Color.RED);
						label_errOperand2errorterm.setAlignment(Pos.BASELINE_RIGHT);
						setupLabelUI(label_errOperand2errorterm, "Arial", 14, Calculator.WINDOW_WIDTH-10-10, Pos.BASELINE_LEFT,
									    Calculator.WINDOW_WIDTH/2- 150, 126);

		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 250);

		// Establish the result output field.  It is not editable, so the text can be selected and copied,
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 18, Calculator.WINDOW_WIDTH/2 +30, Pos.BASELINE_LEFT, 10, 290, false);
		text_Result.setStyle("-fx-text-fill:black;");

		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errResult, "Arial", 18, Calculator.WINDOW_WIDTH-20, Pos.BASELINE_LEFT, 400, 205);
		label_errResult.setTextFill(Color.RED);

		// Establish the Error Term textfield for the second operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Resulterrorterm, "Arial", 18, 150, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2+140, 290, true);
		// text_Resulterrorterm.textProperty().addListener((observable, oldValue, newValue)
//				-> {setOperand1(); });

		// Establish an error message for the first operand Error Term, left aligned
				//label_Resulterrorterm.setTextFill(Color.RED);
			//	label_Resulterrorterm.setAlignment(Pos.BASELINE_RIGHT);
			//	setupLabelUI(label_Resulterrorterm, "Arial", 14, Calculator.WINDOW_WIDTH-10-10, Pos.BASELINE_LEFT,
			//				    Calculator.WINDOW_WIDTH/2- 150, 126);

		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1* buttonSpace-BUTTON_OFFSET-60 , 360);
		button_Add.setStyle("-fx-text-fill:black;");
		button_Add.setOnAction((event) -> { addOperands(); });

		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET-40, 360);
		button_Sub.setStyle("-fx-text-fill:black;");
		button_Sub.setOnAction((event) -> { subOperands(); });

		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET-40, 360);
		button_Mpy.setStyle("-fx-text-fill:black;");
		button_Mpy.setOnAction((event) -> { mpyOperands(); });

		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET-40, 360);
		button_Div.setStyle("-fx-text-fill:black;");
		button_Div.setOnAction((event) -> { divOperands(); });

		// Establish the square root "sqrt" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET-40, 360);
		button_sqrt.setStyle("-fx-text-fill:black;");
		button_sqrt.setOnAction((event) -> { sqrtOperands(); });

		// Error Message for the Measured Value for operand 1
		errMVPart1.setFill(Color.BLACK);
		errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		errMVPart2.setFill(Color.RED);
		errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		errMeasuredValue1 = new TextFlow(errMVPart1, errMVPart2);
		errMeasuredValue1.setMinWidth(Calculator.WINDOW_WIDTH-10);
		errMeasuredValue1.setLayoutX(22);
		errMeasuredValue1.setLayoutY(100);

		// Error Message for the Error Term for operand 1
	    errETPart1.setFill(Color.BLACK);
	    errETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errETPart2.setFill(Color.RED);
	    errETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errerrorterm1 = new TextFlow(errETPart1, errETPart2);
		// Establish an error message for the first operand just above it with, left aligned
		errerrorterm1.setMinWidth(Calculator.WINDOW_WIDTH-10);
		errerrorterm1.setLayoutX(Calculator.WINDOW_WIDTH/2+150);
		errerrorterm1.setLayoutY(100);

		// Error Message for the Measured Value for operand 2
		errMVPart3.setFill(Color.BLACK);
		errMVPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		errMVPart4.setFill(Color.RED);
		errMVPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		errMeasuredValue2 = new TextFlow(errMVPart3, errMVPart4);
		errMeasuredValue2.setMinWidth(Calculator.WINDOW_WIDTH-10);
		errMeasuredValue2.setLayoutX(22);
		errMeasuredValue2.setLayoutY(200);

		// Error Message for the Error Term for operand 2
	    errETPart3.setFill(Color.BLACK);
	    errETPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errETPart4.setFill(Color.RED);
	    errETPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errerrorterm2 = new TextFlow(errETPart3, errETPart4);
		// Establish an error message for the first operand just above it with, left aligned
		errerrorterm2.setMinWidth(Calculator.WINDOW_WIDTH-10);
		errerrorterm2.setLayoutX(Calculator.WINDOW_WIDTH/2 +150);
		errerrorterm2.setLayoutY(200);

		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_DoubleCalculator, label_Operand1, text_Operand1, label_errOperand1, label_symbol1, label_symbol2,
				label_symbol3,label_Operand2, text_Operand2, label_errOperand2, label_Result, text_Result, label_errResult, label_errOperand1errorterm,
				label_errOperand2errorterm, label_Resulterrorterm, text_Operand1errorterm ,text_Operand2errorterm, button_Add, button_Sub,
				errerrorterm1, errerrorterm2, button_Mpy, button_Div, text_Resulterrorterm, button_sqrt, errMeasuredValue1, errMeasuredValue2);
	}


		/**********
	   * Private local method to initialize the standard fields for a label
		 */
		private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);
		}

		/**********
		 * Private local method to initialize the standard fields for a text field
		 */
		private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);
		t.setEditable(e);
		}

		/**********
	 * Private local method to initialize the standard fields for a button
	 */
		private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);
		}


			/**********************************************************************************************

			User Interface Actions

			**********************************************************************************************/

			/**********
			 * Private local method to set the value of the first operand given a text value. The method uses the
			 * business logic class to perform the work of checking the string to see it is a valid value and if
			 * so, saving that value internally for future computations. If there is an error when trying to convert
			 * the string into a value, the called business logic method returns false and actions are taken to
			 * display the error message appropriately.
			 */
			private void setOperand1() {
				text_Result.setText("");                    // Any change of an operand probably invalidates
//				text_Resulterrorterm("");
				errMVPart1.setText("");
				errMVPart2.setText("");
				label_Result.setText("Result");						// the result, so we clear the old result.
				label_errResult.setText("");
				label_errOperand1errorterm.setText("");
				errETPart1.setText("");
				errETPart2.setText("");
				performGo();
				if (perform.setOperand1(text_Operand1.getText(), text_Operand1errorterm.getText() )) {	// Set the operand and see if there was an error
					label_errOperand1.setText("");					// If no error, clear this operands error
					if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
						label_errOperand2.setText("");				// as well.

				}
				else
					performGo();
			}





			/**********
			 * Private local method to set the value of the second operand given a text value. The logic is exactly the
			 * same as used for the first operand, above.
			 */
			private void setOperand2() {
				text_Result.setText("");								// See setOperand1's comments. The logic is the same!

				errMVPart3.setText("");
				errMVPart4.setText("");
				label_Result.setText("Result");
				label_errResult.setText("");
				label_errOperand2errorterm.setText("");
				errETPart3.setText("");
				errETPart4.setText("");
				performGo1();
				label_Result.setText("Result");
				label_errResult.setText("");
				if (perform.setOperand2(text_Operand2.getText(), text_Operand2errorterm.getText())) {
					label_errOperand2.setText("");
					if (text_Operand1.getText().length() == 0)
						label_errOperand1.setText("");
					}
				else
					performGo1();
			}




			/**********
			 * This method is called when an binary operation button has been pressed. It assesses if there are issues
			 * with either of the binary operands or they are not defined. If not return false (there are no issues)
			 *
			 * @return	True if there are any issues that should keep the calculator from doing its work.
			 */
			private boolean binaryOperandIssues() {
				String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
				String errorMessage2 = perform.getOperand2ErrorMessage();
				if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
					label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
					if (errorMessage2.length() > 0) {					// Check the second and display it if there is
						label_errOperand2.setText(errorMessage2);		// and error with the second as well.
						return true;										// Return true when both operands have errors
					}
					else {
						return true;										// Return true when only the first has an error
					}
				}
				else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
					label_errOperand2.setText(errorMessage2);			// operand. If non-empty string, display the error
					return true;											// message and return true... the second has an error
				}														// Signal there are issues

				// If the code reaches here, neither the first nor the second has an error condition. The following code
				// check to see if the operands are defined.
				if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
					label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
					if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
						label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed
						return true;										// Signal there are issues
					}
					return true;
				} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the second. Both
					label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
					return true;											// Signal there are issues
				}

				return false;											// Signal there are no issues with the operands
			}

			/*******************************************************************************************************
			 * This portion of the class defines the actions that take place when the various calculator
			 * buttons (add, subtract, multiply, and divide) are pressed.
			 */

			/**********
			 * This is the add routine
			 *
			 */
			private void addOperands(){
				// Check to see if both operands are defined and valid
				if (binaryOperandIssues()) 									// If there are issues with the operands, return

				return;												// without doing the computation

				// If the operands are defined and valid, request the business logic method to do the addition and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.addition();						// Call the business logic add method
				label_errResult.setText("");			// Reset any result error messages from before
				StringTokenizer St = new StringTokenizer(theAnswer,":");
				if (theAnswer.length() > 0) { // Check the returned String to see if it is okay

					text_Result.setText(St.nextToken());			// If okay, display it in the result field and
					text_Resulterrorterm.setText(St.nextToken());
					label_Result.setText("Sum");								// change the title of the field to "Sum"
				}
				else {														// Some error occurred while doing the addition.

					text_Result.setText("");									// Do not display a result if there is an error.
					text_Resulterrorterm.setText("");
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
				}

			}



			/**********
			 * This is the subtract routine
			 *
			 */
			private void subOperands(){
				if (binaryOperandIssues()) 									// If there are issues with the operands, return
					return;													// without doing the computation

				// If the operands are defined and valid, request the business logic method to do the subtraction and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.subtraction();						// Call the business logic sub method
				StringTokenizer St = new StringTokenizer(theAnswer,":");
				label_errResult.setText("");									// Reset any result error messages from before
				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					text_Result.setText(St.nextToken());							// If okay, display it in the result field and
					text_Resulterrorterm.setText(St.nextToken());
					label_Result.setText("Difference");								// change the title of the field to "Difference"
				}
				else {														// Some error occurred while doing the subtraction.
					text_Result.setText("");									// Do not display a result if there is an error.
					text_Resulterrorterm.setText("");
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
			}
			}

			/**********
			 * This is the multiply routine
			 *
			 */
			private void mpyOperands(){
				if (binaryOperandIssues()) 									// If there are issues with the operands, return
					return;													// without doing the computation

				// If the operands are defined and valid, request the business logic method to do the multiplication and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.multiplication();						// Call the business logic add method
				label_errResult.setText("");									// Reset any result error messages from before
				StringTokenizer St = new StringTokenizer(theAnswer,":");
				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					text_Result.setText(St.nextToken());							// If okay, display it in the result field and
					text_Resulterrorterm.setText(St.nextToken());
					label_Result.setText("Product");								// change the title of the field to "Product"
				}
				else {														// Some error occurred while doing the multiplication.
					text_Result.setText("");									// Do not display a result if there is an error.
					text_Resulterrorterm.setText("");
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
				}
			}


			private void divOperands(){
				if (binaryOperandIssues()) 									// If there are issues with the operands, return
					return;													// without doing the computation

				// If the operands are defined and valid, request the business logic method to do the division and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.division();						// Call the business logic add method
				label_errResult.setText("");									// Reset any result error messages from before

				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					StringTokenizer St = new StringTokenizer(theAnswer,":");
					text_Result.setText(St.nextToken());							// If okay, display it in the result field and
					text_Resulterrorterm.setText(St.nextToken());
					label_Result.setText("Quotient");								// change the title of the field to "Quotient"


				 if (BusinessLogic.zero) {
					text_Result.setText("");
					label_Result.setText("Error");
					label_errResult.setText("error");

				}}
				else {														// Some error occurred while doing the division.
					text_Result.setText("");									// Do not display a result if there is an error.
					text_Resulterrorterm.setText("");
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
				}
			}
			private void sqrtOperands(){
				//if (binaryOperandIssues()) 									// If there are issues with the operands, return

				//	return;
			//	 Check to see if 1st operands are defined and valid
														// without doing the computation
		   	   // If the operand are defined and valid, request the business logic method to do the square root calculation and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.squareroot();	// Call the business logic square root function method
			//text_Operand2.setText("Second Operand Not Considered for squareroot");// set the error message
			label_errResult.setText("");									// Reset any result error messages from before
			if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
				text_Result.setText(theAnswer);
				// If okay, display it in the result field and

			label_Result.setText("Sqrt");								// change the title of the field to 'sqrt'
			}
			else {														// Some error occurred while doing the square root.
			text_Result.setText("");									// Do not display a result if there is an error.
				label_Result.setText("Result");							// Reset the result label if there is an error.
				label_errResult.setText(perform.getResultErrorMessage());
			}// Display the error message.
			}

	private void performGo() {
	String errMessage = CalculatorValue.checkMeasureValue(text_Operand1.getText());
	if (errMessage != "") {
	System.out.println(errMessage);
	label_errOperand1.setText(CalculatorValue.measuredValueErrorMessage);
	if (CalculatorValue.measuredValueIndexofError <= -1) return;
	String input = CalculatorValue.measuredValueInput;
	errMVPart1.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
	errMVPart2.setText("\u21EB");
}
else {
	errMessage = CalculatorValue.checkerrorterm(text_Operand1errorterm.getText());
	if (errMessage != "") {
		System.out.println(errMessage);
		label_errOperand1errorterm.setText(CalculatorValue.errortermErrorMessage1);
		if (CalculatorValue.errortermIndexofError1 <= -1) return;
		String input = CalculatorValue.errortermInput1;
		errETPart1.setText(input.substring(0, CalculatorValue.errortermIndexofError1));
		errETPart2.setText("\u21EB");
	}


}
}
	private void performGo1() {
		String errMessage = CalculatorValue.checkMeasureValue(text_Operand2.getText());
		if (errMessage != "") {
			System.out.println(errMessage);
			label_errOperand2.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			errMVPart3.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			errMVPart4.setText("\u21EB");
		}
		else {
			errMessage = CalculatorValue.checkerrorterm(text_Operand2errorterm.getText());
			if (errMessage != "") {
				System.out.println(errMessage);
				label_errOperand2errorterm.setText(CalculatorValue.errortermErrorMessage1);
				if (CalculatorValue.errortermIndexofError1 <= -1) return;
				String input = CalculatorValue.errortermInput1;
				errETPart3.setText(input.substring(0, CalculatorValue.errortermIndexofError1));
				errETPart4.setText("\u21EB");
			}

}
}

}