package curso.springboot.model;

public enum Cargo {

	JUNIOR("Junior"), PLENO("Pleno"), SENIOR("Sénior");

	private String nome;

	private String valor;

	public String getValor() {
		return valor = this.name();
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	private Cargo(String nome) {
		this.nome = nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return this.name();
	}

}
