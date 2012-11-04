package br.com.projetointegrador.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import br.com.projetointegrador.TO.User;
import br.com.projetointegrador.table.TbUser;


public class UserDAO extends SQLite {
	
	public final static String TAG = "UserDAO";
	
	public UserDAO(Activity context) {
		super(context);
		CreateTable();
	}
	
	protected void CreateTable() {
		try {
			CreateOrOpenDatabase();
			
			String sql = "CREATE TABLE IF NOT EXISTS " + TbUser.TABLE + " (" +
							"LOGIN varchar(16)," +
							"SENHA varchar(20)," +
							"LEMBRAR_SENHA char(1)" +
						")";
			
			UserDAO.db.execSQL(sql);
			
			Log.i(TAG, TbUser.TABLE + ", criada");
		} catch (Exception e) {
			Log.e(TAG, TbUser.TABLE + ", falha ao criar");
		} finally {
			db.close();
		}
	}
	
	public boolean Insert(User user) {
		boolean valid;
		try {
			CreateOrOpenDatabase();
			
			ContentValues cv = new ContentValues();
			cv.put(TbUser.LOGIN, user.Login);
			cv.put(TbUser.SENHA, user.Password);
			cv.put(TbUser.LEMBRAR_SENHA, String.valueOf(user.LembrarSenha));
			
			if (db.insert(TbUser.TABLE, null, cv) > 0) {
				Log.i(TAG, User.class.getName() + " - cadastrada");
				valid = true;
			} else {
				throw new Exception("Falha ao cadastrar - " + User.class.getName());
			}
		} catch (Exception e) {
			Log.e(TAG, "Falha ao cadastrar - " + User.class.getName());
			valid = false;
		} finally {
			db.close();
		}
		
		return valid;
	}
	
	public User GetUser() {
		User user = null;
		try {
			CreateOrOpenDatabase();
			
			Cursor cursor = db.query(TbUser.TABLE, TbUser.COLUNAS, null, null, null, null, null);
			if (cursor.moveToNext()) {
				Log.i(TAG, User.class.getName() + " - encontrado");
				user = new User();
				user.Login = cursor.getString(cursor.getColumnIndex(TbUser.LOGIN));
				user.Password = cursor.getString(cursor.getColumnIndex(TbUser.SENHA));
				user.LembrarSenha = cursor.getString(cursor.getColumnIndex(TbUser.LEMBRAR_SENHA)).charAt(0);
			}
			cursor.close();
		} catch (Exception e) {
			Log.e(TAG, "Falha ao buscar - " + User.class.getName());
		} finally {
			db.close();
		}
		
		return user;
	}
	
	public boolean DeleteAll() {
		boolean valid = true;
		try {
			CreateOrOpenDatabase();
			
			db.delete(TbUser.TABLE, "1", null);
		} catch (Exception e) {
			Log.e(TAG, "Falha ao deletar - " + User.class.getName());
			valid = false;
		} finally {
			db.close();
		}
		
		return valid;
	}
	

}
