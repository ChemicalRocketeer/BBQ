package com.davidaaronsyndicate.aptosstbbq;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aptosstbbq.bbqapp.menu.BBQMenu;
import com.aptosstbbq.bbqapp.menu.Ingredient;
import com.aptosstbbq.bbqapp.web.WebIn;

import java.util.List;

public class SoldOut extends ActionBarActivity implements WebIn.Listener {

    public static final String TAG = "Aptos St BBQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_out);
        new WebIn().addListener(this).execute(WebIn.defaultURL);
    }

    private void displaySoldOutItems(BBQMenu menu){
        int count =0;
        LinearLayout build = (LinearLayout) findViewById(R.id.soldOutDisplay);
        List<Ingredient> ings = menu.getIngredients();
        for(Ingredient ing : ings){
            if(ing.isSoldOut()){
                count++;
                TextView v = new TextView(this);
                v.setText(ing.getName());
                v.setTextColor(Color.WHITE);
                v.setTextSize(20);
                v.setTypeface(null, Typeface.ITALIC);
                v.setGravity(Gravity.CENTER);
                build.addView(v);
            }
        }
        if(count ==0){
            TextView v = new TextView(this);
            v.setText("Everything is available so come on in!");
            v.setTextColor(Color.WHITE);v.setTextSize(20);v.setGravity(Gravity.CENTER);
            build.addView(v);
            count = 0;
        }
    }

    @Override
    public void WebInComplete(WebIn win) {
        Log.i(TAG, win.getResult());
        if (win.getResult().equals("")) {
            Log.w(TAG, "failed to read menu from web");
            LinearLayout build = (LinearLayout) findViewById(R.id.soldOutDisplay);
            TextView v = new TextView(this);
            v.setText("Failed to read menu from web");
            v.setTextColor(Color.WHITE);
            build.addView(v);
        } else {
            displaySoldOutItems(BBQMenu.fromJSON(win.getResult()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sold_out, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
