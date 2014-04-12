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
public class SetRegularSmsTimePickerDialogFragment extends AbsDialogFragment {
	protected TimePicker timePicker;
	protected Spinner orderSpinner;

	private void assignTimePicker(View addedView) {
		timePicker = (TimePicker) addedView.findViewById(R.id.timePicker);
	}

	@Override
	protected int getTitleStringId() {
		return R.string.setting_regular_sms_sending_time;
	}

	@Override
	protected void modifyFixedView(LinearLayout fixedView) {
		orderSpinner = new Spinner(getActivity());
		orderSpinner.setId(R.id.orderSpinner);
		orderSpinner.setAdapter(new RegularSmsTimeOrderArrayAdapter(getActivity(), 0));
		fixedView.setOrientation(LinearLayout.VERTICAL);
		fixedView.addView(orderSpinner);
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
		SmsSender.Order order = ((ArrayAdapter<SmsSender.Order>)orderSpinner.getAdapter()).getItem(orderSpinner.getSelectedItemPosition());
		String hour = timePicker.getCurrentHour().toString();
		if ( hour.length() == 1 ) {
			hour = "0" + hour;
		}
		String minute = timePicker.getCurrentMinute().toString();
		if ( minute.length() == 1 ) {
			minute = "0" + minute;
		}
		SmsSender.sendSms(newSemsPhoneNumber, SmsSender.CommandCategory.SET, SmsSender.CommandType.TIME, order, hour + minute);
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

class RegularSmsTimeOrderArrayAdapter extends ArrayAdapter<SmsSender.Order> {
	private Activity activity;

	public RegularSmsTimeOrderArrayAdapter(Activity activity, int resource) {
		super(activity, 0);
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return SmsSender.Order.values().length - 3;
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
		textView.setText(getItem(position).getSmsSendingTime());
		//Log.i("utsnap", convertView.toString());
		return convertView;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		if ( convertView == null ) {
			convertView = activity.getLayoutInflater().inflate(R.layout.order_spinner_item, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.orderTextView);
		textView.setText(getItem(position).getSmsSendingTime());
		//Log.i("utsnap", convertView.toString());
		return convertView;
	}
}
