package workspace;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlExtract {

	public static void main(String[] args) {
		String path = "E:/1.txt";
		String context = null;;
		File file = new File(path);
		BufferedReader reader = null;
		StringBuffer buf = new StringBuffer();
		String regex = "href=\"([^\"]*)\"";   
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 context = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pattern p = Pattern.compile(regex, Pattern.DOTALL);  
         
	    Matcher m = p.matcher(context);  
	    while(m.find()){
	    	System.out.println(m.group(0));
	    	break;
	    	
	    } 
	    String url = m.group(0);
	    URL urlnew = null;
	    String urlretrieve = url.substring(6).replace('"', ' ');
		System.out.println(url.substring(6).replace('"', ' '));
		try {
			urlnew = new URL(urlretrieve);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		HttpURLConnection conn = null;
		try {
  			 conn = (HttpURLConnection) urlnew.openConnection();
  			
	   		} catch (IOException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}
	    	try {
				conn.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	conn.setConnectTimeout(500);   
	    	try {
				if(conn.getResponseCode()==200){
					InputStream is = conn.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte [] buffer = new byte[1024];
					int len =0;
					while((len=is.read(buffer))!= -1){
						baos.write(buffer,0,len);
						
					}
					System.out.println(baos.toString());
					
				}
	    	}catch(IOException e){
	    		
	    		
	    	}
	    	//String contextfactor = 

	}

}
