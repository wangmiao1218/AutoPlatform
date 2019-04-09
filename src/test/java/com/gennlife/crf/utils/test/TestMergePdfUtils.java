package com.gennlife.crf.utils.test;

import org.junit.Test;

import com.gennlife.crf.utils.MergePdfUtils;

public class TestMergePdfUtils {
	
	private String file1="C:\\Users\\www\\Desktop\\1.pdf";
	private String file2="C:\\Users\\www\\Desktop\\2.pdf";
	private String newfile="C:\\Users\\www\\Desktop\\merge.pdf";
	
	@Test
	public void mergePdfFiles(){
		String[] files = {file1,file2};
		boolean b = MergePdfUtils.mergePdfFiles(files, newfile);
		System.out.println(b);
	}
	
}	
