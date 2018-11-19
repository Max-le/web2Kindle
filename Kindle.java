import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;


public class Kindle{

	static final boolean DEBUG = true ; 

	public static void main(String[] args) {

		final String ADRESS = "http://rss.nytimes.com/services/xml/rss/nyt/Technology.xml";
		String[] links = readRSS(ADRESS);
		
		String listoflinks = new String();
		for(int i = 0 ; i < non_null_lentgh(links) ; i++){
			System.out.println(i + " - "+links[i]);
			listoflinks = listoflinks + links[i]+ "\n";
		}	

		//Put old links in txt file in a Scanner
		Scanner olds = new Scanner("list_links.txt");

		try {
			//Create new file
			PrintWriter writer = new PrintWriter("list_links.txt", "UTF-8");

			writer.println(listoflinks);
			writer.close();
		}
		catch(IOException e ){
			System.out.println("Error ! 🎃");
		}
		
		Scanner list = readtxtfile("list_links.txt");
		int j = 0 ; 
		while (list.hasNextLine()){
			System.out.println("READING VIA SCANNER "+list.nextLine());
			j++;
		}


		}//End MAIN


		/** This methods extracts the element with the tag "link" from a xml file.
		@param adress A url link to a rss file ( .xml )
		@return An array containing the elements  

		**/
		public static String[] readRSS(String adress){

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
					//Change this keyword if you to extract something else ( for example the title )
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

//Returns a Scanner object from a txt file
public static Scanner readtxtfile(String file){

	try{
	File inputFile = new File(file);
	Scanner in = new Scanner(inputFile);
	return in;

}
catch(FileNotFoundException e){
	System.out.println("Error : file not found");
	return null;
}

}


//Create a narray with the links from a txt file 
public static String[] txtToArray(String txtfile){

	Scanner 
	String[] list = new list[]
	 
}
}




