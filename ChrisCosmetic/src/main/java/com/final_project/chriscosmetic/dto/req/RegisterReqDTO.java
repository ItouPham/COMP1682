package com.final_project.chriscosmetic.dto.req;

import com.final_project.chriscosmetic.annotation.PasswordValueMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        )
})
public class RegisterReqDTO {

    @NotBlank(message = "Email can not empty")
    @Email(message = "Invalid email")
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    private String confirmPassword;
    @NotBlank(message = "First name can not empty")
    private String firstName;
    @NotBlank(message = "Last name can not empty")
    private String lastName;

    public RegisterReqDTO() {
    }

    public RegisterReqDTO(String email, String password, String confirmPassword, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
