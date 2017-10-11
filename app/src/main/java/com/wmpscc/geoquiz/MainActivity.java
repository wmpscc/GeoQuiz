package com.wmpscc.geoquiz;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.QuoteSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX="index";
    private static final String TAG ="GeoQuiz";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPriveButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex=0;
    private ImageButton mPriveImageButton;
    private ImageButton mNextImageButton;
    private int[] cover=new int[5];
    private int local=0;
    private int score=0;

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
        if(savedInstanceState!=null)
        {
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }

        initView();
        int question=mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        setListener();


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState");
        outState.putInt(KEY_INDEX,mCurrentIndex);


    }


    private void setListener(){

        mPriveImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                local++;
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

        mNextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                local++;
                mCurrentIndex=(mCurrentIndex+1)%mQuestionsBank.length;
                updateQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionsBank.length;
                updateQuestion();
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                mTrueButton.setClickable(false);
                mFalseButton.setClickable(false);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                mTrueButton.setClickable(false);
                mFalseButton.setClickable(false);
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
            score++;
            messageResId=R.string.isTrue;
        }else{
            messageResId=R.string.isFalse;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();;


    }
    private void updateQuestion()
    {
        mTrueButton.setClickable(true);
        mFalseButton.setClickable(true);
        for(int i=0;i<5;i++){
            if(cover[i]==mCurrentIndex){
                mTrueButton.setClickable(false);
                mFalseButton.setClickable(false);
            }
        }
        int question=mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        cover[local%mQuestionsBank.length]=mCurrentIndex;
        if(local%mQuestionsBank.length==mQuestionsBank.length-1){
            Toast.makeText(this,"答题结束，正确率"+score/mQuestionsBank.length*100+"%",Toast.LENGTH_SHORT).show();
        }

    }
    private void initView()
    {
        mQuestionTextView= (TextView) findViewById(R.id.tvQuestion);
        mTrueButton= (Button) findViewById(R.id.btTrue);
        mFalseButton= (Button) findViewById(R.id.btFalse);
        mNextButton= (Button) findViewById(R.id.btNext);
        mPriveButton= (Button) findViewById(R.id.btPrev);
        mNextImageButton= (ImageButton) findViewById(R.id.ivNext);
        mPriveImageButton= (ImageButton) findViewById(R.id.ivPrev);
    }

}
