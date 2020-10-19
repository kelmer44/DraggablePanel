package com.github.pedrovgs;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

public class SlideableDraggablePanel extends DraggablePanel {
    public SlideableDraggablePanel(Context context) {
        super(context);
    }

    public SlideableDraggablePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideableDraggablePanel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private int upMargin = 0;
    private int downMargin = 0;

    public void setMargins(int up, int down, boolean startUp) {
        upMargin = up;
        downMargin = down;
        if (startUp) {
            setTopFragmentMarginBottom(upMargin);
        } else {
            setTopFragmentMarginBottom(downMargin);
        }
    }

    public void slideUp() {
        setTopFragmentMarginBottom(upMargin);
        if (draggableView != null) {
            Log.i("BOTTONAV", "SmoothSliding");
            draggableView.smoothSlideTo(DraggableView.SLIDE_BOTTOM);
            draggableView.changeDragViewPosition();
        }
    }

    public void slideDown() {
        setTopFragmentMarginBottom(downMargin);
        if (draggableView != null) {
            Log.i("BOTTONAV", "SmoothSliding");
            draggableView.smoothSlideTo(DraggableView.SLIDE_BOTTOM);
            draggableView.changeDragViewPosition();
        }
    }
}
