package com.final_project.chriscosmetic.dto.req;

import com.final_project.chriscosmetic.annotation.PasswordValueMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!",
                optional = true
        )
})
public class EditAccountReqDTO {

    private String id;
    @NotBlank(message = "Email can not empty")
    @Email(message = "Invalid email")
    private String email;
    private String password;
    private String confirmPassword;
    @NotBlank(message = "First name can not empty")
    private String firstName;
    @NotBlank(message = "Last name can not empty")
    private String lastName;
    private String address;
    private String telephone;
    private String roleID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public EditAccountReqDTO(String id, String email, String password, String confirmPassword, String firstName, String lastName, String address, String telephone, String roleID) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.roleID = roleID;
    }

    public EditAccountReqDTO() {
    }
}
