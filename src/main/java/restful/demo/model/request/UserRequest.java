package restful.demo.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequest {
    @NotNull(message = "First Name cannot be null")
    private String firstName;
    @NotNull(message = "Last Name cannot be null")
    private String lastName;
    @NotNull(message = "email cannot be null")
    @Email(message = "not a valid email")
    private String email;

    @NotNull(message = "password cannot be null")
    @Size(min = 8,max = 20,message = "password should be between 8 and 20 characters long")
    private String password;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastname() {
        return lastName;
    }
    public void setLastname(String lastname) {
        this.lastName = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
