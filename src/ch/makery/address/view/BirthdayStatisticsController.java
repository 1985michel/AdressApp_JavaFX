package ch.makery.address.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import ch.makery.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

/**
 * O controller para a view de estat�sticas de anivers�rio.
 * 
 * @author Marco Jakob
 */
public class BirthdayStatisticsController {

	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	private CategoryAxis xAxis;

	private ObservableList<String> monthNames = FXCollections.observableArrayList();

	/**
	 * Inicializa a classe controller. Este m�todo � chamado automaticamente
	 * ap�s o arquivo fxml ter sido carregado
	 */
	@FXML
	private void initialize() {
		// Obt�m um array com nomes dos meses em Ingl�s
		String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();

		// Converte o array em uma lista e adiciona em nossa ObservableList de
		// meses
		monthNames.addAll(Arrays.asList(months));

		// Associa os nomes de m�s como categoria para o eixo horizontal
		xAxis.setCategories(monthNames);
	}

	/**
	 * Sets the persons to show the statistics for
	 * 
	 * @param persons
	 */
	public void setPersonData(List<Person> persons) {
		// Conta o n�mero de pessoas tendo seus anivers�rio em um m�s espec�fico
		int[] monthCounter = new int[12];
		for (Person p : persons) {
			int month = p.getBirthday().getMonthValue() - 1;
			monthCounter[month]++;
		}

		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		// Cria um objeto XYChart.Data para cada m�s. Adiciona ele �s s�ries
		for (int i = 0; i < monthCounter.length; i++) {
			series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
		}

		barChart.getData().add(series);
	}

}

/**
 * Como o Controller Funciona
 * 
 * O controller precisar� de acesso para dois elementos do nosso arquivo FXML:
 * 
 * O barChar: Ele tem um tipo String e Integer. O String � usado para o m�s no
 * eixo x e o Integer � usado para o n�mero de pessoas em um m�s espec�fico. O
 * xAxis (eixo x): N�s usaremos este para adicionar os String do m�s. O m�todo
 * initialize() preenche o eixo x com uma lista de todos os meses.
 * 
 * O m�todo setPersonData(...) ser� acessado pela classe MainApp para definir os
 * dados da pessoa. Ele percorre todas as pessoas, conta os anivers�rios por
 * m�s. Ent�o ele adiciona XYChart.Data para todo m�s na s�rie de dados. Cada
 * objeto XYChart.Data representar� uma barra no gr�fico.
 * 
 */
