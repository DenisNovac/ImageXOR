package application.gui.controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class HelpViewController {
	@FXML
	Button backButton;
	@FXML
	RadioButton engButton, ruButton;
	@FXML
	TextArea readmeArea;
	
	@FXML
	private void initialize(){
		backButton.setOnAction((e)->{
			Main.setMainScreen();
		});
		
		engButton.setOnAction((e)->{
			readmeArea.setText(eng);
		});
		
		ruButton.setOnAction((e)->{
			readmeArea.setText(ru);
		});
		
	}
	
	static final String eng =   "SAFE - file, in which you want to hide another file. You can use picture or another file. DO NOT USE EMPTY/TEXT OR LITTLE FILES. By the way, app works great with 500 bytes files.\n\n"+
								"FILE - hiding file.\n\n"+
								"RESULT PATH - here you can enter name for your safe with file inside. It's optional - default name is result. DO NOT SPECIFY FILE EXTENSION HERE, it's defines authomatically.\n\n"+
								"You can input full paths to files or move this app to directory with your files. If Result path empty - app will create safe in app's dirrectory.\n\n"+
								"SAFE LENGTH - after encoding you'll get length of original file. Remember it! This is your PASSWORD to secret file!\n\n"+
								"PASSWORD - you can add extra password for your secret file, BUT YOU WILL NEED TO REMEMBER SAFE LENGTH TOO! This is optional. \n\n"+
								"ENCODING AND DECODING may take lot of time for big files.\n\n"+
								"Notice that this app will create extra file after encoding. This will not delete original files! This means that you need to hide/delete original file and safe to defence your files.\n\n"+
								"Powered by XOR operation." ;
	static final String ru = "SAFE - файл, в котором вы желаете спрятать другой файл. Может быть изображением или другим файлом. НЕ ИСПОЛЬЗУЙТЕ ПУСТЫЕ, ТЕКСТОВЫЕ ИЛИ МАЛЕНЬКИЕ ФАЙЛЫ! Приложение проверено на файлах размером 500 байт, но для меньших нет гарантий.\n\n"+
							"FILE - файл, который вы желаете скрыть.\n\n"+
							"RESULT PATH - путь к файлу-результату. Опционально - если пусто, то программа сгенерирует файл result в папке, откуда запущено приложение. Не УКАЗЫВАЙТЕ РАСШИРЕНИЕ ЗДЕСЬ - ОНО ОПРЕДЕЛЯЕТСЯ АВТОМАТИЧЕСКИ!\n\n"+
							"Вы можете прописывать полные пути к файлам, или запускать приложение из директорий с этими файлами. Тогда необходимо указать только имя файла.\n\n"+
							"SAFE LENGTH - после шифрования вы получите длину оригинального файла-сейфа. ЗАПОМНИТЕ ЕЁ! Это пароль к зашифрованному файлу при декодировании!\n\n"+
							"PASSWORD - вы можете добавить дополнительный пароль для увеличения безопасности. ВАМ НЕОБХОДИМО ЗАПОМИНАТЬ ДЛИНУ ИСХОДНОГО ФАЙЛА, ДАЖЕ ЕСЛИ ВЫ УКАЖЕТЕ ПАРОЛЬ. Не обязательно. \n\n"+
							"ШИФРОВАНИЕ И ДЕШИФРОВАНИЕ могут занять значительное время для больших файлов.\n\n"+
							"Обратите внимание, что приложение создает дополнительный файл при шифровании и расшифровке. Оно не удаляет/переписывает оригинальные файлы. Это значит, что после работы программы вам нужно удалить оригинальные файлы, или спрятать их другим способом.\n\n"+
							"Основано на логической операции 'ИСКЛЮЧАЮЩЕЕ ИЛИ'." ;
}
