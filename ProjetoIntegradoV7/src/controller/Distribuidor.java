package controller;

public class Distribuidor {
	
	private String nome;
	private String outcome;
	
	public Distribuidor(String nome, String outcome) {
		this.nome = nome;
		this.outcome = outcome;
	}
	
	public String getNome() {
		return nome;
	}

	public String getOutcome() {
		return outcome;
	}
	
	public String click() {
		return getOutcome();
	}

}
