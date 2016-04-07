package expense.manager;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewExpenses extends Activity implements OnClickListener
{
	TextView date_text,total,food,shop,entr,medi,travel,other,amt_left,amt_left1,ds,ms,ds1,ms1;
	ImageView cal;
	Button daily,monthly,yearly;
	LinearLayout  l1,l2,l3,l4,l5,l6,l7;
	String year,month,day;
	static final int DATE_DIALOG_ID_date = 1;
	ImageView back;
	Button graph;
	Storedata db;
	boolean flag=false;
	SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewexpenses);
		SharedData.TAG="daily";	
		prefs = this.getSharedPreferences("expense.manager",Context.MODE_WORLD_WRITEABLE);
		db= new Storedata(getApplicationContext());
		
		daily = (Button)findViewById(R.id.button1);
		monthly = (Button)findViewById(R.id.button2);
		yearly = (Button)findViewById(R.id.button3);
		cal = (ImageView)findViewById(R.id.imageView1); 
		back = (ImageView)findViewById(R.id.imageView2);
		graph =(Button)findViewById(R.id.button4);
		date_text = (TextView)findViewById(R.id.textView2);
		total = (TextView)findViewById(R.id.textView4);
		food = (TextView)findViewById(R.id.textView6);
		shop = (TextView)findViewById(R.id.textView8);
		entr = (TextView)findViewById(R.id.textView10);
		medi = (TextView)findViewById(R.id.textView12);
		travel = (TextView)findViewById(R.id.textView14);
		other = (TextView)findViewById(R.id.textView16);
		amt_left = (TextView)findViewById(R.id.textView_left);
		amt_left1 = (TextView)findViewById(R.id.textView_left1);
		ds = (TextView)findViewById(R.id.textViewa2);
		ms = (TextView)findViewById(R.id.textViewb2);
		ds1 = (TextView)findViewById(R.id.textViewa);
		ms1 = (TextView)findViewById(R.id.textViewb);
		
		l1 = (LinearLayout)findViewById(R.id.layout1);
		l2 = (LinearLayout)findViewById(R.id.layout2);
		l3 = (LinearLayout)findViewById(R.id.layout3);
		l4 = (LinearLayout)findViewById(R.id.layout4);
		l5 = (LinearLayout)findViewById(R.id.layout5);
		l6 = (LinearLayout)findViewById(R.id.layout6);
		l7 = (LinearLayout)findViewById(R.id.layout7);
		
		l1.setOnClickListener(this);
		l2.setOnClickListener(this);
		l3.setOnClickListener(this);
		l4.setOnClickListener(this);
		l5.setOnClickListener(this);
		l6.setOnClickListener(this);
		l7.setOnClickListener(this);
		daily.setOnClickListener(this);
		monthly.setOnClickListener(this);
		yearly.setOnClickListener(this);
		cal.setOnClickListener(this);
		back.setOnClickListener(this);
		graph.setOnClickListener(this);
		
		Calendar c = Calendar.getInstance();
		year= ""+c.get(Calendar.YEAR);
		month=""+(c.get(Calendar.MONTH)+1);
		day=""+c.get(Calendar.DAY_OF_MONTH);
		SharedData.Main_Date=day+"/"+month+"/"+year;
		if(day.length()==1)
		{
			SharedData.Main_Date="0"+SharedData.Main_Date;
			Log.e("----", month);
		}
		Log.e("----", month);
		if(month.length()==1)
		{
			String[] tmp=SharedData.Main_Date.split("/");
			SharedData.Main_Date=tmp[0]+"/0"+tmp[1]+"/"+tmp[2];
		}
		date_text.setText("Expenses for "+SharedData.Main_Date);
		Log.e("----", SharedData.Main_Date);
		db.open();
		total.setText("£"+db.getAmount(SharedData.Main_Date,"Expense",""));
		food.setText("£"+db.getAmount(SharedData.Main_Date,"Expense","Food"));
		shop.setText("£"+db.getAmount(SharedData.Main_Date,"Expense","Shopping"));
		entr.setText("£"+db.getAmount(SharedData.Main_Date,"Expense","Entertainment"));
		medi.setText("£"+db.getAmount(SharedData.Main_Date,"Expense","Medical"));
		travel.setText("£"+db.getAmount(SharedData.Main_Date,"Expense","Travel"));
		other.setText("£"+db.getAmount(SharedData.Main_Date,"Expense","Other"));
		db.close();
		
		graph.setVisibility(View.GONE);
		ds.setVisibility(View.GONE);
		ms.setVisibility(View.GONE);

		ds1.setVisibility(View.GONE);
		ms1.setVisibility(View.GONE);
		db.open();
		int inc= db.getAmount((month)+"/"+year,"Income","");
		int exp= db.getAmount((month)+"/"+year,"Expense","");
		amt_left1.setText("£"+(inc-exp));
		db.close();
		if((inc-exp)<0)
			amt_left1.setTextColor(Color.parseColor("#F00000"));
		else
			amt_left1.setTextColor(Color.parseColor("#00FF00"));
		
		Toast toast = Toast.makeText(this, "Click on the categories to get list of your Daily/Monthly/Yearly expenses.", Toast.LENGTH_LONG);
		toast.show();
	}
	
	@Override
	public void onClick(View v) 
	{
		if(v==daily)
		{
			SharedData.TAG="daily";
			db.open();
			String dat= SharedData.Main_Date;
			date_text.setText("Expenses for "+dat);
			total.setText("£"+db.getAmount(dat,"Expense",""));
			food.setText("£"+db.getAmount(dat,"Expense","Food"));
			shop.setText("£"+db.getAmount(dat,"Expense","Shopping"));
			entr.setText("£"+db.getAmount(dat,"Expense","Entertainment"));
			medi.setText("£"+db.getAmount(dat,"Expense","Medical"));
			travel.setText("£"+db.getAmount(dat,"Expense","Travel"));
			other.setText("£"+db.getAmount(dat,"Expense","Other"));
			db.close();
			amt_left1.setVisibility(View.VISIBLE);
			amt_left.setVisibility(View.VISIBLE);
			daily.setBackgroundResource(R.drawable.tab_select);
			monthly.setBackgroundResource(R.drawable.tab_bg);
			yearly.setBackgroundResource(R.drawable.tab_bg);
			graph.setVisibility(View.GONE);
			ds.setVisibility(View.GONE);
			ms.setVisibility(View.GONE);
			ds1.setVisibility(View.GONE);
			ms1.setVisibility(View.GONE);
			total.setTextColor(Color.parseColor("#53C2F0"));
			food.setTextColor(Color.parseColor("#53C2F0"));
			shop.setTextColor(Color.parseColor("#53C2F0"));
			entr.setTextColor(Color.parseColor("#53C2F0"));
			medi.setTextColor(Color.parseColor("#53C2F0"));
			travel.setTextColor(Color.parseColor("#53C2F0"));
			other.setTextColor(Color.parseColor("#53C2F0"));
		}
		else if(v==monthly)
		{
			int tmp_lim=0;
			SharedData.TAG="monthly";
			db.open();
			String dat= SharedData.Main_Date;
			dat =dat.substring(dat.indexOf("/")+1);
			Log.e("dat", dat);
			date_text.setText("Expenses for "+dat);
			total.setText("£"+db.getAmount(dat,"Expense",""));
			food.setText("£"+db.getAmount(dat,"Expense","Food"));
			shop.setText("£"+db.getAmount(dat,"Expense","Shopping"));
			entr.setText("£"+db.getAmount(dat,"Expense","Entertainment"));
			medi.setText("£"+db.getAmount(dat,"Expense","Medical"));
			travel.setText("£"+db.getAmount(dat,"Expense","Travel"));
			other.setText("£"+db.getAmount(dat,"Expense","Other"));
			Calendar c = Calendar.getInstance();
			int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			int amnt =db.getAmount(dat,"Expense","");
			int tmp_dat = Integer.parseInt(SharedData.Main_Date.substring(0,SharedData.Main_Date.indexOf("/")));
			try
			{
				tmp_lim=Integer.parseInt(prefs.getString("expense.manager.Average",""));
				flag=true;
			}
			catch (Exception e) 
			{
				flag=false;
			}
			float avg = amnt/tmp_dat;
			if(flag)
			{
				ds.setText(""+avg);
				ms.setText(""+(avg*monthMaxDays));
				if(avg>tmp_lim)
				{
					ds.setTextColor(Color.parseColor("#F00000"));
					ms.setTextColor(Color.parseColor("#F00000"));
				}
				else
				{
					ds.setTextColor(Color.parseColor("#00FF00"));
					ms.setTextColor(Color.parseColor("#00FF00"));
				}
			}
			else
			{
				ds.setTextColor(Color.parseColor("#00FF00"));
				ms.setTextColor(Color.parseColor("#00FF00"));
				ds.setText(""+avg);
				ms.setText(""+(avg*monthMaxDays));
			}
			Log.e("avg", ""+avg);
			Log.e("avg2", ""+(avg*monthMaxDays));
			

			amt_left1.setVisibility(View.VISIBLE);
			amt_left.setVisibility(View.VISIBLE);
			daily.setBackgroundResource(R.drawable.tab_bg);
			monthly.setBackgroundResource(R.drawable.tab_select);
			yearly.setBackgroundResource(R.drawable.tab_bg);
			graph.setVisibility(View.VISIBLE);	
			ds.setVisibility(View.VISIBLE);
			ms.setVisibility(View.VISIBLE);
			ds1.setVisibility(View.VISIBLE);
			ms1.setVisibility(View.VISIBLE);
			int a0=0,a1=0,a2=0,a3=0,a4=0,a5=0,a6=0;
			try
			{
			a0 = Integer.parseInt(prefs.getString("expense.manager.all",""));
			if(db.getAmount(dat,"Expense","")>a0)
				total.setTextColor(Color.parseColor("#F00000"));
			else
				total.setTextColor(Color.parseColor("#00FF00"));
			}catch (Exception e) {
				total.setTextColor(Color.parseColor("#00FF00"));
			}
			try
			{
			a1 = Integer.parseInt(prefs.getString("expense.manager.Food",""));
			if(db.getAmount(dat,"Expense","Food")>a1)
				food.setTextColor(Color.parseColor("#F00000"));
			else
				food.setTextColor(Color.parseColor("#00FF00"));
			}catch (Exception e) {
				food.setTextColor(Color.parseColor("#00FF00"));
			}
			try
			{
			a2 = Integer.parseInt(prefs.getString("expense.manager.Shopping",""));
			if(db.getAmount(dat,"Expense","Shopping")>a2)
				shop.setTextColor(Color.parseColor("#F00000"));
			else
				shop.setTextColor(Color.parseColor("#00FF00"));
			}catch (Exception e) {
				shop.setTextColor(Color.parseColor("#00FF00"));
			}
			try
			{
			a3 =  Integer.parseInt(prefs.getString("expense.manager.Entertainment",""));
			if(db.getAmount(dat,"Expense","Entertainment")>a3)
				entr.setTextColor(Color.parseColor("#F00000"));
			else
				entr.setTextColor(Color.parseColor("#00FF00"));
			}catch (Exception e) {
				entr.setTextColor(Color.parseColor("#00FF00"));
			}
			try
			{
			a4 = Integer.parseInt(prefs.getString("expense.manager.Medical",""));
			if(db.getAmount(dat,"Expense","Medical")>a4)
				medi.setTextColor(Color.parseColor("#F00000"));
			else
				medi.setTextColor(Color.parseColor("#00FF00"));
			}catch (Exception e) {
				medi.setTextColor(Color.parseColor("#00FF00"));
			}
			try
			{
			a5 = Integer.parseInt(prefs.getString("expense.manager.Travel",""));
			if(db.getAmount(dat,"Expense","Travel")>a5)
				travel.setTextColor(Color.parseColor("#F00000"));
			else
				travel.setTextColor(Color.parseColor("#00FF00"));
			}catch (Exception e) {
				travel.setTextColor(Color.parseColor("#00FF00"));
			}
			try
			{
			 a6 = Integer.parseInt(prefs.getString("expense.manager.Other",""));
			 if(db.getAmount(dat,"Expense","Other")>a6)
				 other.setTextColor(Color.parseColor("#F00000"));
				else
					other.setTextColor(Color.parseColor("#00FF00"));
				}catch (Exception e) {
					other.setTextColor(Color.parseColor("#00FF00"));
				}
			db.close();
		}
		else if(v==yearly)
		{
			SharedData.TAG="yearly";
			db.open();
			String dat= SharedData.Main_Date;
			dat =dat.substring(dat.indexOf("/")+1);
			dat =dat.substring(dat.indexOf("/")+1);
			Log.e("dat", dat);
			date_text.setText("Expenses for "+dat);
			total.setText("£"+db.getAmount(dat,"Expense",""));
			food.setText("£"+db.getAmount(dat,"Expense","Food"));
			shop.setText("£"+db.getAmount(dat,"Expense","Shopping"));
			entr.setText("£"+db.getAmount(dat,"Expense","Entertainment"));
			medi.setText("£"+db.getAmount(dat,"Expense","Medical"));
			travel.setText("£"+db.getAmount(dat,"Expense","Travel"));
			other.setText("£"+db.getAmount(dat,"Expense","Other"));
			db.close();
			amt_left1.setVisibility(View.INVISIBLE);
			amt_left.setVisibility(View.INVISIBLE);
			daily.setBackgroundResource(R.drawable.tab_bg);
			monthly.setBackgroundResource(R.drawable.tab_bg);
			yearly.setBackgroundResource(R.drawable.tab_select);
			ds.setVisibility(View.GONE);
			ms.setVisibility(View.GONE);

			ds1.setVisibility(View.GONE);
			ms1.setVisibility(View.GONE);
			total.setTextColor(Color.parseColor("#53C2F0"));
			food.setTextColor(Color.parseColor("#53C2F0"));
			shop.setTextColor(Color.parseColor("#53C2F0"));
			entr.setTextColor(Color.parseColor("#53C2F0"));
			medi.setTextColor(Color.parseColor("#53C2F0"));
			travel.setTextColor(Color.parseColor("#53C2F0"));
			other.setTextColor(Color.parseColor("#53C2F0"));
		}
		else if(v==graph)
		{
			SharedData.month_year=date_text.getText().toString().trim().substring(13);
			if(SharedData.TAG.equals("monthly"))
			{
				Intent in = new Intent(ViewExpenses.this,Main.class);
				startActivity(in);
			}
			else
			{
				Intent in = new Intent(ViewExpenses.this,Main1.class);
				startActivity(in);
			}
		}
		else if(v==cal)
		{
			showDialog(DATE_DIALOG_ID_date);
		}
		else if(v==back)
		{
			finish();
		}
		else if(v==l1)
		{
			db.open();
			String dat= date_text.getText().toString().substring(13,date_text.getText().toString().length());
			Log.e("Tag", dat);
			db.getselected(dat,"Expense","");
			db.close();
			Intent in = new Intent(ViewExpenses.this,ExpenseList.class);
			startActivity(in);			
		}
		else if(v==l2)
		{
			db.open();
			String dat= date_text.getText().toString().substring(13,date_text.getText().toString().length());
			Log.e("Tag", dat);
			db.getselected(dat,"Expense","Food");
			db.close();
			Intent in = new Intent(ViewExpenses.this,ExpenseList.class);
			startActivity(in);			
		}
		else if(v==l3)
		{
			db.open();
			String dat= date_text.getText().toString().substring(13,date_text.getText().toString().length());
			Log.e("Tag", dat);
			db.getselected(dat,"Expense","Shopping");
			db.close();
			Intent in = new Intent(ViewExpenses.this,ExpenseList.class);
			startActivity(in);			
		}
		else if(v==l4)
		{
			db.open();
			String dat= date_text.getText().toString().substring(13,date_text.getText().toString().length());
			Log.e("Tag", dat);
			db.getselected(dat,"Expense","Entertainment");
			db.close();
			Intent in = new Intent(ViewExpenses.this,ExpenseList.class);
			startActivity(in);			
		}
		else if(v==l5)
		{
			db.open();
			String dat= date_text.getText().toString().substring(13,date_text.getText().toString().length());
			Log.e("Tag", dat);
			db.getselected(dat,"Expense","Medical");
			db.close();
			Intent in = new Intent(ViewExpenses.this,ExpenseList.class);
			startActivity(in);			
		}
		else if(v==l6)
		{
			db.open();
			String dat= date_text.getText().toString().substring(13,date_text.getText().toString().length());
			Log.e("Tag", dat);
			db.getselected(dat,"Expense","Travel");
			db.close();
			Intent in = new Intent(ViewExpenses.this,ExpenseList.class);
			startActivity(in);			
		}
		else if(v==l7)
		{
			db.open();
			String dat= date_text.getText().toString().substring(13,date_text.getText().toString().length());
			Log.e("Tag", dat);
			db.getselected(dat,"Expense","Other");
			db.close();
			Intent in = new Intent(ViewExpenses.this,ExpenseList.class);
			startActivity(in);			
		}
	}
	@Override
	protected Dialog onCreateDialog(int id) 
	{
		int year1=Integer.parseInt(year);
		int month1=Integer.parseInt(month)-1;
		int day1=Integer.parseInt(day);
		return new DatePickerDialog(this, datePickerListener_date,year1, month1,day1);
	}
	private DatePickerDialog.OnDateSetListener datePickerListener_date = new DatePickerDialog.OnDateSetListener() 
	{
		@Override
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) 
		{
			int tmp_lim=0;
			String dialog_date="";
			year = ""+selectedYear;
			month =""+ (selectedMonth+1);
			if(month.length()==1)
				month ="0"+month;
			
			Log.e("month", month);
			day = ""+selectedDay;
			if(day.length()==1)
				day ="0"+day;
			if(SharedData.TAG.equals("daily"))
			{
				dialog_date=(new StringBuilder().append(day).append("/").append(month).append("/").append(year)).toString();
				SharedData.Main_Date=dialog_date;
				date_text.setText("Expenses for "+dialog_date);
				
				String inc_date= dialog_date.substring(dialog_date.indexOf("/")+1);
				db.open();
				int inc= db.getAmount(inc_date,"Income","");
				int exp= db.getAmount(inc_date,"Expense","");
				amt_left1.setText("£"+(inc-exp));
				db.close();
				total.setTextColor(Color.parseColor("#53C2F0"));
				food.setTextColor(Color.parseColor("#53C2F0"));
				shop.setTextColor(Color.parseColor("#53C2F0"));
				entr.setTextColor(Color.parseColor("#53C2F0"));
				medi.setTextColor(Color.parseColor("#53C2F0"));
				travel.setTextColor(Color.parseColor("#53C2F0"));
				other.setTextColor(Color.parseColor("#53C2F0"));
			}
			else if(SharedData.TAG.equals("monthly"))
			{
				Log.e("month", month);
				dialog_date=(new StringBuilder().append(month).append("/").append(year)).toString();
				date_text.setText("Expenses for "+dialog_date);
				SharedData.Main_Date = day +"/"+month+"/"+year;
				db.open();
				String dat =SharedData.Main_Date;
				dat =dat.substring(dat.indexOf("/")+1);
				Log.e("dat", dat);
				int inc= db.getAmount(dialog_date,"Income","");
				int exp= db.getAmount(dialog_date,"Expense","");
				amt_left1.setText("£"+(inc-exp));
				Calendar mycal = new GregorianCalendar(Integer.parseInt(year),(Integer.parseInt(month)-1),Integer.parseInt(day));
				int monthMaxDays = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); 
				Log.e("monthly", ""+monthMaxDays);
				int amnt =db.getAmount(dat,"Expense","");
				int tmp_dat = Integer.parseInt(SharedData.Main_Date.substring(0,SharedData.Main_Date.indexOf("/")));
				try
				{
					tmp_lim=Integer.parseInt(prefs.getString("expense.manager.Average",""));
					flag=true;
				}
				catch (Exception e) 
				{
					flag=false;
				}
				float avg = amnt/tmp_dat;
				if(flag)
				{
					ds.setText(""+avg);
					ms.setText(""+(avg*monthMaxDays));
					if(avg>tmp_lim)
					{
						ds.setTextColor(Color.parseColor("#F00000"));
						ms.setTextColor(Color.parseColor("#F00000"));
					}
					else
					{
						ds.setTextColor(Color.parseColor("#00FF00"));
						ms.setTextColor(Color.parseColor("#00FF00"));
					}
				}
				else
				{
					ds.setTextColor(Color.parseColor("#00FF00"));
					ms.setTextColor(Color.parseColor("#00FF00"));
					ds.setText(""+avg);
					ms.setText(""+(avg*monthMaxDays));
				}
				Log.e("avg", ""+avg);
				Log.e("avg2", ""+(avg*monthMaxDays));
				

				amt_left1.setVisibility(View.VISIBLE);
				amt_left.setVisibility(View.VISIBLE);
				daily.setBackgroundResource(R.drawable.tab_bg);
				monthly.setBackgroundResource(R.drawable.tab_select);
				yearly.setBackgroundResource(R.drawable.tab_bg);
				graph.setVisibility(View.VISIBLE);	
				ds.setVisibility(View.VISIBLE);
				ms.setVisibility(View.VISIBLE);
				ds1.setVisibility(View.VISIBLE);
				ms1.setVisibility(View.VISIBLE);
				int a0=0,a1=0,a2=0,a3=0,a4=0,a5=0,a6=0;
				try
				{
				a0 = Integer.parseInt(prefs.getString("expense.manager.all",""));
				if(db.getAmount(dat,"Expense","")>a0)
					total.setTextColor(Color.parseColor("#F00000"));
				else
					total.setTextColor(Color.parseColor("#00FF00"));
				}catch (Exception e) {
					total.setTextColor(Color.parseColor("#00FF00"));
				}
				try
				{
				a1 = Integer.parseInt(prefs.getString("expense.manager.Food",""));
				if(db.getAmount(dat,"Expense","Food")>a1)
					food.setTextColor(Color.parseColor("#F00000"));
				else
					food.setTextColor(Color.parseColor("#00FF00"));
				}catch (Exception e) {
					food.setTextColor(Color.parseColor("#00FF00"));
				}
				try
				{
				a2 = Integer.parseInt(prefs.getString("expense.manager.Shopping",""));
				if(db.getAmount(dat,"Expense","Shopping")>a2)
					shop.setTextColor(Color.parseColor("#F00000"));
				else
					shop.setTextColor(Color.parseColor("#00FF00"));
				}catch (Exception e) {
					shop.setTextColor(Color.parseColor("#00FF00"));
				}
				try
				{
				a3 =  Integer.parseInt(prefs.getString("expense.manager.Entertainment",""));
				if(db.getAmount(dat,"Expense","Entertainment")>a3)
					entr.setTextColor(Color.parseColor("#F00000"));
				else
					entr.setTextColor(Color.parseColor("#00FF00"));
				}catch (Exception e) {
					entr.setTextColor(Color.parseColor("#00FF00"));
				}
				try
				{
				a4 = Integer.parseInt(prefs.getString("expense.manager.Medical",""));
				if(db.getAmount(dat,"Expense","Medical")>a4)
					medi.setTextColor(Color.parseColor("#F00000"));
				else
					medi.setTextColor(Color.parseColor("#00FF00"));
				}catch (Exception e) {
					medi.setTextColor(Color.parseColor("#00FF00"));
				}
				try
				{
				a5 = Integer.parseInt(prefs.getString("expense.manager.Travel",""));
				if(db.getAmount(dat,"Expense","Travel")>a5)
					travel.setTextColor(Color.parseColor("#F00000"));
				else
					travel.setTextColor(Color.parseColor("#00FF00"));
				}catch (Exception e) {
					travel.setTextColor(Color.parseColor("#00FF00"));
				}
				try
				{
				 a6 = Integer.parseInt(prefs.getString("expense.manager.Other",""));
				 if(db.getAmount(dat,"Expense","Other")>a6)
					 other.setTextColor(Color.parseColor("#F00000"));
					else
						other.setTextColor(Color.parseColor("#00FF00"));
					}catch (Exception e) {
						other.setTextColor(Color.parseColor("#00FF00"));
					}
				db.close();
			}
			else
			{
				dialog_date=(new StringBuilder().append(year)).toString();
				date_text.setText("Expenses for "+dialog_date);
				SharedData.Main_Date = day +"/"+month+"/"+year;
			}

			db.open();
			total.setText("£"+db.getAmount(dialog_date,"Expense",""));
			food.setText("£"+db.getAmount(dialog_date,"Expense","Food"));
			shop.setText("£"+db.getAmount(dialog_date,"Expense","Shopping"));
			entr.setText("£"+db.getAmount(dialog_date,"Expense","Entertainment"));
			medi.setText("£"+db.getAmount(dialog_date,"Expense","Medical"));
			travel.setText("£"+db.getAmount(dialog_date,"Expense","Travel"));
			other.setText("£"+db.getAmount(dialog_date,"Expense","Other"));
			db.close();
			total.setTextColor(Color.parseColor("#53C2F0"));
			food.setTextColor(Color.parseColor("#53C2F0"));
			shop.setTextColor(Color.parseColor("#53C2F0"));
			entr.setTextColor(Color.parseColor("#53C2F0"));
			medi.setTextColor(Color.parseColor("#53C2F0"));
			travel.setTextColor(Color.parseColor("#53C2F0"));
			other.setTextColor(Color.parseColor("#53C2F0"));
		}
	};

//	private DatePickerDialog.OnDateSetListener datePickerListener_month = new DatePickerDialog.OnDateSetListener() 
//	{
//		@Override
//		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) 
//		{
//			year = selectedYear;
//			month = selectedMonth;
//			day = selectedDay;
//			String date=(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year)).toString();
//			date_text.setText("Expenses for "+date);
//
//			db.open();
//			total.setText("£"+db.getAmount(date,"Expense",""));
//			food.setText("£"+db.getAmount(date,"Expense","Food"));
//			shop.setText("£"+db.getAmount(date,"Expense","Shopping"));
//			entr.setText("£"+db.getAmount(date,"Expense","Entertainment"));
//			medi.setText("£"+db.getAmount(date,"Expense","Medical"));
//			travel.setText("£"+db.getAmount(date,"Expense","Travel"));
//			other.setText("£"+db.getAmount(date,"Expense","Other"));
//			db.close();
//		}
//	};
//	
//	private DatePickerDialog.OnDateSetListener datePickerListener_year = new DatePickerDialog.OnDateSetListener() 
//	{
//		@Override
//		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) 
//		{
//			year = selectedYear;
//			month = selectedMonth;
//			day = selectedDay;
//			String date=(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year)).toString();
//			date_text.setText("Expenses for "+date);
//
//			db.open();
//			total.setText("£"+db.getAmount(date,"Expense",""));
//			food.setText("£"+db.getAmount(date,"Expense","Food"));
//			shop.setText("£"+db.getAmount(date,"Expense","Shopping"));
//			entr.setText("£"+db.getAmount(date,"Expense","Entertainment"));
//			medi.setText("£"+db.getAmount(date,"Expense","Medical"));
//			travel.setText("£"+db.getAmount(date,"Expense","Travel"));
//			other.setText("£"+db.getAmount(date,"Expense","Other"));
//			db.close();
//		}
//	};
}