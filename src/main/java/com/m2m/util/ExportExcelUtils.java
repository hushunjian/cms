package com.m2m.util;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;

import com.m2m.annotation.ExcelTitle;
import com.m2m.entity.ExcelData;
import com.m2m.exception.SystemException;

public class ExportExcelUtils {
	
	public static ExcelData setExcelData(List<Object> beans){
		ExcelData excelData = new ExcelData();
		excelData.setName("exportExcel");
		List<String> titles = getTitle(beans.get(0));
		excelData.setTitles(titles);
		List<List<Object>> rows = new ArrayList<List<Object>>();
        for(Object bean : beans){
        	List<Object> row = getFiledValues(bean);
        	rows.add(row);
        }
        excelData.setRows(rows);	
		return excelData;
	}
	
	private static List<String> getTitle(Object o){  
		Field[] fields = o.getClass().getDeclaredFields();
		List<String> titles = new ArrayList<String>();
		for(Field field : fields){
			field.setAccessible(true);
			Annotation[] annotations = field.getDeclaredAnnotations();
			for(Annotation annotation : annotations){
				if(annotation instanceof ExcelTitle){
					ExcelTitle title = (ExcelTitle) annotation;
					titles.add(title.value());
				}
			}
		} 
        return titles;  
    } 
	
	private static List<String> getFiledName(Object o){
		List<String> fieldNames = new ArrayList<String>();
		Field[] fields = o.getClass().getDeclaredFields();
		for(Field field : fields){
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}
	
	private static List<Object> getFiledValues(Object o){  
        List<String> fieldNames=getFiledName(o);  
        List<Object> values=new ArrayList<Object>();  
        for(int i=0;i<fieldNames.size();i++){
            values.add(getFieldValueByName(fieldNames.get(i), o));  
        }  
        return values;  
    }
	
	private static Object getFieldValueByName(String fieldName, Object o) {  
        try {    
            String firstLetter = fieldName.substring(0, 1).toUpperCase();    
            String getter = "get" + firstLetter + fieldName.substring(1);    
            Method method = o.getClass().getMethod(getter, new Class[] {});    
            Object value = method.invoke(o, new Object[] {});
            if(value instanceof Date){
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	value = sdf.format((Date)value);
            }
            return value;    
        } catch (Exception e) {    
            return "";    
        }    
    }
	
	public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws SystemException{
		try {
	        response.setHeader("content-Type", "application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
			exportExcel(data, response.getOutputStream());
		} catch (IOException e) {
			throw new com.m2m.exception.IOException();
		}
    }
	
    public static void exportExcel(ExcelData data, OutputStream out) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            sheet.setColumnWidth(1,6000 );
            writeExcel(wb, sheet, data);
            wb.write(out);
        } finally {
            wb.close();
        }
    }

    private static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
        writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
        autoSizeColumns(sheet, data.getTitles().size() + 1);
    }

    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;
        Font titleFont = wb.createFont();
        titleFont.setFontName("simsun");
        titleFont.setBold(true);
        titleFont.setColor(IndexedColors.BLACK.index);
        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        Row titleRow = sheet.createRow(rowIndex);
        colIndex = 0;
		for (String field : titles) {
			Cell cell = titleRow.createCell(colIndex);
			cell.setCellValue(field);
			cell.setCellStyle(titleStyle);
			colIndex++;
		}
        rowIndex++;
        return rowIndex;
    }

    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex = 0;
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        dataFont.setColor(IndexedColors.BLACK.index);
        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            colIndex = 0;
            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null && !"null".equals(cellData)) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }
    
    private static void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
        	int colWidth = sheet.getColumnWidth(i)*2;
            if(colWidth<255*256){
                sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);    
            }else{
                sheet.setColumnWidth(i,6000 );
            }
        }
    }

    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(BorderSide.TOP, color);
        style.setBorderColor(BorderSide.LEFT, color);
        style.setBorderColor(BorderSide.RIGHT, color);
        style.setBorderColor(BorderSide.BOTTOM, color);
    }
}