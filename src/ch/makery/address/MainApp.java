package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Person;
import ch.makery.address.view.PersonEditDialogController;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp  extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Construtor
     */
    public MainApp() {
        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    /**
     * Retorna os dados como uma observable list de Persons. 
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
		
		 // Set the application icon.
	    this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_icon.png"));
		
		initRootLayout();
		
		showPersonOverview();
	}
	
	/**
	 * Inicializa o root layout (layout base);
	 * */
	public void initRootLayout(){
		try{
			//Carrega o root layout do arquivo fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane)loader.load();
			
			//Mostra a scene (cena) contendo o root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Mostra a person overview dentro do root layout
	 * 
	 * */
	public void showPersonOverview(){
		try{
			//Carrega o person overview
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			//Define o person overview dentro do root layout
			rootLayout.setCenter(personOverview);
			
			//Dá ao controlador acesso ao mainApp
			//Conectando a MainApp com o PersonOverviewController
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);
		
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna o palco principal
	 * */
	public Stage getPrimaryStage(){
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Abre uma janela para editar detalhes para a pessoa especificada.
	 * Se o usuário clicar OK, as mudanças são salvas no objeto pessoa fornecido e retorna true.
	 * 
	 * @param person O objeto pessoa a ser editado
	 * @return true se o usuário clicou Ok. se não, false
	 * @author michel	 * */
	public boolean showPersonEditDialog(Person person){
		//O main abre o WINDOW_MODAL de edição e passa a pessoa para ser editada retornando o ok ou o cancel
		try{
			//Carrega o arquivo fxml e cria um novo stage para a janela popup
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			//cria o palco dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			// Set the application icon.
		    dialogStage.getIcons().add(new Image("file:resources/images/emblem_people_icon.png"));
			
			//Define a pessoa no controller
			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);
			
			//Mostra a janela e espera até o usuário fechar
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
}
