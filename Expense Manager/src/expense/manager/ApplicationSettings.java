package expense.manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ApplicationSettings extends Activity
{
	EditText tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
	Button ok;
	SharedPreferences prefs;
	ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		prefs = this.getSharedPreferences("expense.manager",Context.MODE_WORLD_WRITEABLE);
		setContentView(R.layout.applicationsettings);
		back = (ImageView)findViewById(R.id.imageView2);
		back.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});
		tv1 = (EditText)findViewById(R.id.editText1);
		tv2 = (EditText)findViewById(R.id.editText2);
		tv3 = (EditText)findViewById(R.id.editText3);
		tv4 = (EditText)findViewById(R.id.editText4);
		tv5 = (EditText)findViewById(R.id.editText5);
		tv6 = (EditText)findViewById(R.id.editText6);
		tv7 = (EditText)findViewById(R.id.editText7);
		tv8 = (EditText)findViewById(R.id.editTextl1);
		ok = (Button)findViewById(R.id.button1);
		ok.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				prefs.edit().putString("expense.manager.all",tv1.getText().toString()).commit();
				prefs.edit().putString("expense.manager.Food",tv2.getText().toString()).commit();
				prefs.edit().putString("expense.manager.Shopping",tv3.getText().toString()).commit();
				prefs.edit().putString("expense.manager.Entertainment",tv4.getText().toString()).commit();
				prefs.edit().putString("expense.manager.Medical",tv5.getText().toString()).commit();
				prefs.edit().putString("expense.manager.Travel",tv6.getText().toString()).commit();
				prefs.edit().putString("expense.manager.Other",tv7.getText().toString()).commit();
				prefs.edit().putString("expense.manager.Average",tv8.getText().toString()).commit();
				finish();
			}
		});
	}
	
	@Override
	protected void onStart() 
	{
		super.onStart();
		tv1.setText(prefs.getString("expense.manager.all",""));
		tv2.setText(prefs.getString("expense.manager.Food",""));
		tv3.setText(prefs.getString("expense.manager.Shopping",""));
		tv4.setText(prefs.getString("expense.manager.Entertainment",""));
		tv5.setText(prefs.getString("expense.manager.Medical",""));
		tv6.setText(prefs.getString("expense.manager.Travel",""));
		tv7.setText(prefs.getString("expense.manager.Other",""));
		tv8.setText(prefs.getString("expense.manager.Average",""));
	}
}