import java.net.*;
import java.io.*;

		//TODO : mettre tous les liens dans une array


public class Kindle{

		static final boolean DEBUG = true ; 

		public static void main(String[] args) {
		final String ADRESS = "http://rss.cnn.com/rss/edition_technology.rss";
		String links = readRSS(ADRESS);
		System.out.println(links);


	}
	public static String readRSS(String adress){
		System.out.println("URL adress : " +adress);

		try{

			//Array to store items
			String[] items = new String[10000];


		//Create URL Object 
			URL rssURL = new URL(adress);

			BufferedReader in = new BufferedReader( new InputStreamReader(rssURL.openStream()));

			String source_code="";
			String line;

			//read line-by-line, continue loop while the current line is not empty
			while ((line = in.readLine()) != null ){
				int index_array = 0;
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
					source_code += temp + "\n \n"; 
					index_array++;

				}

		}//end while loop
		in.close();//close buffered reader
		return source_code;
	}
	catch (MalformedURLException ue ){
		System.out.println("Error : the url is incorrect.");
	}
	catch (IOException ioe ) {
		System.out.println("Error while reading the contents.");
	}
	return null;





}
}



