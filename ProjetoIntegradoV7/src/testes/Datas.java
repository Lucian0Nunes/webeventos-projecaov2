package testes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Datas {

	public static void main(String[] args) {
		SimpleDateFormat sd = new SimpleDateFormat("MMMM,EEEE, dd,MM,yyyy,HH,mm,ss");
		String padrao = "No dia 31 de Novembro 2013 as 16:59hs";
		Date data = new Date(); //data atual
		String[] splitString = sd.format(data).split(",");
		System.out.println("Dia "+splitString[3]+" de "+splitString[0]+" "+splitString[4]+" às "+splitString[5]+":"+splitString[6]+"hs");
	}
	
}
