package com.antilo0p.revdev;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import android.widget.TextView;

public class RevdevRSS extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.podcast);
		
		TextView PodcastTitle;
		PodcastTitle = (TextView)findViewById(R.id.textView1);
		
		TextView PodcastCategory;
		PodcastCategory = (TextView)findViewById(R.id.textView2);
		
		WebView PodcastContent;
		PodcastContent = (WebView)findViewById(R.id.webView1);
				
		Bundle mybundle = getIntent().getExtras();
		Integer PodcastNumber = mybundle.getInt("rssnumber");
		
		final String CurrentPodcastTitle = arrays.PodcastTitle[PodcastNumber];
		final String CurrentPodcastContent = arrays.PodcastContent[PodcastNumber];
		final String CurrentPodcastCategory = arrays.PodcastCategory[PodcastNumber];
		
		
		PodcastTitle.setText(CurrentPodcastTitle);
		PodcastCategory.setText(CurrentPodcastCategory);
		//PodcastContent.loadData("<html>" + CurrentPodcastContent + "</html>", "text/html", "utf-8");
		PodcastContent.loadDataWithBaseURL (null, CurrentPodcastContent, "text/html", "utf-8",
		"about:blank"); 
				
		
	}
	
	

}
