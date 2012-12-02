package br.com.projetointegrador.View;

import android.widget.CheckBox;
import android.widget.TextView;

public class InteresseViewHolder {

	private CheckBox checkBox;
	private TextView textView;

	public InteresseViewHolder() {
	}

	public InteresseViewHolder(TextView textView, CheckBox checkBox) {
		this.checkBox = checkBox;
		this.textView = textView;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

	public TextView getTextView() {
		return textView;
	}

	public void setTextView(TextView textView) {
		this.textView = textView;
	}

}
