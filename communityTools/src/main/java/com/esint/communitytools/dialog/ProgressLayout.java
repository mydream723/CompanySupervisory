package com.esint.communitytools.dialog;

import com.esint.communitytools.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

class ProgressLayout extends FrameLayout {
	private Context context;
	private static final int DEFAULT_COUNT = 5;
	private int spotsCount;

	public ProgressLayout(Context context) {
		this(context, null);
		this.context = context;
	}

	public ProgressLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		this.context = context;
	}

	public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs, defStyleAttr, 0);
		this.context = context;
	}

	private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.Dialog, defStyleAttr,
				defStyleRes);

		spotsCount = a.getInt(R.styleable.Dialog_DialogSpotCount, DEFAULT_COUNT);
		a.recycle();
	}

	public int getSpotsCount() {
		return spotsCount;
	}
}
