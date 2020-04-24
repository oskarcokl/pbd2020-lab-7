package si.uni_lj.fri.lrk.lab7;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.Nullable;

import static si.uni_lj.fri.lrk.lab7.MainActivity.ACTION_CLASSIFIER_TRAINING;



public class TrainClassifierService extends IntentService {

    public TrainClassifierService() {
        super("TrainClassifierService");
    }


    private static final String TAG = "TrainClassifierService";


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(TAG,"onHandleIntent");

        if (intent.getAction().equals(ACTION_CLASSIFIER_TRAINING))
        {
            float accMean = intent.getFloatExtra("accMean", 0);
            float accVar = intent.getFloatExtra("accVar", 0);
            float accMCR = intent.getFloatExtra("accMCR", 0);
            String label = intent.getStringExtra("label");
            trainClassifier(accMean, accVar, accMCR, label);
        }
    }


    void trainClassifier(Float mean, Float var, Float MCR, String label)
    {
        Log.d(TAG,"trainClassifier with "+mean+" "+var+" "+MCR+" "+label);

        // TODO: Train the classifier here; include mean, variance, and MCR


    }



}
