package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import entidades.Agenda;
import entidades.Evento;
import facade.AgendaFacade;
import facade.EventoFacade;

@ManagedBean(name = "agendaDeEventos")
	
public class AgendaDeEventos {
	private Evento evento;

	private List<Agenda> lista;

	public AgendaDeEventos() {
		getAgenda();
		getDescEvento();
	}
	
	
	public List<Agenda> getAgenda() {
		if (this.lista == null) {
			AgendaFacade dao = new AgendaFacade();
			this.lista = dao.getLista();
		}
		return lista;
	}
	
	
	public Evento getDescEvento(){
		if(evento == null){
			evento = new Evento();
			EventoFacade f = new EventoFacade();
			evento = f.buscaEvento(1l);
		}
		return evento;
	}


	public Evento getEvento() {
		return evento;
	}


	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
}