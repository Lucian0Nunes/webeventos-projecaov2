package facade;

import java.util.List;

import dao.EventoDao;
import dao.UsuarioDao;
import entidades.Usuario;

public class UsuarioFacade {

	private UsuarioDao usuarioDao;
	private EventoDao eventoDao;

	public UsuarioFacade() {
		usuarioDao = new UsuarioDao();
	}
	
	public boolean adiciona(Usuario usuario) {
		return usuarioDao.adiciona(usuario);
	}

	@SuppressWarnings("unused")
	public void verificaCadastroReserva() {
		boolean tem = eventoDao.qtdVagasDisponiveis();
		String messageCadastro = null;
		if (!tem) {
			messageCadastro = "Voce esta fazendo um cadastro reserva...";
		}

	}
	
	public String[] validaLogin(String nome,String senha){
		return usuarioDao.validaLogin(nome, senha);
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

	public void atualiza(Usuario usuario) {
		usuarioDao.atualiza(usuario);
	}

}
