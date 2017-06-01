/*
 * Copyright (c) 2017 BSC Praha, spol. s r.o.
 */

package cz.dojcar.interview.crawler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

/**
 * Created by michaldojcar on 01.06.2017
 */
public class HtmlPageTest {

    @Test
    public void htmlPage_can_be_created() throws Exception {
        String url = "http://ceai.io";
        String content = "<html />";
        new HtmlPage(url, content);
    }

    @Test
    public void htmlPage_links_are_parsed() throws Exception {
        String content = "<html>" +
                "<body>" +
                "<a href=\"http://www.google.cz\">1</a>" +
                "<a href=\"home\">2</a>" +
                "</body>" +
                "</html>";
        HtmlPage htmlPage = new HtmlPage("http://mypage.cz", content);
        assertThat(htmlPage.getLinks(), is(Arrays.asList("http://www.google.cz", "http://mypage.cz/home")));
    }
}
