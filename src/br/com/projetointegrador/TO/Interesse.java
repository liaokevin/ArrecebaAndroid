package br.com.projetointegrador.TO;

public class Interesse {

	private String nome = "";
	private boolean checked = false;

	public Interesse() {
	}

	public Interesse(String nome) {
		this.nome = nome;
	}

	public Interesse(String nome, boolean checked) {
		this.nome = nome;
		this.checked = checked;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String toString() {
		return nome;
	}

	public void toggleChecked() {
		checked = !checked;
	}

}
