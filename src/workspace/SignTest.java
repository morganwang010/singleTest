package workspace;


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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

 
public class SignTest  {
	 
	 
	 
		public static void main(String[] args) {
		 	Integer xrunning = 0;
	       Integer xstopped = 0;
	       Integer yrunning = 0;
	       Integer ystopped = 0;
	   
	      
	       String apikey = "GzpGrVPWbLwWS9I67jD5Rp5QPmEqfx9tXUjJNII8HYdHNaMFQ-sLB-ZAmgcQehx53fHea5IYur8S3Q6cODAS3g";
	       String secretkey = "wZvi3RxtwyFbZd1TErLoAInu9LqAFugEqSguVzugpETNB6YWUiTOGBiyQ9AHfjS3y8kgnm1e7Iddy_0YkWIylQ";
	       String unsignedRequest = "apikey=" + apikey +  "&" + "command=listVirtualMachines";
	      
	       
	   		 
	       unsignedRequest = unsignedRequest.toLowerCase();
	     
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
	       byte[] encryptedBytes = mac.doFinal();
	       System.out.println(encryptedBytes);
	       String computedSignature = Base64.encodeBase64String(encryptedBytes);
	       
	       System.out.println(computedSignature);
	      
	 }

	 
	 

}

