package br.com.projecao.testes;

import javax.persistence.EntityManager;

import br.com.projecao.dao.UsuarioDao;
import br.com.projecao.entidades.Usuario;

public class TestaValidaUsuario {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		EntityManager manager = SegundaFabrica.getInstance();

		try {
			manager.getTransaction().begin();

			UsuarioDao dao = new UsuarioDao(manager);
			Usuario usuario = new Usuario();

			usuario = dao.validaLogin("1234", "1234");
			
			System.out.println(usuario.getNome());
			System.out.println(usuario.getSobrenome());

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
