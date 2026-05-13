package POPpages_GenericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * @author Suryalakshmi
 * This method is use to fetch the data from excel
 * 
 */
public class ExcelFileUtility {
	
	Workbook wb=null;
	
/**
 * This method is used to fetchdata from excel
 * @param sheetname
 * @param Rindex
 * @param Cindex
 * @return
 * @throws EncryptedDocumentException
 * @throws IOException
 */
	public String FetchDatafromExcelFile(String sheetname,int Rindex,int Cindex) throws EncryptedDocumentException, IOException {
		
		FileInputStream efis=new FileInputStream("./src/test/resources/VitigerTestData.xlsx");
		wb = WorkbookFactory.create(efis);
		String data=wb.getSheet(sheetname).getRow(Rindex).getCell(Cindex).toString();
		return data;
		
	}
	
	/**
	 * This method is used to write back data in new cell excel
	 * @param sheetname
	 * @param Rindex
	 * @param Cindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public void writeBackDataToExcelFile(String sheetname,int Rindex,int Cindex,String data) throws EncryptedDocumentException, IOException {
		FileInputStream efis=new FileInputStream("./src/test/resources/orgExcel.xlsx");
		wb=WorkbookFactory.create(efis);
		
		wb.getSheet(sheetname).createRow(Rindex).createCell(Cindex).setCellValue(data);
		
		FileOutputStream fos=new FileOutputStream("./src/test/resources/orgExcel.xlsx");
		
		wb.write(fos);
	}
	/**
	 * This method is used to write back to existing row and cell
	 * @param sheetname
	 * @param Rindex
	 * @param Cindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeBackDataToExcel_ExistingRow(String sheetname,int Rindex,int Cindex,String data) throws EncryptedDocumentException, IOException {
		FileInputStream efis=new FileInputStream("./src/test/resources/orgExcel.xlsx");
		wb=WorkbookFactory.create(efis);
		
		wb.getSheet(sheetname).getRow(Rindex).createCell(Cindex).setCellValue(data);
		
		FileOutputStream fos=new FileOutputStream("./src/test/resources/orgExcel.xlsx");
		
		wb.write(fos);
	}
	/**
	 * This method is used to close the excel
	 * 
	 * @throws IOException
	 */
	public void closeExcel() throws IOException {
		
		wb.close();
		
		
	}

}
