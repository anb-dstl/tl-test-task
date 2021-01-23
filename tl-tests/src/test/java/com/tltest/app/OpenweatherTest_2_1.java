package com.tltest.app;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class OpenweatherTest_2_1 {
	public String username = "test user";
	public String email = "ratawe3448@aiclbd.com";
	public String password = "travelline1337";
	public String sign_in_url = "https://home.openweathermap.org/users/sign_in";
	
	@Test
	public void signInByClick() {		
		open(sign_in_url);
		$(By.name("user[email]")).setValue(email);
		$(By.name("user[password]")).setValue(password);
		$(By.name("commit")).click();
		$(".user-sign-in").shouldHave(text(username)).click();
		$(".logout").click();
	}
	@Test
	public void signInByEnter() {		
		open(sign_in_url);
		$(By.name("user[email]")).setValue(email);
		$(By.name("user[password]")).setValue(password).pressEnter();
		$(".panel-green").shouldHave(text("Signed in successfully."));
		$(".user-sign-in").click();
		$(".logout").click();
	}

}
