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

public class Main1 extends Activity 
{
	ImageView back;
	int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0,k=0,l=0;
	List<PieDetailsItem> piedata = new ArrayList<PieDetailsItem>(0);
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		
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
		a = db.getAmount("01/"+SharedData.month_year,"Expense","");
		b = db.getAmount("02/"+SharedData.month_year,"Expense","");
		c = db.getAmount("03/"+SharedData.month_year,"Expense","");
		d = db.getAmount("04/"+SharedData.month_year,"Expense","");
		e = db.getAmount("05/"+SharedData.month_year,"Expense","");
		f = db.getAmount("06/"+SharedData.month_year,"Expense","");
		g = db.getAmount("07/"+SharedData.month_year,"Expense","");
		h = db.getAmount("08/"+SharedData.month_year,"Expense","");
		i = db.getAmount("09/"+SharedData.month_year,"Expense","");
		j = db.getAmount("10/"+SharedData.month_year,"Expense","");
		k = db.getAmount("11/"+SharedData.month_year,"Expense","");
		l = db.getAmount("12/"+SharedData.month_year,"Expense","");
		
		int items[] = {a,b,c,d,e,f,g,h,i,j,k,l};
		db.close();
		int colors[] = { -1, -3355444, -16711681,-16711936, -65281,  -256, -16747520,  -16777216, -7829368, -12303292, -16776961 , -65536};
//		String itemslabel[] = {"vauesr ur 100", "vauesr ur 200","vauesr ur 300", "vauesr ur 400", "vauesr ur 500","vauesr ur 600" };
		
		for (int i = 0; i < items.length; i++) 
		{
			itemCount = items[i];
			item = new PieDetailsItem();
			item.count = itemCount;
//			item.label = itemslabel[i];
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