package dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import suporte.Constantes;
import suporte.JavaMailApp;
import suporte.QrCode;
import entidades.Usuario;

public class UsuarioDao {

	private EntityManager manager;
	private EventoDao eventoDao;
	

	
	public UsuarioDao() {
		this.manager = FactoryManager.getInstance();
	}

	public boolean adiciona(Usuario usuario) {
		try {
			manager.getTransaction().begin();
			eventoDao = new EventoDao(manager);
			
			// para adicionar precisa saber quantas vagas existem, para depois saber
			// se o
			// usuario vai para o cadastro reserva
			JavaMailApp mail = new JavaMailApp();
			usuario.setData_cadastro(Calendar.getInstance());
			usuario.setNivelAcesso(1); // usuario comum
			if (eventoDao.qtdVagasDisponiveis()) {
				usuario.setCadastroPreliminar(true);
				eventoDao.designaVaga();
				this.manager.persist(usuario);
				mail.mandaEmail(Constantes.PRECADASTRO, usuario, null);
			} else {
				usuario.setCadastroReserva(true);
				this.manager.persist(usuario);
				mail.mandaEmail(Constantes.CADASTRORESERVA, usuario, null);
			}			
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		}finally{
			manager.close();
		}
		
	}

	public Usuario procura(Long id) {
		Usuario usuario = null;
		try {
			manager.getTransaction().begin();
			usuario = this.manager.find(Usuario.class, id);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		return usuario;
	}

	public void remove(Long id) {
		try {
			manager.getTransaction().begin();
			Usuario usuario = this.procura(id);
			this.manager.remove(usuario);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}

	}

	public void atualiza(Usuario usuario) {
		try {
			manager.getTransaction().begin();
			this.manager.merge(usuario);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally{
			manager.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getLista() {
		Query query = this.manager.createQuery("SELECT x FROM Usuario x");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public void enviaEmailCadastroReservaDiasAntesDoEvento() {
		int qtdReserva = adicionandoVagasNaoConfirmadas();
		Query query = this.manager.createQuery("SELECT x FROM Usuario x where x.cadastroReserva = true ORDER BY x.data_cadastro");
		List<Usuario> listaReserva = query.getResultList();

		JavaMailApp mail = new JavaMailApp();
		for (int i = 0; i < qtdReserva; i++) {
			mail.mandaEmail(Constantes.SELECAORESERVA, listaReserva.get(i),null);
		}
	}

	@SuppressWarnings("unchecked")
	public void enviaEmailConfirmacao() {
		Query query = this.manager.createQuery("SELECT x FROM Usuario x where x.cadastroReserva = false ");
		List<Usuario> listaConfirmacao = query.getResultList();
		JavaMailApp mail = new JavaMailApp();
		for (Usuario usuario : listaConfirmacao) {
			mail.mandaEmail(Constantes.SELECAORESERVA, usuario, null);
		}

	}

	@SuppressWarnings("unchecked")
	public void enviaEmailComQRCode() {
		Query query = this.manager.createQuery("SELECT x FROM Usuario x where x.confirmado = true");
		List<Usuario> listaReserva = query.getResultList();
		QrCode code = new QrCode();
		JavaMailApp mail = new JavaMailApp();
		for (Usuario usuario : listaReserva) {
			String localQrCode = code.getQrCode(usuario);
			mail.mandaEmail("mensagem de confirmação de email com QrCode", usuario, localQrCode);
		}

	}

	public int adicionandoVagasNaoConfirmadas() {
		Query query = this.manager.createQuery("SELECT COUNT(x.cadastroPreliminar) FROM Usuario x WHERE x.cadastro_reserva = false and x.confirmado = false");
		int qtdVagasNaoConfirmadas = (Integer) query.getSingleResult();
		return qtdVagasNaoConfirmadas;

	}
	
	public String[] validaLogin(String nome,String senha){
		Query query = this.manager.createQuery("SELECT x.nome,x.nivelAcesso from Usuario x where x.login = :pLogin and x.senha = :pSenha");
		query.setParameter("pLogin", nome);
		query.setParameter("pSenha", senha);
		
		 if(!query.getResultList().isEmpty()){
			 Object[] nomeUsuario = (Object[]) query.getSingleResult();
			 String a = ""+nomeUsuario[0];
			 String b = ""+nomeUsuario[1];
			 String[] resulta ={a,b};
			 return resulta;
		 }else{
			 return null;
		 }
		 
	}
	

}
