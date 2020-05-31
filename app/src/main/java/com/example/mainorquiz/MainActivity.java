package com.example.mainorquiz;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private Button nextButton;
    private TextView questionTextView;

    //Keys
   // public static final String KEY_TITLE = "title";
   // public static final String KEY_THOUGHT = "thought";



    //Connection to Firestore
   // private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private int currentQuestionIndex = 0;

    private Questions[] questionBank = new Questions[] {
            new Questions(R.string.question_dmitri, true),
            new Questions(R.string.question_inna, true),
            new Questions(R.string.question_anton, false), // Anton is data security lecturer
            new Questions(R.string.question_curriculum, true),
            new Questions(R.string.question_plagiarism, true),
            new Questions(R.string.question_mainor, true),
            new Questions(R.string.question_tuition, false),// Correct yearly tuition for SE curriculum is  4660€
            new Questions(R.string.question_international, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton =findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.answer_text_view);

        falseButton.setOnClickListener(this);// register  buttons to listen to click events
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);

                break;

            case R.id.true_button:
                checkAnswer(true);
                break;

            case R.id.next_button:
                // go to next question

                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length; // wow now i understand why we need remainders
                updateQuestion();
        }
    }

    private void updateQuestion () {
        Log.d("Current", "onClick" + currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userChooseCorrect) {
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId;

        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
        } else{
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastMessageId,
                Toast.LENGTH_SHORT)
                .show();



    }
}
