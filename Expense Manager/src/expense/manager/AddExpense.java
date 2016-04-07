package expense.manager;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddExpense extends Activity implements OnClickListener
{
	Storedata sd;
	int id=0;
	int CAMERA_REQUEST=8;
	EditText date,amt,desc;
	Button save,clear,camera,clear_img;
	ImageView prod_img,back;
	Spinner cat_strgory;
	SharedPreferences prefs;
	String amt_str,cat_str,desc_str,dat_str;
	boolean flag=false,flag2=false;;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    sd = new Storedata(getApplicationContext());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addexpense);
		
		prefs = this.getSharedPreferences("expense.manager",Context.MODE_WORLD_WRITEABLE);
		
		date = (EditText)findViewById(R.id.editText1);
		amt = (EditText)findViewById(R.id.editText2);
		desc = (EditText)findViewById(R.id.editText3);
		
		save = (Button)findViewById(R.id.button1);
		clear = (Button)findViewById(R.id.button2);
		camera = (Button)findViewById(R.id.button3);
		clear_img = (Button)findViewById(R.id.button4);
		
		cat_strgory = (Spinner)findViewById(R.id.spinner1);
		
		prod_img = (ImageView)findViewById(R.id.imageView1);
		back = (ImageView)findViewById(R.id.imageView2);

		save.setOnClickListener(this);
		clear.setOnClickListener(this);
		camera.setOnClickListener(this);
		clear_img.setOnClickListener(this);
		back.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() 
	{
		super.onStart();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = df.format(c.getTime());
		date.setText(formattedDate);
		id = prefs.getInt("expense.manager.id1",0);
	}
	
	@Override
	public void onClick(View v) 
	{
		if(v==save)
		{
			amt_str =amt.getText().toString();
			desc_str =desc.getText().toString();
			cat_str=cat_strgory.getSelectedItem().toString().trim();
			dat_str=date.getText().toString().trim();
			if(amt_str.trim().equals("£")||amt_str.trim().equals(""))
			{
				Toast.makeText(getApplicationContext(),"Please enter Amount.", Toast.LENGTH_LONG).show();
			}
			else
			{
				int valule1=0;
				try
				{
					valule1=Integer.parseInt(prefs.getString("expense.manager."+cat_str,"0"));
				}
				catch (Exception e) 
				{
					Log.e("---> ", e.toString());
				}
				int valule2=Integer.parseInt(amt_str);
				sd.open();
				int valule3=sd.getAmount(dat_str.substring(dat_str.indexOf("/")+1),"Expense",cat_str);
				sd.close();
				if(valule1==0)
				{
					int valul1=0;
					try
					{
						flag2=true;
						valul1=Integer.parseInt(prefs.getString("expense.manager.all","0"));
					}
					catch (Exception e) 
					{
						flag2=false;
						Log.e("eeee", e.toString());
					}
					
					int valul2=Integer.parseInt(amt_str);
					sd.open();
					int valul3=sd.getAmount(dat_str.substring(dat_str.indexOf("/")+1),"Expense","");
					sd.close();
					if(valul1<(valul2+valul3) && flag2)
					{
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setMessage("Total Expense has exceeded its limit.Do you want to continue ?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
					}
					else
					{
						try
						{
							sd.open();
							id++;
							prefs.edit().putInt("expense.manager.id1",id).commit();
							if(flag)
								sd.insertData(""+id,date.getText().toString(),amt_str,cat_str, desc_str,"Expence_mgr_"+id,"Expense");
							else
								sd.insertData(""+id,date.getText().toString(),amt_str,cat_str, desc_str,"none","Expense");
							sd.close();
							finish();
						}
						catch (Exception e)
						{
							System.out.println("----> "+e.toString());
							Toast.makeText(getApplicationContext(),"Unable to add expanese Please Retry.", Toast.LENGTH_LONG).show();
						}
					}
				}
				else
				{
					if(cat_str.equals("Food")&&valule1<(valule2+valule3))
					{
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setMessage("Expense on Food has Exceeded its limit.Do you want to continue ?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
					}
					else if(cat_str.equals("Shopping")&&valule1<(valule2+valule3))
					{
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setMessage("Expense on Shopping has Exceeded its limit.Do you want to continue ?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
					}
					else if(cat_str.equals("Entertainment")&&valule1<(valule2+valule3))
					{
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setMessage("Expense on Entertainment has Exceeded its limit.Do you want to continue ?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
					}
					else if(cat_str.equals("Medical")&&valule1<(valule2+valule3))
					{
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setMessage("Expense on Medical has Exceeded its limit.Do you want to continue ?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
					}
					else if(cat_str.equals("Travel")&&valule1<(valule2+valule3))
					{
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setMessage("Expense on Travel has Exceeded its limit.Do you want to continue ?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
					}
					else if(cat_str.equals("Other")&&valule1<(valule2+valule3))
					{
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setMessage("Expense on Other has Exceeded its limit.Do you want to continue ?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
					}
					else
					{
						int valul1=0;
						try
						{
							valul1=Integer.parseInt(prefs.getString("expense.manager.all","0"));
						}
						catch (Exception e) 
						{
							Log.e("eeee", e.toString());
						}
						
						int valul2=Integer.parseInt(amt_str);
						sd.open();
						int valul3=sd.getAmount(dat_str.substring(dat_str.indexOf("/")+1),"Expense","");
						sd.close();
						if(valul1<(valul2+valul3))
						{
							AlertDialog.Builder builder = new AlertDialog.Builder(this);
							builder.setMessage("Total Expence has exceeded its limit.Do you want to continue ?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
						}
						else
						{
							try
							{
								sd.open();
								id++;
								prefs.edit().putInt("expense.manager.id1",id).commit();
								Log.e("dat_str", dat_str);
								Log.e("amt_str", amt_str);
								Log.e("cat_str", cat_str);
								Log.e("desc_str", desc_str);
								if(flag)
									sd.insertData(""+id,date.getText().toString(),amt_str,cat_str, desc_str,"Expence_mgr_"+id,"Expense");
								else
									sd.insertData(""+id,date.getText().toString(),amt_str,cat_str, desc_str,"none","Expense");
								sd.close();
								finish();
							}
							catch (Exception e)
							{
								System.out.println("----> "+e.toString());
								Toast.makeText(getApplicationContext(),"Unable to add expanese Please Retry.", Toast.LENGTH_LONG).show();
							}
						}
					}
				}
			}
		}
		else if(v==back)
		{
			this.finish();
		}
		else if(v==clear)
		{
			amt.setText("");
			desc.setText("");
		}
		else if(v==camera)
		{
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
            startActivityForResult(cameraIntent, CAMERA_REQUEST); 
		}
		else if(v==clear_img)
		{
			id = prefs.getInt("expense.manager.id1",0);
//			gets back to the previous id
			prod_img.setImageResource(R.drawable.no_photo);
			flag=false;
		}
	}
	
	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() 
	{
	    public void onClick(DialogInterface dialog, int which) 
	    {
	        switch (which)
	        {	
	        case DialogInterface.BUTTON_POSITIVE:
				try
				{
					sd.open();
					id++;
					prefs.edit().putInt("expense.manager.id1",id).commit();
					if(flag)
						sd.insertData(""+id,dat_str,amt_str,cat_str, desc_str,"Expence_mgr_"+id,"Expense");
					else
						sd.insertData(""+id,dat_str,amt_str,cat_str, desc_str,"none","Expense");
					sd.close();
					finish();
				}
				catch (Exception e)
				{
					System.out.println("----> "+e.toString());
					Toast.makeText(getApplicationContext(),"Unable to add expanese Please Retry.", Toast.LENGTH_LONG).show();
				}
			
	            break;

	        case DialogInterface.BUTTON_NEGATIVE:
	           dialog.dismiss();
	            break;
	        }
	    }

	};
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		 if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) 
		 {
	         try
	         {
				 Bitmap photo = (Bitmap) data.getExtras().get("data");
		         FileOutputStream fos;
	        	 fos = openFileOutput("Expence_mgr_"+(id+1), Context.MODE_PRIVATE);
	        	 photo.compress(Bitmap.CompressFormat.PNG, 100, fos);
	        	 fos.close();
		         prod_img.setImageBitmap(photo);
		         flag=true;
	         }
	         catch (Exception e)
	         {
	        	 System.out.println("=====> "+e.toString());
	         }
		 }
	}
}