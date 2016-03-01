package com.example.blackops.livescoreboard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    Button gender,round,start,update;
    EditText team1,team2;
    CharSequence genders[],rounds[];
    RadioGroup quarter;
    TextView score1,score2;
    CheckBox crazy;

    String q ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        genders = new CharSequence[] {"Boys", "Girls"};
        rounds = new CharSequence[] {"Group", "Quarters","Semis","Finals"};
        q="";
        quarter = (RadioGroup)findViewById(R.id.radioGroup);
        score1 = (TextView)findViewById(R.id.textView2);
        score2 = (TextView)findViewById(R.id.textView3);
        gender = (Button) findViewById(R.id.gender);
        round = (Button) findViewById(R.id.round);
        team1 = (EditText)findViewById(R.id.team1);
        team2 = (EditText)findViewById(R.id.team2);
        start = (Button)findViewById(R.id.start);
        update = (Button)findViewById(R.id.update);
        crazy = (CheckBox)findViewById(R.id.crazy);

        quarter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int numbers[] = {R.id.radioButton2, R.id.radioButton3, R.id.radioButton4, R.id.radioButton5, R.id.radioButton6};
                for (int i = 0; i < 5; i++) {
                    if (checkedId == numbers[i]) {
                        switch (i) {
                            case 0:
                                q = "1";
                                break;
                            case 1:
                                q = "2";
                                break;
                            case 2:
                                q = "3";
                                break;
                            case 3:
                                q = "4";
                                break;
                            case 4:
                                q = "FT";
                                break;
                        }

                        break;
                    }
                }
                //     Toast.makeText(getApplicationContext(), q, Toast.LENGTH_SHORT).show();
            }
        });
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Boys/Girls?");
                builder.setItems(genders, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        gender.setText(genders[which].toString());
                    }
                });
                builder.show();
            }
        });

        round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Round?");
                builder.setItems(rounds, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        round.setText(rounds[which].toString());
                    }
                });
                builder.show();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender.setEnabled(false);
                round.setEnabled(false);
                start.setEnabled(false);
                team1.setEnabled(false);
                team2.setEnabled(false);
                quarter.check(R.id.radioButton2);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isCompleted = "N";
                String isCrazy=crazy.isChecked()?"Y":"N";
                String gend=gender.getText().toString();
                String r = round.getText().toString();
                String s1 = score1.getText().toString();
                String s2 = score2.getText().toString();
                String t1 = team1.getText().toString();
                String t2 = team2.getText().toString();
                sendToServer(isCompleted,isCrazy,gend,r,q,s1,s2,t1,t2);
            }
        });
    }

    public void team1Click(View v)
    {
        int score = Integer.parseInt(score1.getText().toString());
        switch(v.getId())
        {
            case R.id.button:
            {
                score-=3;
                break;
            }
            case R.id.button2:
            {
                score-=2;
                break;
            }
            case R.id.button3:
            {
                score-=1;
                break;
            }
            case R.id.button4:
            {
                score+=1;
                break;
            }
            case R.id.button5:
            {
                score+=2;
                break;
            }
            case R.id.button6:
            {
                score+=3;
                break;
            }
        }
        score1.setText(score+"");
    }

    public void team2Click(View v)
    {
        int score = Integer.parseInt(score2.getText().toString());
        switch(v.getId())
        {
            case R.id.button7:
            {
                score-=3;
                break;
            }
            case R.id.button8:
            {
                score-=2;
                break;
            }
            case R.id.button9:
            {
                score-=1;
                break;
            }
            case R.id.button10:
            {
                score+=1;
                break;
            }
            case R.id.button11:
            {
                score+=2;
                break;
            }
            case R.id.button12:
            {
                score+=3;
                break;
            }
        }
        score2.setText(score+"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    void sendToServer(String isCompleted,String isCrazy,String gend,String qrter,String rnd, String s1,String s2,String t1,String t2)
    {
        //Call API to send to server
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset) {
            String isCompleted = "Y";
            String isCrazy=crazy.isChecked()?"Y":"N";
            String gend=gender.getText().toString();
            String r = round.getText().toString();
            String s1 = score1.getText().toString();
            String s2 = score2.getText().toString();
            String t1 = team1.getText().toString();
            String t2 = team2.getText().toString();
            sendToServer(isCompleted,isCrazy,gend,q,r,s1,s2,t1,t2);
            gender.setEnabled(true);
            round.setEnabled(true);
            start.setEnabled(true);
            team1.setEnabled(true);
            team2.setEnabled(true);
            gender.setText("Gender");
            round.setText("Round");
            team1.setText("");
            team2.setText("");
            score1.setText("0");
            score2.setText("0");
            q="";
            quarter.check(R.id.radioButton2);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
