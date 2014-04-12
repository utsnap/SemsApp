package com.example.SemsApp.fragment.dialog.new_sems;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.SemsApp.R;
import com.example.SemsApp.utility.SmsSender;

/**
 * Created by Administrator on 2014-04-08.
 */
public class SetWaringRangeDialogFragment extends AbsDoubleSpinnerDialogFragment {
	private EditText maxTemperatureEditText;
	private EditText minTemperatureEditText;

	private void assignEditTexts(View addedView) {
		maxTemperatureEditText = (EditText) addedView.findViewById(R.id.maxTemperatureEditText);
		minTemperatureEditText = (EditText) addedView.findViewById(R.id.minTemperatureEditText);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		return dialog;
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
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(maxTemperatureEditText.getWindowToken(), 0);
		imm.hideSoftInputFromWindow(minTemperatureEditText.getWindowToken(), 0);

		String maxTemp = maxTemperatureEditText.getText().toString();
		if ( maxTemp.equals("") ) {
			Toast.makeText(getActivity(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
			return;
		}
		if ( maxTemp.length() < 2 ) {
			maxTemp = "0" + maxTemp;
		}
		String minTemp = minTemperatureEditText.getText().toString();
		if ( minTemp.equals("") ) {
			Toast.makeText(getActivity(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
			return;
		}
		if ( minTemp.length() < 2 ) {
			minTemp = "0" + minTemp;
		}
		SmsSender.MachineNumber machineNumber = ((ArrayAdapter<SmsSender.MachineNumber>)machineNumberSpinner.getAdapter()).getItem(machineNumberSpinner.getSelectedItemPosition());
		SmsSender.SensorNumber sensorNumber = ((ArrayAdapter<SmsSender.SensorNumber>)sensorNumberSpinner.getAdapter()).getItem(sensorNumberSpinner.getSelectedItemPosition());
		SmsSender.sendSms(newSemsPhoneNumber, SmsSender.CommandCategory.SET, SmsSender.CommandType.LIMT, machineNumber, sensorNumber, maxTemp + minTemp);
	}

	@Override
	protected void negativeButtonClicked(DialogInterface dialog, int which) {
		;
	}
}
