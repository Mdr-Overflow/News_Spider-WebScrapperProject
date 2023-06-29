package Proj;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Scout{
    public List<String> getRandomURLs(String url) throws IOException, URISyntaxException {
        List<String> urls = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();

        Elements links = doc.select("a[href]");

        URI uri = new URI(url);
        String domain = uri.getHost();

        for (Element link : links) {
            String absUrl = link.attr("abs:href");
            if (absUrl.contains(domain)) {
                urls.add(absUrl);
            }
        }

        Random rand = new Random();
        int randomNum = rand.nextInt((10 - 2) + 1) + 2;

        List<String> randomURLs = new ArrayList<>();
        for (int i = 0; i < randomNum; i++) {
            randomURLs.add(urls.get(rand.nextInt(urls.size())));
        }

        return randomURLs;
    }

    public String faviconURL(String URL) {
        try {
            String faviconUrl = getFavicon(URL);
            System.out.println("Favicon URL: " + faviconUrl);
            if (faviconUrl != null)
                 return faviconUrl;
            return "Resources\\GoogleIconPage.png";
        } catch (IOException e) {
            System.out.println("Error reading favicon: " + e.getMessage());
            return  "Resources\\GoogleIconPage.png";
        }

    }

    public static String getFavicon(String siteUrl) throws IOException {
        Document doc = Jsoup.connect(siteUrl).get();
        Element link = doc.head().select("link[rel=icon]").first();
        if (link != null) {
            return link.absUrl("href");
        } else {
            return null;
        }
    }
}
