/**
 * The fourth cipher method, swaping the first letter in the word with the last letter,
 * swaping the second letter with the second to last letter, and following this pattern
 * @author Xijie Guo
 *
 */
public class AtbashCipher extends CryptographyStrategy {
	
	/**
	 * Encrypt the text by swaping every pair of letters in accordance with atbash cipher method
	 * @param plainText the plain text to be encrypted
	 */
	protected String encrypt(String plainText) {
		//Convert an input String into a String array
		String[] input = plainText.split("");
		int inputLength = input.length;
		
		//Encrypt the whole String array by looping through the whole String array and swaping 
		//every pair of letters
		for(int i = 0; i < inputLength / 2; i++) {
			swapTwoLetters(input, i, inputLength - i - 1);
		}
		return readTextAfterSwap(inputLength, input);
	}
	
	/**
	 * Decrypt the text by swaping every pair of letters in accordance with atbash cipher method
	 * @param cipherText the encrypted text to be decrypted
	 */
	protected String decrypt(String cipherText) {
		//Convert an output String into a String array
		String[] output = cipherText.split("");
		int outputLength = cipherText.length();
		
		//Decrypt the whole String array by looping through the whole String array and swaping 
		//every pair of letters
		for(int i = 0; i < outputLength / 2; i++) {
			swapTwoLetters(output, i, outputLength - i - 1);
		}
		return readTextAfterSwap(outputLength, output);
	}
	
	/**
	 * Swap two letters in accordance with atbash cipher rule
	 * @param inputArray represents the array in which swapping happens
	 * @param index1 the index of the first letter needed to be swapped
	 * @param index2 the index of the second letter needed to be swapped
	 */
	private void swapTwoLetters(String[] inputArray, int index1, int index2) {
		String temp = inputArray[index1];
		inputArray[index1] = inputArray[index2];
		inputArray[index2] = temp;
	}

	/**
	 * Read the text after decryption
	 * @param length the array length
	 * @param array the String array which stores letters
	 * @return the text after implementing atbash cipher
	 */
	private String readTextAfterSwap(int length, String[] array) {
		String resultText = "";
		for(int i = 0; i < length; i++) {
			resultText += array[i];
		}
		return resultText;
	}
}
