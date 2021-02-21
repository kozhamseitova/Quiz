package com.company.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class QuestionsActivity extends AppCompatActivity {
    private final String SCORE_KEY="SCORE";
    private final String INDEX_KEY="INDEX";
    private TextView mTxtQuestion;
    private TextView btn1;
    private TextView btn2;
    private TextView btn3;
    private TextView btn4;
    private ImageView imageView;
    private int image;
    private int mQuestionIndex;
    private String mQuizQuestion;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private ProgressBar mProgressBar;
    private TextView mQuizstatsTextView;
    private int mUserScore;
    private int questionNumber;



    private Questions[] questionCollection = new Questions[]{
            new Questions(1, "What is it?", R.drawable.heart, "Heart", "Apple", "Blood", "Sun", 1),
            new Questions(2, "When is Valentine's Day?", R.drawable.unnamed, "The 24th of February", "The 14th of January", "The 12th of February", "The 14th of February", 4),
            new Questions(3, "What city is called the city of all lovers?", R.drawable.lovecity, "Paris", "London", "Moscow", "Nur-Sultan", 1),
            new Questions(4, "What is the name of the famous bridge for lovers in Venice?", R.drawable.bridge, "Great Belt Bridge", "Bridge of Sighs", "Ponte Vecchio", "Golden Gate Bridge", 2),
            new Questions(5, "What flower is used to guess love?", R.drawable.flower, "Rose", "Lily", "Chamomile", "Cactus", 3),
    };

    final int USER_PROGRESS=(int)Math.ceil(100.0/questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        mTxtQuestion = findViewById(R.id.tv_question);
        btn1 = findViewById(R.id.tv_option_one);
        btn2 = findViewById(R.id.tv_option_two);
        btn3 = findViewById(R.id.tv_option_three);
        btn4 = findViewById(R.id.tv_option_four);
        imageView = findViewById(R.id.iv_image);
        mProgressBar = findViewById(R.id.progressBar);
        mQuizstatsTextView = findViewById(R.id.tv_progress);

        Questions q1=questionCollection[mQuestionIndex];
        mQuizQuestion = q1.getQuestion();
        option1 = q1.getOptionOne();
        option2 = q1.getOptionTwo();
        option3 = q1.getOptionThree();
        option4 = q1.getOptionFour();
        image = q1.getImage();

        imageView.setImageResource(image);
        mTxtQuestion.setText(mQuizQuestion);
        btn1.setText(option1);
        btn2.setText(option2);
        btn3.setText(option3);
        btn4.setText(option4);
        mQuizstatsTextView.setText("1 / 5");



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(1);
                changeQuestionOnButtonClick();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(2);
                changeQuestionOnButtonClick();

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(3);
                changeQuestionOnButtonClick();

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(4);
                changeQuestionOnButtonClick();

            }
        });

    }
    private void changeQuestionOnButtonClick(){
        mQuestionIndex=(mQuestionIndex+1)%5;
        if(mQuestionIndex==0){
            String value = String.valueOf(mUserScore);
            SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("value", value);
            editor.apply();
            Intent intent = new Intent(QuestionsActivity.this, ResultActivity.class);
            startActivity(intent);
            finish();
        }

        mQuizQuestion = questionCollection[mQuestionIndex].getQuestion();
        option1 = questionCollection[mQuestionIndex].getOptionOne();
        option2 = questionCollection[mQuestionIndex].getOptionTwo();
        option3 = questionCollection[mQuestionIndex].getOptionThree();
        option4 = questionCollection[mQuestionIndex].getOptionFour();
        image = questionCollection[mQuestionIndex].getImage();

        imageView.setImageResource(image);
        mTxtQuestion.setText(mQuizQuestion);
        btn1.setText(option1);
        btn2.setText(option2);
        btn3.setText(option3);
        btn4.setText(option4);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizstatsTextView.setText((questionNumber+1)+"/5");
    }
    private void evaluateUserAnswer(int userGuess){
        int currentQuestionAnswer = questionCollection[mQuestionIndex].getCorrectAnswer();
        if(currentQuestionAnswer == userGuess){
            mUserScore=mUserScore+1;
            questionNumber = questionNumber + 1;
        }
    }

}