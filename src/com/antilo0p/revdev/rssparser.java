package com.antilo0p.revdev;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class rssparser {
	
	
	public static void parse(){
	URL url;
	try {
		url = new URL("http://www.revdevsolutions.com/index.php?option=com_kusoma&id=1");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
              DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
              DocumentBuilder db = dbf.newDocumentBuilder();
              Document doc;
              doc = db.parse(url.openStream());
              doc.getDocumentElement().normalize();
              NodeList itemLst = doc.getElementsByTagName("item");
              
              arrays.PodcastTitle = new String[itemLst.getLength()];
              arrays.PodcastURL = new String[itemLst.getLength()];
              arrays.PodcastContent = new String[itemLst.getLength()];
              arrays.PodcastCategory = new String[itemLst.getLength()];
             
              for(int i=0; i < itemLst.getLength(); i++){
                   
                    Node item = itemLst.item(i);
                    if(item.getNodeType() == Node.ELEMENT_NODE){
                          Element ielem = (Element)item;
                          NodeList title = ielem.getElementsByTagName("title");
                          NodeList link = ielem.getElementsByTagName("link");
                          //NodeList description = ielem.getElementsByTagName("description"); 
                          NodeList content = ielem.getElementsByTagName("description");
                          //NodeList media = ielem.getElementsByTagName("media:content");
                          NodeList category = ielem.getElementsByTagName("category");
                          
                         // String mediaurl = media.item(0).getAttributes().getNamedItem("url").getNodeValue();
                          
                          arrays.PodcastTitle[i] = title.item(0).getChildNodes().item(0).getNodeValue();
                          arrays.PodcastURL[i] = link.item(0).getChildNodes().item(0).getNodeValue();
                          arrays.PodcastContent[i] = content.item(0).getChildNodes().item(0).getNodeValue();
                          // arrays.PodcastMedia[i] = mediaurl;
                          arrays.PodcastCategory[i] = category.item(0).getChildNodes().item(0).getNodeValue();
                          
                          /*System.out.print(title.item(0).getChildNodes().item(0).getNodeValue());
                          System.out.print("\t\n");
                          System.out.print(link.item(0).getChildNodes().item(0).getNodeValue());
                          System.out.print("\t\n");
                          System.out.print(content.item(0).getChildNodes().item(0).getNodeValue());
                          System.out.print("\t\n");
                          System.out.print(mediaurl);
                          System.out.print("\t\n");
                          System.out.print("\t\n");*/
                    }
                   
              }
              
        }
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DOMException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	

}
