package testes;

import dao.UsuarioDao;

public class EnvioQRCode {
	
	
	
	public static void main(String[] args) {
		
		try {
			UsuarioDao dao = new UsuarioDao();
			dao.enviaEmailComQRCode();
			
		} catch (Exception e) {

		}
	}

}
