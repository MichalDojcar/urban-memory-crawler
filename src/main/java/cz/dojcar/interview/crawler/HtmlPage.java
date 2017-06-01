package cz.dojcar.interview.crawler;

import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by michaldojcar on 01.06.2017
 */
public class HtmlPage {

    private final Document document;

    public HtmlPage(String url, String content) {
        this.document = Jsoup.parse(content);
        this.document.setBaseUri(url);
    }

    public List<String> getLinks() {
        Elements elements = document.getElementsByTag("a");
        List<String> links = new LinkedList<>();
        for (Element element : elements)
            links.add(element.absUrl("href"));
        return links;
    }
}
