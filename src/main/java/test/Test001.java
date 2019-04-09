package test;


public class Test001 {
	public static void main(String[] args) throws Exception {
	
		String path = "E:\\test\\a a\\a a.txt";
		String urlString = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
		String urlString1 = "C:\\Program Files (x86)\\Google\\Chrome\\Application";
		//Runtime.getRuntime().exec("cmd /c start " + "\"\" \"" + urlString + "\" \"http://10.0.2.162/uranus/search_index.html\"");
		//Runtime.getRuntime().exec("cmd /c start " + "\"\" \"" + urlString1+"\\chrome.exe" + "\" \"http://10.0.2.162/uranus/search_index.html\"");
		
		Runtime.getRuntime().exec("cmd /c start " + "\"\" \"" + urlString1+"\\chrome.exe\" \"http://10.0.2.162/uranus/search_index.html\"");

		//Runtime.getRuntime().exec("cmd /c start E:\\test\\a~1\\a a.txt");
	}

}
