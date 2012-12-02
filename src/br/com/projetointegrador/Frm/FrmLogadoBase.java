package br.com.projetointegrador.Frm;

import android.view.Menu;
import android.view.MenuItem;
import br.com.projetointegrador.ArrecebaAndroidActivity;

public class FrmLogadoBase extends ArrecebaAndroidActivity {
	private static final int INTERESSES = 0;
	private static final int LOGOUT = 1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		try {
			MenuItem menuInteresses = menu.add(INTERESSES, INTERESSES, 0, "Interesses");
			menuInteresses.setShortcut('0', 'I');
			menuInteresses.setIcon(android.R.drawable.ic_menu_edit);

			MenuItem menuLogout = menu.add(LOGOUT, LOGOUT, 0, "Logout");
			menuLogout.setShortcut('0', 'L');
			menuLogout.setIcon(android.R.drawable.ic_lock_power_off);
		} catch (Exception e) {
			makeDialog("Erro", "Erro : " + e.getMessage());
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case INTERESSES:
			redirect(FrmInteresse.class);
			break;
		case LOGOUT:
			logout();
			break;
		}
		return true;
	}

}
