package com.example.SemsApp.fragment.dialog.new_sems;

import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 2014-04-07.
 */
public class SetRegularSmsTimePickerDialogFragment extends AbsDialogFragment {
	protected TimePicker timePicker;

	private void assignTimePicker(View addedView) {
		timePicker = (TimePicker) addedView.findViewById(R.id.timePicker);
	}


	@Override
	protected int getTitleStringId() {
		return R.string.setting_regular_sms_sending_time;
	}

	@Override
	protected void modifyFixedView(LinearLayout fixedView) {
		View addedView = getActivity().getLayoutInflater().inflate(R.layout.time_picker_dialog_fixed_view, null, false);
		assignTimePicker(addedView);
		fixedView.addView(addedView);
	}

	@Override
	protected void modifyVariableView(LinearLayout variableView) {
		;
	}

	@Override
	protected int getPositiveButtonTitleId() {
		return R.string.setting;
	}

	@Override
	protected void positiveButtonClicked(DialogInterface dialog, int which) {
		;
	}

	@Override
	protected int getNegativeButtonTitleId() {
		return R.string.cancel;
	}

	@Override
	protected void negativeButtonClicked(DialogInterface dialog, int which) {
		;
	}
}
