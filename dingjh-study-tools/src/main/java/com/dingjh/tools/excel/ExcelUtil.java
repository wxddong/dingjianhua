package com.dingjh.tools.excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @项目名称：dingjh-study-tools
 * @类名称：ExcelUtil
 * @类描述：
 * @创建人：dingjianhua
 * @创建时间：2014-11-3 下午4:57:58
 * @version 1.0.0
 */
public class ExcelUtil {
	private static final Logger LOGGER=LogManager.getLogger(ExcelUtil.class);

	public static List<List<ListOrderedMap>> parseExcel(File file) {
		if (file.exists() && file.isFile()) {
			try {
				Workbook workbook = null;
				if (file.getName().endsWith(".xls")){
					workbook = new HSSFWorkbook(new FileInputStream(file));
				}else{
					workbook = new XSSFWorkbook(new FileInputStream(file));
				}
				return parseExcel(workbook);
			} catch (IOException e) {
				LOGGER.error("parse excel fail!" + e);
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 解析Xlsx
	 * 
	 * @Title: parseXlsx
	 * @param file
	 * @return
	 * @author dingjianhua
	 * @throws IOException
	 * @date 2014-11-3 下午5:06:27
	 */
	private static List<List<ListOrderedMap>> parseExcel(Workbook workbook) throws IOException {
		List<List<ListOrderedMap>> list = new ArrayList<List<ListOrderedMap>>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			Sheet sheet = workbook.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			list.add(readRow(sheet));
		}
		return list;
	}

	/**
	 * 去除excel中一个sheet的数据
	 * 
	 * @Title: readRow
	 * @param hssfSheet
	 * @return
	 * @author dingjianhua
	 * @date 2014-11-3 下午5:27:41
	 */
	private static List<ListOrderedMap> readRow(Sheet sheet) {
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		ListOrderedMap map = null;
		// 循环行Row
		for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			map = new ListOrderedMap();
			// 循环列Cell
			for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
				Cell hssfCell = row.getCell(cellNum);
				if (hssfCell == null) {
					continue;
				}
				getValue(hssfCell, cellNum, rowNum, map);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取单个cell中的值
	 * 
	 * @Title: getValue
	 * @param cell
	 * @param index
	 * @return
	 * @author dingjianhua
	 * @date 2014-11-3 下午5:28:11
	 */
	private static void getValue(Cell cell, int index, int row, ListOrderedMap map) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
		map.put('"' + index + '"', cell.getStringCellValue());
	}
}
