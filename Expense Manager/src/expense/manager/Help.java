package expense.manager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Help extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
         Button button1 = (Button) findViewById(R.id.Button01);
         button1.setOnClickListener(new OnClickListener() 
         {
         	@Override
             public void onClick(View v) 
         	{
         		finish();
         	}
         });
         ImageView back = (ImageView)findViewById(R.id.imageView2);
         back.setOnClickListener(new OnClickListener()
         {	
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});
	}
}
