
import lombok.Data;


import java.io.Serializable;

@Data
public class RegistrationDto implements Serializable {

    private String login = "vasya";
    private String password = "password";
    private String status;


    public RegistrationDto() {

    }

    public RegistrationDto(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
