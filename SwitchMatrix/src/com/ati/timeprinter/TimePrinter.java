package com.ati.timeprinter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimePrinter {

	public static void printCurrentTime() {
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime localTime = LocalTime.now();
		System.out.println(myFormat.format(localTime));
		
	}

}
