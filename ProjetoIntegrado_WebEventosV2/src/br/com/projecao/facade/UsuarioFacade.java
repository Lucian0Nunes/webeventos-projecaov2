package br.com.projecao.facade;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.projecao.dao.EventoDao;
import br.com.projecao.dao.UsuarioDao;
import br.com.projecao.entidades.Usuario;
import br.com.projecao.util.JavaMailApp;

public class UsuarioFacade {

	private UsuarioDao usuarioDao;
	private EventoDao eventoDao;
	private static String mensagemOK = "cadastro efetuado com sucesso";
	private static String mensagemReserva = "você está na lista reserva";

	public UsuarioFacade(EntityManager manager) {
		usuarioDao = new UsuarioDao(manager);
		eventoDao = new EventoDao(manager);
	}

	public void adiciona(Usuario usuario) {
		
		//para adicionar precisa saber quantas vagas existem, para depois saber se o 
		// usuario vai para o cadastro reserva 
		JavaMailApp mail = new JavaMailApp();
		usuario.setData_cadastro(Calendar.getInstance());
		usuario.setNivelAcesso(1); //usuario comum
		if(eventoDao.qtdVagasDisponiveis()){
			usuario.setCadastroPreliminar(true);
			usuarioDao.adiciona(usuario);
			mail.mandaEmail(mensagemOK, usuario,null);
			eventoDao.designaVaga();
		}else{
			usuario.setCadastroReserva(true);
			usuarioDao.adiciona(usuario);
			mail.mandaEmail(mensagemReserva, usuario,null);
		}
	}
	
	public void verificaCadastroReserva(){
		boolean tem = eventoDao.qtdVagasDisponiveis();
		String messageCadastro = null;
		if(!tem){
			messageCadastro = "Voce esta fazendo um cadastro reserva...";
		}
				
	}

	public Usuario busca(Long id) {
		return usuarioDao.procura(id);
	}

	public void remove(Long id) {
		usuarioDao.remove(id);
	}

	public List<Usuario> getLista() {
		return usuarioDao.getLista();
	}

	public Usuario atualiza(Usuario usuario) {
		return usuarioDao.atualiza(usuario);
	}

}
