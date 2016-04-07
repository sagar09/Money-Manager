package expense.manager;

import java.io.FileInputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpenseAdapter extends ArrayAdapter<String>
{
	String mail_id="";
    Context context;
    int layoutResourceId;   
    String data0[],data1[],data2[],data3[];
   
    public ExpenseAdapter(Context context, int layoutResourceId, String[] data0, String[] data1, String[] data2, String[] data3)
    {
        super(context, layoutResourceId, data0);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data0 = data0;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
        View row = convertView;
        TextView cate,amt,desc;
        ImageView img;

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            cate = (TextView)row.findViewById(R.id.textView1);
            amt = (TextView)row.findViewById(R.id.textView2);
            desc = (TextView)row.findViewById(R.id.textView3);
            img = (ImageView)row.findViewById(R.id.imageView1);


        if(!data3[position].equals("none"))
        {
        	FileInputStream fis;
  	      	try 
  	      	{
  	      		fis = getContext().openFileInput(data3[position]);
  	      		Bitmap bitmapA = BitmapFactory.decodeStream(fis);
  	      		fis.close();
  	      		if(bitmapA != null)
  	      		{
  	      			img.setImageBitmap(bitmapA);
  	      			bitmapA=null;
  	      		}
  	      	}
  	      	catch (Exception e) 
  	      	{
  	      		System.out.println("---> "+e.toString());
  	      	}
        }
        cate.setText(data2[position]);
        amt.setText("£"+data1[position]);
        desc.setText(data0[position]);
      
        return row;
    }
    

}
