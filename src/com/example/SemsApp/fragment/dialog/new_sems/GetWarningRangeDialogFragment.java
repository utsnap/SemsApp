package com.example.SemsApp.fragment.dialog.new_sems;

import android.content.DialogInterface;
import android.widget.LinearLayout;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 2014-04-08.
 */
public class GetWarningRangeDialogFragment extends AbsSingleSpinnerDialogFragment {
	@Override
	protected int getTitleStringId() {
		return R.string.getting_warning_range;
	}

	@Override
	protected void modifyVariableView(LinearLayout variableView) {
		;
	}

	@Override
	protected int getPositiveButtonTitleId() {
		return R.string.lockup;
	}

	@Override
	protected void positiveButtonClicked(DialogInterface dialog, int which) {
		;
	}

	@Override
	protected void negativeButtonClicked(DialogInterface dialog, int which) {
		;
	}
}