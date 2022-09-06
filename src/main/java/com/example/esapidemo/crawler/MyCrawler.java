package com.example.esapidemo.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.ObjectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.util.regex.Pattern;

public class MyCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "https://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href.startsWith("https://blog.51cto.com/u");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @SneakyThrows
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
//            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
//            String text = htmlParseData.getText();
//            String html = htmlParseData.getHtml();
//            Set<WebURL> links = htmlParseData.getOutgoingUrls();
//
//            System.out.println("Text length: " + text.length());
//            System.out.println("Html length: " + html.length());
//            System.out.println("Number of outgoing links: " + links.size());
            final Document document = Jsoup.connect(url).get();

            String title = document.select("#page_center > article > div.common-section.common-spacing.mb30.article-detail > div.title > h1").text();
            System.out.println("===================>>>" + title);
            String content = document.select("#page_center > article > div.common-section.common-spacing.mb30.article-detail > div.article-content-wrap").text();

            if (!ObjectUtils.isEmpty(title)) {
                saveData(title, content, url);
            }

        }
    }


    public void saveData(String title, String content, String url) throws SQLException {
        Connection connection = DataConnection.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("insert into blog(title, content, url, category) values(?, ?, ?, '嵌入式')");
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, content);
        preparedStatement.setString(3, url);
        final int executeUpdate = preparedStatement.executeUpdate();
        System.out.println("执行新增结果： ----》 " + executeUpdate);
        connection.close();
    }
}