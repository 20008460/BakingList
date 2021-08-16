package sg.edu.rp.c346.id20008460.bakinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBUsers extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "song.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_USERS = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_ROLE = "Role";
    

    public DBUsers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTable = "CREATE TABLE " + TABLE_USERS + "( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT , "
                + COLUMN_PASSWORD + " TEXT , "
                + COLUMN_ROLE + " TEXT )";
        db.execSQL(createSongTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS); // every data in it will be gone
        onCreate(db);

    }

    public long insertUser(String username , String passwords, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME , username);
        values.put(COLUMN_PASSWORD , passwords);
        values.put(COLUMN_ROLE , role);
        long result = db.insert (TABLE_USERS, null , values);
        db.close();
        return result;
    }

    public ArrayList<Users> getAllUser() {
        ArrayList<Users> songs = new ArrayList<Users>();

        String selectQuery =
                "SELECT " + COLUMN_ID + ", "
                        + COLUMN_USERNAME + ", "
                        + COLUMN_PASSWORD + ", "
                        + COLUMN_ROLE + " "
                        + "FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String password = cursor.getString(2);
                String role = cursor.getString(3);

                Users song= new Users(id, username, password , role);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public int updateUser(Users data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, data.getUsername());
        values.put(COLUMN_PASSWORD, data.getPassword());
        values.put(COLUMN_ROLE, data.getRole());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_USERS, values, condition, args);

        db.close();
        return result;
    }

    public int deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_USERS, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Users> filterRole(String keyword) {
        ArrayList<Users> users = new ArrayList<Users>();

        SQLiteDatabase db = this.getReadableDatabase();


        String[] columns= {COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_ROLE};
        String condition = COLUMN_ROLE + " Like ?";
        String[] args = { "%" +  keyword + "%"};


        Cursor cursor = db.query(TABLE_USERS, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String password = cursor.getString(2);
                String role = cursor.getString(3);

                Users user= new Users(id, username, password, role);
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }


}
