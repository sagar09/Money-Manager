package expense.manager;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main extends Activity 
{
	ImageView back;
	int a=0,b=0,c=0,d=0,e=0,f=0;
	List<PieDetailsItem> piedata = new ArrayList<PieDetailsItem>(0);
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		PieDetailsItem item;
		int maxCount = 0;
		int itemCount = 0;
		back = (ImageView)findViewById(R.id.imageView2);
		back.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});
		Storedata db = new Storedata(getApplicationContext());
		db.open();
		Log.e("SharedData.month_year", SharedData.month_year);
		a = db.getAmount(SharedData.month_year,"Expense","Food");
		b = db.getAmount(SharedData.month_year,"Expense","Shopping");
		c = db.getAmount(SharedData.month_year,"Expense","Entertainment");
		d = db.getAmount(SharedData.month_year,"Expense","Medical");
		e = db.getAmount(SharedData.month_year,"Expense","Travel");
		f = db.getAmount(SharedData.month_year,"Expense","Other");
		int items[] = {a,b,c,d,e,f};
		db.close();
		int colors[] = { -1, -3355444, -7829368, -12303292, -16776961 , -65536};
		
		for (int i = 0; i < items.length; i++) 
		{
			itemCount = items[i];
			item = new PieDetailsItem();
			item.count = itemCount;
			item.color = colors[i];
			piedata.add(item);
			maxCount = maxCount + itemCount;
		}
		
		int size = 320;
		int BgColor = 0xffa11b1;
		Bitmap mBaggroundImage = Bitmap.createBitmap(size, size,Bitmap.Config.ARGB_8888);
		
		View_PieChart piechart = new View_PieChart(this);
		piechart.setLayoutParams(new LayoutParams(size, size));
		piechart.setGeometry(size, size, 2, 2, 2, 2, 2130837504);
		piechart.setSkinparams(BgColor);
		piechart.setData(piedata, maxCount);
		piechart.invalidate();
		piechart.draw(new Canvas(mBaggroundImage));
		piechart = null;
		
		ImageView mImageView = new ImageView(this);
		mImageView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		
//		mImageView.setBackgroundColor(BgColor);
		mImageView.setImageBitmap(mBaggroundImage);
		LinearLayout finalLayout = (LinearLayout) findViewById(R.id.pie_container);
		finalLayout.addView(mImageView);
	}
}