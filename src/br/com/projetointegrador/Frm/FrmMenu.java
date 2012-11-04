package br.com.projetointegrador.Frm;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.projetointegrador.R;

public class FrmMenu extends FrmLogadoBase {

	private static final int LOGOUT = 0;
	private static final int CRUD_LIST = 1;
	public Button bt_logout, bt_crud_list;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		bt_logout = (Button) findViewById(R.menu.logout);
		bt_logout.setId(LOGOUT);
		bt_crud_list = (Button) findViewById(R.menu.crud_list);
		bt_crud_list.setId(CRUD_LIST);
		
		setListeners();
	}
	
	private void setListeners() {
		bt_logout.setOnClickListener(allListener);
		bt_logout.setOnClickListener(allListener);
	}
	
	private OnClickListener allListener = new OnClickListener() {
		
		public void onClick(View v) {
			switch (v.getId()) {
				case LOGOUT:
					logout();
					break;
				case CRUD_LIST:
					redirect(FrmCrudList.class);
					break;
			}
		}
	};
	
}
