//Sony Theakanath
//May 19, 2013
//Data Structures

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;


/**
   Tester class. Just runs the program.
   
   Reflection: The program was fairly simple to make. Once searching online
   I figured how to scrape the website source code. The only trouble I had 
   was to configure the data based on the difference of each website. To counter 
   this I just made a separate function to clean up the source code. The data
   structures used in this project are HashMaps and ArrayLists. The ArrayList 
   data structure is in the RSSReader class and the HashMaps are used in
   the Article class. 
*/
public class RSSFeed_Theakanath {
   public static void main(String[]args) {
      RSSReader app = new RSSReader();
      app.run();
   }
}

/**
   RSSReader takes all of the data and then sorts them according to the
   data they have. Indexes everything into Hashmaps and then allows the
   user to search the files based on occurrence. 
*/
class RSSReader {
   private ArrayList<Article> articles;
   
   /**
      Default constructor. Handles all of the datapullin'.
      If user wants to add more URLs, just enter them below.
   */
   public RSSReader() {
      try {
         articles = new ArrayList<Article>();
         System.out.println("Building database... Please wait");
         pullData("http://news.google.com/news?pz=1&cf=all&ned=us&hl=en&output=rss");
         System.out.println("Pulled News Google");
         pullData("http://www.npr.org/rss/rss.php?id=1001");
         System.out.println("Pulled NPR");
         pullData("http://feeds.feedburner.com/time/topstories?format=xml");
         System.out.println("Pulled Time");
         pullData("http://feeds.mercurynews.com/mngi/rss/CustomRssServlet/568/200735.xml");
         System.out.println("Pulled San Jose Mercury News");
         
         //Enter more links here
         //pullData(<link here>);
      } catch (IOException i) {
         System.err.println("Pull Exception!");
      }
   }
   
   /**
      Runs the program and loops for the user.
   */
   public void run() {
      Scanner scan = new Scanner(System.in);
      System.out.println("Please enter a single search term [enter to break]: ");
      String response = scan.nextLine();
      while(!response.equals("")) {
         ArrayList<Article> found = findWord(response);
         if(found.size() == 0) {
            System.out.println("None of today's news articles contain the word '" + response + "'.");
         } else if (found.size() < 10) {
            System.out.println("Nice! We found " + found.size() + " articles that include the word '" + response + "'.");
            printArticles(found, 100);
         } else {
            System.out.println("We found " + found.size() + " articles with the word " + response + "'. [We'll just list 10 of them, though.]"); 
            printArticles(found, -1);
         }
         System.out.println("Please enter a single search term [enter to break]: ");
         response = scan.nextLine();
      }  
   }
   
   /**
      Adds articles to the arraylist to be sorted. 
   */
   public void pullData(String stringurl) throws IOException {
      URL url = new URL(stringurl);
      URLConnection con = url.openConnection();
      InputStream is =con.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      String line = null;
      String data = "";
      while ((line = br.readLine()) != null) {
         data+= line;
      }
      try {
         boolean done = false;
         while(!done) {
            if(data.indexOf("<item>") != -1) {
               String newstring = data.substring(data.indexOf("<item>"), data.indexOf("</item>")+7);
               data = data.substring(data.indexOf("</item>")+7);
               String cleaned = "<item>" + newstring.substring(newstring.indexOf("<title>"), newstring.indexOf("</title>")+8) + newstring.substring(newstring.indexOf("<description>"), newstring.indexOf("</description>")+14) + newstring.substring(newstring.indexOf("<link>"), newstring.indexOf("</link>")+7) + "</item>";
               JAXBContext jc = JAXBContext.newInstance(Article.class);
               Unmarshaller unmarshaller = jc.createUnmarshaller();
               StringReader xml = new StringReader(cleaned);
               Article article = (Article)unmarshaller.unmarshal(xml);
               article.getWordCount(newstring);
               articles.add(article);
            } else {
               done = true; 
            }
         }
      } catch (Exception e) {
         System.err.println("Pulldata error! " + e);
      }
   }
   
   /**
      Creates an ArrayList of all of articles that contain that
      certain query.
   */
   public ArrayList<Article> findWord(String query) {
      ArrayList<Article> found = new ArrayList<Article>();
      for(int x = 0; x < articles.size(); x++) {
         if(articles.get(x).getOcc(query) > 0) {
            Article newarticle = articles.get(x);
            newarticle.setNum(newarticle.getOcc(query));
            found.add(newarticle);
         }
      }
      Collections.sort(found, new Comparator<Article>() {
         public int compare(Article s1, Article s2) {
            return s1.getNum().compareTo(s2.getNum());
         }
      });
      return found;
   }
   
   /**
      Prints all of the articles stored in the arraylist.
   */
   public void printArticles(ArrayList<Article> a, int printcount) {
      if(printcount == -1)
         printcount = 10;
      else
         printcount = a.size();
      for(int x = 0; x < printcount; x++) {
         System.out.println("Article " + (x+1));
         System.out.println(a.get(x).title);
         System.out.println(a.get(x).link + "\n");
      }
   }
   
   /**
      Class for the article. Stores all the the details of the article
      in a string.
   */
   @XmlRootElement(name="item")
   @XmlAccessorType(XmlAccessType.FIELD)
   static class Article {
      public HashMap<String, Integer> wordcount;
      private Integer numofocc; 
      
      @XmlElement(name="title")
      String title;
      
      @XmlElement(name="link")
      String link;
      
      @XmlElement(name="description")
      String description;
      
      /**
         Gets the number of occurrences of the certain query. 
      */
      public Integer getOcc(String item) {
	      Integer num = wordcount.get(item.toLowerCase());
	      return num == null ? 0 : num;
      }
      
      /**
         Gets numofocc.   
      */
      public Integer getNum() {
         return numofocc;
      }
      
      /**
         Setter for numofocc
      */
      public void setNum(Integer num) {
         numofocc = num;
      }
      
      /**
         Creates the hashmap of all of the words found in the article. 
      */
      public void getWordCount(String data) {
   	   final String invalidChars = "[^a-zA-Z0-9\\s]";
   	   data = data.toLowerCase().replaceAll(invalidChars," ");
   	   String[] split = data.split("\\s");
   	   HashMap<String, Integer> map = new HashMap<String, Integer>();
   	   for(String s : split) {
   	      if(s.length() > 0) { 
               Integer val = map.get(s);
	            if(val == null)
	               val = 0;
	            map.put(s,val+1);
            }
         }
   	   wordcount = map;
      }
   }
}