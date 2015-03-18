package fr.imie.imiendk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText et1;

	static {
		System.loadLibrary("prime-jni");
	}

	public native int isPrimeC(int i);
	public native int[] getPrimesC(int i);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//edittext
		et1 = (EditText) findViewById(R.id.editText1);

		//btn c
		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				long startTime = System.nanoTime();

					String sval = et1.getText().toString();
					int val = Integer.parseInt(sval);
					toastPrime(isPrimeC(val));

				long endTime = System.nanoTime();
				long duration = (endTime - startTime);

				toast("Durée : "+String.valueOf(duration));
			}
		});

		//btn java
		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				long startTime = System.nanoTime();

					String sval = et1.getText().toString();
					int val = Integer.parseInt(sval);

					toastPrime(isPrimeJava(val));

				long endTime = System.nanoTime();
				long duration = (endTime - startTime);

				toast("Durée : "+String.valueOf(duration));
			}
		});

		//btn c all
		Button btn3 = (Button) findViewById(R.id.button3);
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				long startTime = System.nanoTime();

					String sval = et1.getText().toString();
					int val = Integer.parseInt(sval);

					String txt = "";
					int[] values = getPrimesC(val);
					for (int i = 0; i < values.length; i++) {
						if(values[i] <= val && values[i] > 0)
							txt += String.valueOf(values[i]) + " ";
					}

					toast(txt);

				long endTime = System.nanoTime();
				long duration = (endTime - startTime);

				toast("Durée : "+String.valueOf(duration));
			}
		});

		//btn java all
		Button btn4 = (Button) findViewById(R.id.button4);
		btn4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				long startTime = System.nanoTime();

					String sval = et1.getText().toString();
					int val = Integer.parseInt(sval);
					toast(getPrimesJava(val));

				long endTime = System.nanoTime();
				long duration = (endTime - startTime);

				toast("Durée : "+String.valueOf(duration));
			}
		});

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

	private int isPrimeJava(int n){
		int b = 0;
		int c;

		for (c = 2 ; c <= n - 1 ; c++)
		{
			if ( n%c == 0 )
			{
				break;
			}
		}

		if ( c == n )
			b = 1;

		return b;
	}

	private String getPrimesJava(int n) {

		int i = 3, count, c;
		String s = "";

		if ( n >= 1 )
		{
			s += "2 ";
		}

		for ( count = 2 ; count <= n ;  count ++)
		{
			for ( c = 2 ; c <= i - 1 ; c++ )
			{
				if ( i%c == 0 )
					break;
			}
			if ( c == i )
			{
				s += String.valueOf(i)+" ";
				count++;
			}
			i++;
		}

		return s;
	}

	private void toastPrime(int n){
		String text;

		if(n == 0){
			text = "n'est pas premier";
		} else {
			text = "est premier";
		}

		toast(text);
	}

	private void toast(String text){
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
