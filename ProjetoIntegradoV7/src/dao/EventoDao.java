package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Evento;

public class EventoDao {

	private EntityManager manager;
	
	public EventoDao() {
		
		this.manager = FactoryManager.getInstance();
	}
	
	public EventoDao(EntityManager manager){
		this.manager = manager;
	}
	
	public void adiciona(Evento evento){
		try {
			manager.getTransaction().begin();
			this.manager.persist(evento);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		
	}	
	
	public Evento procura(Long id){
		Evento evento = null;
		try {
			evento =  this.manager.find(Evento.class, id);
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return evento;
	}
	
	public void remove(Long id){
		
		try {
			manager.getTransaction().begin();
			Evento evento = this.procura(id);
			this.manager.remove(evento);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}

	}
	
	
	public Evento procuraEvento(Long id){
		Evento evento = null;
		try {
			manager.getTransaction().begin();
			evento =  this.manager.find(Evento.class, id);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		return evento;
	}
	
	public void atualiza(Evento evento){
		try {
			this.manager.merge(evento);
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> getLista(){
		Query query = this.manager.createQuery("SELECT x FROM Evento x");
		return query.getResultList();
	}
	
	public boolean qtdVagasDisponiveis(){
		Query query = this.manager.createQuery("SELECT x.qtd_vagas FROM Evento x");
		int qtd = (Integer) query.getSingleResult();
		if(qtd > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public int qtdDeDiasFloat(){
		Query query = this.manager.createQuery("SELECT x.dias_float FROM Evento x");
		int qtd = (Integer) query.getSingleResult();
		return qtd;
	}
	
	
	public void designaVaga(){
		Evento evento = procura(1l);
		evento.setQtd_vagas((evento.getQtd_vagas() -1));
		atualiza(evento);
	}
	
	
}
