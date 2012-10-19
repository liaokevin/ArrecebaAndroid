package br.com.projetointegrador;

import br.com.projetointegrador.DAO.CrudDAO;
import br.com.projetointegrador.DAO.WebService;
import br.com.projetointegrador.TO.Crud;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ArrecebaAndroidActivity extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView list = (ListView) findViewById(R.id.listView1);

		Crud[] crud = CrudDAO.GetList();
		
		ArrayAdapter<Crud> aa = new ArrayAdapter<Crud>(this, android.R.layout.simple_list_item_1, crud);
		list.setAdapter(aa);
		
		Toast.makeText(this, crud[0].Name, 1000).show();
	}
}