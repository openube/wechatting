package com.yx.wechat;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity implements IWXAPIEventHandler {
	
	private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        text = (EditText) findViewById(R.id.text);
        
        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.registerApp(APP_ID);
        
        btnShare = (Button) findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String realText = text.getText().toString();
				
				SendMessageToWX.Req req = new Req();
				
				req.transaction = "text" + System.currentTimeMillis();
				req.scene = SendMessageToWX.Req.WXSceneTimeline;
				req.message = new WXMediaMessage();
				req.message.description = "Hello";
				req.message.mediaObject = new WXTextObject(realText);
				
				api.sendReq(req);
			}
		});
        
        api.handleIntent(getIntent(), this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	api.unregisterApp();
    	super.onDestroy();
    }
    
    private IWXAPI api;
    public static final String APP_ID = "wxbecf95bee4661b32";
    private Button btnShare;
	@Override
	public void onReq(BaseReq arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onResp(BaseResp arg0) {
		// TODO Auto-generated method stub
		
	}
}
