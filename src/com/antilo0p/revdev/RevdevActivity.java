package com.antilo0p.revdev;

import android.app.Activity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RevdevActivity extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	Bundle mybundle = new Bundle();
    	mybundle.putString("rssnumber", "0");
    
    	//call the DoInBackground AsyncTask class
        new DoInBackground().execute();
        //rssparser.parse();
    }

    
    //method to run the parse method from the rssparser class
    public void do_update() 
    {
    	rssparser.parse();
    }
    
    //method to populate the listview with the RSS titles
    public void populate_listview()
    {
    	
    	ListView lv1;
        lv1 = (ListView)findViewById(R.id.listView1);
    	
        lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , arrays.PodcastTitle));
        
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() 
        {
		    public void onItemClick(AdapterView parent, View v, int position, long id)
		    {
		    	//start RevdevRSS activity passing position in bundle
		    	Intent myintent = new Intent("com.antilo0p.revdev.RevdevRSS");
		    	Bundle mybundle = new Bundle();
		    	mybundle.putInt("rssnumber", position);
		    	myintent.putExtras(mybundle);

		    	startActivity(myintent);		    				    
		    	
		    }
		});
    }
    
 
    private class DoInBackground extends AsyncTask<Void, Void, Void>
                                 implements DialogInterface.OnCancelListener
    {
        private ProgressDialog dialog;
               
        protected void onPreExecute() 
        {
        	dialog = ProgressDialog.show(RevdevActivity.this, "", "Cargando RevDev Mobile, espera...", true);
        }
       
        protected Void doInBackground(Void... unused) 
        { 
        	do_update(); 
        	return null; 
        }
        
        protected void onPostExecute(Void unused) 
        { 
        	dialog.dismiss(); 
        	populate_listview();
        }
        
        public void onCancel(DialogInterface dialog) 
        { 
        	cancel(true); 
        	dialog.dismiss(); 
        }
    }
    

}