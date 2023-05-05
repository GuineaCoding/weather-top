package controllers;

import java.util.List;

import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Admin");

    List<Station> stations = Station.findAll();
    render ("dashboard.html", stations);
  }
  public static void deletePlaylist (Long id)
  {
    Station station = Station.findById(id);
    Logger.info ("Removing" + station.name);
    station.delete();
    redirect ("/dashboard");
  }
  public static void addStation (String title)
  {
    Station station = new Station(title);
    Logger.info ("Adding a new station called " + title);
    station.save();
    redirect ("/dashboard");
  }
}

