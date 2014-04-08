package com.example.SemsApp.fragment.dialog.new_sems;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.SemsApp.R;
import com.example.SemsApp.utility.SmsSender;

/**
 * Created by Administrator on 2014-04-07.
 */
public class SetEmergencyContactDialogFragment extends AbsDialogFragment {
	private Spinner orderSpinner;
	private EditText emergencyContackEditText;

	private void assignSpinnerEditText(View addedView) {
		orderSpinner = (Spinner) addedView.findViewById(R.id.orderSpinner);
		emergencyContackEditText = (EditText) addedView.findViewById(R.id.emergencyContackEditText);
	}

	@Override
	protected int getTitleStringId() {
		return R.string.setting_emergency_contact;
	}

	@Override
	protected void modifyFixedView(LinearLayout fixedView) {
		View addedView = getActivity().getLayoutInflater().inflate(R.layout.emergency_contact_fixed_view, null, false);
		assignSpinnerEditText(addedView);
		orderSpinner.setAdapter(new OrderSpinnerAdapter(getActivity(), 0));
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

class OrderSpinnerAdapter extends ArrayAdapter<SmsSender.Order> {
	private Activity activity;

	public OrderSpinnerAdapter(Activity activity, int resource) {
		super(activity, 0);
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return SmsSender.Order.values().length;
	}

	@Override
	public SmsSender.Order getItem(int position) {
		return SmsSender.Order.values()[position];
	}

	@Override
	public long getItemId(int position) {
		return (long)position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if ( convertView == null ) {
			convertView = activity.getLayoutInflater().inflate(R.layout.order_spinner_item, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.orderTextView);
		textView.setText(getItem(position).getEmergencyContact());
		//Log.i("utsnap", convertView.toString());
		return convertView;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		if ( convertView == null ) {
			convertView = activity.getLayoutInflater().inflate(R.layout.order_spinner_item, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.orderTextView);
		textView.setText(getItem(position).getEmergencyContact());
		//Log.i("utsnap", convertView.toString());
		return convertView;
	}
}