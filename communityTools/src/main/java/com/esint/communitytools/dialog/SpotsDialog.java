package com.esint.communitytools.dialog;

import com.esint.communitytools.R;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class SpotsDialog extends AlertDialog {
	private Context context;
	private static final int DELAY = 150;
	private static final int DURATION = 1500;

	private int size;
	private AnimatedView[] spots;
	private AnimatorPlayer animator;
	private TextView showTextView;
	private String showContent;
	// private SpotsDialogListener listener;

	public SpotsDialog(Context context, String showContent) {
		this(context);
		this.context = context;
		this.showContent = showContent;
		// listener = (SpotsDialogListener) context;
	}

	public SpotsDialog(Context context) {
		this(context, R.style.SpotsDialogDefault);
		this.context = context;
	}

	public SpotsDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public SpotsDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setContentView(R.layout.dialog_spot);

		setContentView(R.layout.dialog_spot);

		setCanceledOnTouchOutside(false);

		initProgress();
	}

	@Override
	protected void onStart() {
		super.onStart();

		animator = new AnimatorPlayer(createAnimations());
		animator.play();
	}

	@Override
	protected void onStop() {
		super.onStop();
		// listener.alertStop();
		animator.stop();

	}

	private void initProgress() {
		showTextView = (TextView) findViewById(R.id.dialog_show);
		showTextView.setText(showContent);

		ProgressLayout progress = (ProgressLayout) findViewById(R.id.dialog_progress);
		size = progress.getSpotsCount();

		spots = new AnimatedView[size];
		int size = getContext().getResources().getDimensionPixelSize(R.dimen.dialog_spot_size);
		int progressWidth = getContext().getResources().getDimensionPixelSize(R.dimen.dialog_progress_width);
		for (int i = 0; i < spots.length; i++) {
			AnimatedView v = new AnimatedView(getContext());
			v.setBackgroundResource(R.drawable.spot_dialogspot);
			v.setTarget(progressWidth);
			v.setXFactor(-1f);
			progress.addView(v, size, size);
			spots[i] = v;
		}
	}

	private Animator[] createAnimations() {
		Animator[] animators = new Animator[size];
		for (int i = 0; i < spots.length; i++) {
			Animator move = ObjectAnimator.ofFloat(spots[i], "xFactor", 0, 1);
			move.setDuration(DURATION);
			move.setInterpolator(new HesitateInterpolator());
			move.setStartDelay(DELAY * i);
			animators[i] = move;
		}
		return animators;
	}

	public interface SpotsDialogListener {
		public void alertStop();
	}

}
