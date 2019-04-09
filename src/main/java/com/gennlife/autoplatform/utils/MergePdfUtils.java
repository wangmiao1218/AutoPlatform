package com.gennlife.autoplatform.utils;

import java.io.FileOutputStream;  
import com.lowagie.text.Document;  
import com.lowagie.text.pdf.PdfCopy;  
import com.lowagie.text.pdf.PdfImportedPage;  
import com.lowagie.text.pdf.PdfReader;  

/**
 * @Description: 合并多个pdf
 * @author: wangmiao
 * @Date: 2018年1月10日 上午10:46:42 
 */
public class MergePdfUtils {
	  
    /** 
    * @Title: mergePdfFiles 
    * @Description: 合并pdf文件
    * @param: @param files：绝对路径（"e:\\1.pdf", "e:\\2.pdf" ...）
    * @param: @param newfile：合并后产生的文件绝对路径（e:\\temp.pdf）
    * @param: @return :成功返回true，失败返回false
    * @return: boolean
    * @throws 
    */
    public static boolean mergePdfFiles(String[] files, String newfile) {  
        boolean retValue = false;  
        Document document = null;  
        try {  
            document = new Document(new PdfReader(files[0]).getPageSize(1));  
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));  
            document.open();  
            for (int i = 0; i < files.length; i++) {  
                PdfReader reader = new PdfReader(files[i]);  
                int n = reader.getNumberOfPages();  
                for (int j = 1; j <= n; j++) {  
                    document.newPage();  
                    PdfImportedPage page = copy.getImportedPage(reader, j);  
                    copy.addPage(page);  
                }  
            }  
            retValue = true;  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            document.close();  
        }  
        return retValue;  
    }  
	
}
