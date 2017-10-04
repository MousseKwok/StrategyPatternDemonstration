/**
 * Abstract strategy class. 
 * @author Xijie Guo
 *
 */
public abstract class CryptographyStrategy {
	
	protected abstract String encrypt(String plainText);

	protected abstract String decrypt(String cipherText);

}
