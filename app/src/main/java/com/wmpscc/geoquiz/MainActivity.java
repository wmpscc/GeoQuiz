package com.wmpscc.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.QuoteSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPriveButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex=0;

    private Question[] mQuestionsBank=new Question[]{
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_oceans,true)

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        int question=mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionsBank.length;
                updateQuestion();
            }
        });
        mPriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mCurrentIndex-1<0){
                    mCurrentIndex=(mCurrentIndex-1)*-1;
                    int messageResId=0;
                    messageResId=R.string.isTop;
                    Toast.makeText(view.getContext(),messageResId,Toast.LENGTH_SHORT).show();
                }
                else{
                mCurrentIndex=(mCurrentIndex-1)%mQuestionsBank.length;
                updateQuestion();
                }
            }
        });

    }


    private void checkAnswer(boolean userPressedTrue){
        int messageResId=0;
        boolean answer=mQuestionsBank[mCurrentIndex].isAnswerTrue();
        if(answer==userPressedTrue){
            messageResId=R.string.isTrue;
        }else{
            messageResId=R.string.isFalse;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();;


    }
    private void updateQuestion()
    {
        int question=mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);



    }
    private void initView()
    {
        mQuestionTextView= (TextView) findViewById(R.id.tvQuestion);
        mTrueButton= (Button) findViewById(R.id.btTrue);
        mFalseButton= (Button) findViewById(R.id.btFalse);
        mNextButton= (Button) findViewById(R.id.btNext);
        mPriveButton= (Button) findViewById(R.id.btPrev);

    }

}
