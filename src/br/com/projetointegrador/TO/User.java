package br.com.projetointegrador.TO;

public class User {
	
	public int UserId;
	public String Name, Email, Login, Password;
	public char Sexo, LembrarSenha;

	/**
	 * 
	 * @return Msg de erro caso usuário não seja válido ou null caso usuário for válido
	 */
	public String valid() {
		if (Name.equals("")) {
			return "O nome é obrigatório!";
		} else if (Email.equals("")) {
			return "O email é obrigatório!";
		} else if (Login.equals("")) {
			return "O login é obrigatório!";
		} else if (Password.equals("")) {
			return "A senha é obrigatória!";
		}
		
		return null;
	}
	
}
