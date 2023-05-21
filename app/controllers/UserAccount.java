package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class UserAccount extends Controller {
    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }

    public static void loadAccountData() {
        // Get the logged-in member data
        Member member = getLoggedInUser();

        // Render the editDetails view with the member data
        render("editDetails.html", member);
    }
    public static void changeData(String firstName, String lastName, String email, String newPassword, String confirmPassword) {
        // Get the logged-in member
        Member member = getLoggedInUser();

        if (member == null) {
            // Handle the case when the member is not found or not logged in
            // You can redirect to a login page or display an error message
            // For example:
            flash.error("Please log in to edit your account.");
            redirect("/login");
        }

        // Validate the input values
        if (!isValidInput(firstName, lastName)) {
            // Display an error if the first name or last name is not valid and redirect back to the edit page
            flash.error("Make sure that First Name and Last Name don't contain alphanumeric characters.");
            loadAccountData();
        }

        if (!isValidEmailFormat(email)) {
            // Display an error message if the email format is invalid and redirect back to the edit page
            flash.error("Invalid Email, please choose a correct one.");
            loadAccountData();
        }

        Member existingMember = Member.findByEmail(email);
        if (existingMember != null && !existingMember.equals(member)) {
            // Display an error message if the email is already registered to another member
            flash.error("Email already registered. Please choose a different email.");
            loadAccountData();
        }

        // Update the member's fields with the new values
        member.setFirstname(firstName);
        member.setLastname(lastName);
        member.setEmail(email);
        member.setPassword(newPassword);
        // Update the password if a new password is provided
        if (!newPassword.isEmpty() && !newPassword.equals(confirmPassword)) {
            // Display an error message if the passwords don't match and redirect back to the edit page
            flash.error("Passwords do not match.");

            loadAccountData();
        }

        // Save the updated member
        member.save();

        // Redirect to a success page or display a success message
        flash.success("Account details updated successfully.");
        redirect("/dashboard");
    }



    public static void register(String firstname, String lastname, String email, String password) {
        // Log the registration attempt with the provided email
        Logger.info("Registering new user " + email);

        // Check if a member with the same email already exists
        Member existingMember = Member.findByEmail(email);
        if (existingMember != null) {
            // Display an error message if email is wrong format redirect back to the signup page
            flash.error("Email already registered. Please choose a different email.");
            redirect("/signup");
        }

        // Validate the input values
        if (!isValidInput(firstname, lastname)) {
            // Display an error if name or lastname is not valid and redirect back to the signup page
            flash.error("Make sure that First Name or Last Name don't contain alphanumeric characters.");
            redirect("/signup");
        }
        if (!isValidEmailFormat(email)) {
            // Display an error message using flash and redirect back to the signup page
            flash.error("Invalid Email, please choose a correct one.");
            redirect("/signup");
        }

        // Create a new member with the provided details
        Member member = new Member(firstname, lastname, email, password);
        member.save();

        // Redirect to the login page after successful registration
        redirect("/login");
    }

    private static boolean isValidInput(String firstname, String lastname) {
        // Checking if if the name fields are not empty and contain only letters
        // Return true input these are valid, otherwise return false
        return !firstname.isEmpty() && !lastname.isEmpty() && isAlphaOnly(firstname) && isAlphaOnly(lastname);
    }

    private static boolean isValidEmailFormat(String email) {
        // Checking if the email is not empty and is in a valid format
        // Return true if the email is valid, otherwise return false
        return !email.isEmpty() && isValidEmail(email);
    }

    private static boolean isAlphaOnly(String value) {
        // Checking if the value contains only letters
        // Return true if the value contains only letters, otherwise return false
        return value.matches("[a-zA-Z]+");
    }

    private static boolean isValidEmail(String email) {
        // Added email validation logic. Return true if the email is valid, otherwise return false

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$";
        return email.matches(emailRegex);
    }

    public static void authenticate(String email, String password) {
        // Log the authentication attempt with the provided email and password
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        // Find the member by the provided email
        Member member = Member.findByEmail(email);

        // Check if the member exists and the password is correct
        if (member != null && member.checkPassword(password)) {
            // Authentication successful, store the member's ID in the session
            Logger.info("Authentication successful");
            session.put("logged_in_MemberId", member.id);
            redirect("/dashboard");
        } else {
            // Authentication failed, display an error flash message
            flash.error("Invalid email or password");
            redirect("/login");
        }
    }

    public static void logout() {
        // Clear the session and redirect to the homepage
        session.clear();
        redirect("/");
    }

    public static Member getLoggedInUser() {
        Member member = null;
        if (isSessionInPlace()) {
            // Retrieve the logged-in member's id from the session
            String memberId = session.get("logged_in_MemberId");
            // Find the member by the ID
            member = Member.findById(Long.parseLong(memberId));
        } else {
            // If no session is in place, redirect to the login page
            login();
        }
        return member;
    }

    public static boolean isSessionInPlace() {
        // Check if the session contains the logged member id
        return session.contains("logged_in_MemberId");
    }
}
