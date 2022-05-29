package blockchain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {
	public KeyPairGenerator public_key;
	public long value;
	public PrivateKey privKey;
	public PublicKey publicKey;

	public void Key_Generator() throws Exception {
		// Creating KeyPair generator object
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

		// Initializing the KeyPairGenerator
		keyPairGen.initialize(2048);

		// Generating the pair of keys
		KeyPair pair = keyPairGen.generateKeyPair();

		// Getting the private key from the key pair
		privKey = pair.getPrivate();

		// Getting the public key from the key pair
		publicKey = pair.getPublic();
	}

}
