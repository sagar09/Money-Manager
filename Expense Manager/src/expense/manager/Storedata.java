
package expense.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Storedata
{
	public static final String id = "id";
	public static final String date = "date";
	public static final String amount = "amount";
	public static final String category = "category";
	public static final String description = "description";
	public static final String img_name = "img_name";
	public static final String type = "type";
	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	public static final String DATABASE_NAME = "Expense Manager";
	public static final String DATABASE_TABLE = "Expences_mgr";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE ="create table " + DATABASE_TABLE + " (" + id +" text not null, " + date + " text not null,"+ amount +" text not null,"+ category +" text not null,"+ description +" text not null,"+ img_name +" text not null,"+ type +" text not null);";
	private final Context mCtx;
	private static class DatabaseHelper extends SQLiteOpenHelper 
	{
		DatabaseHelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) 
		{
				db.execSQL(DATABASE_CREATE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	
	public Storedata(Context ctx)
	{
		this.mCtx = ctx;
	}

	public Storedata open() throws SQLException 
	{
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	  public void insertData(String id, String date,String amount,String category,String description,String img_name,String type) 
	  {
		    ContentValues initialValues = new ContentValues();
		    initialValues.put(Storedata.id, id);
		    initialValues.put(Storedata.date, date);
		    initialValues.put(Storedata.amount, amount);
		    initialValues.put(Storedata.description, description);
	    	initialValues.put(Storedata.category, category);
	    	initialValues.put(Storedata.img_name, img_name);
	    	initialValues.put(Storedata.type, type);
		    mDb.insert(DATABASE_TABLE,null,initialValues);
	  }
  	  
	  public void getData() 
	  {
		  Cursor cursor =  mDb.query(DATABASE_TABLE, new String[] {id,date,amount,description,category,img_name,type}, null, null, null, null, null);
	      if (cursor.moveToFirst()) 
	      {
	           do 
	           {
	        	   Log.e("id",cursor.getString(0));
					  Log.e("date",cursor.getString(1));
					  Log.e("amount",cursor.getString(2));
					  Log.e("description",cursor.getString(3));
					  Log.e("category",cursor.getString(4));
					  Log.e("img_name",cursor.getString(5));
					  Log.e("type",cursor.getString(6));
	            }
	            while (cursor.moveToNext());
	           
	        }
	  }
	  
	  public void getselected(String dat,String typ,String cat)
	  {
		  try
		  {
			  SharedData.id.clear();
			  SharedData.date.clear();
			  SharedData.amount.clear();
			  SharedData.description.clear();
			  SharedData.category.clear();
			  SharedData.img_name.clear();
			  SharedData.type.clear();
			  Cursor cursor = mDb.query(DATABASE_TABLE, new String[] {id,date,amount,description,category,img_name,type},date+" like '%"+dat+"%' AND "+type+" like '%"+typ+"%' AND "+category+" like '%"+cat+"%'", null, null, null, null);
			  if (cursor.moveToFirst()) 
		      {
		           do 
		           {
		        	   SharedData.id.add(cursor.getString(0));
		        	   SharedData.date.add(cursor.getString(1));
		        	   SharedData.amount.add(cursor.getString(2));
		        	   SharedData.description.add(cursor.getString(3));
		        	   SharedData.category.add(cursor.getString(4));
		        	   SharedData.img_name.add(cursor.getString(5));
		        	   SharedData.type.add(cursor.getString(6));
		           }
		           while (cursor.moveToNext());
		      }
			  cursor.close();
		  }
		  catch (Exception e) 
		  {
			  Log.e("err db", e.toString());
		  }
	  }
	  
	  public int getAmount(String dat,String typ,String cat)
	  {
		  int total=0;
		  try
		  {
			  Cursor cursor = mDb.query(DATABASE_TABLE, new String[] {amount},date+" like '%"+dat+"%' AND "+type+" like '%"+typ+"%' AND "+category+" like '%"+cat+"%'", null, null, null, null);
			  if (cursor.moveToFirst()) 
		      {
		           do 
		           {
					  total=total+Integer.parseInt(cursor.getString(0));
		           }
		           while (cursor.moveToNext());
		      }
			  cursor.close();
		  }
		  catch (Exception e) 
		  {
			  Log.e("err db", e.toString());
		  }
		  return total;
	  }
	  
	public void close() 
	{
		mDb.close();
		mDbHelper.close();
	}
}