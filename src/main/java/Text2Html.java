import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by 1000038 on 2019. 7. 9..
 */
public class Text2Html {

    static public String make(String text) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">")
                .append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ko\" lang=\"ko\">")
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />")
                .append("<body>");

        sb.append("</body></html>");

        String htmlTemplate = sb.toString();
        Document doc = Jsoup.parse(htmlTemplate, "", Parser.htmlParser());
        Document.OutputSettings settings = doc.outputSettings();
        settings.charset("UTF-8")
                .escapeMode(Entities.EscapeMode.base)
                .indentAmount(1)
                .syntax(Document.OutputSettings.Syntax.xml); // html5: <meta > or <link >, xml: <meta />
        doc.outputSettings(settings);

        Element novelSection = doc.body();
        BufferedReader reader = new BufferedReader(new StringReader(text));
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String trim = line.trim();
                if (trim.length() > 0) {
                    Element p = doc.createElement("p");

                    /*
                     * Replace whitespace(0x20) to nbsp(0xa0) in a text novel
                     * 0x20: whitespace
                     * 0xA0: Non-breaking space (nbsp)
                     */
                    //String encodedText = line.replace("\u0020", "\u00A0");
                    //p.text(encodedText);

                    p.text(line);
                    novelSection.appendChild(p);
                } else {
                    novelSection.appendChild(doc.createElement("br"));
                }
            }
            novelSection.appendChild(doc.createElement("br"));

            doc.insertChildren(0, new XmlDeclaration("xml version=\"1.0\" encoding=\"UTF-8\"", false));

            return doc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
