package com.gennlife.autoplatform.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @Description: 文件的处理工具集
 * @author: wangmiao
 * @Date: 2017年8月21日 上午11:39:18
 */
public class FileUtils {
	private static Logger logger = Logger.getLogger(FileUtils.class); 
	
	/** 
	* @Title: writeContentToFile 
	* @Description: 将字符串写入到文件中
	* @author: wangmiao
	* @Date: 2018年7月6日 下午4:01:17 
	* @param: @param filePath
	* @param: @param content
	* @return: void
	* @throws 
	*/
	public static String writeContentToFile(String filePath,String content) {
		FileWriter writer;
		try {
			writer = new FileWriter(filePath);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ok";
	}

	/**
	 * @Title: readFileContent
	 * @Description: 读取文件内容，返回String
	 * @param: @param Path
	 * @param: @return :
	 * @return: String
	 * @throws
	 */
	public static String readFileContent(String Path) {
		String laststr = "";
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			fileInputStream = new FileInputStream(Path);
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

	/**
	 * @Title: readFileAndPrintAllDateStr
	 * @Description: 按行读取文件，读取所有日期格式的字符串
	 * @param: File file：文件
	 * @return: void
	 * @throws
	 */
	public static void readFileAndPrintAllDateStr(File file) {
		String reg = "[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}[ ][0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}";
		Pattern pattern = Pattern.compile(reg);
		String str = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while ((str = br.readLine()) != null) {
				Matcher matcher = pattern.matcher(str);
				if (matcher.find()) {
					String group = matcher.group();
					logger.info(group);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Title: readFileAndReturnValue
	 * @Description: 按行读取文件，返回含有value的整行字符串
	 * @param: File file :文件路径
	 * @param: String value
	 * @return: String：返回含有value的整行字符串
	 * @throws
	 */
	public static String readFileAndReturnValue(File file, String value) {
		String str = null;
		String returnValue = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while ((str = br.readLine()) != null) {
				if (str.contains(value)) {
					returnValue = str;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnValue;

	}

	/**
	 * @Title: readFileAndReplaceToNewFile
	 * @Description: 按行读取文件，读取包含oldStr的行，替换为replaceStr，并另存为一个新文件
	 * @param: File oldfile：旧文件
	 * @param: String oldStr：旧的值
	 * @param: String replaceStr：替换后的值
	 * @param: File newFlie :新的文件
	 * @return: void
	 * @throws
	 */
	public static void readFileAndReplaceStrToNewFile(File oldfile,
			String oldStr, String replaceStr, File newFlie) {
		String str = null;
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		try {
			fr = new FileReader(oldfile);
			br = new BufferedReader(fr);
			StringBuffer buf = new StringBuffer();
			for (int i = 1; (str = br.readLine()) != null
					&& !str.contains(oldStr); i++) {
				buf = buf.append(str);
				buf = buf.append(System.getProperty("line.separator"));
			}
			buf = buf.append(replaceStr);
			while ((str = br.readLine()) != null) {
				buf = buf.append(System.getProperty("line.separator"));
				buf = buf.append(str);
			}
			fw = new FileWriter(newFlie);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @Title: copyFile
	 * @Description: 拷贝文件到另一个目录
	 * @param: @param oldPath：E:\\yujie\\2\\模板结构-乳腺癌.xlsx
	 * @param: @param newPath: E:\\yujie\\2\\new_模板结构-乳腺癌.xlsx
	 * @return: void
	 * @throws
	 */
	public static void copyFile(String oldfile, String newfile)
			throws Exception {
		FileInputStream fis = new FileInputStream(oldfile);
		String path = ListAndStringUtils
				.stringToSubstringReturnFilePath(newfile);
		if (!new File(path).exists()) {
			new File(path).mkdirs();
		}

		FileOutputStream fos = new FileOutputStream(newfile);
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = fis.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		fos.close();
		fis.close();
	}

	/**
	 * @Title: deleteFile
	 * @Description: 删除某个文件夹下的所有文件夹和文件
	 * @param: @param delpath：文件绝对路径
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	public static boolean deleteFile(String delpath) throws Exception {
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				File[] fileList = file.listFiles();
				for (int i = 0; i < fileList.length; i++) {
					File delfile = fileList[i];
					if (!delfile.isDirectory()) {
						delfile.delete();
					} else if (delfile.isDirectory()) {
						deleteFile(fileList[i].getPath());
					}
					fileList[i].delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * @Title: getFilesArrayList
	 * @Description: 获取指定目录下的所有的文件（不包括文件夹），采用了递归
	 * @param: @param obj 文件路径
	 * @param: @return :
	 * @return: ArrayList<File>:例如：F:\\uploadFile\\1\\模板结构-乳腺癌.xlsx形式的文件数组
	 * @throws
	 */
	public static ArrayList<File> getFilesArrayList(Object obj) {
		File directory = null;
		if (obj instanceof File) {
			directory = (File) obj;
		} else {
			directory = new File(obj.toString());
		}
		ArrayList<File> files = new ArrayList<File>();
		if (directory.isFile()) {
			files.add(directory);
			return files;
		} else if (directory.isDirectory()) {
			File[] fileArr = directory.listFiles();
			for (int i = 0; i < fileArr.length; i++) {
				File fileOne = fileArr[i];
				files.addAll(getFilesArrayList(fileOne));
			}
		}
		return files;
	}

	/**
	 * @Title: getFileNameList
	 * @Description: 查询路径下所有文件，并返回纯文件名的list，使用上面getFilesArrayList方法
	 * @param: @param filePath: F:\\DRGs\\newadd
	 * @param: @return :
	 * @return: List<String>
	 * @throws
	 */
	public static List<String> getFileNameList(String filePath) {
		List<String> fileNameListList = new ArrayList<String>();
		ArrayList<File> files = FileUtils.getFilesArrayList(filePath);
		List<String> list = ListAndStringUtils
				.arrayListFilesToStringList(files);
		for (int i = 0; i < list.size(); i++) {
			String fileName = ListAndStringUtils
					.stringToSubstringReturnFileName(list.get(i));
			fileNameListList.add(fileName);
		}
		return fileNameListList;
	}

}
