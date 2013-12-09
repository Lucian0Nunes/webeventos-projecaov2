package testes;

import entidades.Usuario;
import facade.UsuarioFacade;

public class CadastroUsuario {

	public static void main(String[] args) {

		// EventoFacade daoEventoFacade = new EventoFacade();
		// Evento evento = new Evento();
		// Calendar calendar = Calendar.getInstance();
		// evento.setData_inicio(calendar);
		// evento.setDescricaoEvento("XXI Encontro Nacional de Desenvolvedores");
		// evento.setDias_float(2);
		// evento.setNome("Desenvolvedores Poliglotas");
		// evento.setQtd_vagas(10);
		// daoEventoFacade.adiciona(evento);

		UsuarioFacade dao = new UsuarioFacade();

		Usuario usuario = new Usuario();
		usuario.setNome("Luciano Nunes");
		usuario.setCpf("829.077.751-16");
		usuario.setEmail("wesley.fernandes.arasujo@gmail.com");
		usuario.setLogin("luciano");
		usuario.setSenha("123");

		dao.adiciona(usuario);

		// dao.enviaEmailDiasFloat();

	}
}
