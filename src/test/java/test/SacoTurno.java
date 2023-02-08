package test;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SacoTurno {


    public static void main(String[] args) throws IOException {
    	System.setProperty("webdriver.chrome.driver", new File("./Drivers/chromedriver").getCanonicalPath());
		WebDriver driver = new ChromeDriver();
		String MesParaSacarTurno = "Junio";
		boolean sePudoSacarTurno = false;
		String mensaje = "Se pudo sacar turno en el mes " + MesParaSacarTurno;
		try {
			
		        driver.get("https://www.hospitalaleman.com/portalpacientes/login");
				driver.manage().window().maximize() ;
		        driver.findElement(By.id("email")).sendKeys("22587947");
		        driver.findElement(By.id("password")).sendKeys("Inicio01!");
		        driver.findElement(By.cssSelector("[type='submit']")).click();        
		
		        WebDriverWait wait = new WebDriverWait(driver, 10);
		        WebElement element = wait.until(
		                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".button-ha.txt-white")));
		        if (element != null)
		        	element.click();

		        element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-primary")));
		        driver.findElement(By.cssSelector(".btn-primary")).click();
		        
		        element = wait.until(
		                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#speciality")));
		        driver.findElement(By.cssSelector("#speciality")).click();
		        driver.findElement(By.cssSelector("#speciality [role='combobox']")).sendKeys("REUMATOLOGIA");
		        driver.findElement(By.cssSelector("[role='option']")).click();
		        
		        driver.findElement(By.cssSelector("#doctor")).click();
		        driver.findElement(By.cssSelector("#doctor [role='combobox']")).sendKeys("Nitsche");
		        driver.findElement(By.cssSelector("[role='option']")).click();
		
		        driver.findElement(By.cssSelector(".btn.blue.float-left")).click();
		
		        element = wait.until(
		                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#timeBand")));
		        driver.findElement(By.cssSelector("#timeBand")).click();
		        driver.findElement(By.cssSelector("#timeBand [role='combobox']")).sendKeys("Indistinto");
		        driver.findElement(By.cssSelector("[role='option']")).click();
		
		        driver.findElement(By.cssSelector("#locations")).click();
		        driver.findElement(By.cssSelector("[role='option']")).click();
		
		        driver.findElement(By.cssSelector(".blue.float-left")).click();
		
		        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".next")));
		        element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".next")));
		        Thread.sleep(3000);
		        if (element != null)
		        	element.click();
		        
		        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector((".btn.red.float-left"))));
		        if (driver.findElement(By.cssSelector(".txt-caps.txt-blue")).getText().contains(MesParaSacarTurno)) {
		        	
		        	List<WebElement>turnosDisponibles = driver.findElements(By.cssSelector("[class*='available-appointments ']"));
		        	if (turnosDisponibles.size() != 0) {
		        		turnosDisponibles.get(0).click();        		
		        			        	
			        	List<WebElement>horariosDisponibles = driver.findElements(By.cssSelector("[class*='item-list ng-star-inserted']"));
			        	if (horariosDisponibles.size() != 0) {
			        		horariosDisponibles.get(0).click();        	
			                driver.findElement(By.cssSelector(".btn.blue.float-left")).click();
					        element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector((".yellow"))));
			                element.click();
			                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector((".modal-window"))));		                
			                Thread.sleep(2000);
			                element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector((".next"))));
					        element.click();
			                Thread.sleep(2000);
					        sePudoSacarTurno = true;    
			        	}
			        	else
					        mensaje = "No hay horarios disponibles en el mes " + MesParaSacarTurno; 

		        	}
		        	else 
			        	mensaje = "No hay turnos disponibles en el mes " + MesParaSacarTurno; 
		        }      
		        else 
		        	mensaje = "No está por defecto el mes " + MesParaSacarTurno; 
		        
		        Assert.assertTrue(sePudoSacarTurno, mensaje);
			}
		catch (Exception e) {
	        e.printStackTrace();
		}
		finally{
	        driver.close();
		}

    }
}