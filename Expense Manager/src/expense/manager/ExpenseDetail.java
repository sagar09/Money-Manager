package expense.manager;

import java.io.FileInputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpenseDetail extends Activity
{
	TextView cat,amt,date,desc;
	ImageView img,back;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expensedetails);
		
		cat = (TextView)findViewById(R.id.textView3);
		amt = (TextView)findViewById(R.id.textView5);
		date = (TextView)findViewById(R.id.textView7);
		desc = (TextView)findViewById(R.id.textView9);
		
		img = (ImageView)findViewById(R.id.imageView1);
		back = (ImageView)findViewById(R.id.imageView2);
		back.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});
		cat.setText(SharedData.array4[SharedData.selected_value]);
		amt.setText("£"+SharedData.array1[SharedData.selected_value]);
		date.setText(SharedData.array0[SharedData.selected_value]);
		desc.setText(SharedData.array2[SharedData.selected_value]);
		try
		{
			FileInputStream fis = getApplicationContext().openFileInput(SharedData.array3[SharedData.selected_value]);
	      	Bitmap bitmapA = BitmapFactory.decodeStream(fis);
	      	fis.close();
	    	img.setImageBitmap(bitmapA);
		}
		catch (Exception e) 
		{
			Log.e("---", e.toString());
		}
	}
}
