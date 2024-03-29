package br.com.projecao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.projecao.entidades.Usuario;
import br.com.projecao.util.JavaMailApp;
import br.com.projecao.util.QrCode;

public class UsuarioDao {

	private EntityManager manager;
	private static String mensagemDeConfirmacao = "mensagem de confirmação do evento";
	private static String mensagemDeConfirmacaoReserva = "mensagem de confirmação do evento para o cadastro Reserva";

	public UsuarioDao(EntityManager manager) {
		this.manager = manager;
	}

	public void adiciona(Usuario usuario) {
		this.manager.persist(usuario);

	}

	public Usuario procura(Long id) {
		return this.manager.find(Usuario.class, id);
	}

	public void remove(Long id) {
		Usuario usuario = this.procura(id);
		this.manager.remove(usuario);
	}

	public Usuario atualiza(Usuario usuario) {
		return this.manager.merge(usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getLista() {
		Query query = this.manager.createQuery("SELECT x FROM Usuario x");
		return query.getResultList();
	}

	public Usuario validaLogin(String login, String senha) {
		Query query = this.manager.createQuery("SELECT x FROM Usuario x where "
				+ "login = :pLogin and senha = :pSenha");
		query.setParameter("pLogin", login);
		query.setParameter("pSenha", senha);
		Usuario user;
		try {
			user = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		return user;
	}
	

	@SuppressWarnings("unchecked")
	public void enviaEmailCadastroReservaDiasAntesDoEvento() {

		int qtdReserva = adicionandoVagasNaoConfirmadas();
		Query query = this.manager
				.createQuery("SELECT x FROM Usuario x where x.cadastroReserva = true ORDER BY x.data_cadastro");
		List<Usuario> listaReserva = query.getResultList();

		JavaMailApp mail = new JavaMailApp();

		for (int i = 0; i < qtdReserva; i++) {
			mail.mandaEmail(mensagemDeConfirmacaoReserva, listaReserva.get(i),
					null);
		}
	}

	@SuppressWarnings("unchecked")
	public void enviaEmailConfirmacao() {
		Query query = this.manager
				.createQuery("SELECT x FROM Usuario x where x.cadastroReserva = false ");
		List<Usuario> listaConfirmacao = query.getResultList();
		JavaMailApp mail = new JavaMailApp();
		for (Usuario usuario : listaConfirmacao) {
			mail.mandaEmail(mensagemDeConfirmacao, usuario, null);
		}

	}

	@SuppressWarnings("unchecked")
	public void enviaEmailComQRCode() {
		Query query = this.manager
				.createQuery("SELECT x FROM Usuario x where x.confirmado = true");
		List<Usuario> listaReserva = query.getResultList();
		QrCode code = new QrCode();
		JavaMailApp mail = new JavaMailApp();
		for (Usuario usuario : listaReserva) {
			String localQrCode = code.getQrCode(usuario);
			mail.mandaEmail("mensagem de confirma��o de email com QrCode",
					usuario, localQrCode);
		}

	}

	public int adicionandoVagasNaoConfirmadas() {

		Query query = this.manager
				.createQuery("SELECT COUNT(x.cadastroPreliminar) FROM Usuario x WHERE x.cadastro_reserva = false and x.confirmado = false");
		int qtdVagasNaoConfirmadas = (Integer) query.getSingleResult();
		return qtdVagasNaoConfirmadas;

	}

}
