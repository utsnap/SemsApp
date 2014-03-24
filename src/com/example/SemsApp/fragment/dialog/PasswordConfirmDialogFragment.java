package com.example.SemsApp.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 14. 3. 20.
 * 각 기계의 기능을 수행하기에앞서, 안전을 위해 비밀번호를 확인하는 다이얼로그
 * 이 다이얼로그는 프레그먼트 수준에서 관리한다.
 */
public class PasswordConfirmDialogFragment extends DialogFragment {
	private static final String EXTRA_PASSWORD = "extra_password";
	private static final String EXTRA_VIEW_PAGER_ID = "extra_view_pager_id";

	private String password;
	private int viewPagerId;

	public static PasswordConfirmDialogFragment newInstance(String password, int viewpagerId) {
		Bundle bundle = new Bundle();
		bundle.putString(EXTRA_PASSWORD, password);
		bundle.putInt(EXTRA_VIEW_PAGER_ID, viewpagerId);

		PasswordConfirmDialogFragment passwordConfirmDialogFragment = new PasswordConfirmDialogFragment();
		passwordConfirmDialogFragment.setArguments(bundle);
		return passwordConfirmDialogFragment;
	}

	private PasswordConfirmDialogFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle bundle = getArguments();
		password = bundle.getString(EXTRA_PASSWORD);
		viewPagerId = bundle.getInt(EXTRA_VIEW_PAGER_ID);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final EditText editText = new EditText(getActivity());
		editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
		editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		editText.setLines(1);
		editText.setRawInputType(InputType.TYPE_CLASS_NUMBER);

		builder.setTitle("비빌번호를 입력하세요 : " + password)
			.setView(editText)
			.setPositiveButton("확인", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String inputPassword = editText.getText().toString();
					if (password.equals(inputPassword)) {
						Log.i("utsnap", "비빌번호 일치");
						InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
						//필수 : 기계의 기능을 수행하는 다이얼로그를 띄운다. 뷰페이저의 id를 참조하여 알맞는 기계의 기능 다이얼로그를 띄운다.
						switch (viewPagerId) {
							case R.id.oldSemsViewPager:
								new OldSemsFunctionDialogFragment().show(getFragmentManager(), "");
								break;
							case R.id.newSemsViewPager:
								new NewSemsFunctionDialogFragment().show(getFragmentManager(), "");
								break;
							case R.id.ledViewPager:
							new LedFunctionDialogFragment().show(getFragmentManager(), "");
								break;
							case R.id.carbonViewPager:
								new CarbonFunctionDialogFragment().show(getFragmentManager(), "");
								break;
						}
					} else {
						Log.i("utsnap", "비빌번호 틀림");
					}
				}
			})
			.setNegativeButton("취소", null);
		AlertDialog dialog = builder.create();
		dialog.getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		return dialog;
	}
}