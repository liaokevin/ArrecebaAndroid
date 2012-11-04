package br.com.projetointegrador.DAO;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class SQLite {
	
	public static SQLiteDatabase db;
	public static Activity context;
	
	public SQLite(Activity context) {
		SQLite.context = context;
		CreateOrOpenDatabase();
	}

	public void CreateOrOpenDatabase() {
		db = SQLite.context.openOrCreateDatabase("arreceba", Context.MODE_WORLD_READABLE, null);
	}
	
	protected abstract void CreateTable();

}
