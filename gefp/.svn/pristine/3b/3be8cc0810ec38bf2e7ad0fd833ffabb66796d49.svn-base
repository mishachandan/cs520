package gefpmvc.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderSpring {
	
	static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	static Md5PasswordEncoder md5Ecodere = new Md5PasswordEncoder();
	
	public static String encode(String input){
		return passwordEncoder.encode(input);
	}
	
	public static boolean matched(String input, String encryptedContent){
		//String encryptInput = encode(input);
		return passwordEncoder.matches(input, encryptedContent);
	}
	
	public static String encodeMd5(String input){
		
		return md5Ecodere.encodePassword(input, "");
	}
	
	public static boolean matchedMd5(String input, String encryptedContent){
		//String encryptInput = encode(input);
		return md5Ecodere.isPasswordValid(encryptedContent, input, "");
	}
	
	
	public static void main(String[] args) {
		String str =encodeMd5("G157176016");
		System.out.println(str);
		System.out.println(matchedMd5("G157176016",str));
		System.out.println(encode("abcd"));
		System.out.println(matched("abcd",encode("abcd")));
	}
}
