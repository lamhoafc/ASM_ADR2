package fpoly.account.asm_adr2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoly.account.asm_adr2.helper.DbHelper;
import fpoly.account.asm_adr2.model.UserModel;

public class UserDAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insertUser(UserModel user) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME_USERNAME, user.getUsername());
        values.put(DbHelper.COLUMN_NAME_PASSWORD, user.getPassword());
        return db.insert(DbHelper.TABLE_USER_NAME, null, values);
    }

    public boolean checkUserExists(String username) {
        db = dbHelper.getReadableDatabase();
        String[] columns = {DbHelper.COLUMN_NAME_USERNAME};
        String selection = DbHelper.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(DbHelper.TABLE_USER_NAME, columns, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkLogin(String username, String password) {
        db = dbHelper.getReadableDatabase();
        String[] columns = {DbHelper.COLUMN_NAME_USERNAME};
        String selection = DbHelper.COLUMN_NAME_USERNAME + " = ? AND " + DbHelper.COLUMN_NAME_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(DbHelper.TABLE_USER_NAME, columns, selection, selectionArgs, null, null, null);
        boolean success = cursor.getCount() > 0;
        cursor.close();
        return success;
    }
}
