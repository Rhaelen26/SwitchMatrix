import com.ati.matrix.Matrix;
import com.ati.stepfinder.StepFinder;
import com.ati.timeprinter.TimePrinter;

public class Main {

	public static void main(String[] args) {
		Matrix matrix=new Matrix();
		
		//matrix.initializeMatrix(); 
	
		//for quick tests
		createEasyMatrix(matrix);
		
		//#1
		matrix.printMatrix();
		System.out.println();

		//#2
		TimePrinter.printCurrentTime();
		System.out.println();
		
		//#3 IMPORTANT: The program does not check if the initial matrix is "good" or not
		//	The description of the task implicates that every solution needs at least a step so I left the "0 step solution" out
		StepFinder finder=new StepFinder(matrix);
		finder.findSteps();
		
	}

	private static void createEasyMatrix(Matrix matrix) {
		matrix.press(0, 0);
		matrix.press(5, 0);
		matrix.press(2, 1);
		matrix.press(4, 3);
		matrix.press(1, 1);
		matrix.press(3, 3);
		matrix.press(1, 5);
		
	}

}
