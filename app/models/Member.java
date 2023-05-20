package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {
    public String firstname;
    public String lastname;
    public String email;
    public String password;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Station> stations = new ArrayList<Station>();

    public Member(String firstname, String lastname, String email, String password) {
        // Initialize the Member object with provided details
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public static Member findByEmail(String email) {
        // Find a Member by email using the JPA query
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        // Check if the provided password matches the Member's password
        return this.password.equals(password);
    }
}
