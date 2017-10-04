import javax.swing.JFrame;

/**
 * Use this class to run the whole prgram
 * @author Xijie Guo
 *
 */
public class CryptographyApplication {
	
	/**
	 * main method to start the application
	 * @param args represent String arguments
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setTitle("Cryptography Program");
		
		frame.setSize(800, 700);
		
		frame.add(new CryptographyController());
		
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
