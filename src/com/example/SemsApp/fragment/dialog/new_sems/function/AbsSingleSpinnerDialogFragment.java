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
public abstract class AbsSingleSpinnerDialogFragment extends AbsFunctionDialogFragment {

	protected Spinner machineNumberSpinner;

	private void assignAddedViewsInFixedView(View addedView) {
		machineNumberSpinner = (Spinner) addedView.findViewById(R.id.machineNumberSpinner);
		machineNumberSpinner.setAdapter(new MachineNumberArrayAdapter(getActivity(), 0));
	}

	@Override
	protected void modifyFixedView(LinearLayout fixedView) {
		View addedView = getActivity().getLayoutInflater().inflate(R.layout.machine_number_spinner_dialog_fixed_view, null, false);
		assignAddedViewsInFixedView(addedView);
		fixedView.addView(addedView);
	}

	@Override
	protected int getNegativeButtonTitleId() {
		return R.string.cancel;
	}
}

class MachineNumberArrayAdapter extends ArrayAdapter<NewSemsSmsSender.MachineNumber> {
	private Activity activity;

	public MachineNumberArrayAdapter(Activity activity, int resource) {
		super(activity, android.R.layout.simple_list_item_1);
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return NewSemsSmsSender.MachineNumber.values().length;
	}

	@Override
	public NewSemsSmsSender.MachineNumber getItem(int position) {
		return NewSemsSmsSender.MachineNumber.values()[position];
	}

	@Override
	public long getItemId(int position) {
		return (long)position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if ( convertView == null ) {
			convertView = activity.getLayoutInflater().inflate(R.layout.machine_number_spinner_item, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.machineNumberTextView);
		textView.setText(getItem(position).getDetailName());
		return convertView;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		if ( convertView == null ) {
			convertView = activity.getLayoutInflater().inflate(R.layout.machine_number_spinner_item, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.machineNumberTextView);
		textView.setText(getItem(position).getDetailName());
		return convertView;	}
}