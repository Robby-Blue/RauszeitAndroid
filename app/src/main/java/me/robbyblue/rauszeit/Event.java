package me.robbyblue.rauszeit;

import android.text.Html;
import android.text.Spanned;

import org.jsoup.nodes.Element;

public class Event {

    private final String name;
    private final Spanned description;
    // add more here, like date and time

    public Event(Element eventData) {
        Element article = eventData.getElementsByClass("article-inner").get(0);

        Element nameElement = article.getElementsByTag("h1").get(0);
        this.name = nameElement.text();

        // one br has style clear:both, after this its description
        Element entry = eventData.getElementsByClass("entry-content").get(0);
        Element clearElement = entry.getElementsByAttributeValue("style", "clear:both").get(0);
        int clearIndex = entry.childNodes().indexOf(clearElement) - 1;

        StringBuilder descriptionHtmlBuilder = new StringBuilder();
        for(int i = clearIndex; i < entry.childrenSize();i++){
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

}
