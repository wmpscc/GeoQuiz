package com.wmpscc.geoquiz;

/**
 * Created by wmpscc on 2017/10/11.
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }


    public int getTextResId(){
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;

    }

    public Question(int textResId, boolean answerTrue){
        mTextResId=textResId;
        mAnswerTrue=answerTrue;


    }

}
