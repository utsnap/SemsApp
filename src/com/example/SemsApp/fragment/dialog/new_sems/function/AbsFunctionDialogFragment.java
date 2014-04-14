package com.example.SemsApp.fragment.dialog.new_sems.function;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 2014-04-07.
 */
public abstract class AbsFunctionDialogFragment extends DialogFragment {
	public static final String EXTRA_NEW_SEMS_PHONE_NUMBER = "extra_new_sems_phone_number";

	private LinearLayout fixedView;
	private LinearLayout variableView;
	private View customView;
	protected String newSemsPhoneNumber;

	private void assignCustomViews(View view) {
		fixedView = (LinearLayout) view.findViewById(R.id.fixed_view);
		variableView = (LinearLayout) view.findViewById(R.id.variable_view);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		newSemsPhoneNumber = getArguments().getString(EXTRA_NEW_SEMS_PHONE_NUMBER, "");
		customView = getActivity().getLayoutInflater().inflate(R.layout.abs_dialog_custom_view, null, false);
		assignCustomViews(customView);
		modifyFixedView(fixedView);
		modifyVariableView(variableView);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder
			.setTitle(getTitleStringId())
			.setView(customView)
			.setPositiveButton(getPositiveButtonTitleId(), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					positiveButtonClicked(dialog, which);
				}
			})
			.setNegativeButton(getNegativeButtonTitleId(), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					negativeButtonClicked(dialog, which);
				}
			});
		return builder.create();
	}

	/**
	 * 다이얼로그의 타이들에 해당하는 string
	 * */
	protected abstract int getTitleStringId();

	/**
	 * fixed view는 리니어 레이아웃이다.
	 * 추후에 여기에 적당한 뷰들을 추가한다.
	 * 또한 뷰에 해당하는 이벤트핸들러도 추가한다.
	 * */
	protected abstract void modifyFixedView(LinearLayout fixedView);

	/**
	 * variable view는 리니어 레이아웃이다.
	 * 추후에 여기에 적당한 뷰들을 추가한다.
	 * 또한 뷰에 해당하는 이벤트핸들러도 추가한다.
	 * */
	protected abstract void modifyVariableView(LinearLayout variableView);

	/**
	 * positive button에 보여질 string id를 반환한다.
	 * */
	protected abstract int getPositiveButtonTitleId();

	/**
	 * positive button이 눌렸을 때 수행하는 작업을 정의한다.
	 * */
	protected abstract void positiveButtonClicked(DialogInterface dialog, int which);

	/**
	 * negative button에 보여질 string id를 반환한다.
	 * */
	protected abstract int getNegativeButtonTitleId();

	/**
	 * negative button이 눌렸을 때 수행하는 작업을 정의한다.
	 * */
	protected abstract void negativeButtonClicked(DialogInterface dialog, int which);
}