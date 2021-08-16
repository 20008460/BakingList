package sg.edu.rp.c346.id20008460.bakinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBRecipes extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recipe.db";
    private static final int DATABASE_VERSION = 6;
    private static final String TABLE_RECIPE = "recipes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "Title";
    private static final String COLUMN_SUMMARY = "SUMMARY";
    private static final String COLUMN_INGREDIENTS = "INGREDIENTS";
    private static final String COLUMN_DETAILS = "DETAILS";
    private static final String COLUMN_DATE = "DATE";
    private static final String COLUMN_USERNAME = "USERNAME";


    public DBRecipes(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createRecipeTable = "CREATE TABLE " + TABLE_RECIPE + "( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT , "
                + COLUMN_SUMMARY + " TEXT , "
                + COLUMN_INGREDIENTS + " TEXT , "
                + COLUMN_DETAILS + " TEXT , "
                + COLUMN_DATE + " TEXT , "
                + COLUMN_USERNAME + " TEXT )";
        db.execSQL(createRecipeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE); // every data in it will be gone
        onCreate(db);

    }

    public long insertRecipe(String title , String summary, String ingredients, String details , String date, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE , title);
        values.put(COLUMN_SUMMARY , summary);
        values.put(COLUMN_INGREDIENTS , ingredients);
        values.put(COLUMN_DETAILS , details);
        values.put(COLUMN_DATE , date);
        values.put(COLUMN_USERNAME , username);
        long result = db.insert (TABLE_RECIPE, null , values);
        db.close();
        return result;
    }

    public ArrayList<Recipe> getAllRecipe() {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        String selectQuery =
                "SELECT " + COLUMN_ID + ", "
                        + COLUMN_TITLE + ", "
                        + COLUMN_SUMMARY + ", "
                        + COLUMN_INGREDIENTS + ", "
                        + COLUMN_DETAILS + ", "
                        + COLUMN_DATE + ", "
                        + COLUMN_USERNAME + " "
                        + "FROM " + TABLE_RECIPE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String summary = cursor.getString(2);
                String ingredients = cursor.getString(3);
                String details = cursor.getString(4);
                String date = cursor.getString(5);
                String username = cursor.getString(6);

                Recipe recipe= new Recipe(id, title, summary , ingredients , details , date, username);
                recipes.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recipes;
    }

    public int updateRecipe(Recipe data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SUMMARY, data.getSummary());
        values.put(COLUMN_INGREDIENTS, data.getIngredients());
        values.put(COLUMN_DETAILS, data.getDetails());
        values.put(COLUMN_DATE, data.getDate());
        values.put(COLUMN_USERNAME, data.getUsername());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_RECIPE, values, condition, args);

        db.close();
        return result;
    }

    public int deleteReceipe(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_RECIPE, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Recipe> filterPersonal(String keyword) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        SQLiteDatabase db = this.getReadableDatabase();


        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SUMMARY, COLUMN_INGREDIENTS , COLUMN_DETAILS, COLUMN_DATE , COLUMN_USERNAME};
        String condition = COLUMN_USERNAME + " Like ?";
        String[] args = { "%" +  keyword + "%"};


        Cursor cursor = db.query(TABLE_RECIPE, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String summary = cursor.getString(2);
                String ingre = cursor.getString(3);
                String details = cursor.getString(4);
                String date = cursor.getString(5);
                String username = cursor.getString(6);

                Recipe recipe= new Recipe(id, title, summary, ingre, details, date, username);
                recipes.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recipes;
    }


}
