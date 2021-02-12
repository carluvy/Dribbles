package com.coolbanter.examprep.game;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.coolbanter.examprep.R;
import com.coolbanter.examprep.data.Smiley;

import java.util.List;

public class AnswersView extends RadioGroup implements RadioGroup.OnCheckedChangeListener {

    private OnAnswerListener mAnswerListener;

    public AnswersView(Context context) {
        super(context);
        setOnCheckedChangeListener(this);
    }

    public AnswersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnCheckedChangeListener(this);
    }

//    @Override
//    protected void onCreateContextMenu(ContextMenu menu) {
//        super.onCreateContextMenu(menu);
//        ConstraintLayout mLayout = findViewById( R.id.main_view);
//
//        RadioButton button = new RadioButton(getContext());
//        button.setLayoutParams(new ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.MATCH_PARENT,
//                ConstraintLayout.LayoutParams.MATCH_PARENT));
//        mLayout.addView(button);
//    }

    /**
     * Loads RadioButtons from the list of Smileys
     *
     * @param smileys list of {@see Smiley}
     */
    public void loadAnswers(List<Smiley> smileys) {
        if (smileys == null) {
            return;
        }

//        else {
//
//
//            int ans = 0;

//            Intent intentExtra = ((Activity) getContext()).getIntent();
//            intentExtra.getStringExtra("pref_key_answers");
//            String valueKey = intentExtra.getStringExtra("pref_key_answers");

//            switch(valueKey) {
//                case "0":
//                    ans = 2;
//                    return;
//                case "1":
//                    ans = 3;
//                    return;
//                case "3":
//                    ans = 4;
//                    return;
//
//            }
//        }
        removeAllViews();
//        LayoutInflater layoutInflater = LayoutInflater.from(getContext()).inflate(R.layout.answer_view, this, true);
        LayoutInflater inflater = LayoutInflater.from(getContext());

//        RecyclerView recyclerView = findViewById(R.id.rec);


        for (Smiley smiley : smileys) {

//            int view = R.layout.activity_main;
//            LinearLayout parentLayout = findViewById(R.id.main_view);
//            View view = (View) inflater.inflate(R.layout.answer_view, this, false);
//            RadioGroup group = (RadioGroup) inflater.inflate(R.layout.answer_item, this, false);
            RadioButton button = (RadioButton) inflater.inflate(R.layout.answer_item, this, false);
            button.setText(smiley.getName());
            button.setTag(R.string.answer_tag, smiley.getCode());


            addView(button);
        }


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton button = group.findViewById(checkedId);
        mAnswerListener.onAnswerSelected((String) button.getTag(R.string.answer_tag));
    }

    /**
     * Set enabled state of child views and parent view
     *
     * @param enabled True if child views are enabled?
     */
    @Override
    public void setEnabled(boolean enabled) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(enabled);
        }
        super.setEnabled(enabled);
    }

    public interface OnAnswerListener {
        void onAnswerSelected(String answer);
    }

    public void setOnAnswerListener(OnAnswerListener listener) {

        mAnswerListener = listener;
    }


}
