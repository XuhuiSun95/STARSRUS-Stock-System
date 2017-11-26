package Utility;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class MovieXMLParser {
    public static String list_all_movie() {
        return "";
    }

    public static String display_info(String title) {
        String res = "\n";

        try{
	        File fXmlFile = new File("src/data/Movies.xml");
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("MOVIE");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if(eElement.getElementsByTagName("TITLE").item(0).getTextContent().equals(title)) {
                        res += "Movie Title: ";
                        res += eElement.getElementsByTagName("TITLE").item(0).getTextContent();
                        res += "\n";
                        res += "Production Year: ";
                        res += eElement.getElementsByTagName("PRODUCTIONYEAR").item(0).getTextContent();
                        res += "\n";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static String top_movie(String time_interval) {
        String res = "\n";
        
        int year1 = Integer.valueOf(time_interval.substring(0,4));
        int year2 = Integer.valueOf(time_interval.substring(5,9));

        try{
	        File fXmlFile = new File("src/data/Movies.xml");
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("MOVIE");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String rank = eElement.getElementsByTagName("RANKING").item(0).getTextContent();
                    int year = Integer.valueOf(eElement.getElementsByTagName("PRODUCTIONYEAR").item(0).getTextContent());
                    if(rank.equals("5.0") && year1 <= year && year <= year2 ) {
                        res += "Movie Title: ";
                        res += eElement.getElementsByTagName("TITLE").item(0).getTextContent();
                        res += "\n";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static String display_review(String title) {
        String res = "\n";

        try{
	        File fXmlFile = new File("src/data/Movies.xml");
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("MOVIE");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if(eElement.getElementsByTagName("TITLE").item(0).getTextContent().equals(title)) {
                        res += "Movie Title: ";
                        res += eElement.getElementsByTagName("TITLE").item(0).getTextContent();
                        res += "\n";

                        for ( int num = 0; num < eElement.getElementsByTagName("REVIEWS").getLength(); num++) {
                            Element rev = (Element) eElement.getElementsByTagName("REVIEWS").item(num);
                            res += "Author: ";
                            res += rev.getAttribute("AUTHOR");
                            res += "\n";
                            res += "Review: ";
                            res += eElement.getElementsByTagName("REVIEWS").item(num).getTextContent();
                            res += "\n----------------\n";
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
