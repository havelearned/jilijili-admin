package top.jilijili.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Amani
 * @date 2023年07月25日 23:10
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String filepath = "D:\\Downloads\\歌曲数据导入模板.xlsx";
        File file = new File(filepath);


        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellType().toString()) {
                    case "DATE":
                        System.out.print(cell.getDateCellValue());
                        break;
                    case "STRING":
                        System.out.print(cell.getStringCellValue());
                        break;
                    case "NUMERIC":
                        System.out.print(cell.getNumericCellValue());
                        break;
                    case "BOOLEAN":
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case "ERROR":
                        System.out.print(cell.getErrorCellValue());
                        break;
                    case "NULL":
                        System.out.print("\t NULL");
                        break;
                    case "BLANK":
                        System.out.print("\t BLANK");
                        break;
                    case "FORMULA":
                        System.out.print(cell.getStringCellValue());
                        break;
                }

            }
            System.out.println();
        }

    }
}

/**
 * 歌曲表
 *
 * @TableName music_song
 */
@TableName(value = "music_song")
@Data
class ExcelData implements Serializable {
    /**
     *
     */
    @Excel(name = "歌曲id", width = 20)
    private Long songId;


    /**
     * 歌曲名称
     */
    @Excel(name = "歌曲名称", width = 20)
    private String name;

    /**
     * 状态::2 草稿, 1 上架, 0 下架
     */
    @Excel(name = "歌曲状态", width = 20, replace = {"草稿_2", "上架_1", "下架_0"})
    private Integer status;

    /**
     * 歌词
     */

    @Excel(name = "歌词", width = 20, imageType = 3)
    private File lyric;

    /**
     * 歌曲源
     */

    @Excel(name = "播放文件", width = 20, imageType = 3)
    private File sourceFile;

    /**
     *
     */

    private Date createdTime;

    /**
     *
     */
    private Date updatedTime;


}