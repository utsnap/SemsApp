package com.example.SemsApp.fragment.dialog.new_sems;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		return dialog;
	}

	@Override
	protected int getTitleStringId() {
		return R.string.setting_emergency_contact;
	}

	@Override
	protected void modifyFixedView(LinearLayout fixedView) {
		View addedView = getActivity().getLayoutInflater().inflate(R.layout.emergency_contact_fixed_view, null, false);
		assignSpinnerEditText(addedView);
		orderSpinner.setAdapter(new EmergencyPhoneNumberOrderSpinnerAdapter(getActivity(), 0));
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
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(emergencyContackEditText.getWindowToken(), 0);

		String emergencyPhoneNumber = emergencyContackEditText.getText().toString();
		if ( emergencyPhoneNumber.equals("") ) {
			Toast.makeText(getActivity(), "전화번호를 입력해야 합니다.", Toast.LENGTH_SHORT).show();
			return;
		}
		SmsSender.Order order = ((ArrayAdapter<SmsSender.Order>)orderSpinner.getAdapter()).getItem(orderSpinner.getSelectedItemPosition());
		SmsSender.sendSms(newSemsPhoneNumber, SmsSender.CommandCategory.SET, order, emergencyPhoneNumber);
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

class EmergencyPhoneNumberOrderSpinnerAdapter extends ArrayAdapter<SmsSender.Order> {
	private Activity activity;

	public EmergencyPhoneNumberOrderSpinnerAdapter(Activity activity, int resource) {
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