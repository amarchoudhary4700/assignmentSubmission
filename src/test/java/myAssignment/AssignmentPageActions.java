package myAssignment;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AssignmentPageActions {
	WebDriver driver;
	WebDriverWait wait;
	
	public AssignmentPageActions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(30000) );
	}
	
	/* this method will wait untill element appears on screen */
	private WebElement waitForElementToLoad(WebElement element){
		return (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	String jsonString = "[{\"name\":\"Bob\",\"age\":20,\"gender\":\"male\"},{\"name\":\"George\",\"age\":42,\"gender\":\"male\"},{\"name\":\"Sara\",\"age\":42,\"gender\":\"female\"},{\"name\":\"Conor\",\"age\":40,\"gender\":\"male\"},{\"name\":\"Jennifer\",\"age\":42,\"gender\":\"female\"}]";
	
	
	public void launchUrl(String url){
		driver.get(url);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*")));
		System.out.println("Launched url: " + url);
	}


	public String getPageHeading() {
		WebElement pageHeading = driver.findElement(By.cssSelector(".page-body h1"));
		waitForElementToLoad(pageHeading);
		wait.until(ExpectedConditions.visibilityOf(pageHeading));
		return pageHeading.getText();
	}
	
	public boolean checkTableHasPreFilledValues(){
		boolean flag = false;
		List<WebElement> checkTableIsAppearingWithPreFilledValues = driver.findElements(By.cssSelector("#dynamictable td"));
		String preFilledValues[] = {"Bob", "20", "George", "42"};
		for(int i=0; i<checkTableIsAppearingWithPreFilledValues.size(); i++) {
			if(checkTableIsAppearingWithPreFilledValues.get(i).getText().equalsIgnoreCase(preFilledValues[i])) {
				flag = true;
			} else {
				break;
			}
		}
		return flag;
	}


	public void clickTableDataButton() {
		WebElement tableDataBtn = driver.findElement(By.cssSelector(".page-body summary"));
		tableDataBtn.click();
		System.out.println("Clicked table data button");
		
	}

	public void sendNewValuesToInputField() {
		WebElement newVaulueInput = driver.findElement(By.id("jsondata"));
		newVaulueInput.clear();
		newVaulueInput.sendKeys(jsonString);
	}

	public boolean checkTableGetsPopulatedWithNewValues() {
		boolean flag = false;
		List<WebElement> tableDataWebElement = driver.findElements(By.cssSelector("#dynamictable td")); 

		List<Object> tableDataStringValue = new ArrayList<>();
		for(WebElement actualData : tableDataWebElement) {
			tableDataStringValue.add(actualData.getText());
		}
		
		List<String> valuesList = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonArray = objectMapper.readTree(jsonString);

            for (JsonNode jsonNode : jsonArray) {
                Iterator<String> fieldNames = jsonNode.fieldNames();
                while (fieldNames.hasNext()) {
                    String fieldName = fieldNames.next();
                    JsonNode fieldValue = jsonNode.get(fieldName);
                    valuesList.add(fieldValue.asText());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0; i<tableDataStringValue.size(); i++) {
        	System.out.println(tableDataStringValue.get(i)+" "+ valuesList.get(i));
        }
        if(valuesList.equals(tableDataStringValue)) {
        	flag = true;
        }
        return flag;
    }

	public void clickRefreshTableButton() {
		WebElement refreshTableBtn = driver.findElement(By.id("refreshtable"));
		refreshTableBtn.click();
		System.out.println("Clicked refresh table button");
		
	}
	
}
