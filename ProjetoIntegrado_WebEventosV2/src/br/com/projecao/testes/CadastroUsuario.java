package br.com.projecao.testes;

import javax.persistence.EntityManager;

import br.com.projecao.entidades.Usuario;
import br.com.projecao.facade.UsuarioFacade;

public class CadastroUsuario {

	public static void main(String[] args) {

		EntityManager manager = SegundaFabrica.getInstance();
		
		try {
			manager.getTransaction().begin();
			
			UsuarioFacade dao = new UsuarioFacade(manager);

			Usuario usuario = new Usuario();
			usuario.setNome("Marcelo Antunes");
			usuario.setCpf("829.077.751-16");
			usuario.setEmail("marcmusic10@hotmail.com");
			usuario.setLogin("marcelo");
			usuario.setSenha("123");

			Usuario usuario2 = new Usuario();
			usuario2.setNome("Wanessa Santos");
			usuario2.setCpf("829.077.751-16");
			usuario2.setEmail("nessaasanttos@gmail.com");
			usuario2.setLogin("wanessa");
			usuario2.setSenha("123");

			
			Usuario usuario3 = new Usuario();
			usuario3.setNome("Andrew Ribeiro");
			usuario3.setCpf("829.077.751-16");
			usuario3.setEmail("andrewribeiro93@gmail.com");
			usuario3.setLogin("andrew");
			usuario3.setSenha("123");

			
			Usuario usuario4 = new Usuario();
			usuario4.setNome("Nira Fr");
			usuario4.setCpf("829.077.751-16");
			usuario4.setEmail("nirafalves@gmail.com");
			usuario4.setLogin("nira");
			usuario4.setSenha("123");
			
			Usuario usuario5 = new Usuario();
			usuario5.setNome("Wesley Fernandes");
			usuario5.setCpf("829.077.751-16");
			usuario5.setEmail("linucran@gmail.com");
			usuario5.setLogin("linucran");
			usuario5.setSenha("123");
		
			Usuario usuario1 = new Usuario();
			usuario1.setNome("Marcelo Antunes");
			usuario1.setCpf("829.077.751-16");
			usuario1.setEmail("marcelo@marcmusic.com.br");
			usuario1.setLogin("marcelo2");
			usuario1.setSenha("123");			
			
			
			dao.adiciona(usuario);
			dao.adiciona(usuario1);
			dao.adiciona(usuario2);
			dao.adiciona(usuario3);
			dao.adiciona(usuario4);
			dao.adiciona(usuario5);

			
			//dao.enviaEmailDiasFloat();
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}finally{
			manager.close();
			//factory.close();
		}
	}
	
	
}
