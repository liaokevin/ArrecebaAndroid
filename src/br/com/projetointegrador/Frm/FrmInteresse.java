package br.com.projetointegrador.Frm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
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
import br.com.projetointegrador.TO.Interesse;
import br.com.projetointegrador.TO.InteresseViewHolder;

public class FrmInteresse extends Activity {

	private ListView lvInteresses;
	private Interesse[] interesse;
	private ArrayAdapter<Interesse> Adapter;
	private Button btn_Salva;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interesse_list);
		lvInteresses = (ListView) findViewById(R.id.listView1);
		btn_Salva = (Button) findViewById(R.id.btnSalvar);
		btn_Salva.setOnClickListener(btn);

		lvInteresses
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View item,
							int position, long id) {
						Interesse interesse = Adapter.getItem(position);
						interesse.toggleChecked();
						InteresseViewHolder viewHolder = (InteresseViewHolder) item
								.getTag();
						viewHolder.getCheckBox().setChecked(
								interesse.isChecked());
					}
				});

		// alimentando o listview.
		interesse = (Interesse[]) getLastNonConfigurationInstance();
		if (interesse == null) {
			interesse = new Interesse[] { new Interesse("INTERESSE 2"),
					new Interesse("INTERESSE 7"),
					new Interesse("INTERESSE 11"),
					new Interesse("INTERESSE 3"), new Interesse("INTERESSE 8"),
					new Interesse("INTERESSE 12"),
					new Interesse("INTERESSE 4"), new Interesse("INTERESSE 9"),
					new Interesse("INTERESSE 13"),
					new Interesse("INTERESSE 5"),
					new Interesse("INTERESSE 10"),
					new Interesse("INTERESSE 14"), new Interesse("INTERESSE 6") };
		}
		ArrayList<Interesse> interesseList = new ArrayList<Interesse>();
		interesseList.addAll(Arrays.asList(interesse));

		// Setando a arrayadapter como o adapter da listview
		Adapter = new InteressesArrayAdapter(this, interesseList);
		lvInteresses.setAdapter(Adapter);

	}

	OnClickListener btn = new OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
	};

	private static class InteressesArrayAdapter extends ArrayAdapter<Interesse> {

		private LayoutInflater inflater;

		public InteressesArrayAdapter(Context context,
				List<Interesse> lista_interesse) {
			super(context, R.layout.interesse_view, R.id.textView1, lista_interesse);
			inflater = LayoutInflater.from(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// interesse
			Interesse inter = (Interesse) this.getItem(position);
			CheckBox checkBox;
			TextView textView;

			// adicionando nova linha
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.interesse_view, null);

				// procurando as visoes
				textView = (TextView) convertView.findViewById(R.id.textView1);
				checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);
				convertView.setTag(new InteresseViewHolder(textView, checkBox));

				checkBox.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						Interesse interesse = (Interesse) cb.getTag();
						interesse.setChecked(cb.isChecked());
					}
				});
			} else {
				InteresseViewHolder viewHolder = (InteresseViewHolder) convertView
						.getTag();
				checkBox = viewHolder.getCheckBox();
				textView = viewHolder.getTextView();
			}

			// Tag the CheckBox with the Planet it is displaying, so that we can
			// access the planet in onClick() when the CheckBox is toggled.
			checkBox.setTag(inter);

			// Display planet data
			checkBox.setChecked(inter.isChecked());
			textView.setText(inter.getNome());

			return convertView;
		}

	}

	public Object onRetainNonConfigurationInstance() {
		return interesse;
	}
}