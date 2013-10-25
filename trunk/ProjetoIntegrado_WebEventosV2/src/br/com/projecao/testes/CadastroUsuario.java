package br.com.projecao.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.projecao.entidades.Evento;
import br.com.projecao.facade.EventoFacade;

public class CadastroUsuario {

	public static void main(String[] args) {

		EntityManager manager = SegundaFabrica.getInstance();

		try {
			manager.getTransaction().begin();

			 EventoFacade daoEventoFacade = new EventoFacade(manager);
			 Evento evento = new Evento();
			 Calendar calendar = Calendar.getInstance();
			 evento.setData_inicio(calendar);
			 evento.setDescricaoEvento("XXI Encontro Nacional de Desenvolvedores");
			 evento.setDias_float(2);
			 evento.setNome("Desenvolvedores Poliglotas");
			 evento.setQtd_vagas(10);
			 daoEventoFacade.adiciona(evento);
			
//			UsuarioFacade dao = new UsuarioFacade(manager);
//
//			Usuario usuario = new Usuario();
//			usuario.setNome("Luciano Nunes");
//			usuario.setCpf("829.077.751-16");
//			usuario.setEmail("kbana10@hotmail.com");
//			usuario.setLogin("luciano");
//			usuario.setSenha("123");
//
//			dao.adiciona(usuario);

			// dao.enviaEmailDiasFloat();
			manager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
			// factory.close();
		}
	}

}
