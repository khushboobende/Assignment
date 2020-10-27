package newpackage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MyClass1 {

	public static void main(String[] args) throws IOException {
		
		 int multiplier = 100; // configurable value
		 PrintWriter out = new PrintWriter("result.txt"); 
	     
		 FileInputStream fstream = new FileInputStream("input.txt");
		 DataInputStream in = new DataInputStream(fstream);
	     BufferedReader br = new BufferedReader(new InputStreamReader(in));
	     String data;
	     while ((data = br.readLine()) != null) {
	         System.out.print("data = " + data + "\n");
	    	 
	    	 for (String number: data.split("\\|")) {
	            	    		
	    	    int newnumber = Integer.parseInt(number);;
	    		newnumber = newnumber*multiplier;
	    		System.out.println(newnumber);
	    	    out.println(newnumber);	             	             
	          }
	    	    out.close();
	    }
   }
}
