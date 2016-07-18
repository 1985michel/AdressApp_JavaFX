package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PersonOverviewController {
	
	@FXML
	private TableView<Person> personTable;
	
	@FXML
	private TableColumn<Person,String> firstNameColumn;
	
	@FXML
	private TableColumn<Person,String> lastNameColumn;
	
	@FXML
	private Label firstNameLabel;
	
	@FXML
	private Label lastNameLabel;
	
	@FXML
	private Label streetLabel;
	
	@FXML
	private Label postalCodeLabel;
	
	@FXML
	private Label cityLabel;
	
	@FXML
	private Label birthdayLabel;
	
	//Reference to the main application
	private MainApp mainApp;
	
	/**
	 * O contrutor que é chamado antes do método inicialize()
	 * */
	public PersonOverviewController(){}
	
	/**
	 * Inicializea a classe controller. Este método é chamado automaticamente após o arquivo
	 * FXML ter sido carregado
	 * */
	@FXML
	private void initialize(){
		//Inicializa a table de pessoa com duas colunas
		//-> significa que a aplicação está usando Lambda do Java 8
		//é semelhante ao PropertyValueFactory mas é type-safe;
		//determina qual campo dentro dos objetos de Person devem ser usados para determinda coluna
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		
		//Limpa os detalhes da pessoa
		showPersonDetails(null);
		
		//Detecta mudanças de seleção e mostra os detalhes da pessoa quando houver mudança
		/**
		 * Com personTable.getSelectionModel... 
		 * nós obtemos a selectedItemProperty da tabela de pessoas e adiciona um listener (detector) a ela. 
		 * Sempre que o usuário selecionar uma pessoa na tabela, nossa expressão lambda é executada. 
		 * Nós obtemos a pessoa selecionada recentemente e passamos para o método showPersonDetails(...)
		 * */
		personTable.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->showPersonDetails(newValue));
	}
	
	/**
	 * É chamado pela aplicação principal para dar uma referência de volta a si mesmo
	 * 
	 * @param mainApp
	 * */
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
		
		//Adiciona os dados da observableList na tabela
		personTable.setItems(mainApp.getPersonData());
	}
	
	/**
	 * Preenche todos os campos de Texto para mostrar detalhes sobre a pessoa
	 * Se a pessoa especificada for null, todos os campos de texto são limpos.
	 * */
	private void showPersonDetails(Person person){
		if(person!=null){
			//preenche as labels com informações do objeto person
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
		}else{
			//Person is null, remove todo o texto;
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}
	
	/**
	 * Chamado quando o usuário clica no botão delete
	 * Qualquer método dentro do nosso que for anotado com @FXML 
	 * (ou for public) é acessível pelo Scene Builder
	 * */
	@FXML
	private void handleDeletePerson(){
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex>=0) personTable.getItems().remove(selectedIndex);
		else{
			Alert alert = new Alert(AlertType.WARNING);
			
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhuma pessoa Selecionada");
			alert.setContentText("Por favor, selecione uma pessoa na tabela");
			
			alert.showAndWait();
		}
	}


	/**
	 * Chamado quando o usuário clica no botão novo.
	 * Abre uma janela para editar detalhes da nova pessoa.
	 * */
	@FXML
	private void handleNewPerson(){
		Person tempPerson = new Person();//Cria uma variável pessoa
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson); // passa essa pessoa para o main
		if(okClicked) mainApp.getPersonData().add(tempPerson);//se der ok, adicione a nova pessoa a observable list.
		//A validação dos dados é feita pelo controler do Modal de edição
	}
	
	/**
	 * Chamado quando o usuário clica no botão edit. Abre a janela para editar detalhes da pessoa selecionada
	 * */
	@FXML
	private void handleEditPerson(){
		//a linha abaixo é tão interessant... vc não seleciona uma row, seleciona um objeto!
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if(selectedPerson != null){
			//a linha abaixo exibe o dialog com os dados da pessoa e retorna se clicou ok
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if(okClicked){
				showPersonDetails(selectedPerson);
			}
		}else{
			//Nada selecionado
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhuma pessoa selecionada");
			alert.setContentText("Por favor, selecione uma pessoa na tabela");
			alert.showAndWait();
		}
	}

}
