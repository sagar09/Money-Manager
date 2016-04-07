package expense.manager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Intent;

public class SplashScreen extends Activity 
{
	protected boolean _active = true;
    protected int _splashTime = 3000;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread splashTread = new Thread() 
	    {
        	@Override
		    public void run() 
		    {
        		try 
		        {
        			int waited = 0;
		            while(_active && (waited < _splashTime)) 
		            {
		            	sleep(100);
		                if(_active) 
		                	waited += 100;
		            }
		        }
        		catch(InterruptedException e) 
        		{}
        		finally 
        		{
        			finish();
        			Intent in = new Intent(SplashScreen.this,MainScreen.class);
        			startActivity(in);
        		}
		    }
	    };
	    splashTread.start();
    }
}

