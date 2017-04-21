package athletics.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import athletics.validators.PasswordMatches;
import athletics.validators.ValidEmail;

@PasswordMatches
public class CustomerDto {

	@NotNull
    @Size(min = 1)
    private String firstName;
	
	@ValidEmail
	@NotNull
	@Size(min = 1, message="Email не может быть пустым")
	private String email;
	
	@NotNull
    @Size(min = 6, message="Пароль не может быть пустым")
	private String password;
	
	@NotNull
	@Size(min = 6)
	private String matchingPassword;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CustomerDto [firstName=" + firstName + ", email=" + email + ", password=" + password
				+ ", matchingPassword=" + matchingPassword + "]";
	}
	
	
}
