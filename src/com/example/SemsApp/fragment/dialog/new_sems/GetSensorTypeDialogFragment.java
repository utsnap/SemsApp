package com.example.SemsApp.fragment.dialog.new_sems;

import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.example.SemsApp.R;
import com.example.SemsApp.utility.NewSemsSmsSender;

/**
 * Created by Administrator on 2014-04-08.
 */
public class GetSensorTypeDialogFragment extends AbsSingleSpinnerDialogFragment {
	@Override
	protected int getTitleStringId() {
		return R.string.getting_sensor_type;
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
		NewSemsSmsSender.MachineNumber machineNumber = ((ArrayAdapter<NewSemsSmsSender.MachineNumber>)machineNumberSpinner.getAdapter()).getItem(machineNumberSpinner.getSelectedItemPosition());
		NewSemsSmsSender.sendSms(newSemsPhoneNumber, NewSemsSmsSender.CommandCategory.GET, NewSemsSmsSender.CommandType.KIND, machineNumber.toString());
	}

	@Override
	protected void negativeButtonClicked(DialogInterface dialog, int which) {
		;
	}
}
