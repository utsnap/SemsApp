package com.example.SemsApp.fragment.dialog.new_sems.simple;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import com.example.SemsApp.data.new_sems.AbsNewSemsData;

/**
 * Created by vaio-pc on 2014-04-14.
 */
public abstract class AbsSimpleDialogFragment extends DialogFragment {
	protected AbsNewSemsData absNewSemsData;

	protected AbsSimpleDialogFragment(AbsNewSemsData absNewSemsData) {
		this.absNewSemsData = absNewSemsData;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return super.onCreateDialog(savedInstanceState);
	}
}
