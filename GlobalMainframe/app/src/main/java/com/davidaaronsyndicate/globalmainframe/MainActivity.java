package com.davidaaronsyndicate.globalmainframe;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
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
        new Thread(new WebIn().addListener(this)).start();
    }

    private void displayButtons(){
        //GradientDrawable shape = new GradientDrawable();
        //shape.setCornerRadius(8);
        Display d = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int screenWidth = d.getWidth();
        List<Ingredient> ings = menu.getIngredients();
        GridLayout layout = (GridLayout) findViewById(R.id.setSoldOut);
        layout.setColumnCount(3);
        layout.setRowCount(ings.size()/3);
        for(Ingredient ing : ings){
            Button b = new Button(this);
           // b.setBackground(shape);
            b.setWidth(screenWidth / 3);
            b.setText(ing.getName());
            b.setBackgroundColor(ing.getStatusColor());
            b.setGravity(Gravity.CENTER);

            layout.addView(b);
            b.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    setButtonColor((Button) view);
                    WebOut.out(menu);
                }
            });
        }
    }

    @Override
    public void webInComplete(WebIn win, WebIn.Status status, String s) {
        if ("".equals(win.getResult())) {
            Log.w(TAG, "failed to read menu from web");
            final RelativeLayout build = (RelativeLayout) findViewById(R.id.baseLayout);
            final TextView v = new TextView(this);
            v.setText("Failed to read menu from web");
            v.setTextColor(Color.WHITE);
            runOnUiThread(new Runnable() {
                public void run() {
                    build.addView(v);
                }
            });
        } else {
            menu = BBQMenu.fromJSON(win.getResult());
            runOnUiThread(new Runnable() {
                public void run() {
                    displayButtons();
                }
            });
        }
    }
    private void setButtonColor(Button b){
          Ingredient ing = menu.getIngredient(b.getText().toString());
          menu.getIngredient(ing.getName()).toggleStatus();
          b.setBackgroundColor(ing.getStatusColor());
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
