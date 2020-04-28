package com.automation.training.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.text.html.HTMLDocument.HTMLReader.FormAction;
import org.testng.Reporter;

/**
 * 
 * @author angel.hojsgaard | Globant
 *Testng Reporterlog implementation
 */
public class Logger {

	//	private static final String DATEPATTERN = "MM-dd-yyyy HH:mm:ss ZZ";
	private static final String LOGTEMPLATE = "[%s %s] %s";



	public static String fechaActual() {

		LocalDate fecha = LocalDate.now();
		String sbFecha = fecha.toString();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaLocal = LocalDate.parse(sbFecha, formato);

		return formato.format(fechaLocal);
	}

	/**
	 * Print formatted info on Console and testng reporter 
	 * @param message
	 * @author angel.hojsgaard | Globant
	 */

	public static void printInfo(String message){


		String logMessage = String.format(LOGTEMPLATE,"INFO",fechaActual(),message);
		//		String logMessage = String.format(LOGTEMPLATE,"INFO",DateTime.now().toString(DATEPATTERN),message);
		Reporter.log(logMessage,true);	
	}

	/**
	 * Print formatted debug info on Console and testng reporter 
	 * @param message
	 * @author angel.hojsgaard | Globant
	 */	

	public static void printDebug(String message){
		String logMessage = String.format(LOGTEMPLATE,"DEBUG",fechaActual(),message);
		Reporter.log(logMessage,true);	
	}

	/**
	 * Print formatted error info on Console and testng reporter 
	 * @param message
	 * @author angel.hojsgaard | Globant
	 */

	public static void printError(String message) {
		String logMessage = String.format(LOGTEMPLATE,"ERROR",fechaActual(),message);
		Reporter.log(logMessage,true);	

	}

	/**
	 * Print formatter warning info on Console and testng reporter
	 * @param string
	 * @author angel.hojsgaard | Globant
	 */

	public static void printWarning(String message) {
		String logMessage = String.format(LOGTEMPLATE,"WARNING",fechaActual(),message);
		Reporter.log(logMessage,true);	
	}
}
