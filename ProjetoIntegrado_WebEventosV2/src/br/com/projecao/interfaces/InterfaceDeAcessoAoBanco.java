package br.com.projecao.interfaces;

import java.io.Serializable;

public interface InterfaceDeAcessoAoBanco extends Serializable {

	void setId(Long id);
	Long getId();
	
}
