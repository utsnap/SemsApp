package com.example.SemsApp.fragment.dialog.new_sems;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 2014-04-08.
 */
public class SetWaringRangeDialogFragment extends AbsDoubleSpinnerDialogFragment {
	private EditText maxTemperature;
	private EditText minTemperature;

	private void assignEditTexts(View addedView) {
		maxTemperature = (EditText) addedView.findViewById(R.id.maxTemperature);
		minTemperature = (EditText) addedView.findViewById(R.id.minTemperature);
	}


	@Override
	protected int getTitleStringId() {
		return R.string.setting_warning_range;
	}

	@Override
	protected void modifyVariableView(LinearLayout variableView) {
		View addedView = getActivity().getLayoutInflater().inflate(R.layout.warning_range_variable_view, null, false);
		assignEditTexts(addedView);
		variableView.addView(addedView);
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
	protected void negativeButtonClicked(DialogInterface dialog, int which) {
		;
	}
}
