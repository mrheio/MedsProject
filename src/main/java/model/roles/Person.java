package model.roles;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javafx.beans.property.*;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "role", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Patient", value = Patient.class),
        @JsonSubTypes.Type(name = "Doctor", value = Doctor.class)
})
public abstract class Person {

    protected final StringProperty surname;
    protected final StringProperty forename;
    protected final StringProperty fullName;
    protected final ObjectProperty<LocalDate> birthday;
    protected StringProperty email;
    protected StringProperty username;
    protected StringProperty password;
    protected final StringProperty role;

    public Person() {
        this.surname = new SimpleStringProperty();
        this.forename = new SimpleStringProperty();
        this.fullName = new SimpleStringProperty();
        this.birthday = new SimpleObjectProperty<LocalDate>();
        this.email = new SimpleStringProperty();
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.role = new SimpleStringProperty();
    }

    public Person(String surname, String forename, LocalDate birthday, String email, String username, String password, String role) {
        this.surname = new SimpleStringProperty(surname);
        this.forename = new SimpleStringProperty(forename);
        this.fullName = new SimpleStringProperty(surname+" "+forename);
        this.birthday = new SimpleObjectProperty<LocalDate>(birthday);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.role = new SimpleStringProperty(role);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getForename() {
        return forename.get();
    }

    public StringProperty forenameProperty() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename.set(forename);
    }

    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getRole() {
        return role.get();
    }

    public StringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(surname.getValue(), person.surname.getValue()) &&
                Objects.equals(forename.getValue(), person.forename.getValue()) &&
                Objects.equals(birthday.getValue(), person.birthday.getValue()) &&
                Objects.equals(role.getValue(), person.role.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, forename, birthday, email, username, password, role);
    }

    @Override
    public String toString() {
        return "Person{" +
                "surname=" + surname +
                ", forename=" + forename +
                ", birthday=" + birthday +
                ", email=" + email +
                ", username=" + username +
                ", password=" + password +
                ", role=" + role +
                '}';
    }
}
