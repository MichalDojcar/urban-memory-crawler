package cz.dojcar.interview.crawler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Created by michaldojcar on 01.06.2017
 */
public class CrawlerTest {

    @Test
    public void crawler_crawls_linear_structure() throws Exception {
        Crawler crawler = new LinearMockCrawler("http://myPage.cz", 3);
        List<String> result = crawler.crawl();
        assertThat(result, is(Arrays.asList("http://myPage.cz", "http://seznam.cz", "http://seznam.cz")));
    }

    private static class LinearMockCrawler extends Crawler {

        public LinearMockCrawler(String url, int depth) {
            super(url, depth);
        }

        @Override
        protected String getContent(String url) throws IOException {
            return "<html><a href=\"http://seznam.cz\">x</a></html>";
        }

        @Override
        protected Crawler fork(String url, int depth) {
            return new LinearMockCrawler(url, depth);
        }
    }

    @Test
    public void crawler_crawls_binary_tree_structure() throws Exception {
        Crawler crawler = new BinaryTreeMockCrawler("http://myPage.cz", 3);
        List<String> result = crawler.crawl();
        assertThat(result, is(Arrays.asList("http://myPage.cz"
                , "http://myPage.cz/1", "http://myPage.cz/1/1", "http://myPage.cz/1/2"
                , "http://myPage.cz/2", "http://myPage.cz/2/1", "http://myPage.cz/2/2")));
    }

    private static class BinaryTreeMockCrawler extends Crawler {

        public BinaryTreeMockCrawler(String url, int depth) {
            super(url, depth);
        }

        @Override
        protected String getContent(String url) throws IOException {
            return "<html>" +
                    "<a href=\""+ url + "/1\">x</a>" +
                    "<a href=\""+ url + "/2\">x</a>" +
                    "</html>";
        }

        @Override
        protected Crawler fork(String url, int depth) {
            return new BinaryTreeMockCrawler(url, depth);
        }
    }
}
