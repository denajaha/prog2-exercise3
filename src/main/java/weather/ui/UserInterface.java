package weather.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;
import weather.ctrl.MyException;
import weather.ctrl.WeatherController;

public class UserInterface 
{

	private WeatherController ctrl = new WeatherController();
	private Longitude x;
	private Latitude y;
	//GeoCoordinates location;

	public void getWeatherForCity1(){
		this.x = new Longitude(25.0);
		this.y = new Latitude(18.0);
		//DONE enter the coordinates
		try {
			ctrl.process(ctrl.getLocation(this.x,this.y));
		} catch (MyException e) {
			e.printStackTrace();
		}

	}

	public void getWeatherForCity2() {
		//DONE enter the coordinates
		this.x = new Longitude(35.0);
		this.y = new Latitude(20.0);
		try {
			ctrl.process(ctrl.getLocation(this.x,this.y));
		} catch (MyException e) {
			e.printStackTrace();
		}

	}

	public void getWeatherForCity3() {
		//DONE enter the coordinates
		this.x = new Longitude(45.0);
		this.y = new Latitude(40.0);
		try {
			ctrl.process(ctrl.getLocation(this.x,this.y));
		} catch (MyException e) {
			e.printStackTrace();
		}

	}
	
	public void getWeatherByCoordinates()   {
		//DONE read the coordinates from the cmd
		//DONE enter the coordinates
		System.out.println("Please enter longitude:");
		Scanner enter_longitude = new Scanner(System.in);
		double enter_long = enter_longitude.nextDouble();
		Longitude longitude_console = new Longitude(enter_long);

		System.out.println("Please enter latitude:");
		Scanner enter_latitude = new Scanner(System.in);
		double enter_lat = enter_latitude.nextDouble();
		Latitude latitude_console = new Latitude(enter_lat);

		GeoCoordinates console = new GeoCoordinates(longitude_console, latitude_console);

		try {
			ctrl.process(console);
		} catch (MyException e) {
			e.printStackTrace();
		}

	}



	public void start() {
		Menu<Runnable> menu = new Menu<>("Weather Infos");
		menu.setTitel("Wählen Sie eine Stadt aus:");
		menu.insert("a", "City 1", this::getWeatherForCity1);
		menu.insert("b", "City 2", this::getWeatherForCity2);
		menu.insert("c", "City 3", this::getWeatherForCity3);
		menu.insert("d", "City via Coordinates:",this::getWeatherByCoordinates);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
