package com.ssa.playbuton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.playing_button)
    PlayButtonLayout mPlayButtonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buffer_on)
    void bufferOn() {
        mPlayButtonLayout.buffering(true);
    }

    @OnClick(R.id.buffer_off)
    void bufferOff() {
        mPlayButtonLayout.buffering(false);
    }
}
