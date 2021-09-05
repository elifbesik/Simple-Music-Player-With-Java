package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button play;
    SeekBar ilerlet;
    SeekBar volume;
    TextView ilk;
    TextView son;
    MediaPlayer mp;
    int totalTime;
    View view;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.play);
        ilk = (TextView) findViewById(R.id.ilk);
        son = (TextView) findViewById(R.id.son);

        //Media Player
        mp = MediaPlayer.create(this, R.raw.music);
        mp.setLooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();

        //İlertletme çubbuğu
        ilerlet = (SeekBar) findViewById(R.id.ilerlet);
        ilerlet.setMax(totalTime);
        ilerlet.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mp.seekTo(progress);
                            ilerlet.setProgress(progress);
                        }
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
        //Ses ayarı
        volume = (SeekBar) findViewById(R.id.volume);
        volume.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        float volumeNum = progress / 100f;
                        mp.setVolume(volumeNum, volumeNum);
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }

                );
    }
    public void playBtnClick(View view){
        if (!mp.isPlaying()) {
            // Durdurma
            mp.start();
            play.setBackgroundResource(R.drawable.pause);
        } else {
            //Playing
            mp.pause();
            play.setBackgroundResource(R.drawable.play);
        }
    }
}
