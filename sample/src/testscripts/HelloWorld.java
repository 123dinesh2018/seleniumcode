package testscripts;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class HelloWorld {

	private static final String FILE_NAME = "C:/Users/Dinesh.IN/Downloads/form1.xlsx";
	static String username;
	static String password;
	public List<Bean> getReadXLData(Iterator<Row> iterator ) {
		List <Bean> li=new ArrayList<Bean>();

		try {

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				Bean bean= new Bean();
				List<String> list=new ArrayList<>();
				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();

					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						list.add(currentCell.getStringCellValue());
						System.out.print(currentCell.getStringCellValue() + "==");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						// System.out.print("Numeric values......");
					}

				}
				bean.setUserName(list.size()>0?list.get(0):"");
				bean.setPassword(list.size()>1?list.get(1):"");
				bean.setResult(list.size()>2?list.get(2):"");
				li.add(bean);
				System.out.println();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return	li;
	}


	public static void main(String[] args) throws InterruptedException {

		HelloWorld hl=new HelloWorld();
		List<Bean> li=new ArrayList<Bean>();
		XSSFSheet datatypeSheet = null;

		FileInputStream excelFile;
		try {
			excelFile = new FileInputStream(new File(FILE_NAME));
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			li=hl.getReadXLData(iterator);





			System.out.println("list---:"+li);
			username=li.get(0).getUserName();
			password=li.get(0).getPassword();
			// hl.window(username,password);


			System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");

			WebDriver driver = new ChromeDriver();

			String baseurl="http://demo.guru99.com/test/newtours/";
			/*String expectedTitle="Welcome: Mercury Tours";
		String actualTitle = "";*/

			driver.get(baseurl);
			//actualTitle=driver.getTitle();
			int rownum=0;
			for(Bean bean:li) {
				System.out.println("UserName .."+bean.getUserName());
				System.out.println("Password .."+bean.getPassword());
				driver.findElement(By.name("userName")).sendKeys(bean.getUserName());
				driver.findElement(By.name("password")).sendKeys(bean.getPassword());
				driver.findElement(By.name("submit")).click();
				boolean sample = driver.getPageSource().contains("Login Successfully");
				if (sample==true){
					System.out.println("Test Passed!");
					bean.setResult("Pass");
					driver.get(baseurl);

				} else {
					System.out.println("Test Failed");
					bean.setResult("Fail");

				}
				Row row = datatypeSheet.getRow(rownum);
				Cell cell=row.createCell(2);
				cell.setCellValue(bean.getResult());
				rownum++;
			}
			excelFile.close();
			FileOutputStream outputStream = new FileOutputStream(new File(FILE_NAME));

			//write data in the excel file

			workbook.write(outputStream);

			//close output stream

			outputStream.close();
			driver.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void window(String username,String passowrd) {
		System.out.print("username :"+username +"  Password :"+password);	

	}


	/*public List<Bean> getWriteXLData() {
		List <Bean> li=new ArrayList<Bean>();

		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			XSSFSheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();


			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				Bean bean= new Bean();
				List<String> list=new ArrayList<>();
				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();

					System.out.println("current cell:"+currentCell);

					if (currentCell.getCellTypeEnum() == CellType.STRING) {

						list.add(currentCell.setCellValue());
						System.out.print(currentCell.SetCellValue() + "==");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						// System.out.print("Numeric values......");
					}

				}
				bean.setResult(list.size()>2?list.get(2):"");
				li.add(bean);
				System.out.println();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return	li;
	}*/
}