package br.com.projetointegrador.TO;

import java.io.IOException;
import java.math.BigDecimal;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.com.projetointegrador.DAO.WebService;

public class Produto {
	
	public int ProdutoId;
	public String Nome;
	public String Descricao;
	public BigDecimal Preco;
	public String URL;
	public String Foto;
	
	public String toString() {
		return Nome;
	}
	
	public Bitmap getImage() throws IOException {
		if (Foto != null && Foto != "") {
			java.net.URL url = new java.net.URL(WebService.URL + Foto);
			return BitmapFactory.decodeStream(url.openConnection().getInputStream());
		}
		return null;
	}
}
