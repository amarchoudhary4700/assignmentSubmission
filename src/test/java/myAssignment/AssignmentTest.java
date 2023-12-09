package myAssignment;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AssignmentTest{

	SetDriverForBrowser setDriver = new SetDriverForBrowser();
	
	@Test
	public void completeAssignment() {
		/* 
		 * Can change browser by changing parameter
		 */
		WebDriver driver = setDriver.setUp("Chrome");
		
		/*
		 * Below class contains all the action methods
		 */
		AssignmentPageActions pageAction = new AssignmentPageActions(driver);
		
		
		
		/* launching url */
		pageAction.launchUrl("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		String checkPageHeading = pageAction.getPageHeading();
		boolean checkTableHasPrefilledValues = pageAction.checkTableHasPreFilledValues();
		
		/* clicking on button */
		pageAction.clickTableDataButton();
		
		pageAction.sendNewValuesToInputField();
		pageAction.clickRefreshTableButton();
		
		boolean checkNewValuesAreAppearingInTable = pageAction.checkTableGetsPopulatedWithNewValues();
		
		Assert.assertEquals(checkPageHeading, "Dynamic HTML TABLE Tag", 
				"[ASSERT FAILED]: " + "Page is not loaded");
		Assert.assertTrue(checkTableHasPrefilledValues, "[ASSERT FAILED]: " + "Pre filled values are not appearing");
		Assert.assertTrue(checkNewValuesAreAppearingInTable, "[ASSERT FAILED]: " + "New values are not appearing in table");
	}
}
