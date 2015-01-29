package com.davidaaronsyndicate.globalmainframe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aptosstbbq.bbqapp.menu.BBQMenu;
import com.aptosstbbq.bbqapp.menu.Ingredient;
import com.aptosstbbq.bbqapp.web.WebIn;
import com.aptosstbbq.bbqapp.web.WebOut;

import java.util.List;


public class MainActivity extends ActionBarActivity implements WebIn.Listener {
    public static final String TAG = "Aptos St BBQ";
    private BBQMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new WebIn().addListener(this).execute(WebIn.defaultURL);
    }
    private void displayButtons(){
        List<Ingredient> ings = menu.getIngredients();
        GridLayout layout = (GridLayout) findViewById(R.id.setSoldOut);
        layout.setColumnCount(4);
        for(Ingredient ing : ings){
            Button b = new Button(this);
            b.setText(ing.getName());
            b.setBackgroundColor(ing.isSoldOut() ? Color.RED : Color.GREEN);
            b.setGravity(Gravity.CENTER);
            layout.addView(b);
            Log.i(TAG, ing.getName());
            b.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    setButtonColor((Button) view);
                    WebOut.out(menu);
                }
            });
        }
    }

    public void WebInComplete(WebIn win) {
        if (win.getResult().equals("")) {
            Log.w(TAG, "failed to read menu from web");
            LinearLayout build = (LinearLayout) findViewById(R.id.setSoldOut);
            TextView v = new TextView(this);
            v.setText("Failed to read menu from web");
            v.setTextColor(Color.WHITE);
            build.addView(v);
        } else {
            menu = BBQMenu.fromJSON(win.getResult());
            displayButtons();
        }
    }
    private void setButtonColor(Button b){
          Ingredient ing = menu.getIngredient(b.getText().toString());
          menu.toggleSoldOut(ing.getName());
          b.setBackgroundColor(ing.isSoldOut() ? Color.RED : Color.GREEN);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
