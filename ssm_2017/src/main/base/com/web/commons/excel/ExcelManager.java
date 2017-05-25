package com.web.commons.excel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



/**
 * 
 * ClassName:ExcelManager excel 导入导出工具类
 *
 * @author   JohnYe
 * @version  
 * @since    
 * @Date	 2014	2014-8-25		下午4:08:20
 *
 * @see
 */
public class ExcelManager {
	private String name = "批量导出";
	public String rootPath = "";
	private WritableWorkbook writebook;
	private String templature = "";
	private int defaultSheet = 0;


	public WritableWorkbook getWritebook() {
		return writebook;
	}
	public void setWritebook(WritableWorkbook writebook) {
		this.writebook = writebook;
	}
	public static ExcelManager instanceWriteWordBook(String excelPath, String templaturePath, String rootPath) {
		try {
			excelPath = rootPath + excelPath;
			ExcelManager excel = new ExcelManager();
			if (excelPath.equals("")) {
				System.out.println("[warning] 未输入excel文件路径");
			}
			excel.rootPath = rootPath;
			excel.templature = rootPath + templaturePath;
			Workbook rw = null;
			WritableWorkbook wwb = null;
			File file = new File(excelPath);
			if (!file.exists()) {
 
			}
			if (!(excel.templature.equals(""))) {
				rw = Workbook.getWorkbook(new File(excel.templature));
			}
			if (rw != null)
				wwb = Workbook.createWorkbook(file, rw);
			else
				wwb = Workbook.createWorkbook(file);
			excel.setWritebook(wwb);
			return excel;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void addContents(int startRow, int startCol,
			List<String[]> contentList) {
		if(CollectionUtils.isNotEmpty(contentList)){
			for (int i = 0; i < contentList.size(); ++i) {
				String[] dataArray = (String[]) contentList.get(i);
				setContent(startRow + i, startCol, dataArray);
			}
			++startRow;
		}
	}
	
	private void setContent(int row, int startCol, String[] content) {
		for (int i = 0; i < content.length; ++i) {
			String value =content[i];
			addContent(row, startCol + i, value);
		}
	}
	
	public void addContent(int row, int col, String content) {
		try {
			Label cell = new Label(col, row, content);
			WritableSheet sheet = getWriteSheet(this.defaultSheet, this.name);
			sheet.addCell(cell);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	public WritableSheet getWriteSheet(int sheetIndex, String name) {
		if ((this.writebook.getSheets().length > 0)
				&& (this.writebook.getSheet(sheetIndex) != null))
			return this.writebook.getSheet(sheetIndex);
		return this.writebook.createSheet(name, sheetIndex);
	}
	
	public void write() {
		try {
			this.writebook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.writebook.close();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


//	public String getExcelPath() {
//		return excelPath;
//	}
//
//
//
//	public void setExcelPath(String excelPath) {
//		this.excelPath = excelPath;
//	}
//


	public String getRootPath() {
		return rootPath;
	}



	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
}