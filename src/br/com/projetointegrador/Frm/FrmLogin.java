package br.com.projetointegrador.Frm;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import br.com.projetointegrador.ArrecebaAndroidActivity;
import br.com.projetointegrador.R;
import br.com.projetointegrador.DAO.SystemDAO;
import br.com.projetointegrador.DAO.UserDAO;
import br.com.projetointegrador.TO.User;

public class FrmLogin extends ArrecebaAndroidActivity {
	
	public Button registrarse , bt_login;
	public EditText login , senha;
	public CheckBox lembrarsenha;
	
	public void onCreate(Bundle savedInstanceState) {
		if (user == null) {
			user = new User();
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		registrarse = (Button) findViewById(R.login.bt_registrarse);
		bt_login = (Button) findViewById(R.login.bt_login);
		login = (EditText) findViewById(R.login.ti_login);
		senha = (EditText) findViewById(R.login.ti_password);
		lembrarsenha = (CheckBox) findViewById(R.login.chk_lembrarminhasenha);
		
		if (user.Login != null) {
			login.setText(user.Login);
		}
		
		setListeners();
	}
	
	private void setListeners() {
		registrarse.setOnClickListener(registrarseListener);
		bt_login.setOnClickListener(loginListener);
	}
	
	private OnClickListener loginListener = new OnClickListener() {
		
		public void onClick(View v) {
			login();
		}
	};
	
	private OnClickListener registrarseListener = new OnClickListener() {
		
		public void onClick(View arg0) {
			redirect(FrmRegistrarse.class);
		}
	};
	
	private boolean login() {
		User user = new User();
		user.Login = login.getText().toString();
		user.Password = senha.getText().toString();
		
		try {
			user = SystemDAO.Login(user);
			
			if (!(user.UserId > 0)) {
				throw new Exception();
			}
			user.LembrarSenha = lembrarsenha.isChecked() ? 'S' : 'N';
			
			UserDAO dao = new UserDAO(this);
			dao.DeleteAll();
			dao.Insert(user);
			
			ArrecebaAndroidActivity.user = user;
			
			redirect(FrmProdutos.class);
			return true;
		} catch (Exception e) {
			makeDialog("Erro" , "Login ou senha inválidos");
			return false;
		}
	}
}
