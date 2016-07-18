package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//Classe auxiliar para lidar com datas
public class DateUtil {
	/**
	 * Padrao utilizado para lidar com datas
	 * */
	private static final String DATE_PATTERN = "dd.MM.yyyy";
	
	/**
	 * Formatador de data
	 * Para outros formatos pesquisar DateTimeFormatter
	 * */
	private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern(DATE_PATTERN);


	/**
	 * M�todo que retorna a data como uma String formatada
	 * */
	public static String format(LocalDate date){
		if(date==null)	return null;
		return DATE_FORMATER.format(date);
	}
	
	/**
	 * Converte de String para o Objeto LocalDate
	 * */
	public static LocalDate parse(String dateString){
		try{
			return DATE_FORMATER.parse(dateString,LocalDate::from);
		}catch(DateTimeParseException e){
			return null;
		}
	}
	
	/**
	 * Checa se a String � uma data v�lida
	 * */
	public static boolean isValidDate(String dateString){
		//Tenta converter a String [m�todo crazy e pr�tico!]
		return DateUtil.parse(dateString) !=null;
	}
}
