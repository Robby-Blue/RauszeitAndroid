package me.robbyblue.rauszeit.day;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

import me.robbyblue.rauszeit.eventpreview.EventPreview;

public class Day {

    private final String date;
    private final ArrayList<EventPreview> events;

    public Day(String date, Element tableElement) {
        this.date = date;
        this.events = parseEvents(tableElement);
    }

    private ArrayList<EventPreview> parseEvents(Element tableElement) {
        ArrayList<EventPreview> events = new ArrayList<>();

        Element eventsElement = tableElement.getElementsByTag("tbody").get(0);
        for (Element eventData : eventsElement.getElementsByTag("tr")) {
            events.add(new EventPreview(eventData));
        }

        return events;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<EventPreview> getEvents() {
        return events;
    }
}
