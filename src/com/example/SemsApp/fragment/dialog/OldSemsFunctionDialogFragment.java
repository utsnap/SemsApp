package com.example.SemsApp.fragment.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.SemsApp.R;
import com.example.SemsApp.application.SemsApplication;
import com.example.SemsApp.data.OldSemsData;
import com.example.SemsApp.data.lab.DataLab;

/**
 * Created by Administrator on 14. 3. 20.
 * 옛날 샘스기계의 기능을 사용하기 위한 다이얼로그
 */
public class OldSemsFunctionDialogFragment extends AbsFunctionDialog {
	private Callbacks callbacks;

	@Override
	protected String getTitle() {
		return "Old Sems 기능";
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		callbacks = (Callbacks) getActivity();
	}

	@Override
	protected View getDetailView() {
		View view = getActivity().getLayoutInflater().inflate(R.layout.old_sems_function_dialog, null, false);

		Button button = (Button) view.findViewById(R.id.addDataButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DataLab<OldSemsData> dataDataLab = ((SemsApplication)getActivity().getApplicationContext()).dataLabEnumMap.get(SemsApplication.MachineType.OLD_SEMS);
				if ( dataDataLab.size() < 4 ) {
					//dataDataLab.add(OldSemsData.getInstance(dataDataLab.size() + 1, (dataDataLab.size() + 1) + "번째 데이터"));
					callbacks.dataLabChanged();
				}
			}
		});

		button = (Button) view.findViewById(R.id.removeDataButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DataLab<OldSemsData> dataDataLab = ((SemsApplication)getActivity().getApplicationContext()).dataLabEnumMap.get(SemsApplication.MachineType.OLD_SEMS);
				if ( !dataDataLab.isEmpty() ) {
					dataDataLab.remove(dataDataLab.size() - 1);
					callbacks.dataLabChanged();
				}
			}
		});

		return view;
	}

	@Override
	protected void setEventHandlers() {
		;
	}

	public interface Callbacks {
		void dataLabChanged();
	}
}