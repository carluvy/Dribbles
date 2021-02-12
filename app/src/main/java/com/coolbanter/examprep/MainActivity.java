package com.coolbanter.examprep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.widget.EmojiAppCompatTextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.coolbanter.examprep.data.Smiley;
import com.coolbanter.examprep.game.AnswerAdapter;
import com.coolbanter.examprep.game.AnswersView;
import com.coolbanter.examprep.game.GameViewModel;
import com.coolbanter.examprep.game.GameViewModelFactory;
import com.coolbanter.examprep.game.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EmojiAppCompatTextView mQuestionView;
    private AnswersView mAnswersView;
    private TextView mResult;
    private TextView listValue;
    private TextView listCode;
    private GameViewModel mGameViewModel;
    private Smiley mSmiley;
    private ListView mListView;
    private AnswerAdapter mAdapter;
    private RecyclerView mRecycler;
//    private AnswersView answersView;
    private List<Smiley> mSmileyList;
    private RadioButton answersItem;
    ViewGroup mLayout;
    SharedPreferences mPreferences;
//    public static final String

//            KEY_PREF_EXAMPLE_SWITCH = "example_switch";
//    private String sharedPrefFile = "com.coolbanter.dribble";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EmojiCompat.Config config = new BundledEmojiCompatConfig(this);
        EmojiCompat.init(config);
//
//



        setContentView(R.layout.activity_main);



        GameViewModelFactory viewModelFactory = GameViewModelFactory.createFactory(this);
        mGameViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel.class);



//        mLayout = findViewById(R.id.answer_view);
        mAnswersView = findViewById(R.id.answer_view1);
//        mAnswersView = new AnswersView(MainActivity.this);
//        mAnswersView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        ViewGroup content = (ViewGroup) findViewById(android.R.id.content).getRootView();
//        content.addView(mAnswersView);

        mSmiley = new Smiley("", "", "");


//        mAnswersView.loadAnswers(mSmileyList);


//        initAction();
//        mLayout.addView(mAnswersView.getRootView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));

//        RadioButton button = findViewById(R.id.answer_view);






//        button.toggle();







        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mQuestionView = findViewById(R.id.question);
        mResult = findViewById(R.id.result);
//        answersView = findViewById(R.id.n_answers);

        mGameViewModel.getCurrentAnswer().observe(this, this::updateContent);
        mGameViewModel.getResults().observe(this, this::showResults);
        mGameViewModel.setUpGame().observe(this, this::loadRound);


//        mGameViewModel.setUpGame();
//        loadRound(mSmileyList);

        mRecycler = findViewById(R.id.answer_recycler);



        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(manager);
//        mRecycler.setHasFixedSize(true);
//
        mSmileyList = new ArrayList<>();
//        mSmiley.getClass().isArray();
        Smiley [] mi = new Smiley[0];

        DividerItemDecoration itemDecoration = new DividerItemDecoration(mRecycler.getContext(), manager.getOrientation());
        mRecycler.addItemDecoration(itemDecoration);


        mAdapter = new AnswerAdapter(mSmileyList, this);
//        mAdapter.setSmiley(mSmileyList);
//        mRecycler.setAdapter(mAdapter);
//        mAdapter.setSmiley(mSmileyList);
        mAdapter.setSmiley(mSmileyList);
        mRecycler.setAdapter(mAdapter);





        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::loadNewGame);
//        onAnswersChange(m.getText().toString());
//        loadRound(smileys);
//        loadRound(mSmileyList);
        PreferenceManager.setDefaultValues(this, R.xml.preferences,false);


//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean switchPref = preferences.getBoolean(SettingActivity.KEY_PREF_EXAMPLE_SWITCH, false);
////        Toast.makeText(this, Boolean.toString(switchPref),
////                Toast.LENGTH_SHORT).show();





    }


    /**
     * Listener on answer selection.
     */
    private void onAnswersChange(String answer) {
        mGameViewModel.updateResult(answer);

        mAnswersView.setEnabled(true);



    }

    /**
     * Loads new game and updates observer.
     */
    private void loadNewGame(@Nullable View view) {

        mGameViewModel.resetGame();
        mAnswersView.setEnabled(true);
        mQuestionView.setText(mSmiley.getName());
        mResult.setText(null);
//        mQuestionView.setText(mSmiley.getmEmoji());
        mGameViewModel.startNewGameRound();
    }

    /**
     * Load answers for the next round.
     */
    private void loadRound(List<Smiley> smileys) {
//       mSmileyList = smileys;
//        mGameViewModel.setUpGame();
       mAnswersView.loadAnswers(smileys);
//       mAdapter.setSmiley(smileys);
//       String [] myList = new String[]{mSmiley.getCode(), mSmiley.getName(), mSmiley.getEmoji()};
//       List list = Arrays.asList(myList);
        String ans = "0";
        int size = mSmileyList.size();
        int limit2 = 2;
        int limit3 = 3;
        int limit4 = 4;

        Bundle extras = getIntent().getExtras();
        String answer;
        if (extras != null) {
            answer = extras.getString("pref_key_answers");

            if (answer.equals("2")) {
//                ans.equals("0");
                Math.min(smileys.size(), limit2);
                mAnswersView.loadAnswers(smileys);
            }
            if (answer.equals("3")) {

                Math.min(smileys.size(), limit3);
                mAnswersView.loadAnswers(smileys);
            }
            if (answer.equals("4")) {
//                ans.equals("Show two answers");

                Math.min(smileys.size(), limit4);
                mAnswersView.loadAnswers(smileys);
            }
        }


        }




//        mGameViewModel.startNewGameRound();
//        onAnswersChange(mResult.getText().toString());


    @Override
    protected void onResume() {
        super.onResume();
//        loadRound(mSmileyList);
//        mAnswersView.
        mAnswersView.setOnAnswerListener(this::onAnswersChange);
    }

    /**
     * Show results of each round.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showResults(Result result) {
        if (result == null) {
            return;
        }
//        mResult.setTextColor(getColor(result.getColor()));
        mResult.setText(result.getResult());
        mResult.setTextColor(result.getColor());

        if (!result.getEnableAnswersView()) {
            mAnswersView.setEnabled(false);
        }
    }

    private void updateContent(Smiley smiley) {
        if (smiley == null) {


        } else {

            mAnswersView.setEnabled(true);

            mQuestionView.setText(smiley.getName());
            onAnswersChange((String) mResult.getText());
            mGameViewModel.updateResult((String) mResult.getText());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_list:
                Intent listIntent = new Intent(this, SmileyListActivity.class);
                startActivity(listIntent);
                return true;
            case R.id.action_add:
                Intent addIntent = new Intent(this, AddSmileyActivity.class);
                startActivity(addIntent);
                return true;
            case R.id.action_settings:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
