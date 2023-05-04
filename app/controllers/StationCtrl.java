package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {
    public static void index(String name) {
        Station station = Station.find("byName", name).first();
        Logger.info("Station name = " + name);
        render("station.html", station);
    }

    public static void deleteReading(Long stationId, Long readingId) {
        Station station = Station.findById(stationId);
        Reading reading = Reading.findById(readingId);
        Logger.info("Removing reading with code " + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        render("station.html", station);
    }

    public static void addReading(Long stationId, int code, float temperature, float windSpeed, int pressure, float windDirection) {
        Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection);
        Station station = Station.findById(stationId);
        station.readings.add(reading);
        station.save();
        redirect("/station/" + station.name);
    }
}

