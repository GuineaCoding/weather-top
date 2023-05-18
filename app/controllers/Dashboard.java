package controllers;

import java.util.List;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering Admin");
        Member member = UserAccount.getLoggedInUser();
        List<Station> stations = null;
        if (member != null) {
            stations = member.stations;
        }
        render("dashboard.html", stations);
    }

    public static void addStation(String title, double lat, double lng) {
        Logger.info("Adding a new station called " + title);
        Member member = UserAccount.getLoggedInUser();
        if (member != null) {
            Station station = new Station(title, lat, lng);
            member.stations.add(station);
            member.save();
        }
        redirect("/dashboard");
    }

}

