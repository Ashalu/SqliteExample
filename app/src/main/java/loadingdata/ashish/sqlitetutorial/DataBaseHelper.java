package loadingdata.ashish.sqlitetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Aashish on 12/19/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    public static  String Database_Name = "Student.db";
    public static  String Table_Name = "Student";
    public static String Col1 = "Id";
    public static String Col2 = "Name";
    public static String Col3 = "Surname";
    public static String Col4 = "Marks";

    public DataBaseHelper(Context context) {
        super(context, Database_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table "+Table_Name+"(Id Integer Primary Key AutoIncrement, Name String, Surname String, Marks Integer );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if Exists"+Table_Name);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(String name, String Surname, int Marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2, name);
        contentValues.put(Col3, Surname);
        contentValues.put(Col4, Marks);
        long result = db.insert(Table_Name, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getRecords()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+Table_Name,null);
        return cursor;
    }

    public boolean UpdateData(String Id,String Name,String Surname,int Marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col1,Id);
        contentValues.put(Col2,Name);
        contentValues.put(Col3,Surname);
        contentValues.put(Col4,Marks);
        db.update(Table_Name,contentValues,"Id=?",new String[]{Id});
        return true;

    }
    public int deleteRecords(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Table_Name,"Id=?",new String[] {id});
    }

        }
