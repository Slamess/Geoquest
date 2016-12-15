package fr.enssat.andreas_romain.geoquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @SerializedName("quests")
    List<Quest> questsList = new ArrayList<Quest>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Button mapbt = (Button) findViewById(R.id.mapButton);
        mapbt.setOnClickListener(this);
        try {
            parseJSON();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        showList();
    }

    protected void showList() {
        ListView QuestsList = (ListView) findViewById(R.id.QuestsList);
        QuestsAdapter questsAdapter = new QuestsAdapter(MenuActivity.this, questsList);
        QuestsList.setAdapter(questsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MenuActivity.this, QuestActivity.class);
        startActivity(intent);
    }

    public void parseJSON() throws FileNotFoundException {
        InputStream json = getResources().openRawResource(R.raw.quests);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = json.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            json.close();
        } catch (IOException e) {

        }

        Gson gson = new GsonBuilder().create();
        Quest quest = gson.fromJson(outputStream.toString(), Quest.class);
        this.questsList.add(quest);
    }
}
