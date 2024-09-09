package org.example.password;

public class User {
	private String password;

	public void initPassword(PasswordGenerator passwordGenerator) {
		// AS-IS : 현재의 상태나 모습 (개선되기 이전의 상태)
		//RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();

		// TO-BE : 미래의 이상적인 상태나 목표 (개선된 후의 상태)
		// => 기존의 강한 결합을 약한 결합으로 만들게 됨
		String password = passwordGenerator.generatePassword();

		// 비밀번호는 최소 8자 이상 12자 이하여야 한다.
		if (password.length() >= 8 && password.length() <= 12) {
			this.password = password;
		}
	}

	public String getPassword() {
		return password;
	}
}
