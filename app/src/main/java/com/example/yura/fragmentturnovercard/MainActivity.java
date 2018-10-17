package com.example.yura.fragmentturnovercard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private boolean mShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
        assert frameLayout != null;
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }
    }

    private void flipCard() {
        if (mShowingBack) {
            mShowingBack = false;
            getSupportFragmentManager().popBackStack();
        } else {
            mShowingBack = true;
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.animator.card_flip_right_enter,
                            R.animator.card_flip_right_exit,
                            R.animator.card_flip_left_enter,
                            R.animator.card_flip_left_exit)
                    .replace(R.id.container, new CardBackFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
