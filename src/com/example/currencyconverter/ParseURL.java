package com.example.currencyconverter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.os.AsyncTask;
import android.util.Log;


public class ParseURL extends AsyncTask<String, Void, String>{
	 @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	    }
	@Override
    protected String doInBackground(String... strings) {
       StringBuffer buffer = new StringBuffer();
        try {
            Log.d("JSwa", "Connecting to ["+strings[0]+"]");
            Document doc  = Jsoup.connect(strings[0]).get();
            Log.d("JSwa", "Connected to ["+strings[0]+"]");
            // Get document (HTML page) title
            String title = doc.title();
            Log.d("JSwA", "Title ["+title+"]");
         //   buffer.append("NEW Title: " + title + "\r\n");
            String s=doc.select("span[class=bld]").text();
            Log.d("JSwA", "VValue ["+s+"]");
            return s;
	        }
        
        catch(Throwable t) {
            t.printStackTrace();
        }

        return buffer.toString();
    }

	
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("on poost", "post ["+s+"]");
        
        
    }
    
}