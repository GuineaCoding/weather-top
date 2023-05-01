package controllers;

import java.util.List;

import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index()
  {
    List<Station> stations = Station.findAll();
    render ("dashboard.html", stations);
  }
  public static void addStation (String title)
  {
    Station playlist = new Station (title, 0);
    Logger.info ("Adding a new playlist called " + title);
    playlist.save();
    redirect ("/dashboard");
  }
}

