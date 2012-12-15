package br.com.projetointegrador.TO;

public class Interesse {
	
	public int InteresseId;
	public String Nome = "";
	public boolean checked = false;
	
	public Interesse() {}
	
	public Interesse(String nome) {
		this.Nome = nome;
	}
	
	public Interesse(String nome , boolean checked) {
		this.Nome = nome;
		this.checked = checked;
	}
	
	public String toString() {
		return Nome;
	}
	
	public void toggleChecked() {
		checked = !checked;
	}
	
}
