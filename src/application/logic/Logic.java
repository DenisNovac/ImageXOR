package application.logic;

import java.io.*;

public class Logic {
	public static String encode(String safeS, String fileS, String resultName, String keyS){
		String key=keyS;

		int i; //байт для переписывания
		int sum=0; //считает байты внутри файлов

		String answer=""; //Строка, которая будет собирать ответ для вывода в консоль/ярлык

		try (BufferedInputStream safe = new BufferedInputStream( new FileInputStream(safeS) );//контейнер
			 BufferedInputStream file= new BufferedInputStream ( new FileInputStream(fileS) ) ){//объект
			//тут определим формат сейфа

			//другие варианты оберток:
			/*InputStreamReader safeISR = new InputStreamReader(  new FileInputStream(safeS)  );
			BufferedReader safe = new BufferedReader (safeISR);

			InputStreamReader fileISR = new InputStreamReader(  new FileInputStream(fileS)  );
			BufferedReader file = new BufferedReader (fileISR);*/

			if (resultName==null) resultName="result";
			String resUrl=resultName+"."+FormatByte.getFormat(safeS); //составляем путь для файла-результата шифрования
			//в FormatByte определяем формат сейфа, чтобы создать результат в том же формате и пользователь ничего не перепутал, дурашка

			BufferedOutputStream result = new BufferedOutputStream ( new FileOutputStream(resUrl) );
			//FileOutputStream result = new FileOutputStream(resUrl); //вывод, очень медленный!!!
			System.out.println("Starting the encoding...");
			//Переписываем изображение в наш файл

			do {
				i=safe.read();
				if (i==-1) break;
				result.write(i);
				sum++;
			}while (i!=-1);

			//obviously this makes the code really slow - DO NOT USE

			/*while (safe.available()>0){
				i=safe.read();
				sum++;
				result.write(i);
			}*/

			answer+=("Image sum: "+String.valueOf(sum)+" - REMEMBER OR WRITE DOWN THIS VALUE! \n");

			if (key==null) key=String.valueOf(sum); //если пользователь не ввел дополнительный пароль - используем длину исходной картинки для шифрования
			else key+=String.valueOf(sum);

			sum=0;
			//Передаём пароль в класс шифрования
			CipherClass Vernam = new CipherClass(key);
			//теперь допишем в файл формат шифруемого файла

			int [] format = FormatByte.go(fileS); //получили формат файла в байтах
			result.write(format.length); //сразу после длины файла впишет длину расширения выходного файла байтом

			for (int j:format){
				j=Vernam.code(j); //шифруем каждый байт, несущий название расширения
				result.write(j);
			}

			do {
				i=file.read();
				if (i!=-1){
					i=Vernam.code(i);
					result.write(i);
					sum++;
				}
			}while (i!=-1);
			result.close();

			answer+=("File sum: "+String.valueOf(sum)+"\n" + 
				"Encoding complete. Safe name: "+resUrl+"\n");
			return answer;

		} catch (Exception e){
			return ("Error: "+e);
		}

	}//end of encode method


	public static String decode (String safeS, String lengthS, String decodeName, String keyS){
		String key=keyS;
		if (key==null) key=lengthS; //если пароль не был введен
		else key+=lengthS;

		//Передаём пароль в класс шифрования
		CipherClass Vernam = new CipherClass(key);


		int sum=0; //считает, как далеко продвинулись в файле
		int i; //байт для переписывания
		int length=Integer.parseInt(lengthS); //длина файла, чтобы расшифровать


		try (BufferedInputStream safe = new BufferedInputStream (new FileInputStream(safeS) ) )
		{
			System.out.println("Starting the decoding...");
			while (true) {
				i=safe.read();
				if (i!=-1){
					sum++;
				}
				if (sum==length) break;
			} //конец исходного файла

			int formatLength = safe.read(); //прочитали длину разрешения файла
			int [] formatByte = new int[formatLength];//создали массив такой длины
			//заполняем массив интов расшифрованным разрешением файла
			for (int j=0; j<formatByte.length; j++){
				i=safe.read();
				i=Vernam.code(i); //расшифровываем каждый байт, несущий название расширения
				formatByte[j]=i;
			}
			String format = FormatByte.back(formatByte);

			if (decodeName==null) decodeName="decode";
			String decodeUrl=decodeName+"."+format;

			//теперь с этим форматом расшифровываем наш файл
			BufferedOutputStream result = new BufferedOutputStream(  new FileOutputStream(decodeUrl) );
			sum=0;
			do {
				i=safe.read();
				if (i!=-1){
					i=Vernam.code(i);
					result.write(i);
					sum++;
				}
			}while (i!=-1);
			result.close();
			return ("Decode result: "+String.valueOf(sum) +"\n"+
					"Decoding complete. Decode name: "+decodeUrl+"\n");
				
		} catch (Exception e){
			return ("Error: "+e);
		}

	}//end of decode method
}
