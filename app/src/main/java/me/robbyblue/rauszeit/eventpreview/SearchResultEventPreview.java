package me.robbyblue.rauszeit.eventpreview;

import org.jsoup.nodes.Element;

public class SearchResultEventPreview extends EventPreview {

    public SearchResultEventPreview(Element element) {
        Element titleElement = element.getElementsByClass("entry-title").get(0);
        this.name = titleElement.text().trim();
        this.link = titleElement.getElementsByTag("a").get(0).attr("href");

        Element descriptionElement = element.getElementsByClass("entry-summary").get(0);
        this.description = descriptionElement.text().trim();
    }

}
