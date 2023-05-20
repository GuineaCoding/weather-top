package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class About extends Controller {
    public static void index() {
        // Log a message to indicate that the "about" page is being rendered
        Logger.info("Rendering about");
        // Render the "about.html" template
        render("about.html");
    }
}