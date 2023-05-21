package controllers;

import java.util.Date;
import java.util.List;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {
    public static void index(Long name) {
        // Find the station by id
        Station station = Station.findById(name);
        Logger.info("Station id = " + name);
        // Render the "station.html" template with the station object
        render("station.html", station);
    }

    public static void addReading(Long id, int code, float temperature, float windSpeed, int pressure, float windDirection, Date date) {
        // Create a new Reading object with the provided data
        Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection, date);
        // Find the station by  id
        Station station = Station.findById(id);
        // Add the reading to the station's readings collection
        station.readings.add(reading);
        // Save the changes to the station
        station.save();
        // Redirect the user back to the station page
        redirect("/stations/" + id);
    }

    public static void deleteReading(Long id, Long readingId) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingId);
        Logger.info("Removing reading: " + reading.id);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect("/stations/" + id);
    }

}


