package application.logic;

public class FormatByte {

	public static int[] go (String path){
		String format = getFormat(path); //вытаскиваем формат в виде строки

		int byteFormat[]= new int[format.length()];

		for (int i=0; i<format.length(); i++){
			byteFormat[i]=(int)format.charAt(i);
		}
		return byteFormat;
	}


	public static String back (int[] formatByte){
		String format="";
		for (int i:formatByte){
			format+=(char)i;
		}
		return format;
	}


	public static String getFormat(String path){ //метод определяет формат
		String format="";

		boolean dot = false;
		for (int i=0; i<path.length();i++){
			if (dot==true) format+=path.charAt(i);
			if ((int)path.charAt(i)==46) dot=true; //46 - код точки в аски
		}

		if (dot==false) return null;
		return format;
	}
}
