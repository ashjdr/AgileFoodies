package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.app.NotificationManager;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import android.app.Fragment;
import android.support.v4.app.Fragment;

import com.google.android.gms.ads.AdView;

import java.util.Locale;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class TimerFragment extends Fragment {

    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    private Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_timer, container, false);





            mEditTextInput = v.findViewById(R.id.edit_text_input);
            mTextViewCountDown = v.findViewById(R.id.text_view_countdown);
            mButtonSet = v.findViewById(R.id.button_set);

            mButtonStartPause = v.findViewById(R.id.button_start_pause);
            mButtonReset = v.findViewById(R.id.button_reset);

            mButtonSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String input = mEditTextInput.getText().toString();
                    if (input.length() == 0) {
                        Toast.makeText(TimerFragment.this.getContext(), "Field can't be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    long millisInput = Long.parseLong(input) * 60000;
                    if (millisInput == 0) {
                        Toast.makeText(TimerFragment.this.getContext(), "Please enter a positive number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    setTime(millisInput);
                    mEditTextInput.setText("");
                }
            });

            mButtonStartPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTimerRunning) {
                        pauseTimer();
                    } else {
                        startTimer();
                    }
                }
            });

            mButtonReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTimer();
                }
            });

    return v;}
            






    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                mTimerRunning = false;
                playNotification();
                updateWatchInterface();



            }
        }.start();

        mTimerRunning = true;
        updateWatchInterface();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }

    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }

        mTextViewCountDown.setText(timeLeftFormatted);
    }


    private void playNotification(){
//        try {
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
//            r.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

//Define sound URI
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext())          //TODO: fix depreciated resource
                .setSmallIcon(R.drawable.timericon)
                .setContentTitle("Times Up")
                .setContentText("Time to check your recipe!")
                .setSound(soundUri); //This sets the sound to play

//Display notification
        assert notificationManager != null;
        notificationManager.notify(0, mBuilder.build());
    }


    @SuppressLint("SetTextI18n")
    private void updateWatchInterface() {
        if (mTimerRunning) {
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        } else {
            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");

            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }

            if (mTimeLeftInMillis < mStartTimeInMillis) {
                mButtonReset.setVisibility(View.VISIBLE);
            } else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }}


@Override
    public void onStop() {
        super.onStop();

        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);

        editor.apply();

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false);

        updateCountDownText();
        updateWatchInterface();

        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }
        }
        }}





