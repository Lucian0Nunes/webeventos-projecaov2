package controller;

import javax.faces.bean.ManagedBean;

import entidades.Evento;
import facade.EventoFacade;


@ManagedBean
public class ControleDeEvento {	
	
	private Evento evento;	
	
	public ControleDeEvento() {
		getEventoCompleto();
	}
	
	
	public void getEventoCompleto(){
		if(evento == null){
			EventoFacade f = new EventoFacade();
			evento = f.busca(1l);
		}
		
	}


	public Evento getEvento() {
		return evento;
	}


	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	

}
