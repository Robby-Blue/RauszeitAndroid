package me.robbyblue.rauszeit.day;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

import me.robbyblue.rauszeit.event.Event;

public class Day {

    private final String date;
    private final ArrayList<Event> events;

    public Day(String date, Element tableElement) {
        this.date = date;
        this.events = parseEvents(tableElement);
    }

    private ArrayList<Event> parseEvents(Element tableElement) {
        ArrayList<Event> events = new ArrayList<>();

        Element eventsElement = tableElement.getElementsByTag("tbody").get(0);
        for (Element eventData : eventsElement.getElementsByTag("tr")) {
            events.add(new Event(eventData));
        }

        return events;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
