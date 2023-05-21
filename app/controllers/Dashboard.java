package controllers;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {
    public static void index() {
        // Log a message to indicate that the "dashboard" page is being rendered
        Logger.info("Rendering Admin");

        // get the logged-in member
        Member member = UserAccount.getLoggedInUser();

        // initialize the list of stations
        List<Station> stations = null;

        // check if a member is logged in
        if (member != null) {
            // Assign the member's stations to the "stations" list
            stations = member.stations;

            // sort the stations alphabetically
            stations.sort(new Comparator<Station>() {
                public int compare(Station station1, Station station2) {
                    return station1.name.compareToIgnoreCase(station2.name);
                }
            });
        }

        // render the "dashboard.html" and passing the "stations" list as a parameter
        render("dashboard.html", stations);
    }

    public static void addStation(String title, double lat, double lng) {
        // Log a message to indicate that a new station is being added
        Logger.info("Adding a new station called " + title);

        // Get the logged-in member
        Member member = UserAccount.getLoggedInUser();

        // Check if a member is logged in
        if (member != null) {
            // Check if the station name already exists in the user's stations list
            if (isStationNameExists(member, title)) {
                // Display an error message using flash and redirect back to the dashboard page
                flash.error("Station name already exists in your account.");
                redirect("/dashboard");
                return;
            }

            // Validate the title, latitude, and longitude values
            if (isValidTitle(title) && isValidLatitude(lat) && isValidLongitude(lng)) {
                // Create a new Station object with the provided title, latitude, and longitude
                Station station = new Station(title, lat, lng);

                // Add the new station to the member's stations list
                member.stations.add(station);

                // Save the member to persist the changes
                member.save();
            } else {
                // Redirect the user back to the dashboard page with an error message
                flash.error("Invalid input values");
                redirect("/dashboard");
                return;
            }
        }

        // Redirect the user back to the dashboard page
        redirect("/dashboard");
    }

    private static boolean isValidTitle(String title) {
        // Check if the title is not empty or null
        return title != null && !title.isEmpty();
    }

    private static boolean isValidLatitude(double lat) {
        return lat >= -90 && lat <= 90;
    }

    private static boolean isValidLongitude(double lng) {
        return lng >= -180 && lng <= 180;
    }

    private static boolean isStationNameExists(Member member, String name) {
        // Iterate over the stations in the member's stations list
        for (Station station : member.stations) {
            // Check if the station name matches the provided title
            if (station.name.equals(name)) {
                return true; // Station name already exists
            }
        }
        return false; // Station name does not exist
    }

    public static void deleteStation(Long id) {
        //getting the logged user
        Member member = UserAccount.getLoggedInUser();
        Logger.info("Deleting a Station");
        //finding the station id
        Station station = Station.findById(id);
        //delete station
        member.stations.remove(station);
        member.save();
        station.delete();
        redirect("/dashboard");
    }
}

