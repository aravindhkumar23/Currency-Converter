package com.example.currencyconverter;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class menu extends Activity {

	//menu function
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
		switch (item.getItemId())
        {
                case R.id.quit:
                	Toast.makeText(this, "quit is Selected", Toast.LENGTH_SHORT).show();
                	this.finish();
                return true;
 
                case R.id.about:
                	final PopupWindow pwindo;
                	 Button btnClosePopup;
                    Toast.makeText(this, "about is Selected", Toast.LENGTH_SHORT).show();
                    LayoutInflater inflater = (LayoutInflater)this
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
