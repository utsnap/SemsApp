package com.example.SemsApp.fragment.dialog.new_sems;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.SemsApp.R;
import com.example.SemsApp.utility.SmsSender;

/**
 * Created by Administrator on 2014-04-08.
 */
public class SetSensorAvailabilityDialogFragment extends AbsDoubleSpinnerDialogFragment {
	private Spinner availabilitySpinner;

	private void assignSpinner(View addedView) {
		availabilitySpinner = (Spinner) addedView.findViewById(R.id.availabilitySpinner);
	}


	@Override
	protected int getTitleStringId() {
		return R.string.setting_sensor_availability;
	}

	@Override
	protected void modifyVariableView(LinearLayout variableView) {
		View addedView = getActivity().getLayoutInflater().inflate(R.layout.sensor_availability_variable_view, null, false);
		assignSpinner(addedView);
		availabilitySpinner.setAdapter(new SensorAvailabilityArrayAdapter(getActivity(), 0));
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

class SensorAvailabilityArrayAdapter extends ArrayAdapter<SmsSender.Availability> {
	private Activity activity;

	public SensorAvailabilityArrayAdapter(Activity activity, int resource) {
		super(activity, 0);
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return SmsSender.Availability.values().length;
	}

	@Override
	public SmsSender.Availability getItem(int position) {
		return SmsSender.Availability.values()[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
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