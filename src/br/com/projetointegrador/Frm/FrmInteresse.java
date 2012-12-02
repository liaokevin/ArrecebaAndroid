package br.com.projetointegrador.Frm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import br.com.projetointegrador.R;
import br.com.projetointegrador.DAO.InteresseDAO;
import br.com.projetointegrador.View.Interesse;
import br.com.projetointegrador.View.InteresseViewHolder;

public class FrmInteresse extends FrmLogadoBase {
	
	private ListView lvInteresses;
	private Interesse[] interesse;
	private ArrayAdapter<Interesse> Adapter;
	private Button bt_salvar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interesse_list);
		lvInteresses = (ListView) findViewById(R.interesse_list.list);
		bt_salvar = (Button) findViewById(R.interesse_list.bt_salvar);
		bt_salvar.setOnClickListener(btSalvarListener);
		
		lvInteresses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent , View item , int position , long id) {
				Interesse interesse = Adapter.getItem(position);
				interesse.toggleChecked();
				InteresseViewHolder viewHolder = (InteresseViewHolder) item.getTag();
				viewHolder.getCheckBox().setChecked(interesse.checked);
			}
		});
		
		// alimentando o listview.
		interesse = (Interesse[]) getLastNonConfigurationInstance();
		if (interesse == null) {
			interesse = InteresseDAO.GetList(user);
		}
		ArrayList<Interesse> interesseList = new ArrayList<Interesse>();
		interesseList.addAll(Arrays.asList(interesse));
		
		// Setando a arrayadapter como o adapter da listview
		Adapter = new InteressesArrayAdapter(this , interesseList);
		lvInteresses.setAdapter(Adapter);
		
	}
	
	OnClickListener btSalvarListener = new OnClickListener() {
		
		public void onClick(View v) {
			int count = Adapter.getCount();
			
			ArrayList<Integer> selecionadoList = new ArrayList<Integer>();
			
			for (int i = 0 ; i < count ; i++) {
				Interesse interesse = Adapter.getItem(i);
				if (interesse.checked) {
					selecionadoList.add(interesse.InteresseId);
				}
			}
			
			InteresseDAO.UpdatePreferences(user , selecionadoList);
		}
	};
	
	private static class InteressesArrayAdapter extends ArrayAdapter<Interesse> {
		
		private LayoutInflater inflater;
		
		public InteressesArrayAdapter(Context context , List<Interesse> lista_interesse) {
			super(context , R.layout.interesse_view , R.id.textView1 , lista_interesse);
			inflater = LayoutInflater.from(context);
		}
		
		@Override
		public View getView(int position , View convertView , ViewGroup parent) {
			// interesse
			Interesse inter = (Interesse) this.getItem(position);
			CheckBox checkBox;
			TextView textView;
			
			// adicionando nova linha
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.interesse_view , null);
				
				// procurando as visoes
				textView = (TextView) convertView.findViewById(R.id.textView1);
				checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);
				convertView.setTag(new InteresseViewHolder(textView , checkBox));
				
				checkBox.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						Interesse interesse = (Interesse) cb.getTag();
						interesse.checked = cb.isChecked();
					}
				});
			} else {
				InteresseViewHolder viewHolder = (InteresseViewHolder) convertView.getTag();
				checkBox = viewHolder.getCheckBox();
				textView = viewHolder.getTextView();
			}
			
			// Tag the CheckBox with the Planet it is displaying, so that we can
			// access the planet in onClick() when the CheckBox is toggled.
			checkBox.setTag(inter);
			
			// Display planet data
			checkBox.setChecked(inter.checked);
			textView.setText(inter.Nome);
			
			return convertView;
		}
		
	}
	
	public Object onRetainNonConfigurationInstance() {
		return interesse;
	}
}
