package org.openspaces.android.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HelloWorld extends Activity {

	/** Called when the activity is first created. */
	private EditText inputText;
	private TextView outputText;
	private CharSequence currentName = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		inputText = (EditText) findViewById(R.id.textInput);
		outputText = (TextView) findViewById(R.id.textOutput);
		Button button = (Button) findViewById(R.id.okButton);
		button.setOnClickListener(new OkClickListener());
	}

	private class OkClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			currentName = inputText.getText();
			outputText.setText("Hello world, " + currentName + "!");
			Toast.makeText(HelloWorld.this, "Welcome to hello world, " + currentName + "!", Toast.LENGTH_LONG).show();
		}
	}
}
