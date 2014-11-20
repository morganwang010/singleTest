package workspace;

import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class SignRequest {
	public static String url;
	public static String apikey;
	public static String secretkey;
	public static String command;
	
	
	public static void main (String[] args) {
		// Parameters
		List<String> argsList = Arrays.asList(args);
		Iterator<String> iter = argsList.iterator();
		/*
		while (iter.hasNext()) {
			String arg = iter.next();
			if (arg.equals("-a")) {
				apikey = iter.next();
				
			}
			if (arg.equals("-u")) {
				url = iter.next();
			}
			
			if (arg.equals("-s")) {
				secretkey = iter.next();
			}
		}
		
		*/
		 //String url="command=createAccount&accounttype=0&email=12314@abc.com&firstname=aaud&lastname=ccoud&password=netcenter&username=appledd";
		//String url = "command=listOtherRegionResource";
		 //String url = "command=listApis";
		//String url = "command=listPublicIpAddresses";
		// String url = "command=registerTemplate";
		//String url="command=listVirtualMachines";
		// String url = "command=listNetworks";
		// String url = "command=listAccounts";
		
//		String url = "command=createFirewallRule";
		String url = "command=reconnectHost";
		// String url = "command=vmNotice";
		//String url = "command=createPortForwardingRule";
		String apikey="0kyyQ4W-H3MGXxeVez13Nwi5ipVGLMHKI0b-yJjJLXnb5Ge7mC3kHirKBFfLP-YP1VaBIF4zg2ISyUtD-SJhUQ";//admin apikey
		String secretkey = "9Wnrg0G6m1emdyfZpJ9-SlJU_PXv7v2x-rl0xtWyznMZGP2pxETBKFQePwXix1wc__gVlFp0s25bTrFwcuE4Ng";//admin secretkey
		//String apikey = "GzpGrVPWbLwWS9I67jD5Rp5QPmEqfx9tXUjJNII8HYdHNaMFQ-sLB-ZAmgcQehx53fHea5IYur8S3Q6cODAS3g";  //user morgan apikey
		//String secretkey = "wZvi3RxtwyFbZd1TErLoAInu9LqAFugEqSguVzugpETNB6YWUiTOGBiyQ9AHfjS3y8kgnm1e7Iddy_0YkWIylQ";  //user morgan secretkey
		//String apikey = "ZGrg_iekhc__vcwsfX6iS3GJiQTM2A1OywFTJYF36_EdMspGVQiZ2kNfaR9u0gg-z1IIVvn61sixrmyGegBg1Q";	//user 10267867	
		//String secretkey = "Fh8OlpG5xvXbmgcPqBU7bjabR8S75Hhfb3Da5zntdQ7GhFsSHTWm9-uLvUDgpYYN-9OJqGIk6lA_S2Njc4pl8w"; 	//user 10267867	
		//apikey = "MkBsNZfMpIySgaPVGEtHGQYLewbdWNfRLEXp7wqFCFAumlVspwGjWHj2AVtvZpg8G_2sOTNhWBWP3kG6h76ZYQ";
		//secretkey = "72/oU1necJk+yNzcZ6bThHcD6K1BvB7qtZFMovD1u9wgXiSNB6zW460kjjVqWw84uB/Vo5Hh/mNIpt8/YyraYuf3HB567zxeGTdjzInx1zCFJkpW4Geja4DIePG8hCKn";
	
		
		TreeMap<String, String> param = new TreeMap<String, String>();
		//浠ヤ笅鏄痗loudstack 涓�涓被 SignRequest.java涓敓鎴� signature鐨勭畻娉曪紝---by morgan
		String temp = "";
		param.put("apikey", apikey);
		param.put("response", "json");
		//param.put("id", "5482");
		//param.put("ipAddress", "210.73.36.63");
/*
		param.put("ipaddressid","5cb50a83-fc33-4212-b532-e7c94a8914d5");
		param.put("privateport", "22");
		param.put("privateendport", "22");
		param.put("publicport", "6000");
		param.put("publicendport", "6000");
		param.put("virtualmachineid", "07abbd83-2a7c-4511-9418-bd6eff312d76");
		param.put("openfirewall", "false");
		 *///param.put("protocol", "tcp");
//		param.put("startport", "10060");
//		param.put("ipaddressid", "ca16bc5e-2099-4464-8316-1554575d72fb");
//		param.put("protocol", "tcp");
//		 param.put("endport", "10060");
		 
//		 param.put("cidrlist", "0.0.0.0/0");
		 param.put("id", "1");
		/*param.put("account", "10034777");
		
		param.put("domainid", "1");
		param.put("zoneid", "2");
		param.put("url", "http://159.226.148.141:8080/client/44.qcow2");
		param.put("ostypeid", "14");
		param.put("name", "55");
		param.put("hypervisor", "KVM");
		param.put("format", "QCOW2");
		param.put("displaytext", "44");
		*/
		//param.put("domain_id", "1");
		StringTokenizer str1 = new StringTokenizer (url, "&");
		while(str1.hasMoreTokens()) {
			String newEl = str1.nextToken();
			StringTokenizer str2 = new StringTokenizer(newEl, "=");
			String name = str2.nextToken();
			String value= str2.nextToken();
			param.put(name, value);
		}
		
		//sort url hash map by key
		Set c = param.entrySet();
		Iterator it = c.iterator();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry)it.next();
			String key = (String) me.getKey();
			String value = (String) me.getValue();
			try {
				temp = temp + key + "=" + URLEncoder.encode(value, "UTF-8") + "&";
			} catch (Exception ex) {
				System.out.println("Unable to set parameter " + value + " for the command " + param.get("command"));
			}
			
		}
		System.out.println(temp);
		System.out.println("hahahahahahahahah");
		temp = temp.substring(0, temp.length()-1 );
		
		System.out.println(temp);
		String requestToSign = temp.toLowerCase();	
		System.out.println("After sorting: " + requestToSign);
		System.out.println("----------------------------");
		//requestToSign = "apikey=mkbsnzfmpiysgapvgethgqylewbdwnfrlexp7wqfcfaumlvspwgjwhj2avtvzpg8g_2sotnhwbwp3kg6h76zyq&command=listvirtualmachines&account=10267867";
		System.out.println("After sorting: " + requestToSign);
		String signature = UtilsForTest.signRequest(requestToSign, secretkey);
		System.out.println("After Base64 encoding: " + signature);
		String encodedSignature = "";
		try {
			encodedSignature = URLEncoder.encode(signature, "UTF-8");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		//**************鐢熸垚signature缁撴潫    by  morgan  **********************
		
	      
           
           String unsignedRequest = requestToSign;
//浠ヤ笅閮ㄥ垎鏄痑piserver.java閲岀敓鎴恠ignature鐨勭畻娉曪紝杩欓噷鐢熸垚鐨剆ignature锛宑loudstack绉颁负computed signature,瑕佷笌澶栭儴浼犲叆鐨勮繘琛屾瘮瀵�--by morgan
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
	//*************************鐢熸垚computed signature  缁撴潫  by morgan*********************	
           
           
           System.out.println("After computedSignature  encoding: " + computedSignature);
           System.out.println("After UTF8 encoding: " + encodedSignature);
		 url = temp + "&signature=" + encodedSignature;
		System.out.println("After sort and add signature: " + url);
		
	}
}
