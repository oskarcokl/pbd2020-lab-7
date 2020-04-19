package si.uni_lj.fri.lrk.lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    public static final String ACTION_CLASSIFIER_TRAINING = "si.uni_lj.fri.lrk.lab7.TRAIN_CLASSIFIER";

    AccBroadcastReceiver mBcastRecv;

    // TODO: Uncomment MachineLearningManager declaration
    // MachineLearningManager mManager;

    Handler handler;

    Boolean mSensing;
    Boolean mTraining;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBcastRecv = new AccBroadcastReceiver();

        this.handler = new Handler();

        final Button controlButton = findViewById(R.id.btn_control);

        mSensing = false;
        mTraining = false;

        controlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSensing) {
                    controlButton.setText(R.string.txt_start);
                    stopSensing();
                } else {
                    controlButton.setText(R.string.txt_stop);
                    startSensing();
                }
            }
        });

        Switch sw = findViewById(R.id.sw_training);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mTraining = true;

                    findViewById(R.id.tv_select).setVisibility(View.VISIBLE);
                    findViewById(R.id.radioGroup).setVisibility(View.VISIBLE);
                    findViewById(R.id.tv_result).setVisibility(View.INVISIBLE);
                } else {
                    mTraining = false;

                    findViewById(R.id.tv_select).setVisibility(View.INVISIBLE);
                    findViewById(R.id.radioGroup).setVisibility(View.INVISIBLE);
                    findViewById(R.id.tv_result).setVisibility(View.VISIBLE);
                }
            }
        });

        initClassifier();

    }


    @Override
    protected void onStart() {
        super.onStart();

        // TODO: Register local broadcast receiver
    }


    @Override
    protected void onStop() {
        super.onStop();

        // TODO: Unregister local broadcast receiver
    }

    void startSensing()
    {
        Log.d(TAG,"startSensing()");

        mSensing = true;

        // TODO: set Handler to run AccSenseService every five seconds

    }

    void stopSensing()
    {

        Log.d(TAG,"stopSensing()");

        mSensing = false;

        this.handler.removeMessages(0);

        View v  = findViewById(R.id.container);
        v.setBackgroundColor(Color.WHITE);
    }


    void initClassifier()
    {
        Log.d(TAG,"initClassifier");

        // TODO: Instantiate the classifier

    }


    public void recordAccData(float mean, float variance, float MCR) {

        Log.d(TAG, "recordAccData Intensity: " + mean + " var " + variance + " MCR " + MCR);

        Switch s = findViewById(R.id.sw_training);

        if (s.isChecked()) {

            // TODO: get the label of the selected radio button

            // TODO: send data to TrainClassifierService


        } else {

            // TODO: Do the inference (classification) and set the result (also screen background colour)

        }
    }


    public class AccBroadcastReceiver extends BroadcastReceiver {

        public static final String ACTION_SENSING_RESULT = "si.uni_lj.fri.lrk.lab7.SENSING_RESULT";
        public static final String MEAN = "mean";
        public static final String VARIANCE = "variance";
        public static final String MCR = "MCR";

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(TAG, " AccBroadcastReceiver onReceive...");

            float mean = intent.getFloatExtra(MEAN, 0);
            float variance = intent.getFloatExtra(VARIANCE, 0);
            float mcr = intent.getFloatExtra(MCR, 0);

            Log.d(TAG, "recordAccData Intensity: " + mean + " var " + variance + " MCR " + mcr);
            recordAccData(mean, variance, mcr);
        }
    }

}
