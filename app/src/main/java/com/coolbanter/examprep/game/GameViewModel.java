package com.coolbanter.examprep.game;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.coolbanter.examprep.R;
import com.coolbanter.examprep.data.DataRepository;
import com.coolbanter.examprep.data.Smiley;

import java.util.List;
import java.util.Random;

/**
 * Model for emoji matching game.
 */
public class GameViewModel extends ViewModel {

    private final DataRepository mRepository;
    private LiveData<List<Smiley>> mSmileys;
    private final MediatorLiveData<List<Smiley>> mSmileysMediator = new MediatorLiveData<>();
    private final MutableLiveData<Smiley> mCurrentSmiley = new MutableLiveData<>();
    private final MutableLiveData<Result> mResult = new MutableLiveData<>();

    private int mCorrectScore = 0;
    private int mIncorrectScore = 0;
    private final int mLimitSmiley;

    public GameViewModel(DataRepository repository, int answerLimit) {
        this.mRepository = repository;
        this.mLimitSmiley = answerLimit;
    }

    /**
     * Load Smileys from the database and set up a game round.
     *
     * @return observable list data of Smileys
     */
    public MediatorLiveData<List<Smiley>> setUpGame() {
        if (mSmileys == null) {
            mSmileysMediator.addSource(loadSmileys(), mSmileysMediator::setValue);
        }

        return mSmileysMediator;
    }

    /**
     * Rests the game with new random Smileys.
     */
    public void resetGame() {
        mSmileysMediator.removeSource(mSmileys);
        mSmileysMediator.addSource(loadSmileys(), mSmileysMediator::setValue);

        mCorrectScore = 0;
        mIncorrectScore = 0;
    }

    public MutableLiveData<Smiley> getCurrentAnswer() {
        return mCurrentSmiley;
    }

    private LiveData<List<Smiley>> loadSmileys() {
        mSmileys = mRepository.getRandomSmileys(mLimitSmiley);
        return mSmileys;
    }

    /**
     * Set up a round for the game.
     */
    public void startNewGameRound() {
        if (getTotalScore() == 3) {
            return;
        }

        Random random = new Random();
        Smiley previous = mCurrentSmiley.getValue();
        List<Smiley> smileys = mSmileys.getValue();

        if (smileys == null || smileys.size() == 0) {
            return;
        }
        int anInt = random.nextInt(smileys.size());
        Smiley smiley = smileys.get(anInt);

        if (previous != null && smileys.size() > 1 && !smiley.getName().isEmpty() &&
                previous.getName().equals(smiley.getName())) {
            startNewGameRound();
            return;
        }

        mCurrentSmiley.setValue(smiley);
    }

    public MutableLiveData<Result> getResults() {
        return mResult;
    }

    /**
     * Check the answer and determine end of three round game or start new round.
     *
     * @param answer an answer string from AnswersView
     */
    public void updateResult(String answer) {
        Smiley smiley = mCurrentSmiley.getValue();
        if (smiley == null) {
            return;
        }

        Result result;
        if (answer.equals(smiley.getCode())) {
            increaseCorrectScore();
            result = new Result(R.color.green, R.string.correct_answer);
        } else {
            increaseIncorrectScore();
            result = new Result(R.color.red, R.string.wrong_answer);
        }

        if (getTotalScore() != 3) {
            startNewGameRound();
            mResult.setValue(result);
        } else {
            goodGame();
        }
    }

    /**
     * Sets win or lose result.
     */
    private void goodGame() {
        Result result;
        if (mCorrectScore == 3) {
            result = new Result(R.color.design_default_color_secondary_variant, R.string.win_game, false);
        } else if (mCorrectScore == 2) {
            result = new Result(R.color.green, R.string.maybe_game, false);
        } else {
            result = new Result(R.color.design_default_color_error, R.string.lose_game, false);
        }
        mResult.setValue(result);
    }

    private void increaseCorrectScore() {
        this.mCorrectScore += 1;
    }

    private void increaseIncorrectScore() {
        this.mIncorrectScore += 1;
    }

    private int getTotalScore() {
        return mCorrectScore + mIncorrectScore;
    }

}
