package br.com.projetointegrador.Frm;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import br.com.projetointegrador.ArrecebaAndroidActivity;
import br.com.projetointegrador.R;
import br.com.projetointegrador.DAO.SystemDAO;
import br.com.projetointegrador.TO.User;

public class FrmRegistrarse extends ArrecebaAndroidActivity {
	
	private EditText nome, email, login, senha;
	private RadioGroup sexo;
	private Button cancelar, registrarse;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrarse);
		
		nome = (EditText) findViewById(R.registrarse.ti_nome);
		email = (EditText) findViewById(R.registrarse.ti_email);
		login = (EditText) findViewById(R.registrarse.ti_login);
		senha = (EditText) findViewById(R.registrarse.ti_senha);
		sexo = (RadioGroup) findViewById(R.registrarse.rdog_sexo);
		cancelar = (Button) findViewById(R.registrarse.bt_cancelar);
		registrarse = (Button) findViewById(R.registrarse.bt_registrarse);
		
		setListeners();
	}
	
	private void setListeners() {
		cancelar.setOnClickListener(mainListener);
		registrarse.setOnClickListener(registrarseListener);
	}
	
	private OnClickListener registrarseListener = new OnClickListener() {
		
		public void onClick(View arg0) {
			User user = new User();
			user.Name = nome.getText().toString().trim();
			user.Email = email.getText().toString().trim();
			user.Login = login.getText().toString().trim();
			user.Password = senha.getText().toString().trim();
			RadioButton rb = (RadioButton) findViewById(sexo.getCheckedRadioButtonId());
			user.Sexo = rb.getTag().toString().charAt(0);
			
			String msg = user.valid();
			if (msg != null) {
				makeDialog("Atenção", msg);
			}

			if (SystemDAO.CreateUser(user)) {
				makeDialog("Sucesso", "Registro efetuado com sucesso, efetue login.");
				user = null;
				redirect(FrmLogin.class);
			} else {
				makeDialog("Erro", "Ocorreu um erro ao efetuar seu registro, tente novamente.");
			}
		}
	};
	
}
