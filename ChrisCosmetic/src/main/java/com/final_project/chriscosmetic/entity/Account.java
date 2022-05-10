package com.final_project.chriscosmetic.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@DynamicUpdate
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "account_id")
    private String id;
    @Column(length = 256)
    private String email;
    @Column
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;

    @Column
    private String address;

    @Column(length = 11)
    private String telephone;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "accounts_roles",
            joinColumns = {@JoinColumn(name="account_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")})
    private Set<Role> roles;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Account() {
        super();
    }

    public Account(String id, String email, String password, String firstName, String lastName, String address, String telephone, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", roles=" + roles +
                '}';
    }
}