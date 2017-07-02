package com.web.commons.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * JasonZ -- 导出功能.
 */
public class ExportUtil {
	
	public static void doExportFromRow(HttpServletRequest request,HttpServletResponse response,
            List<String[]> dataList, String templaturePath, String fileName,int row) {
		doExportFromRow(request, response, dataList, templaturePath, fileName, row, "");
	}

    /**
     * 数据从第row+2行开始写入
     */
    public static void doExportFromRow(HttpServletRequest request,HttpServletResponse response,
                                       List<String[]> dataList, String templaturePath, String fileName,int row, String title) {
        String outFilePath = "/excelXml/xls/" + System.currentTimeMillis() + ".xls";
        String realPathRoot = request.getSession().getServletContext().getRealPath("");
        realPathRoot = realPathRoot.replace('\\', '/');
        ExcelManager excelManager = ExcelManager.instanceWriteWordBook(outFilePath, templaturePath, realPathRoot);
        assert excelManager != null;
        if (row >0) {
			row--;
		}
        if (StringUtils.isNotBlank(title)) {
        	excelManager.addContent(0, 0, title);
        }
        excelManager.addContents(row,0,dataList);
        excelManager.write();
        excelManager.close();
        outPutFile(fileName,realPathRoot+outFilePath,response);
    }

  
    /**
     * 导出指定name和path的文件
     * @param fileName
     * @param path
     * @param response
     */
    public static void outPutFile(String fileName, String path,HttpServletResponse response) {
        ServletOutputStream toClient = null;
        InputStream inputStream = null;
        try {
            // 将生成文件推出
            File file = new File(path);
            // 以流的形式下载文件。
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((fileName + ".xls").getBytes("gbk"), "ISO-8859-1"));
            response.setContentType("application/msexcel; charset=utf-8");
            response.addHeader("Content-Length", "" + file.length());
            // 输出文件
            toClient = response.getOutputStream();
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            // 删除文件
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
                if (toClient != null){
                    toClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
