package testes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datas2 {

	public static void main(String[] args) {
		try {
			String str_date = "08/12/2013";
			DateFormat formatter;
			Date date;
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (Date) formatter.parse(str_date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			System.out.println("Today is " + date);
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
		}

	}
}
