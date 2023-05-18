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
        Logger.info("Registering new user " + email);
        Member existingMember = Member.findByEmail(email);
        if (existingMember != null) {
            flash.error("Email already registered. Please choose a different email.");
            redirect("/signup"); // Redirect back to the signup page
        }

        Member member = new Member(firstname, lastname, email, password);
        member.save();

        redirect("/login");
    }

    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password))) {
            Logger.info("Authentication successful");
            session.put("logged_in_MemberId", member.id);
            redirect("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }
    public static void logout() {
        session.clear();
        redirect("/");
    }

    public static Member getLoggedInUser() {
        Member member = null;
        if (isSessionInPlace()) {
            String memberId = session.get("logged_in_MemberId");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static boolean isSessionInPlace() {
        return session.contains("logged_in_MemberId");
    }
}