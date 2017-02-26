package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	public static Stage primaryStage; //выносим ссылку сюда, чтобы ставить в нее сцены из любой точки кода
	public static Boolean Russian = false;
	
	public void start(Stage ps) { //рисует primaryStage и ставит главное окно
		primaryStage=ps;
		ps.getIcons().add(new Image("application/icon.png"));
		ps.setResizable(false);
		ps.setTitle("ImageXOR");
		ps.centerOnScreen();
		setMainScreen();
	}
	
	
	public static void setMainScreen (){
		AnchorPane mainRoot = new AnchorPane();//создаем компоновку
		FXMLLoader mainLoader = new FXMLLoader(); //создаем подгрузку
		mainLoader.setLocation(Main.class.getResource("gui/mainView.fxml"));//подгружаем отображение
		
		try { //ставим рут
			mainRoot=mainLoader.load();
		} catch (IOException e) {
			System.out.println(e);
		} 
		
		Scene mainScene = new Scene(mainRoot);
		primaryStage.setScene(mainScene);
		primaryStage.sizeToScene();
		primaryStage.getScene().getStylesheets().add("application/gui/arra.css");
		primaryStage.show();
		
	}
	
	
	public static void setHelpScreen (){
		AnchorPane helpRoot = new AnchorPane();//создаем компоновку
		FXMLLoader helpLoader = new FXMLLoader(); //создаем подгрузку
		helpLoader.setLocation(Main.class.getResource("gui/helpView.fxml"));//подгружаем отображение
		
		try { //ставим рут
			helpRoot=helpLoader.load();
		} catch (IOException e) {
			System.out.println(e);
		} 
		
		Scene helpScene = new Scene(helpRoot);
		primaryStage.setScene(helpScene);
		primaryStage.sizeToScene();
		primaryStage.getScene().getStylesheets().add("application/gui/arra.css");
		primaryStage.show();
		
	}
	
	
	public static void main(String[] args) {
		launch();
	}
}
