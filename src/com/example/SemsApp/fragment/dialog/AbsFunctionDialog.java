package com.example.SemsApp.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 14. 3. 22.
 */
public abstract class AbsFunctionDialog extends DialogFragment {
	protected View detailView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detailView = getDetailView();
		setEventHandlers();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(getTitle())
			.setView(detailView)
			.setNegativeButton("취소", null);
		return builder.create();
	}

	protected abstract String getTitle();

	protected abstract View getDetailView();

	protected abstract void setEventHandlers();
}