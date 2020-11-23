package utils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
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
}
