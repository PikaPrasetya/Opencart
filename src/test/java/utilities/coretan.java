package utilities;

import java.io.IOException;

public class coretan {

	public static void main(String[] args) throws IOException {

		String path = System.getProperty("user.dir")+"//TestData//OpencartLogin.xlsx";
		ExcelUtils xlutil = new ExcelUtils(path);
		int row = xlutil.getRowCount("Sheet1");
		int cell = xlutil.getcolCount("Sheet1", 1);
		System.out.println(row);
		System.out.println(cell);
	}

}
