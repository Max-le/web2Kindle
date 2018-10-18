import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;


public class Kindle{

	static final boolean DEBUG = true ; 

	public static void main(String[] args) {
		//open_in_Chrome("https://www.economist.com/business/2018/07/19/the-nord-stream-2-pipeline-will-strengthen-russias-hand");

		final String ADRESS = "http://rss.nytimes.com/services/xml/rss/nyt/Technology.xml";
		String[] links = readRSS(ADRESS);
			System.out.println("# of non-null elements : "+ non_null_lentgh(links));
			System.out.println("The array : ");
			System.out.println();

			for(int i = 0 ; i < non_null_lentgh(links) ; i++){
				System.out.println(i + " - "+links[i]);
				String name_article = "tmp/article"+i+".html";
				//System.out.println("Saving...");
				//saveHTMLdoc(links[i],name_article);
			}
		


			System.out.println("Waiting...");
		    open_in_Chrome(links[22]);
			try{
				TimeUnit.SECONDS.sleep(10);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException");		
			}
			System.out.println("Press ALT+K...");
		   pressALT_K_in_Chrome();
		   System.out.println("Done.");



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
					String keyword = "link";
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

public static void saveHTMLdoc(String adress,String outputName){


	try { 

            // Create URL object 
		URL url = new URL(adress); 
		BufferedReader readr =  
		new BufferedReader(new InputStreamReader(url.openStream())); 

            // Enter filename in which you want to download 
		BufferedWriter writer =  
		new BufferedWriter(new FileWriter(outputName)); 

            // read each line from stream till end 
		String line; 
		while ((line = readr.readLine()) != null) { 
			writer.write(line); 
		} 

		readr.close(); 
		writer.close(); 
		System.out.println( outputName+" was successfully Downloaded."); 
	} 

        // Exceptions 
	catch (MalformedURLException mue) { 
		System.out.println("Malformed URL Exception raised"); 
	} 
	catch (IOException ie) { 
		System.out.println("IOException raised"); 
	} 

}// end saveHTMLDoc method

public static void open_in_Chrome(String adress){
	try{


		if ( DEBUG) System.out.println("Opening in Chrome...");

		String[] args = new String[] {"open","-a","Google Chrome" ,adress};
		Process proc = new ProcessBuilder(args).start();

	}
	catch (IOException io){
		System.out.println("Error.");
	}

}

//!!!this method will press ALT+K in Chrome, which will send the article to the device.
public static void pressALT_K_in_Chrome(){
	try{
		String[] args = new String[] {"open","press_ALT_K.app"};
		Process proc = new ProcessBuilder(args).start();
	}
	catch (IOException io){
		System.out.println("Error.");
	}
}

}



