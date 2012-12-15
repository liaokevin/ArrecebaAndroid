package br.com.projetointegrador.View;

import android.widget.ImageView;
import android.widget.TextView;

public class ProdutoViewHolder {
	
	private TextView textView;
	private ImageView imageView;
	
	public ProdutoViewHolder() {}
	
	public ProdutoViewHolder(TextView textView , ImageView imageView) {
		this.textView = textView;
		this.imageView = imageView;
	}
	
	public TextView getTextView() {
		return textView;
	}
	
	public void setTextView(TextView textView) {
		this.textView = textView;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
}
