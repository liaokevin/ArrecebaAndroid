package br.com.projetointegrador.Frm;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.projetointegrador.R;
import br.com.projetointegrador.DAO.CrudDAO;
import br.com.projetointegrador.TO.Crud;

public class FrmCrudList extends FrmLogadoBase {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crud_list);
		
		ListView list = (ListView) findViewById(R.crud_list.list);

		Crud[] crud = CrudDAO.GetList(user);
		
		ArrayAdapter<Crud> aa = new ArrayAdapter<Crud>(this, android.R.layout.simple_list_item_1, crud);
		list.setAdapter(aa);
	}
	
}
