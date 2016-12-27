package BL;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by damarananta on 12/27/16.
 * Note: Assume all data can be located by id, Since there is no real web page to be tested. Or we can change to xpath, byname, etc
 */

public class TransactionData extends BaseTest{
	
	protected String TrxID_UI;
	protected String SellerName_UI;
	protected String DeliveryService_UI;
	protected String DateOrder_UI;
	protected String AddressShipment_UI;
	DBConnector DBConn = new DBConnector();
	//Test on UI
	@Test(enabled=true, priority=1)
	public void getTrxID_UI(){
		//Check availability (asssume the id is id_TrxID)
		WebElement getTrxID = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("id_TrxID")));
		getTrxID.isDisplayed();
		
		TrxID_UI = getTrxID.getText();
		//Check wording
		String TrxIDWording = TrxID_UI.substring(0, 15);
		Assert.assertEquals(TrxIDWording, String.Seller);
		
		//Gathering transaction ID
		TrxID_UI = TrxID_UI.substring(15); //remove "Seller: "
		
	}
	@Test(enabled=true, priority=2)
	public void getSellerName_UI(){
		//Check availability (assume the id is SellerName)
		WebElement getSellerName = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("SellerName")));
		getSellerName.isDisplayed();
		
		SellerName_UI = getSellerName.getText();
		//Check wording
		String getSellerNameWording = SellerName_UI.substring(0, 8);
		Assert.assertEquals(getSellerNameWording, String.Seller);
		
		//Gathering seller name
		SellerName_UI = SellerName_UI.substring(8); //remove "Seller: "
		
	}
	
	@Test(enabled=true, priority=3)
	public void getDeliveryService_UI(){
		//Check availability (assume the id is DeliveryService)
		WebElement getDeliveryService = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("DeliveryService")));
		getDeliveryService.isDisplayed();
		
		DeliveryService_UI = getDeliveryService.getText();
		//Check wording
		String getDeliveryServiceWording = DeliveryService_UI.substring(0, 17);
		Assert.assertEquals(getDeliveryServiceWording, String.DeliveryService);
		
		//Gathering delivery service
		DeliveryService_UI = DeliveryService_UI.substring(17);
		
	}
	
	@Test(enabled=true, priority=4)
	public void getDateOrder_UI(){
		//Check availability (assume the id is DateOrder)
		WebElement getDateOrder = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("DateOrder")));
		getDateOrder.isDisplayed();
		
		DateOrder_UI = getDateOrder.getText();
		//Check wording
		String getDateOrderWording = DateOrder_UI.substring(0,11);
		Assert.assertEquals(getDateOrderWording, String.DateOrder);
		
		//Gathering date order
		DateOrder_UI = DateOrder_UI.substring(11);
	}
	
	@Test(enabled=true, priority=5)
	public void getAddressShipment_UI(){
		//Check availability (assume the id is AddressShipment)
		WebElement getAddressShipment = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("AddressShipment")));
		getAddressShipment.isDisplayed();
		
		AddressShipment_UI = getAddressShipment.getText();
		//Check wording
		String getAddressShipmentWording = AddressShipment_UI.substring(0,17);
		Assert.assertEquals(getAddressShipmentWording, String.AddressShipment);
		
		//Gathering address shipment
		AddressShipment_UI = AddressShipment_UI.substring(17);
		
	}
	
	//Test on database
	@Test(enabled=true, priority=6)
	public void getTrxID_DB(){
		boolean flag = false;
        List<String> listOfDBValues = new ArrayList<String>();
        
        //Search displayed transfer id on UI with DB. Exist or not.
        String sqlQuery = "select "+TrxID_UI+" from employee";
        //Getting list of employee names from employee table
        listOfDBValues = DBConn.executeSQLQuery_List(sqlQuery);
        for (String strName : listOfDBValues) {
            if (strName.equalsIgnoreCase(TrxID_UI)) {
                flag = true;
                break;
            }
        }
        Assert.assertEquals(flag, true);
	}
	
	@Test(enabled=true, priority=7)
	public void getSellerName_DB(){
        String sqlQuery = "select SELLER_NAME from employee WHERE TRX_ID="+TrxID_UI+"";
        //get seller name
        String SellerName_DB = DBConn.executeSQLQuery(sqlQuery);
        Assert.assertEquals(SellerName_UI, SellerName_DB);
	}
	
	@Test(enabled=true, priority=8)
	public void getDeliveryService_DB(){
        String sqlQuery = "select DELIVERY_SERVICE from employee WHERE TRX_ID="+TrxID_UI+"";
        //get delivery service
        String DeliveryService_DB = DBConn.executeSQLQuery(sqlQuery);
        Assert.assertEquals(DeliveryService_UI, DeliveryService_DB);
	}
	
	@Test(enabled=true, priority=9)
	public void getDateOrder_DB(){
        String sqlQuery = "select DATE_ORDER from employee WHERE TRX_ID="+TrxID_UI+"";
        //get date order
        String DateOrder_DB = DBConn.executeSQLQuery(sqlQuery);
        Assert.assertEquals(DateOrder_UI, DateOrder_DB);
	}
	
	@Test(enabled=true, priority=10)
	public void addressShipment_DB(){
        String sqlQuery = "select ADDRESS_SHIP from employee WHERE TRX_ID="+TrxID_UI+"";
        //get date order
        String AddressShipment_DB = DBConn.executeSQLQuery(sqlQuery);
        Assert.assertEquals(AddressShipment_UI, AddressShipment_DB);
	}
	
	@Test(enabled=true, priority=11)
	public void MainMenu(){
		//assume Main menu link id is mainMenuID
		WebElement mainMenu = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("mainMenuID")));
		mainMenu.isDisplayed();
		mainMenu.click();
		//dunno what assertion should I add for mainMenu
	}
}
