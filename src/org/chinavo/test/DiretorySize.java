package org.chinavo.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class DiretorySize {
	public static long readFile(File dir){
		
		long dirsize = 0;
		
		if(dir.isFile())
		{
			dirsize += dir.length();
		}else if(dir.isDirectory()){
			File[] files = dir.listFiles();
			for(File file: files){
				if(file.isFile()){
					dirsize += file.length();
				}
				else if(file.isDirectory()){
					dirsize += readFile(file);
					
					
				}
				
			}
		}
		return dirsize;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File dir = new File("H:");
		String[] filelist = dir.list();
		long total = 0;
		long t = 0;
		for(int i=0;i<filelist.length;i++){
			 File readfile = new File("H:" + "\\" + filelist[i]);
			 t = readFile(readfile)/1024/1024;
			 System.out.println("name=" + readfile.getName() + "  size:=" + t + "MB");
			 total += t;
		}
		System.out.println("OK,all the files is list over,the number is " + filelist.length + "the total size is " + total); 
	}

}
