package expense.manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddMonthlyIncome extends Activity implements OnClickListener
{
	int id=0;
	EditText date,amt,desc;
	Button save,clear;
	SharedPreferences prefs;
	ImageView back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addmontlyincome);

		prefs = this.getSharedPreferences("expense.manager",Context.MODE_WORLD_WRITEABLE);
		back = (ImageView)findViewById(R.id.imageView2);
		
		date = (EditText)findViewById(R.id.editText1);
		amt = (EditText)findViewById(R.id.editText2);
		desc = (EditText)findViewById(R.id.editText3);
		
		save = (Button)findViewById(R.id.button1);
		clear = (Button)findViewById(R.id.button2);
		
		save.setOnClickListener(this);
		clear.setOnClickListener(this);
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
	}
	
	@Override
	public void onClick(View v) 
	{
		if(v==save)
		{
			String amnt =amt.getText().toString();
			String des =desc.getText().toString();
			if(amnt.trim().equals("£")||amnt.trim().equals(""))
			{
				Toast.makeText(getApplicationContext(),"Please enter Amount.", Toast.LENGTH_LONG).show();
			}
			else
			{
				try
				{
					Storedata sd = new Storedata(getApplicationContext());
					sd.open();
					id = prefs.getInt("expense.manager.id2",0);
					id++;
					prefs.edit().putInt("expense.manager.id2",id).commit();
					sd.insertData(""+id,date.getText().toString(),amnt,"none", des,"none","Income");
					sd.close();
					finish();
				}
		        catch (Exception e)
		        {
		        	Toast.makeText(getApplicationContext(),"Unable to add expense, please retry.", Toast.LENGTH_LONG).show();
		        }
			}
		}
		else if(v==back)
		{
			finish();
		}
		else if(v==clear)
		{
			amt.setText("");
			desc.setText("");
		}
	}
}