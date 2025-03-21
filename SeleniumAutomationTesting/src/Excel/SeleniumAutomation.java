package Excel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class SeleniumAutomation {

    public static void main(String[] args) {
        // Set the path to ChromeDriver executable
        System.setProperty("C:\\BrowserDriver\\chrome-win64", "C:\\BrowserDriver\\chromedriver-win64\\chromedriver-win64");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Open the website
            driver.get("https://darkins.in/collections/dragees");

            // Wait for the "Shop" button to be clickable and click it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Increased timeout to 20 seconds
            WebElement shopButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.site-nav__label")));
            shopButton.click();

            // Wait for the first product to be clickable and click it
            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#shopify-section-16154579879a17c71b > div > ul > li:nth-child(1) > div > a")));
            firstProduct.click();

            // Wait for the product details to load
            WebElement productNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ProductSection-product-template > div > div:nth-child(2) > div.product-single__meta > h1")));
            WebElement productPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ProductSection-product-template > div > div:nth-child(2) > div.product-single__meta > div.product__price > dl > div.price__regular > dd > span")));
            WebElement productDescriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ProductSection-product-template > div > div:nth-child(2) > div.product-single__description.rte > p:nth-child(2)")));

            // Extract product details
            String productName = productNameElement.getText();
            String productPrice = productPriceElement.getText();
            String productDescription = productDescriptionElement.getText();

            // Print the details
            System.out.println("Product Name: " + productName);
            System.out.println("Product Price: " + productPrice);
            System.out.println("Product Description: " + productDescription);

            // Store the details in an Excel file
            writeToExcel(productName, productPrice, productDescription);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    private static void writeToExcel(String productName, String productPrice, String productDescription) {
        // Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Product Details");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Product Name");
        headerRow.createCell(1).setCellValue("Product Price");
        headerRow.createCell(2).setCellValue("Product Description");

        // Create data row
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(productName);
        dataRow.createCell(1).setCellValue(productPrice);
        dataRow.createCell(2).setCellValue(productDescription);

        // Write the output to a file
        
        try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Pooja.S\\eclipse-workspace\\SeleniumAutomationTesting\\src\\Excel\\product1.xlsx")) {
        	
            workbook.write(fileOut);
            System.out.println("Data saved to product1.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 
