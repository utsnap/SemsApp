package com.example.SemsApp.fragment.dialog.new_sems.function;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.SemsApp.R;
import com.example.SemsApp.utility.NewSemsSmsSender;

/**
 * Created by Administrator on 2014-04-07.
 */
public abstract class AbsDoubleSpinnerDialogFragment extends AbsFunctionDialogFragment {
	protected Spinner machineNumberSpinner;
	protected Spinner sensorNumberSpinner;

	private void assignMachineNumberSpinner(View addedView) {
		machineNumberSpinner = (Spinner) addedView.findViewById(R.id.machineNumberSpinner);
	}

	private void assignSensorNumberSpinner(View addedView) {
		sensorNumberSpinner = (Spinner) addedView.findViewById(R.id.sensorNumberSpinner);
	}

	@Override
	protected void modifyFixedView(LinearLayout fixedView) {
		fixedView.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		p1.weight=1.0f;
		View addedView = getActivity().getLayoutInflater().inflate(R.layout.machine_number_spinner_dialog_fixed_view, null, false);
		assignMachineNumberSpinner(addedView);
		machineNumberSpinner.setAdapter(new MachineNumberArrayAdapter(getActivity(), 0));
		fixedView.addView(addedView, p1);
		addedView = getActivity().getLayoutInflater().inflate(R.layout.sensor_number_spinner_dialog_fixed_view, null, false);
		assignSensorNumberSpinner(addedView);
		sensorNumberSpinner.setAdapter(new SensorNumberArrayAdapter(getActivity(), 0));
		fixedView.addView(addedView, p1);
	}

	@Override
	protected int getNegativeButtonTitleId() {
		return R.string.cancel;
	}
}

class SensorNumberArrayAdapter extends ArrayAdapter<NewSemsSmsSender.SensorNumber> {
	private Activity activity;

	public SensorNumberArrayAdapter(Activity activity, int resource) {
		super(activity, 0);
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return NewSemsSmsSender.SensorNumber.values().length;
	}

	@Override
	public NewSemsSmsSender.SensorNumber getItem(int position) {
		return NewSemsSmsSender.SensorNumber.values()[position];
	}

	@Override
	public long getItemId(int position) {
		return (long)position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if ( convertView == null ) {
			convertView = activity.getLayoutInflater().inflate(R.layout.sensor_number_spinner_item, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.sensorNumberTextView);
		textView.setText(getItem(position).getDetailName());
		return convertView;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		if ( convertView == null ) {
			convertView = activity.getLayoutInflater().inflate(R.layout.sensor_number_spinner_item, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.sensorNumberTextView);
		textView.setText(getItem(position).getDetailName());
		return convertView;	}
}
