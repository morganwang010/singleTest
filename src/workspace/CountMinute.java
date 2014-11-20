package workspace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountMinute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File minute = new File("E:/1.txt");
		try{
		InputStreamReader read = new InputStreamReader(new FileInputStream(minute));
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		String test11 = null;
		int num = 0;
		 try {
			while((lineTxt = bufferedReader.readLine()) != null){
				String[] minutes = null;
				String[] odd = null;
				if(lineTxt.contains("分"))
				{
					minutes = lineTxt.split("分");
					System.out.println("aaa"+minutes[0]+"ccddee");
					test11 = minutes[0];
					
					System.out.println(minutes[0].length());
					System.out.println(test11.charAt(0));
					num = Integer.parseInt(test11);
					if(lineTxt.contains("秒")){
						System.out.println(num+1);
						
					}
					else{
						System.out.println(num);
						
					}
					
					
				}else{
					System.out.println(1);
					
				}
				
				
				
			     
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         try {
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}catch( FileNotFoundException e){
			System.out.println("file not exist");
			
		}
		
	}

}
