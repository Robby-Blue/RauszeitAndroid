package me.robbyblue.rauszeit;

import android.text.Html;
import android.text.Spanned;

import org.jsoup.nodes.Element;

public class Event {

    private final String name;
    private final Spanned description;
    private final String date;
    private final String time;
    private final String locationName;
    private final String locationLink;
    // add more here, like date and time

    public Event(Element eventData) {
        Element article = eventData.getElementsByClass("article-inner").get(0);

        Element nameElement = article.getElementsByTag("h1").get(0);
        this.name = nameElement.text();

        Element entry = eventData.getElementsByClass("entry-content").get(0);

        // first child is a p which includes the time and date
        // date is an untagged text node and time in an i
        Element timeElement = entry.child(0);
        this.date = timeElement.textNodes().get(1).text().trim();
        this.time = timeElement.getElementsByTag("i").get(0).text().trim();

        // get the location, its the second p in the entry
        // theres an a with the href and and name as text
        Element locationElement = entry.child(1).getElementsByTag("a").get(0);
        this.locationName = locationElement.text();
        this.locationLink = locationElement.attr("href");

        // get description
        // one br has style clear:both, after this its description
        Element clearElement = entry.getElementsByAttributeValue("style", "clear:both").get(0);
        int clearIndex = entry.childNodes().indexOf(clearElement) - 1;

        StringBuilder descriptionHtmlBuilder = new StringBuilder();
        for (int i = clearIndex; i < entry.childrenSize(); i++) {
            descriptionHtmlBuilder.append(entry.child(i).html());
            descriptionHtmlBuilder.append("<br>");
        }

        // rn just build one html of the entire text and then turn it into spans,
        // probably good enough for now
        this.description = Html.fromHtml(descriptionHtmlBuilder.toString());
    }

    public String getName() {
        return name;
    }

    public Spanned getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getLocationLink() {
        return locationLink;
    }
}
