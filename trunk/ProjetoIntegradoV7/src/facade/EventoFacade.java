package facade;

import dao.EventoDao;
import entidades.Evento;

public class EventoFacade {
	
	private EventoDao dao;	
	
	public EventoFacade() {
		dao = new EventoDao();
	}
	
	public void adiciona(Evento evento){
		dao.adiciona(evento);
	}
	
	public Evento busca(Long id){
		return dao.procura(id);
	}
	
	public Evento buscaEvento(Long id){
		return dao.procuraEvento(id);
	}
	
	public void remove(Long id){
		dao.remove(id);
	}
	public void atualiza(Evento evento){
		dao.atualiza(evento);
	}
	public void atualiza2(Evento evento){
		dao.atualiza2(evento);
	}

}
