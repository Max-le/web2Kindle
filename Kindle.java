import java.net.*;
import java.io.*;
import java.util.*;

		//TODO : mettre tous les liens dans une array


public class Kindle{

	static final boolean DEBUG = true ; 

	public static void main(String[] args) {
		final String ADRESS = "http://rss.cnn.com/rss/edition_technology.rss";
		String[] links = readRSS(ADRESS);
		//System.out.println(links);

		if (DEBUG){
			System.out.println("# of non-null elements : "+ non_null_lentgh(links));
			System.out.println("The array : ");
			for(int i = 0 ; i < non_null_lentgh(links) ; i++){
				System.out.println(i + " - "+links[i]);
			}
		}

	}
	public static String[] readRSS(String adress){
		System.out.println("URL adress : " +adress);

		try{

			//Array to store items
			String[] items = new String[10000];

		//Create URL Object 
			URL rssURL = new URL(adress);

			BufferedReader in = new BufferedReader( new InputStreamReader(rssURL.openStream()));

			String source_code="";
			String line;
			int index_array = 0;

			//read line-by-line, continue loop while the current line is not empty
			while ((line = in.readLine()) != null ){
				String keyword = "title";
				if (line.contains("<"+keyword+">")){
					int firstPos = line.indexOf("<"+keyword+">");
					if (DEBUG) System.out.println(firstPos);
					String temp = line.substring(firstPos);
				//remove the tag ( replace by nothing)
					temp = temp.replace("<"+keyword+">","");
					int lastPos = temp.indexOf("</"+keyword+">");
					if (DEBUG) System.out.println(lastPos);
					temp = temp.substring(0, lastPos);
					if (DEBUG) System.out.println("temp : "+temp);
					items[index_array] = temp;
					if (DEBUG) System.out.println(index_array + " in array : "+ items[index_array]);
					source_code += temp + "\n \n"; 
					index_array = index_array + 1 ;

				}

		}//end while loop
		in.close();//close buffered reader
		return items;
	}
	catch (MalformedURLException ue ){
		System.out.println("Error : the url is incorrect.");
	}
	catch (IOException ioe ) {
		System.out.println("Error while reading the contents.");
	}
	return null;





}// end readRSS method

public static int non_null_lentgh(String[] a){
	int i = 0;
	while (a[i] != null) i++;
	return i ; 
}
}



