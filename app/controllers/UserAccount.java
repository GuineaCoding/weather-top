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

    public static void register(String firstname, String lastname, String email, String password) {
        // Log the registration attempt with the provided email
        Logger.info("Registering new user " + email);

        // Check if a member with the same email already exists
        Member existingMember = Member.findByEmail(email);
        if (existingMember != null) {
            // Display an error message using flash and redirect back to the signup page
            flash.error("Email already registered. Please choose a different email.");
            redirect("/signup");
        }

        // Create a new member with the provided details
        Member member = new Member(firstname, lastname, email, password);
        member.save();

        // Redirect to the login page after successful registration
        redirect("/login");
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
            // Retrieve the logged-in member's ID from the session
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
