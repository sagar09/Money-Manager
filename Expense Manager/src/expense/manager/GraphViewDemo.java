package expense.manager;

import android.app.Activity;
import android.os.Bundle;

/**
 * GraphViewDemo creates some dummy data to demonstrate the GraphView component.
 * @author Arno den Hond
 *
 */
public class GraphViewDemo extends Activity 
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		float[] values = new float[] {2.0f,1.5f,2.5f,1.1f,2.5f,2.0f};
		String[] verlabels = new String[] {"£5000","£4000","£3000","£2000","£1000"};
		String[] horlabels = new String[] {"Food","Shopping","Enter","Medical","Travel","Other"};
		GraphView graphView = new GraphView(this, values, "GraphViewDemo",horlabels, verlabels, GraphView.BAR);
		setContentView(graphView);
	}
}