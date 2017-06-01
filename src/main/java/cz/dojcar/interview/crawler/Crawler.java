package cz.dojcar.interview.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by michaldojcar on 01.06.2017
 */
public class Crawler {

    private final String url;
    private final int depth;

    public Crawler(String url, int depth) {
        this.url = url;
        this.depth = depth;
    }

    public List<String> crawl() throws IOException {
        if (depth == 0) return Collections.emptyList();

        List<String> result = new LinkedList<>();
        result.add(url);
        HtmlPage htmlPage = new HtmlPage(url, getContent(url));
        for (String link : htmlPage.getLinks()) {
            Crawler crawler = fork(link, depth - 1);
            result.addAll(crawler.crawl());
        }
        return result;
    }

    protected String getContent(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }

    protected Crawler fork(String url, int depth) {
        return new Crawler(url, depth);
    }
}
