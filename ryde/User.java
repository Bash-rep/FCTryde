package ryde;

public interface User {
	/**
	 * Password verifier. The password sorted is encrypted and this method does what
	 * is needed to check if the password is valid. Returns true if the password is
	 * valid.
	 * 
	 * @param pwd
	 * @return
	 */
	boolean checkPassword(String pwd);
}
