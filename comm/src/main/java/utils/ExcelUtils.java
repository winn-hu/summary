package utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private static final String PATH = "D:/table/";
    private static final String suffix = ".xls";

    public static void write(String name, List<Map<String, Object>> dataList) throws Exception {
        String fileName = PATH+name+suffix;
        File file = new File(fileName);
        if(file.exists()) {
            boolean isDelete = file.delete();
            if(!isDelete) {
                throw new Exception(fileName+" is failed to delete.");
            }
        }

        file.createNewFile();
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        WritableSheet sheet = workbook.createSheet(name, 0);

        Object[] colNames = dataList.get(0).keySet().toArray();
        for(int i = 0, total = colNames.length; i < total; i++) {
            sheet.addCell(new Label(i,0,String.valueOf(colNames[i])));
        }

        for(int i = 0, rowTotal = dataList.size(); i < rowTotal; i++) {
            Map<String, Object> dataMap = dataList.get(i);
            for (int j=0,colTotal = dataMap.size(); j < colTotal; j++) {
                sheet.addCell(new Label(j,i+1, (String) dataMap.get(String.valueOf(colNames[j]))));
            }
        }

        workbook.write();
        workbook.close();

    }

    public static void findKeyword(FileWriter errorWriter,FileWriter findWriter,File file,String keyword) throws IOException {
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            if(files == null ) return;
            for (File subFile : files) {
                findKeyword(errorWriter,findWriter,subFile, keyword);
            }
        } else {
            String[] types = file.getName().split("\\.");
            if(types.length == 2 ){
                String type = types[1];
                if("xls".equals(type) || "xlsx".equals(type)) {
                    Map<String, List<String>> listMap = readExcel(file, keyword);
                    List<String> errors = listMap.get("error");
                    if(errors != null) {
                        for (String error : errors) {
                            errorWriter.write(error);
                        }
                    }
                    List<String> finds = listMap.get("find");
                    if(finds != null) {
                        for (String find : finds) {
                            findWriter.write(find);
                        }
                    }

                }
            }
        }
    }

    public static  Map<String,List<String>> readExcel(File file,String keyword) {
        Map<String,List<String>> result = new HashMap<>();
        List<String> find_result = new ArrayList<>();
        List<String> error_result = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(file);
        } catch (IOException | BiffException e) {
            error_result.add(file.getPath());
            result.put("error", error_result);
            return result;
        }
        for(int i=0,sheets = workbook.getNumberOfSheets(); i < sheets; i++) {
            Sheet sheet = workbook.getSheet(i);
            for (int j = 0,rows = sheet.getRows(); j < rows; j++) {
                Cell[] cells = sheet.getRow(j);
                for (int k = 0; k < cells.length; k++) {
                    String contents = cells[k].getContents();
                    if(contents.contains(keyword)) {
                        find_result.add(String.format("contents: %s\n %s(%d,%d,%d)\n",contents,file.getPath(),i+1,j+1,k+1));
                    }
                }
            }
        }
        workbook.close();
        result.put("find", find_result);
        return result;
    }

    public static FileWriter getWriter(String name) throws IOException {
        File file = new File(name);
        if(file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return new FileWriter(file);
    }

    public static void main(String[] args) {
        try {
            String path = "F:\\inspur\\code\\yhs\\resources\\bzds\\xls";

            FileWriter errorWriter = getWriter(path + "\\errorFile.txt");
            FileWriter findWriter = getWriter(path + "\\findFile.txt");

            findKeyword(errorWriter,findWriter,new File(path), "变更");

            errorWriter.close();
            findWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
