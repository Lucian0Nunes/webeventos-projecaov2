package facade;

import java.util.List;

import dao.AgendaDao;
import entidades.Agenda;

public class AgendaFacade {
	
	private AgendaDao dao;
	
	public AgendaFacade() {
		dao = new AgendaDao();
	}
	
	public void adiciona(Agenda agenda){
		dao.adiciona(agenda);
	}
	
	public Agenda busca(Long id){
		return dao.procura(id);
	}
	
	public void remove(Long id){
		dao.remove(id);
	}
	public void atualiza(Agenda agenda){
		dao.atualiza(agenda);
	}
	
	public List<Agenda> getLista(){
		return dao.getLista();
	}

}
