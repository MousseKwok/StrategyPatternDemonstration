/**
 * The first strategy of cipher method, directly encryting or decrypting
 * by coping the original text
 * @author Xijie Guo
 *
 */
public class CopyCipher extends CryptographyStrategy {

	/**
	 * Encrypt the text according to the copy cipher
	 * @param plainText the plain text to be encrypted
	 */
	protected String encrypt(String plainText) {
		return plainText;
	}
	
	/**
	 * Decrypt the text in accordance with the copy cipher
	 * @param encrypted text to be decrypted
	 */
	protected String decrypt(String cipherText) {
		return cipherText;
	}

}
