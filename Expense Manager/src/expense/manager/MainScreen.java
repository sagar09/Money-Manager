package expense.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainScreen extends Activity implements OnClickListener
{
	Button ami,ae,ve,as,h;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		
		ami = (Button)findViewById(R.id.button1);
		ae = (Button)findViewById(R.id.button2);
		ve = (Button)findViewById(R.id.button3);
		as = (Button)findViewById(R.id.button4);
		h = (Button)findViewById(R.id.button5);
		
		ami.setOnClickListener(this);
		ae.setOnClickListener(this);
		ve.setOnClickListener(this);
		as.setOnClickListener(this);
		h.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) 
	{
		if(v==ami)
		{
			Intent in = new Intent(MainScreen.this,AddMonthlyIncome.class);
			startActivity(in);
		}
		else if(v==ae)
		{
			Intent in = new Intent(MainScreen.this,AddExpense.class);
			startActivity(in);
		}
		else if(v==ve)
		{
			Intent in = new Intent(MainScreen.this,ViewExpenses.class);
			startActivity(in);
		}
		else if(v==as)
		{
			Intent in = new Intent(MainScreen.this,ApplicationSettings.class);
			startActivity(in);			
		}
		else if(v==h)
		{
			Intent in = new Intent(MainScreen.this,Help.class);
			startActivity(in);	
		}
	}
}