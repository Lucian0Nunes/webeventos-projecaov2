package testes;

import facade.UsuarioFacade;

public class ValidaLogin {

	
	public static void main(String[] args) {
		UsuarioFacade f = new UsuarioFacade();
		
	 String[] teste = 	f.validaLogin("linucran", "asmira");
	 System.out.println(teste[0]);
	}
}
