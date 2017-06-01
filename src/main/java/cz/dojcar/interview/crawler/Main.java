package cz.dojcar.interview.crawler;

import java.io.IOException;

/**
 * Created by michaldojcar on 01.06.2017
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler("http://ceai.io", 2);
        System.out.println(crawler.crawl());
    }
}
