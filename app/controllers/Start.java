package controllers;

import play.Logger;
import play.mvc.Controller;

public class Start extends Controller {
    public static void index() {
        // Log a message to indicate that the "start" page is being rendered
        Logger.info("Rendering Start");

        // Check if a session is in place and user is logged
        if (UserAccount.isSessionInPlace()) {
            // If a session is in place, render the "dashboard.html" template
            render("frontPage.html");
        } else {
            // If no session is in place, render the "start.html" template
            render("start.html");
        }
    }
}

