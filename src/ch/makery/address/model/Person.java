package ch.makery.address.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.makery.address.util.LocalDateAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final IntegerProperty postalCode;
	private final StringProperty city;
	private final ObjectProperty<LocalDate> birthday;
	
	public Person() {
		this(null,null);//Sem essa linha dá erro no construtor. interessante.
	}

	public Person(String firstName, String lastName) {
		super();
		this.firstName= new SimpleStringProperty(firstName);
		this.lastName= new SimpleStringProperty(lastName);
	
		
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("some city");
		this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}
	
	
	public String getFirstName(){
		return this.firstName.get();
	}
	
	public StringProperty firstNameProperty(){
		return this.firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName.set(firstName);
	}
	
	
	
	public String getLastName(){
		return this.lastName.get();
	}
	
	public StringProperty lastNameProperty(){
		return this.lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName.set(lastName);
	}
	
	
	
	public String getStreet(){
		return this.street.get();
	}
	
	public StringProperty streetProperty(){
		return this.street;
	}
	
	public void setStreet(String street){
		this.street.set(street);
	}
	
	
	
	public int getPostalCode(){
		return this.postalCode.get();
	}
	
	public IntegerProperty postalCodeProperty(){
		return this.postalCode;
	}
	
	public void setPostalCode(int postalCode){
		this.postalCode.set(postalCode);
	}
	
	
	
	
	public String getCity(){
		return this.city.get();
	}
	
	public StringProperty cityProperty(){
		return this.city;
	}
	
	public void setCity(String city){
		this.city.set(city);
	}
	
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getBirthday(){
		return this.birthday.get();
	}
	
	public ObjectProperty<LocalDate> birthdayProperty(){
		return this.birthday;
	}
	
	public void setBirthday(LocalDate birthday){
		this.birthday.set(birthday);
	}
	
	
	
	
}
