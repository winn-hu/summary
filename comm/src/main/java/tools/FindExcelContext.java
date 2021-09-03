package tools;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 该方式使用jxl.jar，只支持解析xls格式
 */
public class FindExcelContext {

    private static final String PATH = "F:\\";
    private static final String KEYWORD = "变更";

    public static void main(String[] args) {
        try {
            FileWriter errorWriter = getWriter(PATH + "\\errorFile.txt");
            FileWriter findWriter = getWriter(PATH + "\\findFile.txt");

            findFile(errorWriter,findWriter,new File(PATH));

            errorWriter.close();
            findWriter.close();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    private static void findFile(FileWriter errorWriter,FileWriter findWriter,File file) throws IOException {
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            if(files == null ) return;

            for (File subFile : files) {
                findFile(errorWriter,findWriter,subFile);
            }
        } else {
            String[] types = file.getName().split("\\.");
            if(types.length == 2 ){
                String type = types[1];
                if("xls".equals(type) || "xlsx".equals(type)) {
                    findKeyword(errorWriter,findWriter,file);
                }
            }
        }
    }

    private static  void findKeyword(FileWriter errorWriter,FileWriter findWriter,File file) throws IOException {
        Workbook workbook;
        try {
            workbook = Workbook.getWorkbook(file);
        } catch (IOException | BiffException e) {
            errorWriter.write(file.getPath());
            return ;
        }
        for(int i=0,sheets = workbook.getNumberOfSheets(); i < sheets; i++) {
            try {
                Sheet sheet = workbook.getSheet(i);
                for (int j = 0,rows = sheet.getRows(); j < rows; j++) {
                    Cell[] cells = sheet.getRow(j);
                    for (int k = 0; k < cells.length; k++) {
                        String contents = cells[k].getContents();
                        if(contents.contains(KEYWORD)) {
                            String find = String.format("contents: %s\n %s(%d,%d,%d)\n",contents,file.getPath(),i+1,j+1,k+1);
                            findWriter.write(find);
                        }
                    }
                }
            } catch (NullPointerException e) {
                errorWriter.write(file.getPath());
                return ;
            }

        }
        workbook.close();
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
