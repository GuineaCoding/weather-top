package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller
{
    public static void index(String name) {
        Station station = Station.find("byName", name).first();
        Logger.info("Station name = " + name);
        render("station.html", station);
    }
    public static void deletesong (Long id, Long stationid)
    {
        Station station = models.Station.findById(id);
        Reading reading = Reading.findById(stationid);
        Logger.info ("Removing" + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        render("station.html", station);
    }
}

