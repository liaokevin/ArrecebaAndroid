package br.com.projetointegrador.Frm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import br.com.projetointegrador.R;
import br.com.projetointegrador.DAO.ProdutoDAO;
import br.com.projetointegrador.TO.Produto;
import br.com.projetointegrador.View.ProdutoViewHolder;

public class FrmProdutos extends FrmLogadoBase {
	private static final int REFRESH = 2;
	
	private ListView lvProdutos;
	private Produto[] produto;
	private ArrayAdapter<Produto> Adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.produto_list);
		lvProdutos = (ListView) findViewById(R.produto_list.list);
		lvProdutos.setOnItemClickListener(ProdutoClickListener);
		
		// alimentando o listview.
		produto = (Produto[]) getLastNonConfigurationInstance();
		if (produto == null) {
			produto = ProdutoDAO.GetList(user);
		}
		ArrayList<Produto> produtoList = new ArrayList<Produto>();
		produtoList.addAll(Arrays.asList(produto));
		
		// Setando a arrayadapter como o adapter da listview
		Adapter = new ProdutosArrayAdapter(this , produtoList);
		lvProdutos.setAdapter(Adapter);
		
	}
	
	OnItemClickListener ProdutoClickListener = new OnItemClickListener() {
		
		public void onItemClick(AdapterView<?> arg0 , View arg1 , int arg2 , long arg3) {
			Object obj = arg0.getItemAtPosition(arg2);
			Produto produto = (Produto) obj;
			String url = produto.URL;
			
			if (url == null) {
				makeDialog("URL não cadastrada" , "URL não cadastrada");
				return;
			}
			
			if (!url.startsWith("http://") && !url.startsWith("https://"))
				url = "http://" + url;
			
			Intent browser = new Intent(Intent.ACTION_VIEW , Uri.parse(url));
			startActivity(browser);
		}
	};
	
	private static class ProdutosArrayAdapter extends ArrayAdapter<Produto> {
		
		private LayoutInflater inflater;
		
		public ProdutosArrayAdapter(Context context , List<Produto> lista_produto) {
			super(context , R.layout.produto_view , R.id.textView1 , lista_produto);
			inflater = LayoutInflater.from(context);
		}
		
		@Override
		public View getView(int position , View convertView , ViewGroup parent) {
			// produto
			Produto inter = (Produto) this.getItem(position);
			TextView textView;
			ImageView imageView;
			
			// adicionando nova linha
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.produto_view , null);
				
				// procurando as visoes
				textView = (TextView) convertView.findViewById(R.produto_view.textView1);
				imageView = (ImageView) convertView.findViewById(R.produto_view.imageView1);
				convertView.setTag(new ProdutoViewHolder(textView , imageView));
			} else {
				ProdutoViewHolder viewHolder = (ProdutoViewHolder) convertView.getTag();
				textView = viewHolder.getTextView();
				imageView = viewHolder.getImageView();
			}
			
			// Display planet data
			textView.setText(inter.Nome);
			textView.setHint(inter.URL);
			
			try {
				Bitmap bmp = inter.getImage();
				if (bmp != null) {
					imageView.setImageBitmap(bmp);
				}
			} catch (Exception e) {
				Log.e("FrmProduto" , "Bitmap Error");
			}
			
			return convertView;
		}
	}
	
	public Object onRetainNonConfigurationInstance() {
		return produto;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		try {
			MenuItem menuInteresses = menu.add(REFRESH , REFRESH , 0 , "Atualizar");
			menuInteresses.setShortcut('0' , 'R');
			menuInteresses.setIcon(android.R.drawable.ic_menu_rotate);
		} catch (Exception e) {
			makeDialog("Erro" , "Erro : " + e.getMessage());
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case REFRESH:
				redirect(FrmProdutos.class);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
