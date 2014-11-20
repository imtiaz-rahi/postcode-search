package com.metafour.postcode;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class Search extends Activity {

	/** http://postcode.geom4.net/search/100038/TW106DQ/full */
	private static final String CUSTOMER = "100001";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		EditText fld = (EditText) findViewById(R.id.searchText);
		fld.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent e) {
				if (actionId == EditorInfo.IME_NULL && e.getAction() == KeyEvent.ACTION_DOWN && e.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
					searchPostcode(v);
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up searchButton, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void searchPostcode(View view) {
		/* http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception */
		// StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

		Intent intent = new Intent(this, Result.class);
		EditText fld = (EditText) findViewById(R.id.searchText);
		Editable pc = fld.getText();
		if (pc.length() != 0 && pc.toString() != "") {
			intent.putExtra("com.metafour.postcode.POSTALCODES", pc.toString());
			startActivity(intent);
		}
	}
}
