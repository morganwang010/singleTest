package org.chinavo.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HttpConnectionTest {
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		URL url = null;
		Integer yrunning = 0;
		Integer ystopped = 0;
		Integer xrunning = 0;
		Integer xstopped = 0;
		String apikey = "MkBsNZfMpIySgaPVGEtHGQYLewbdWNfRLEXp7wqFCFAumlVspwGjWHj2AVtvZpg8G_2sOTNhWBWP3kG6h76ZYQ";
	    String secretkey = "p3pk4huug1Y5eNE_6i6FgH_ysz86p_hdrCtkF7gUrV7SesoSJCVb2o6Qk41VQeI31PzjBYpE4v4GXAH0VlB--Q";
	    String account = "10267867";
	    apikey =  URLEncoder.encode(apikey, "UTF-8");
	    secretkey =  URLEncoder.encode(secretkey, "UTF-8");
	    account =  URLEncoder.encode(account, "UTF-8");
	     
	    
	    String unsignedRequest =  "account=" + account + "&apikey=" + apikey +  "&" + "command=listVirtualMachines";
	      
	        
	       unsignedRequest = unsignedRequest.toLowerCase();
	      // unsignedRequest = "account=10267867&apikey=mkbsnzfmpiysgapvgethgqylewbdwnfrlexp7wqfcfaumlvspwgjwhj2avtvzpg8g_2sotnhwbwp3kg6h76zyq&command=listvirtualmachines";
	      // unsignedRequest = "apikey=mkbsnzfmpiysgapvgethgqylewbdwnfrlexp7wqfcfaumlvspwgjwhj2avtvzpg8g_2sotnhwbwp3kg6h76zyq&command=listvirtualmachines&account=10267867";
	       Mac mac = null;
			try {
				mac = Mac.getInstance("HmacSHA1");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           SecretKeySpec keySpec = new SecretKeySpec(secretkey.getBytes(), "HmacSHA1");
	           System.out.println("keySpec is " + keySpec);
	           try {
				mac.init(keySpec);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       mac.update(unsignedRequest.getBytes());
	       System.out.println(unsignedRequest);
	       byte[] encryptedBytes = mac.doFinal();
	       System.out.println(encryptedBytes);
	       String computedSignature = Base64.encodeBase64String(encryptedBytes);
	       
	       
	       
	       System.out.println(computedSignature);
		String urladdress = "apikey=" + apikey + "&" + "command=listVirtualMachines" + "&" + "signature=" + computedSignature +"&account=10267867";
	    Map<Integer,String> host = new HashMap<Integer,String>(); //use map to store the different host
	       host.put(1, "http://159.226.148.141:8080/client/api?");
	       host.put(2, "http://210.73.36.59/client/api?");
	   
	       HttpURLConnection conn = null;
	       String urlretrieve;
	       
	       for(Integer key: host.keySet()){ //itertor the host separately
	    	   urlretrieve = host.get(key) + urladdress;
	    	   try {
				url = new URL(urlretrieve);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	   try {
	   			 conn = (HttpURLConnection) url.openConnection();
	   			
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
						String jsonString = baos.toString();
						baos.close();
						is.close();
						 
						Document doc = null;
						DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
						DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder(); 
						doc = dbBuilder.parse(new InputSource(new StringReader(jsonString)));
						Element rootElement = doc.getDocumentElement();
						NodeList phones = rootElement.getElementsByTagName("virtualmachine");
						NodeList list = doc.getChildNodes();  
						
						
					    for (int i = 0; i < list.getLength(); i++) {  
					    	//System.out.println(i);
					           Node session = list.item(i);  
					           NodeList sessionInfo = session.getChildNodes();  
					           for (int j = 0; j < sessionInfo.getLength(); j++) {  
					           	//System.out.println(j);
					               Node node = sessionInfo.item(j);  
					               if(node.getNodeName()=="count")
					               {
					               //	System.out.println(node.getNodeName()+":"+node.getFirstChild().getTextContent());
					               	int ab = Integer.parseInt(node.getFirstChild().getTextContent());
					               }	
					               NodeList sessionMeta = node.getChildNodes();  
					               for (int k = 0; k < sessionMeta.getLength(); k++) {  
					               	if(sessionMeta.item(k).getTextContent().compareTo("Stopped")==0){
					               		if(key==1){
					               			ystopped ++;
					               		}else{
					               			xstopped ++;
					               		}
					               		
					               	}
					               	if(sessionMeta.item(k).getTextContent().compareTo("Running")==0){
					               		if(key == 1){
					               			yrunning ++;
					               		
					               		}else{
					               			xrunning ++;
					               		}
					               		
					               	}
					                   //System.out.println(sessionMeta.item(k).getNodeName() + ":" + sessionMeta.item(k).getTextContent());  
					               } //end of each item in the xml file  --by morgan 
					           }  
					       } 
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DOMException e) {
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
	       System.out.println(yrunning);
	       System.out.println(ystopped);
	       System.out.println(xrunning);
	       System.out.println(xstopped);
	       /*
			try {
				url = new URL(address);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpURLConnection conn = null;
			try {
				 conn = (HttpURLConnection) url.openConnection();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(500);
			
			if(conn.getResponseCode()==200){
				InputStream is = conn.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte [] buffer = new byte[1024];
				int len =0;
				while((len=is.read(buffer))!= -1){
					baos.write(buffer,0,len);
				}
				String jsonString = baos.toString();
				baos.close();
				is.close();
				
				//JSONArray jsonArray = new JSONArray(jsonString);  
				
				//for(int i = 0;i < jsonArray.length();i++){
				//	JSONObject jsonObject =new JSONObject(jsonString);
					
				//	System.out.println(jsonObject.get("version"));
				//	System.out.println(jsonObject.get("type"));
			//}
				 
				Document doc = null;
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder(); 
				doc = dbBuilder.parse(new InputSource(new StringReader(jsonString)));
				Element rootElement = doc.getDocumentElement();
				NodeList phones = rootElement.getElementsByTagName("virtualmachine");
				NodeList list = doc.getChildNodes();  
				int running = 0;
				int stopped = 0;
				
			    for (int i = 0; i < list.getLength(); i++) {  
			    	System.out.println(i);
	                Node session = list.item(i);  
	                NodeList sessionInfo = session.getChildNodes();  
	                for (int j = 0; j < sessionInfo.getLength(); j++) {  
	                	System.out.println(j);
	                    Node node = sessionInfo.item(j);  
	                    if(node.getNodeName()=="count")
	                    {
	                    	System.out.println(node.getNodeName()+":"+node.getFirstChild().getTextContent());
	                    	int ab = Integer.parseInt(node.getFirstChild().getTextContent());
	                    	System.out.print(5*ab);
	                    }	
	                    NodeList sessionMeta = node.getChildNodes();  
	                    for (int k = 0; k < sessionMeta.getLength(); k++) {  
	                    	if(sessionMeta.item(k).getTextContent().compareTo("Stopped")==0){
	                    		stopped++;
	                    		
	                    	}
	                    	if(sessionMeta.item(k).getTextContent().compareTo("Running")==0){
	                    		running++;
	                    		
	                    	}
	                        System.out.println(sessionMeta.item(k).getNodeName() + ":" + sessionMeta.item(k).getTextContent());  
	                    }  
	                }  
	            } 
			    
				System.out.println("Running machines is " + running);
				System.out.println("Stopped machines is " + stopped);
				
				for (int i = 0; i < phones.getLength(); i++) {
					 Node type = phones.item(i);
					 String phoneName = ((Element)type).getAttribute("name");
					 System.out.println(phoneName);
				}
			
			}
	*/
 }
	

}
