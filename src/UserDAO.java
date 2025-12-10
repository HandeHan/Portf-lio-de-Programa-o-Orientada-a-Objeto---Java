package com.meuprojeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {

    private DatabaseHelper dbHelper;

    public UserDAO(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    // CADASTRAR USUÁRIO
    public boolean inserirUsuario(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());

        long result = db.insert(DatabaseHelper.TABLE_USERS, null, cv);

        db.close();
        return result != -1;
    }

    // VALIDAR LOGIN
    public boolean autenticar(String email, String senha) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.TABLE_USERS +
                        " WHERE email=? AND password=?",
                new String[]{ email, senha }
        );

        boolean autenticado = cursor.moveToFirst();
        cursor.close();
        db.close();
        return autenticado;
    }
}
