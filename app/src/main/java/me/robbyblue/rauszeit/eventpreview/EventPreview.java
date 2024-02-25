package me.robbyblue.rauszeit.eventpreview;

import org.jsoup.nodes.Element;

public class EventPreview {

    private final String link;
    private final String name;
    private final String description;
    // add more here, like categories

    public EventPreview(Element eventData) {
        Element importantData = eventData.getElementsByTag("td").get(1);

        Element nameElement = importantData.getElementsByTag("span").get(1);
        this.link = nameElement.getElementsByTag("a").attr("href");
        this.name = nameElement.text();
        Element descriptionElement = importantData.getElementsByTag("span").get(2);
        this.description = descriptionElement.text();
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
