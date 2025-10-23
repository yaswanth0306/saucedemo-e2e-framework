package com.Swag_Labs.Generic;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class Screenshot {
	
	private WebDriver driver;
	
	public void screenshot(WebDriver driver ,String name) throws IOException {
		TakesScreenshot t = (TakesScreenshot)driver;
		File src = t.getScreenshotAs(OutputType.FILE);
		File des = new File("./ScreenShots/"+name+".png");
		Files.copy(src, des);
	}

}
