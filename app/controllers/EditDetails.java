package controllers;

import play.*;
import play.mvc.*;

public class EditDetails extends Controller {
    public static void index() {
        // Log a message to indicate that the "editDetails" page is being rendered
        Logger.info("Rendering about");
        // Render the "editDetails.html" template
        render("editDetails.html");
    }
}