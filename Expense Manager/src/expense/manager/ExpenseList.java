package expense.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ExpenseList extends Activity 
{
	ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		back = (ImageView)findViewById(R.id.imageView2);
		back.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});
		SharedData.array0 = SharedData.date.toArray(new String[SharedData.date.size()]);
		SharedData.array1 = SharedData.amount.toArray(new String[SharedData.amount.size()]);
		SharedData.array2 = SharedData.description.toArray(new String[SharedData.description.size()]);
		SharedData.array3 = SharedData.img_name.toArray(new String[SharedData.img_name.size()]);
		SharedData.array4 = SharedData.category.toArray(new String[SharedData.category.size()]);
		
		ExpenseAdapter adapter = new ExpenseAdapter(ExpenseList.this,R.layout.expenselist,SharedData.array0,SharedData.array1,SharedData.array4,SharedData.array3);
		
	    ListView listView1 = (ListView)findViewById(R.id.listView1);
	    adapter.notifyDataSetChanged();
	    listView1.setAdapter(adapter);
	    
	    listView1.setOnItemClickListener(new OnItemClickListener() 
	    {
	    	@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) 
			{
	    		SharedData.selected_value=arg2;
	    		Intent in = new Intent(ExpenseList.this,ExpenseDetail.class);
	    		startActivity(in);
			}
	    });
	}
}