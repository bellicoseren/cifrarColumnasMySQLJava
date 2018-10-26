package bellicose.com.usuario_cifrado.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;


public class Cifrado {
	/*
	 * @const algoritmo. Algoritmos para cifrar: AES, DES, RSA
	 * @const cI Modo de cifrado
	 */
	private final static String algoritmo = "AES";
	private final static String cI = "AES/CBC/PKCS5Padding";
	
	/*
	 * Funcion que recibe la cadena a cifrar, la llave y el vector
	 * @param texto Texto a cifrar
	 * @param clave Llave en tipo String
	 * @param vi Vector de inicialización
	 * @return texto cifrado
	 * throws Exception Pueden ser: NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException
	 */
	public static String cifrar(String clave, String vi, String texto) throws Exception {
		Cipher cipher = Cipher.getInstance(cI);
		SecretKeySpec sKeySpec = new SecretKeySpec(clave.getBytes(), algoritmo);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(vi.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, ivParameterSpec);
		byte[] cifrado = cipher.doFinal(texto.getBytes());
		
		return new String(encodeBase64(cifrado));
	}
	/*
	 * Funcion que recibe la cadena cifrada, la llave y el vector de inicialización
	 * @param texto texto a cifrar
	 * @param clave llave en tipo String
	 * @param vi vector de inicialización a utilizar
	 * @return texto cifrado
	 * throws Exception pueden ser: NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException
	 */
	public static String decifrar(String clave, String vi, String cifrado) throws Exception {
		Cipher cipher = Cipher.getInstance(cI);
		SecretKeySpec sKeySpec = new SecretKeySpec(clave.getBytes(), algoritmo);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(vi.getBytes());
		byte[] cadenaCifrada = decodeBase64(cifrado);
		cipher.init(Cipher.DECRYPT_MODE, sKeySpec, ivParameterSpec);
		byte[] decifrado = cipher.doFinal(cadenaCifrada);
		
		return new String(decifrado);
	}
	
}
