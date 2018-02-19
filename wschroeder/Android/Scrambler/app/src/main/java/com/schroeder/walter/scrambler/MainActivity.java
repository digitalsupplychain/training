package com.schroeder.walter.scrambler;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            EditText mEdit;
            TextView mView;

            mEdit = (EditText)findViewById(R.id.EditText);
            mView = (TextView) findViewById(R.id.ConvertedText);

            mView.setText(scrambleText (mEdit.getText().toString()));

            }
        });
    }

    private String scrambleText(String text) {
        Word mWord = new Word();
        int mPos;
        int mLimit = text.length();
        String mText;
        String word;

        for (mText="",mPos=0; mPos<mLimit;) {
            for (;mPos<mLimit && !Character.isLetter(text.charAt(mPos)); mPos++) {
                mText = mText + text.charAt(mPos);
            }
            for (word="";mPos<mLimit && Character.isLetter(text.charAt(mPos)); mPos++) {
                word = word + text.charAt(mPos);
            }
            mText = mText + mWord.scramble(word);
        }
        return mText;
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
