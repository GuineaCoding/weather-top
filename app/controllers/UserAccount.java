package controllers;

import models.User;
import play.Logger;
import play.mvc.Controller;

public class UserAccount extends Controller
{
    public static void signup()
    {
        render("signup.html");
    }

    public static void login()
    {
        render("login.html");
    }

    public static void register(String firstname, String lastname, String email, String password)
    {
        Logger.info("Registering new user " + email);
        User member = new User(firstname, lastname, email, password);
        member.save();
        redirect("/");
    }

    public static void authenticate(String email, String password)
    {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        User member = User.findByEmail(email);
        if ((member != null) && (member.checkPassword(password))) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect ("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout()
    {
        session.clear();
        redirect ("/");
    }

    public static User getLoggedInUser()
    {
        User member = null;
        if (isSessionInPlace()) {
            String memberId = session.get("logged_in_Memberid");
            member = User.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static boolean isSessionInPlace() {
        return session.contains("logged_in_Memberid");
    }
}