package newpackage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
;

public class MyClass {

	public static void main(String[] args) throws InterruptedException, IOException {
	
     String exepath = "C:\\ChromeDriver\\ChromeDriver.exe";
     System.setProperty("webdriver.chrome.driver", exepath);
     
     // Initialize browser
     WebDriver driver=new ChromeDriver();

     // Open alibaba.com
     driver.get("https://www.alibaba.com/");

     // Maximize browser
     driver.manage().window().maximize();

     //Search with search text
     WebElement searchtext = driver.findElement(By.name("SearchText"));
     searchtext.sendKeys("iPhone");
     
     //enter search button
     driver.findElement(By.className("ui-searchbar-submit")).sendKeys(Keys.ENTER);
     Thread.sleep(5000);
     
     File f = new File("C:\\testing\\temp.txt");        
     f.createNewFile();
     
     List<WebElement> urlofProduct = driver.findElements
    		 (By.xpath(".//a[contains(@class,'organic-gallery-offer__img-section')]"));
     System.out.println("Number of elements:" +urlofProduct.size());

      List<WebElement> priceofproduct = driver.findElements
    		 (By.xpath(".//p[contains(@class,'elements-offer-price-normal medium')]"));
     System.out.println("Number of elements:" +priceofproduct.size());
   
     double maxvalue = 0;
     for (int j=0; j<priceofproduct.size();j++) 
     {
    	 String productPrice= priceofproduct.get(j)
    	         .getAttribute("title");
    	 
    	 if(productPrice.contains("-")) 
    	 {
    	 productPrice = productPrice.substring(productPrice.indexOf("$")+1, productPrice.lastIndexOf("-"));
    	 System.out.println(productPrice);
    	 }
    	 else
    	 {
    	productPrice = productPrice.replace("$","");
    	 System.out.println(productPrice);
    	 
    	 }
    	 productPrice = productPrice.replace(",","");
    	 String str = productPrice;
    	 double value = Double.parseDouble(str);
    	 if(value > maxvalue){
    		 maxvalue = value;
    	 }    	
     }
     
     System.out.println("Highest Price of PRoduct is = " + maxvalue); 
       
     FileWriter w = new FileWriter("C:\\testing\\temp.txt");
     BufferedWriter out = new BufferedWriter(w);
         
     for (int i=0; i<urlofProduct.size();i++) {
    	 
          System.out.println("Price of product:" + priceofproduct.get(i)
         .getAttribute("title")+" "+ urlofProduct.get(i).getAttribute("href"));
          out.newLine();
          out.write((urlofProduct.get(i).getAttribute("href")));
          out.newLine();
          out.write(priceofproduct.get(i).getAttribute("title"));
          out.newLine();
        }
          out.write(("Highest Price of PRoduct is = " + "$" +maxvalue));
          out.close();
	}
}

