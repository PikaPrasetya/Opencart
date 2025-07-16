package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Data Provider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\TestData\\OpencartLogin.xlsx";
		ExcelUtils xlutil = new ExcelUtils(path); //create an object for ExcelUtils
		
		int totalRow = xlutil.getRowCount("Sheet1");
		int totalCol = xlutil.getcolCount("Sheet1", 0);
		
		String loginData[][] = new String[totalRow][totalCol]; //create two dimensional array to store the data
		
		for(int i=1;i<=totalRow;i++)
		{
			for(int j=0;j<totalCol;j++)
			{
				loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}
	
	//Data provider 2
	
	//Data provider 3

}
