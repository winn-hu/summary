package tools;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.FileUtil;
import utils.Method;

import java.io.*;

public class FindExcelContext2 {

    private static final String PATH = "F:\\";
    private static final String KEYWORD = "变更";

    public static void main(String[] args) {
        try {
            FileUtil.findFile(new File(PATH),new FileMethod());
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    static class FileMethod implements Method {

        @Override
        public Object invoke(Object... t) throws Exception {
            File file = (File) t[0];
            String[] types = file.getName().split("\\.");
            if(types.length == 2 ){
                String type = types[1];
                FileInputStream is = new FileInputStream(file);
                Workbook wb;
                if("xls".equals(type)){
                    try {
                        wb = new HSSFWorkbook(is);
                    } catch (Exception e) {
                        is = new FileInputStream(file);
                        wb = new XSSFWorkbook(is);
                    }
                }else if("xlsx".equals(type)){
                    wb = new XSSFWorkbook(is);
                }else {
                    return new Object();
                }
                findKeyword(wb,file);
                is.close();
            }
            return new Object();
        }
    }

//    private static void findFile(FileWriter findWriter,File file) throws IOException {
//        if(file.isDirectory()) {
//            File[] files = file.listFiles();
//            if(files == null ) return;
//
//            for (File subFile : files) {
//                findFile(findWriter,subFile);
//            }
//        } else {
//            String[] types = file.getName().split("\\.");
//            if(types.length == 2 ){
//                String type = types[1];
//                FileInputStream is = new FileInputStream(file);
//                Workbook wb;
//                if("xls".equals(type)){
//                    try {
//                        wb = new HSSFWorkbook(is);
//                    } catch (Exception e) {
//                        is = new FileInputStream(file);
//                        wb = new XSSFWorkbook(is);
//                    }
//                }else if("xlsx".equals(type)){
//                    wb = new XSSFWorkbook(is);
//                }else {
//                    return;
//                }
//                findKeyword(wb,findWriter,file);
//                is.close();
//            }
//        }
//    }

    private static  void findKeyword(Workbook workbook,File file) throws IOException {
        FileWriter findWriter = getWriter(PATH + "\\findFile.txt");
        for(int i=0,sheets = workbook.getNumberOfSheets(); i < sheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (int j = 0,rows = sheet.getPhysicalNumberOfRows(); j < rows; j++) {
                Row row = sheet.getRow(j);
                if(row == null) continue;
                for (int k = 0, cols = row.getPhysicalNumberOfCells(); k < cols; k++) {
                    Cell cell = row.getCell(k);
                    if(cell != null && CellType.STRING.equals(cell.getCellType())) {
                        String value = cell.getRichStringCellValue().getString();
                        if(value.contains(KEYWORD)) {
                            String find = String.format("contents: %s(%d,%d,%d)\n%s\n",value,i+1,j+1,k+1,file.getPath());
                            findWriter.write(find);
                        }
                    }
                }
            }
            workbook.close();
        }
    }

    private static FileWriter getWriter(String name) throws IOException {
        File file = new File(name);
        if(file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return new FileWriter(file);
    }


}
