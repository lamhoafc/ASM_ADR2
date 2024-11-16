package fpoly.account.asm_adr2.helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER_NAME = "USER";
    public static final String COLUMN_NAME_USERNAME = "USERNAME";
    public static final String COLUMN_NAME_PASSWORD = "PASSWORD";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_USER_NAME
                + " (" + COLUMN_NAME_USERNAME + " TEXT PRIMARY KEY, "
                + COLUMN_NAME_PASSWORD + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_NAME);
            onCreate(db);
        }
    }
}
