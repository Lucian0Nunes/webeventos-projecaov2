package br.com.projecao.testes;

import javax.persistence.EntityManager;

import br.com.projecao.dao.UsuarioDao;

public class EnvioQRCode {
	
	
	
	public static void main(String[] args) {
		
		EntityManager manager = SegundaFabrica.getInstance();
		try {
			UsuarioDao dao = new UsuarioDao(manager);
			dao.enviaEmailComQRCode();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
