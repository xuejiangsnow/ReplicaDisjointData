package com.example.puzzle;

import com.example.puzzle.network.wifi.WifiApplication;
import com.example.puzzle.network.wifi.pack.Global;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class StartInterface extends Activity{
	private static final String TAG = "StartInterface";
	private Button btstart;
	private Button btexit;
	private Button btwifi;
	private Intent intent;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_interface);
        ActivityManager.getInstance().addActivity(StartInterface.this);
        
        btstart = (Button) findViewById(R.id.start_button);
        btstart.setOnClickListener(bt_listener);
        btexit = (Button)findViewById(R.id.exit_button);
        btexit.setOnClickListener(bt_listener); 
        btwifi = (Button) findViewById(R.id.wifi_button);
        btwifi.setOnClickListener(bt_listener);
        
        Global.setIp();
        WifiApplication app = (WifiApplication) this.getApplication();
        Global.setApplication(app);
        Global.setMsgService();
        for (int i = 0; i < 5; i++) {
        	Log.i(TAG, "Global.ip"+Global.IP);
        
        }
        /****
        WifiAdmin wifi_admin = new WifiAdmin(this);
        wifi_admin.enableWiFi();
        for (int i = 0; i < 100; i++) {
        	System.out.println("******************"+wifi_admin.getIP());	
        }
        ****/
    }
	
    private View.OnClickListener bt_listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.start_button:
				intent = new Intent(StartInterface.this,MainActivity.class);  
				startActivity(intent);
				break;

			case R.id.wifi_button:
				intent = new Intent(StartInterface.this,WifiHotActivity.class);
				startActivity(intent);
				break;
				
			case R.id.exit_button:
				ActivityManager.getInstance().exit();  
				//StartInterface.this.finish();
				break;
			}
			
		}
    };
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			 exitApp();
			 return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void exitApp(){	
		AlertDialog.Builder builder = new AlertDialog.Builder(StartInterface.this);
		builder.setIcon(R.drawable.question_dialog_icon);
		builder.setTitle("Exit");
		builder.setMessage("Are you sure to exit��");
		builder.setPositiveButton(R.string.confirm,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						StartInterface.this.finish();
						ActivityManager.getInstance().exit();
					}
				});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.create().show();
	}
}
