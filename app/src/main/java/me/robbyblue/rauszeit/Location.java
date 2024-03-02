package me.robbyblue.rauszeit;

import android.text.Html;
import android.text.Spanned;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Location {

    private final String name;
    private final String exactAddress;
    private final String generalAddress;
    private final Spanned description;

    public Location(Element article) {
        Element nameElement = article.getElementsByTag("h1").get(0);
        this.name = nameElement.text();

        // the structure is like
        // (address)
        // <hr>
        // (description)
        // <hr>
        // (other stuff)

        // first get the indexes of both <hr>s, get the text before the first one for addr
        // and between the first and second one for description

        Element entry = article.getElementsByClass("entry-content").get(0);

        exactAddress = entry.textNodes().get(1).text().trim();
        generalAddress = entry.textNodes().get(2).text().trim();

        Elements hrElements = entry.getElementsByTag("hr");
        int hr1Index = entry.children().indexOf(hrElements.get(0)) + 1;
        int hr2Index = entry.children().indexOf(hrElements.get(1));

        // concat all html strings and then use androids fromHtml to make it look ok
        StringBuilder descriptionHtmlBuilder = new StringBuilder();
        for (int i = hr1Index; i < hr2Index; i++) {
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

    public String getExactAddress() {
        return exactAddress;
    }

    public String getGeneralAddress() {
        return generalAddress;
    }

    public Spanned getDescription() {
        return description;
    }

}
