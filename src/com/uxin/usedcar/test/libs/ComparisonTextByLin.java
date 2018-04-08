package com.uxin.usedcar.test.libs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.junit.Assert;

//import jdk.nashorn.internal.codegen.RuntimeCallSite;


public class ComparisonTextByLin {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		read(filea);
		try {
			Process p = Runtime.getRuntime().exec("adb devices");
			//读取命令返回值
			InputStream is = p.getInputStream();
			InputStreamReader ir = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(ir);
			String lin;
			while ((lin=br.readLine())!=null) {
				System.out.println(lin);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			System.out.println(readAppointedLineNumber(new File(fileb),1));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("进catch");
//			e.printStackTrace();
//		}
	}
	
//	/**
//	 * 逐行比对2个文件
//	 * @param expectedPath
//	 * @param actualPath
//	 */
//	public static void read(String expectedPath,String actualPath){
//		int i = 1;
//		String tempString = null;
//		try {
//			FileReader reader = new FileReader(new File(expectedPath));
//			BufferedReader br = new BufferedReader(reader);
//			while ((tempString = br.readLine()) != null) {
////				System.out.println(tempString+"<第"+i+"行>");
//				if (tempString.equals(readAppointedLineNumber(new File(actualPath), i))) {
//					System.out.println("找到"+readAppointedLineNumber(new File(actualPath), i)+"，行号为"+i);
////					break;
//				}else {
//					System.out.println(tempString+"不存在");
//					Assert.fail(tempString+"不存在");
//				}
//				i++;
////			return tempString;	
//			}
//			br.close();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	//读取指定行
	public static String readAppointedLineNumber(File sourceFile, int lineNumber)  
	            throws IOException {  
	        FileReader in = new FileReader(sourceFile);  
	        LineNumberReader reader = new LineNumberReader(in);
	        String temp = "";
	        String s = "";  
	        if (lineNumber <= 0 || lineNumber > getTotalLines(sourceFile)) {  
	            System.out.println("不在文件的行数范围(1至总行数)之内。");  
	            System.exit(0);
	        }  
	        int lines = 0;  
	        while (s != null) {
	            lines++;  
	            s = reader.readLine();
	            if((lines - lineNumber) == 0) {
	            temp = s;
//	             System.out.println("s="+s);
	           }  
	        }  
	        reader.close();  
	        in.close(); 
	        return temp;
	    }  
	 // 文件内容的总行数。  
	   public static int getTotalLines(File file) throws IOException {  
	        FileReader in = new FileReader(file);  
	        LineNumberReader reader = new LineNumberReader(in);  
	        String s = reader.readLine();  
	        int lines = 0;  
	        while (s != null) {  
	            lines++;  
	            s = reader.readLine();  
	        }  
	        reader.close();  
	        in.close();  
	        return lines;  
	    }  
	public void comparison(){
		
	}
}
