/**
 * The third strategy of cryptography methods, using transpositions to 
 * change the order of letters
 * @author Xijie Guo
 *
 */
public class RailFenceCipher extends CryptographyStrategy {

	/**
	 * Encrypt the plain text in accordance with rail fence cipher method
	 * @param plainText the plain text to be encrypted
	 */
	protected String encrypt(String plainText) {
		//Create a railFence array with 3 rows and text-length number of columns to hold letters 
		String[][] railFence = createRailFenceArray(plainText);
		
		//Place each letter in their right position on rail fence 
		placeLetterForEncrypt(railFence, plainText);
		
		//Get the text after encryption
		String encryptedText = readEncryptText(railFence);
		return encryptedText;
	}
	
	/**
	 * Place each letter according to the rail fence cipher rule for later encryption
	 * @param railFence represents railFence array  
	 * @param plainText the plain text to be decrypted 
	 */
	private void placeLetterForEncrypt(String[][] railFence, String plainText) {
		//Convert a string to string array
		String[] input = plainText.split("");
		
		//If we should keep placing letters downward
		boolean directionDown = false;
		//Initialize the position to start placing letters at row 0, col 0
		int row = 0;
		int col = 0;
		for(int i = 0; i < plainText.length(); i++) {
			//On the first row, should go down
			if(row == 0) {
				directionDown = true;
			}
			//On the last row, shouldn't keep going down
			if(row == 2) {
				directionDown = false;
			}
			//Place letter in the right position
			railFence[row][col++] = input[i];
			
			//Change to a differenct row by checking direction
			row=(directionDown)? row+1 : row-1;
		}
	}
	
	/**
	 * Generate a rail fence array with 3 rows and text-length number of columns
	 * @param length the length of the plain text String
	 * @return the railFence string
	 */
	private String[][] createRailFenceArray(String plainText) {
		String[][] railFence = new String[3][plainText.length()];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < plainText.length(); j++) {
				railFence[i][j] = "";
			}
		}
		return railFence;
	}
	
	/**
	 * After encryption, go through the whole array and generate the right encrypted text
	 * @param railFence a string array with 3 rows and text-length number of columns
	 * @param length represents the length of the plain text
	 * @return the text after encryption
	 */
	private String readEncryptText(String[][] railFence) {
		String encryptedText = "";
		//Loop through the rail fence array to generate the encrypted text
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < railFence[i].length; j++) {
				encryptedText += railFence[i][j];
			}
		}
		return encryptedText;
	}

	/**
	 * Decrypt the encrypted text in accordance with rail fence cipher
	 * @param cipherText the encrypted text to be decrypted
	 */
	protected String decrypt(String cipherText) {
		//Initiazlize a rail fence array
		String[][] railFence = new String[3][cipherText.length()];
		
		//Convert ciper text string to string array
		String[] output = cipherText.split("");
		
		//Place each letter in their right position for later decryption
		placeLetterForDecrypt(output, railFence);
		
		//Get the text after decryption
		String decryptedText = readDecryptText(cipherText, railFence);
		return decryptedText;
	}
	
	/**
	 * Place each letter at right position in rail fence array according to some observed pattern
	 * @param output
	 * @param railFence the rail fence array
	 */
	private void placeLetterForDecrypt(String[] output, String[][] railFence) {
		//index number of the converted string array 
		int index = 0;
		//Column index of the third rail
		int thirdRailCol = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < output.length; j++) {
				//On the first rail, positions that contain corresponding letters 
				//are columns 0, 4, 8...(multiples of 4)
				if(i == 0 && j % 4 == 0) {
					railFence[i][j] = output[index++];
				}
				//On the second rail, positions that contain corresponding letters 
				//are columns 1, 3, 5, 7...(all odd numbers)
				else if(i == 1 && j % 2 != 0) {
					railFence[i][j] = output[index++];
				}
				//On the third rail, positions that contain corresponding letters 
				//are columns 2, 6, 10...(2 + 4k) where k start from 0
				else if(i == 2) {
					if(j == 2 + thirdRailCol * 4) {
						railFence[i][j] = output[index++];
						//Increase the value of k by 1 each time after placing the letter on the third rail
						thirdRailCol++;
					}
				}
			}
		}
	}
	
	/**
	 * Read the text in a zigzag manner after decryption
	 * @param cipherText the text to be decrypted
	 * @param railFence the rail fence array
	 * @return the text after decryption
	 */
	private String readDecryptText(String cipherText, String[][] railFence) {
		String decryptedText = "";
		//If we should keep placing letters downward
		boolean directionDown = false;
		int row = 0;
		int col = 0;
		for(int i = 0; i < cipherText.length(); i++) {
			//On the first row, should go down
			if(row == 0) {
				directionDown = true;
			}
			//On the last row, shouldn't keep going down
			if(row == 2) {
				directionDown = false;
			}
			//Update the decrypted text 
			decryptedText += railFence[row][col++];
			
			//Change to a differenct row by checking direction
			row=(directionDown)? row+1 : row-1;
			
		}
		return decryptedText;
	}
	
}
