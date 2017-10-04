import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;


/**
 * This class is responsible for creating a user interface and displaying the 
 * encrypted and decrypted text on frame. This class can be seen as a combination
 * of GUI and strategy context class
 * @author Xijie Guo
 *
 */
@SuppressWarnings("serial")
public class CryptographyController extends JPanel implements ActionListener {

	private JTextArea plainTextArea;
	private JTextArea cipherArea;
	private JButton encrypt;
	private JButton decrypt;
	private JComboBox<String> methodMenu;
	//Represent current method
	private CryptographyStrategy currentStrategy;
	
	/**
	 * Constructor for CryptographyController class
	 */
	public CryptographyController() {
		super(new BorderLayout());
		createView();
	}

	/**
	 * Create the whole user interface including plain text area, cipher text area, 
	 * two buttons, and one method menu
	 */
	private void createView() {
		JPanel textAreas = new JPanel();
		textAreas.add(createPlainTextPanel());
		textAreas.add(createCipherTextPanel());
		textAreas.setLayout(new GridLayout(2, 1));
		add(textAreas, BorderLayout.CENTER);
		createBottomPart();
	}

	/**
	 * Create a panel to hold the plain text label and plain text area
	 * @return the panel containing plain text label and plain text area
	 */
	private JPanel createPlainTextPanel() {
		JLabel plainTextLabel = new JLabel();
		plainTextLabel.setText("Plain Text");
		plainTextArea = new JTextArea();
		//Switch to next line if the current line is on boundary
		plainTextArea.setLineWrap(true);
		plainTextArea.setWrapStyleWord(true);
		JPanel panel = new JPanel();
		panel.add(plainTextLabel);
		panel.add(plainTextArea);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}

	/**
	 * Create a panel to hold the cipher text label and cipher text area
	 * @return the plane containing cipher text label and cipher text area
	 */
	private JPanel createCipherTextPanel() {
		JLabel cipherLabel = new JLabel();
		cipherLabel.setText("Cipher Text");
		cipherArea = new JTextArea();
		//Swith to next line if the current line is on boundary
		cipherArea.setLineWrap(true);
		cipherArea.setWrapStyleWord(true);

		JPanel panel = new JPanel();
		panel.add(cipherLabel);
		panel.add(cipherArea);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}
	
	/**
	 * Create the bottom part of the user interface including buttons for encryption and 
	 * decryption and a method menu to select different cryptography methods
	 */
	private void createBottomPart() {
		encrypt = new JButton("encrypt");
		decrypt = new JButton("decrypt");
		String[] ciperOptions = {"Coper Ciper", "Caesar Ciper", "Rail Fence Ciper", "Atbash Cipher"};
		methodMenu = new JComboBox<String>(ciperOptions);
		
		encrypt.addActionListener(this);
		decrypt.addActionListener(this);
		methodMenu.addActionListener(this);

		JPanel bottomPanel = new JPanel();
		bottomPanel.add(encrypt);
		bottomPanel.add(decrypt);
		bottomPanel.add(methodMenu);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Determine which components the user is interacting with and 
	 * implement corresponding activities
	 * @param evt for different components the user can interact with
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object selectedButton = evt.getSource();
		//Get which kind of cipher method the user want to use and implement it
		int selectedIndex = methodMenu.getSelectedIndex();
		switch(selectedIndex) {
		case 0 :
			currentStrategy = new CopyCipher();
			encryptOrDecrypt(selectedButton, currentStrategy);
			break;
		case 1 :
			currentStrategy = new CaesarCipher();
			encryptOrDecrypt(selectedButton, currentStrategy);
			break;
		case 2 : 
			currentStrategy = new RailFenceCipher();
			encryptOrDecrypt(selectedButton, currentStrategy);
			break;
		case 3 : 
			currentStrategy = new AtbashCipher();
			encryptOrDecrypt(selectedButton, currentStrategy);
			break;
		default : 
			break;
		}
	}
	
	/**
	 * Determine if the user want to encrypt or decrypt
	 * @param selected for the component the user selects
	 * @param method for corresponding cipher methods
	 */
	private void encryptOrDecrypt(Object selected, CryptographyStrategy method) {
		if(selected == encrypt) {
			//Get the plain text, encrypt it, and display it on the cipher area
			String plainText = plainTextArea.getText();
			String encryptText = method.encrypt(plainText);
			cipherArea.setText(encryptText);
		}
		else if(selected == decrypt) {
			//Get the ciphered text, decrypt it, and display it on the plain text area
			String cipherText = cipherArea.getText();
			String decryptText = method.decrypt(cipherText);
			plainTextArea.setText(decryptText);
		}
	}
	
	/**
	 * main method to print the result on the console to double check if all
	 * cipher methods work well
	 * @param args the text String we pass in 
	 */
	public static void main(String[] args) {
		CryptographyStrategy copy = new CopyCipher();
		System.out.println(copy.encrypt("a third algorithm"));
		System.out.println(copy.decrypt("a third algorithm") + "\n");
		
		CryptographyStrategy caesar = new CaesarCipher();
		System.out.println(caesar.encrypt("a third algorighm"));
		System.out.println(caesar.decrypt("d#wklug#dojruljkp") +"\n");
		
		CryptographyStrategy railFence = new RailFenceCipher();
		System.out.println(railFence.encrypt("a third algorithm"));
		System.out.println(railFence.decrypt("aiarm hr loihtdgt") +"\n");
		

		CryptographyStrategy atbash = new AtbashCipher();
		System.out.println(atbash.encrypt("abcdefghijklmn"));
		System.out.println(atbash.decrypt("nmlkjihgfedcba"));
	}
}
