package testes;

import java.util.ArrayList;
import java.util.List;

import entidades.Agenda;
import facade.AgendaFacade;

public class ListaAgenda {

	public static void main(String[] args) {
		
		List<Agenda> lista = new ArrayList<Agenda>();
		AgendaFacade f = new AgendaFacade();
		
		lista = f.getLista();
		
		for (Agenda agenda : lista) {
			System.out.println(agenda.getDescricao());
		}
		
	}
	
	
}
