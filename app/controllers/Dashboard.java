package controllers;

import java.util.List;

import models.Station;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering Admin");

        User member = UserAccount.getLoggedInUser();
        List<Station> stations = member.stations;
        render("dashboard.html", stations);
    }

    public static void addStation(String title) {
        Station station = new Station(title);
        Logger.info("Adding a new station called " + title);
        station.save();
        redirect("/dashboard");
    }
}

