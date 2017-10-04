/**
 * The second strategy of cryptography methods, encrypting or decryptig by
 * shifting each character 3 positions in ASCII 
 * @author Xijie Guo
 *
 */
public class CaesarCipher extends CryptographyStrategy {

	/**
	 * Encrypt the plain text in accordance with caesar cipher method
	 * @param plainText the plain text to be encrypted 
	 */
	protected String encrypt(String plainText) {
		//Pass in the plainText and -3 as parameters to shift letters in the plain 
	    //text array 3 positions backward
		String encryptText = newTextAfterShift(plainText, 3);
		return encryptText;
	}
	
	/**
	 * Decrypt the text in accordance with caesar cipher method
	 * @param cipherText the encrypted text to be decrypt
	 */
	protected String decrypt(String cipherText) {
		//Pass in the cipherText and -3 as parameters to shift letters in the cipher 
		//text array 3 positions forward
		String decryptedText = newTextAfterShift(cipherText, -3);
		return decryptedText;
	}
	
	/**
	 * Generate a new text based on the given text and 3 positions 
	 * @param givenText represents the text the user need to encrypt/decrypt
	 * @param shiftKey represents the number of positions each character should shift in ASCII
	 * @return a new text after encryption/decryption
	 */
	private String newTextAfterShift(String givenText, int shiftKey) {
		String resultText = "";
		//Loop through the whole text array to change positions by 3 of each letter
		for(int i = 0; i < givenText.length(); i++) {
			char currentChar = givenText.charAt(i);
			char resultChar = (char) ((currentChar + shiftKey) % 256);
			resultText += resultChar;
		}
		return resultText;
	}
}

