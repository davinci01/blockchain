package blockchain;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class HashAlgorithm {
	public String sha256(Object input1) {
		String input = String.valueOf(input1);
		String output = Hashing.sha256().hashString(input, StandardCharsets.UTF_8).toString();
		return output;
	}

}
