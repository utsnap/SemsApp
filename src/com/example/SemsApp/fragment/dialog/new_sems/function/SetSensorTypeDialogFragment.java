package com.example.SemsApp.fragment.dialog.new_sems.function;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.SemsApp.R;
import com.example.SemsApp.utility.NewSemsSmsSender;

/**
 * Created by Administrator on 2014-04-08.
 */
public class SetSensorTypeDialogFragment extends AbsDoubleSpinnerDialogFragment {
	private Spinner sensorTypeSpinner;

	private void assignSpinner(View addedView) {
		sensorTypeSpinner = (Spinner)addedView.findViewById(R.id.sensorTypeSpinner);
	}


	@Override
	protected int getTitleStringId() {
		return R.string.setting_sensor_type;
	}

	@Override
	protected void modifyVariableView(LinearLayout variableView) {
		View addedView = getActivity().getLayoutInflater().inflate(R.layout.sensor_type_variable_view, null, false);
		assignSpinner(addedView);
		sensorTypeSpinner.setAdapter(new SensorTypeArrayAdapter(getActivity(), 0));
		variableView.addView(addedView);
	}

	@Override
	protected int getPositiveButtonTitleId() {
		return R.string.setting;
	}

	@Override
	protected void positiveButtonClicked(DialogInterface dialog, int which) {
		NewSemsSmsSender.MachineNumber machineNumber = ((ArrayAdapter<NewSemsSmsSender.MachineNumber>)machineNumberSpinner.getAdapter()).getItem(machineNumberSpinner.getSelectedItemPosition());
		NewSemsSmsSender.SensorNumber sensorNumber = ((ArrayAdapter<NewSemsSmsSender.SensorNumber>)sensorNumberSpinner.getAdapter()).getItem(sensorNumberSpinner.getSelectedItemPosition());
		NewSemsSmsSender.SensorType sensorType = ((ArrayAdapter<NewSemsSmsSender.SensorType>)sensorTypeSpinner.getAdapter()).getItem(sensorTypeSpinner.getSelectedItemPosition());
		NewSemsSmsSender.sendSms(newSemsPhoneNumber, NewSemsSmsSender.CommandCategory.SET, NewSemsSmsSender.CommandType.KIND, machineNumber, sensorNumber, sensorType.toString());
	}

	@Override
	protected void negativeButtonClicked(DialogInterface dialog, int which) {
		;
	}
}

class SensorTypeArrayAdapter extends ArrayAdapter<NewSemsSmsSender.SensorType> {
	private Activity activity;

	public SensorTypeArrayAdapter(Activity activity, int resource) {
		super(activity, 0);
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return NewSemsSmsSender.SensorType.values().length;
	}

	@Override
	public NewSemsSmsSender.SensorType getItem(int position) {
		return NewSemsSmsSender.SensorType.values()[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if ( convertView == null ) {
			convertView = activity.getLayoutInflater().inflate(R.layout.order_spinner_item, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.orderTextView);
		textView.setText(getItem(position).getSummary());
		return convertView;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		if ( convertView == null ) {
			convertView = activity.getLayoutInflater().inflate(R.layout.order_spinner_item, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.orderTextView);
		textView.setText(getItem(position).getSummary());
		return convertView;
	}
}