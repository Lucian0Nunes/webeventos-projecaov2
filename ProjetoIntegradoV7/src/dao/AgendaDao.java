package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Agenda;

public class AgendaDao {
	
	private EntityManager manager;
	
	public AgendaDao() {
		this.manager = FactoryManager.getInstance();
	}
	
	public void adiciona(Agenda agenda){
		try {
			manager.getTransaction().begin();
			this.manager.persist(agenda);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
	}	
	
	public Agenda procura(Long id){
		Agenda agenda = null;
		try {
			manager.getTransaction().begin();
			agenda = this.manager.find(Agenda.class, id);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}		
		return agenda;
	}
	
	public void remove(Long id){
		
		try {
			manager.getTransaction().begin();
			Agenda agenda = this.procura(id);
			this.manager.remove(agenda);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		

	}
	
	public void atualiza(Agenda agenda){
		
		try {
			manager.getTransaction().begin();
			this.manager.merge(agenda);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Agenda> getLista(){
		Query query = this.manager.createQuery("SELECT x FROM Agenda x");
		return query.getResultList();
	}

}
