package com.example.currencyconverter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	private Spinner spinner1, spinner2;
	private Button btnSubmit;
	TextView connection;
	RelativeLayout bg;
	EditText result;
	ParseURL convHelp=new ParseURL();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		result=(EditText)findViewById(R.id.editText1);
		click_submit_button();//submit button function
		
		
		// check if internet connected or not
		connection=(TextView)findViewById(R.id.connection);
		bg=(RelativeLayout) findViewById(R.id.container);
        if(isConnected()){
            connection.setBackgroundColor(0xFF00CC00);
            connection.setText("You are connected");
        }
        else{
        	bg.setBackgroundColor(0xFFFF0000);
            connection.setText("You are NOT connected to internet check for connection");
        }
        
	}//create
	
	
	//getting the option selected on click 
	public void click_submit_button() {

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				execute_spinner_value();
			}//on click close

			private void execute_spinner_value() {
				// TODO Auto-generated method stub
				String from=String.valueOf(spinner1.getSelectedItem());
				String to=String.valueOf(spinner2.getSelectedItem());
				Matcher From = Pattern.compile("\\(([^)]+)\\)").matcher(from);
				Matcher To = Pattern.compile("\\(([^)]+)\\)").matcher(to);
				String f=null;
				while(From.find()) {f=From.group(1);}
				String t=null;
				while(To.find()) {t=To.group(1);}
				//TODO make it as a function
				final String url="https://www.google.com/finance/converter?a=1&from="+f+"&to="+t;
				Toast.makeText(MainActivity.this,"OnClickListener : " +"\nSpinner 1 : " + f +"\nSpinner 2 : " + t,Toast.LENGTH_SHORT).show();
				//( new ParseURL() ).execute(new String[]{url.toString()}); //url to next page by jsoup
				Toast.makeText(MainActivity.this,"please wait ",Toast.LENGTH_SHORT).show();
				String valueInInr=convHelp.doInBackground(url);
				result.setText(valueInInr);
			}
		});//set on click listener close
	}//add butn click close
	
	// check network connection
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;   
    }
    
    
	// Initiating Menu XML file (menu.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;
    }
    //menu function
    public boolean onOptionsItemSelected(MenuItem item)
    {
        	switch (item.getItemId())
        {
                case R.id.quit:
                	Toast.makeText(MainActivity.this, "quit is Selected", Toast.LENGTH_SHORT).show();
                	this.finish();
                return true;
 
                case R.id.about:
                	final PopupWindow pwindo;
                	 Button btnClosePopup;
                    Toast.makeText(MainActivity.this, "about is Selected", Toast.LENGTH_SHORT).show();
                    LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    		View layout = inflater.inflate(R.layout.about,
                    		(ViewGroup) findViewById(R.id.popup_element));
                    		pwindo = new PopupWindow(layout, 300, 370, true);
                    		pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
                    		
                    		btnClosePopup = (Button) layout.findViewById(R.id.btn_dismiss);
                    		btnClosePopup.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									pwindo.dismiss();
								}
							});
                return true;
 
                default:
                	return super.onOptionsItemSelected(item);
        }//switch
    }    //menu selection
	
    
}
