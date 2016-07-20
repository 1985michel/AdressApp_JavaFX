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
 * O controller para a view de estatísticas de aniversário.
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
	 * Inicializa a classe controller. Este método é chamado automaticamente
	 * após o arquivo fxml ter sido carregado
	 */
	@FXML
	private void initialize() {
		// Obtém um array com nomes dos meses em Inglês
		String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();

		// Converte o array em uma lista e adiciona em nossa ObservableList de
		// meses
		monthNames.addAll(Arrays.asList(months));

		// Associa os nomes de mês como categoria para o eixo horizontal
		xAxis.setCategories(monthNames);
	}

	/**
	 * Sets the persons to show the statistics for
	 * 
	 * @param persons
	 */
	public void setPersonData(List<Person> persons) {
		// Conta o número de pessoas tendo seus aniversário em um mês específico
		int[] monthCounter = new int[12];
		for (Person p : persons) {
			int month = p.getBirthday().getMonthValue() - 1;
			monthCounter[month]++;
		}

		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		// Cria um objeto XYChart.Data para cada mês. Adiciona ele às séries
		for (int i = 0; i < monthCounter.length; i++) {
			series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
		}

		barChart.getData().add(series);
	}

}

/**
 * Como o Controller Funciona
 * 
 * O controller precisará de acesso para dois elementos do nosso arquivo FXML:
 * 
 * O barChar: Ele tem um tipo String e Integer. O String é usado para o mês no
 * eixo x e o Integer é usado para o número de pessoas em um mês específico. O
 * xAxis (eixo x): Nós usaremos este para adicionar os String do mês. O método
 * initialize() preenche o eixo x com uma lista de todos os meses.
 * 
 * O método setPersonData(...) será acessado pela classe MainApp para definir os
 * dados da pessoa. Ele percorre todas as pessoas, conta os aniversários por
 * mês. Então ele adiciona XYChart.Data para todo mês na série de dados. Cada
 * objeto XYChart.Data representará uma barra no gráfico.
 * 
 */
