package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class FrontPage extends Controller {
    public static void index() {
        // Log a message to indicate that the "frontPage" page is being rendered
        Logger.info("Rendering FrontPage");
        // Render the "frontPage.html" template
        render("frontPage.html");
    }
}