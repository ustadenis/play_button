package com.ssa.playbuton;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PlayButtonLayout extends RelativeLayout {

    private View mRootView;

    private PlayButtonState mState = PlayButtonState.STOPPED;

    private OnStateChanged mListener;

    @BindView(R.id.button)
    ImageButton mButton;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    public PlayButtonLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public PlayButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public PlayButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PlayButtonLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mRootView = inflate(context, R.layout.play_button_layout, this);
        ButterKnife.bind(this, mRootView);
        update();
    }

    @OnClick(R.id.button)
    void onButtonClicked() {
        switch (mState) {
            case PLAYING: {
                setState(PlayButtonState.STOPPED);
                if (mListener != null) {
                    mListener.onStopClicked();
                }
                break;
            }
            case STOPPED: {
                setState(PlayButtonState.PLAYING);
                if (mListener != null) {
                    mListener.onPlayClicked();
                }
                break;
            }
        }
    }

    public void setState(PlayButtonState state) {
        mState = state;
        update();
    }

    public void setStateChangedListener(OnStateChanged listener) {
        mListener = listener;
    }

    public void buffering(boolean buffering) {
        mProgressBar.setVisibility(buffering ? VISIBLE : GONE);
    }

    private void playing() {
        mButton.setImageResource(R.drawable.ic_pause_black_24dp);
    }

    private void stopped() {
        mButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
    }

    private void update() {
        switch (mState) {
            case PLAYING: {
                playing();
                break;
            }
            case STOPPED: {
                stopped();
                break;
            }
        }
    }

    public enum PlayButtonState {
        PLAYING,
        STOPPED
    }

    public interface OnStateChanged {
        void onPlayClicked();
        void onStopClicked();
    }
}
