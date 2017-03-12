package application.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

import application.Main;
import application.logic.*;

public class MainViewController {
	@FXML
	TextField safeField,fileField,nameField,lengthField,passwordField;
	@FXML
	Label infoLabel, safeLengthLabel, filePathLabel;
	@FXML
	RadioButton decodeRadio, encodeRadio;
	@FXML
	Button startButton, helpButton, safePathButton, filePathButton, resultPathButton;
	
	String safe,file,name,length,password;
	
	@FXML
	private void initialize(){
		//Тут создаем объекты, управляющие проводниками ОС
		FileChooser fileChooser = new FileChooser(); //объект, получающий файл из проводника
		fileChooser.setTitle("Open Resource File");//заголовок окошко выбора файла
		
		DirectoryChooser dirChooser = new DirectoryChooser(); //объект, получающий путь из проводника
		dirChooser.setTitle("Select directory for result");//заголовок окошка выбора дирректории
		
		safePathButton.setOnAction((e) -> {//выбор пути к сейфу
			fileChooser.setInitialDirectory(new File(".")); //открывает папку, откуда запущена программа
			String path = (  fileChooser.showOpenDialog(Main.primaryStage)  ).getPath(); //получает путь до выбранного файла СТРОКОЙ
			safeField.setText(path); //автоматически вставляет его в строчку пути
		});
		
		filePathButton.setOnAction((e) -> {//выбор пути к файлу
			fileChooser.setInitialDirectory(new File("."));
			String path = (  fileChooser.showOpenDialog(Main.primaryStage)  ).getPath();
			fileField.setText(path);
		});
		
		resultPathButton.setOnAction((e)->{//выбор папки-результата
			dirChooser.setInitialDirectory(new File("."));
			String path = (  dirChooser.showDialog(Main.primaryStage)  ).getPath();
			int i = (int) (Math.random()*945);
			nameField.setText(path+"\\result"+Integer.toString(i));
		});
		
		//выбор режимов работы
		decodeRadio.setOnAction((e)->{
			lengthField.setDisable(false);
			safeLengthLabel.setDisable(false);
			fileField.setDisable(true);
			filePathButton.setDisable(true);
			filePathLabel.setDisable(true);
		} );
		
		encodeRadio.setOnAction((e)->{
			lengthField.setDisable(true);
			safeLengthLabel.setDisable(true);
			fileField.setDisable(false);
			filePathButton.setDisable(false);
			filePathLabel.setDisable(false);
		} );
		
		//переход к окошку помощи
		helpButton.setOnAction( (e) -> {
			Main.setHelpScreen();
		});
		
		//кнопка начала работы
		startButton.setOnAction( (e) -> {
			
			//методы для кодирования
			if (encodeRadio.isSelected()){ //закодировать
				safe=safeField.getText();
				file=fileField.getText();
				if (nameField.getText().length()>1) name=nameField.getText();
				else name=null;
				
				if (passwordField.getText().length()>1) password=passwordField.getText();
				else password=null;
				
				String answer = Logic.encode(safe, file, name, password);
				infoLabel.setText(answer);	
			}
			
			
			if (decodeRadio.isSelected()){ //декодировать
				safe=safeField.getText();
				
				if (lengthField.getText().length()<1) {
					infoLabel.setText("You have to input length!");
				}else length=lengthField.getText();
				
				
				if (nameField.getText().length()>1) name=nameField.getText();
				else name=null;
				
				if (passwordField.getText().length()>1) password=passwordField.getText();
				else password=null;
				
				String answer = Logic.decode(safe, length, name, password);
				infoLabel.setText(answer);	
			}
			nameField.setText("");
			
		}); //end of start button
		
	}//end of initialize
}//end of controller class
