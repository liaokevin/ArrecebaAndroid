package br.com.projetointegrador;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import br.com.projetointegrador.DAO.UserDAO;
import br.com.projetointegrador.Frm.FrmLogin;
import br.com.projetointegrador.Frm.FrmProdutos;
import br.com.projetointegrador.TO.User;

public class ArrecebaAndroidActivity extends Activity {
	
	protected static User user;
	
	public void onResume() {
		super.onResume();
		
		if (user == null) {
			UserDAO dao = new UserDAO(this);
			
			user = dao.GetUser();
			
			if (user == null || user.LembrarSenha == 'N') {
				redirect(FrmLogin.class);
			} else {
				redirect(FrmProdutos.class);
			}
		}
	}
	
	public OnClickListener mainListener = new OnClickListener() {
		
		public void onClick(View arg0) {
			user = null;
			redirect(ArrecebaAndroidActivity.class);
		}
	};
	
	public void redirect(Class<?> cls) {
		startActivity(new Intent(this , cls));
	}
	
	public void makeDialog(String title , String txt) {
		Toast.makeText(this , txt , Toast.LENGTH_SHORT).show();
	}
	
	public void logout() {
		UserDAO dao = new UserDAO(this);
		dao.DeleteAll();
		
		user = null;
		
		redirect(ArrecebaAndroidActivity.class);
	}
	
}
