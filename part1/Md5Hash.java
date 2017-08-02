import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import java.security.*;
/**
* Calculate md5 Hash for the given string
*/
public final class Md5Hash extends UDF {

	public Text evaluate(final Text input) {
	if (input == null) {
	return null;
	}
	try {
		MessageDigest md1 = MessageDigest.getInstance("MD5");
		md1.update(input.toString().getBytes());
		byte[] md5hash = md1.digest();
		StringBuilder md5Builder = new StringBuilder();
		for (byte b : md5hash) {
		md5Builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	}
	return new Text(md5Builder.toString());
	} catch (NoSuchAlgorithmException nsae) {
		System.out.println("Cannot find MD5 algorithm");
		System.exit(1);
	}
	return null;
	}
}
