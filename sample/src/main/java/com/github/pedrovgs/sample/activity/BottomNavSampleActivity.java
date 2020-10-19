/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.SlideableDraggablePanel;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.fragment.MoviePosterFragment;
import com.github.pedrovgs.sample.view.HideableBottomNavView;

/**
 * Sample activity created to show a video from YouTube using a YouTubePlayer.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class BottomNavSampleActivity extends FragmentActivity {

    private static final String VIDEO_POSTER_THUMBNAIL =
            "https://4.bp.blogspot.com/-BT6IshdVsoA/UjfnTo_TkBI/AAAAAAAAMWk/JvDCYCoFRlQ/s1600/"
                    + "xmenDOFP.wobbly.1.jpg";
    private static final String SECOND_VIDEO_POSTER_THUMBNAIL =
            "https://media.comicbook.com/wp-content/uploads/2013/07/x-men-days-of-future-past"
                    + "-wolverine-poster.jpg";
    private static final String VIDEO_POSTER_TITLE = "X-Men: Days of Future Past";

    @BindView(R.id.draggable_panel)
    SlideableDraggablePanel draggablePanel;
    @BindView(R.id.bottom_nav)
    HideableBottomNavView bottomNavView;

    @OnClick(R.id.openvid)
    public void openVid() {
        Log.w("BOTTONAV","ISMINIMIZED?" + draggablePanel.isMinimized());
    }

    @OnClick(R.id.showbottomnav)
    public void showBottomNav() {
        bottomNavView.show();
        draggablePanel.slideUp();
    }

    @OnClick(R.id.hidebottomnav)
    public void hideBottomNav() {
        bottomNavView.hide();
        draggablePanel.slideDown();
    }

    @OnClick(R.id.movevidup)
    public void MoveVidUp() {
        draggablePanel.slideUp();
    }

    @OnClick(R.id.moveviddown)
    public void MoveVidDown() {
        draggablePanel.slideDown();
    }


    /**
     * Initialize the Activity with some injected data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnav_sample);
        ButterKnife.bind(this);
        initializeDraggablePanel();
        hookDraggablePanelListeners();
    }

    /**
     * Method triggered when the iv_thumbnail widget is clicked. This method maximize the
     * DraggablePanel.
     */
    @OnClick(R.id.iv_thumbnail)
    void onContainerClicked() {
        draggablePanel.maximize();
    }


    /**
     * Initialize and configure the DraggablePanel widget with two fragments and some attributes.
     */
    private void initializeDraggablePanel() {
        draggablePanel.setFragmentManager(getSupportFragmentManager());

        MoviePosterFragment topFragment = new MoviePosterFragment();
        topFragment.setPoster(SECOND_VIDEO_POSTER_THUMBNAIL);
        topFragment.setPosterTitle("Top Fragment");
        draggablePanel.setTopFragment(topFragment);


        MoviePosterFragment bottomFragment = new MoviePosterFragment();
        bottomFragment.setPoster(VIDEO_POSTER_THUMBNAIL);
        bottomFragment.setPosterTitle(VIDEO_POSTER_TITLE);
        draggablePanel.setBottomFragment(bottomFragment);


        draggablePanel.initializeView();


        draggablePanel.post(new Runnable() {
            @Override
            public void run() {
                int[] bottomNavLocation = new int[2];
                bottomNavView.getLocationOnScreen(bottomNavLocation);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                int upMargin = bottomNavView.getMeasuredHeight() + getResources().getDimensionPixelSize(R.dimen.single_spacing);
                int downMargin = getResources().getDimensionPixelSize(R.dimen.single_spacing);
                Log.v("BOTTONAV", "Bottom Nav height " + bottomNavView.getMeasuredHeight() + " upMargin=" + upMargin);
                Log.i("BOTTONAV", "Location of bottom nav: " + bottomNavLocation[0] + ", " + bottomNavLocation[1] + ", screen w=" + displayMetrics.widthPixels + ", " + "h=" + displayMetrics.heightPixels);
                draggablePanel.setMargins(upMargin, downMargin, true);
            }
        });

    }

    /**
     * Hook the DraggableListener to DraggablePanel to pause or resume the video when the
     * DragglabePanel is maximized or closed.
     */
    private void hookDraggablePanelListeners() {
        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {

            }

            @Override
            public void onMinimized() {
                //Empty
            }

            @Override
            public void onClosedToLeft() {
            }

            @Override
            public void onClosedToRight() {
            }
        });
    }

}
