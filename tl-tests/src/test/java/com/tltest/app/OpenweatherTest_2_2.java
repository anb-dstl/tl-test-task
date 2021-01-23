package com.tltest.app;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.codeborne.selenide.Selenide;

public class OpenweatherTest_2_2 {
	public String moscow_url = "https://openweathermap.org/city/524901";
	public String london_url = "https://openweathermap.org/city/2643743";
	public String yola_url = "https://openweathermap.org/city/466806";
	
	@BeforeTest 
	public void clearBrowser() {
		open("https://openweathermap.org");
		Selenide.clearBrowserCookies();
		Selenide.clearBrowserLocalStorage();
	}

	//сайт Openweather.com пытается определить местонахождение клиента и оно кэшируется в браузере
	//поэтому даже прямая ссылка на город не всегда открывает этот город, и тест покажет ошибку
	@Test
	public void getCityTemp_Moscow() {	
		open(moscow_url);
		$("h2").shouldHave(text("Moscow, RU"));

		getTemp();
	}
	
	@Test
	public void getCityTemp_London() {	
		open(london_url);
		$("h2").shouldHave(text("London, GB"));

		getTemp();
		
	}
	
	@Test
	public void getCityTemp_Yola() {	
		open(yola_url);
		$("h2").shouldHave(text("Yoshkar-Ola, RU"));
		
		getTemp();
		
	}

	public void getTemp() {
		$(".left").click();		
		$(".owm-loader").should(disappear);
		var celsius = $(".heading").innerText();
		celsius = celsius.substring(0, celsius.length()-2);
		
		$(".right").click();
		$(".owm-loader").should(disappear);
		var fahrenheit = $(".heading").innerText();
		fahrenheit = fahrenheit.substring(0, fahrenheit.length()-2);
			
		Assert.assertTrue(checkConvertion(Integer.parseInt(celsius),Integer.parseInt(fahrenheit)));
	}
	
	public boolean checkConvertion(int celsius,int fahrenheit) {
		System.out.println(celsius+" "+fahrenheit);
		System.out.println(Math.round(celsius * 9 / 5) + 32);
		return Math.abs(Math.round(celsius * 9 / 5) + 32) - fahrenheit <= 1;		
	}
}
