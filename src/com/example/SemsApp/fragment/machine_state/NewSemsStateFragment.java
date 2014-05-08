package com.example.SemsApp.fragment.machine_state;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.SemsApp.R;
import com.example.SemsApp.data.NewSemsData;
import com.example.SemsApp.data.new_sems.NewSemsInfoData;
import com.example.SemsApp.utility.NewSemsSmsSender;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Administrator on 14. 3. 20.
 * 신버전 샘스기계의 정보를 보여주는 프래그먼트
 */
public class NewSemsStateFragment extends Fragment {
	private static final String EXTRA_NEW_SEMS_DATA = "EXTRA_NEW_SEMS_DATA";

	private NewSemsData newSemsData;
	private NewSemsInfoData newSemsInfoData;

	private EnumMap<NewSemsSmsSender.SensorNumber, TextView> sensorTypeTextViewEnumMap;
	private EnumMap<NewSemsSmsSender.SensorNumber, TextView> sensorValueTextViewEnumMap;

	private LinearLayout SamsDataTable;
	private TableRow lableHeader;
	private TextView machineNo;
	private TableRow dataTable;
	private TableRow sensor1LabelTable;
	private TextView sensor1Name;
	private TableRow sensor1DataTable;
	private TextView sensor1Value;
	private TextView sensor1Type;
	private TableRow sensor3LabelTable;
	private TextView sensor3Name;
	private TableRow sensor3DataTable;
	private TextView sensor3Value;
	private TextView sensor3Type;
	private TableRow sensor5LabelTable;
	private TextView sensor5Name;
	private TableRow sensor5DataTable;
	private TextView sensor5Value;
	private TextView sensor5Type;
	private TableRow sensor7LabelTable;
	private TextView sensor7Name;
	private TableRow sensor7DataTable;
	private TextView sensor7Value;
	private TextView sensor7Type;
	private TableRow sensor2LabelTable;
	private TextView sensor2Name;
	private TableRow sensor2DataTable;
	private TextView sensor2Value;
	private TextView sensor2Type;
	private TableRow sensor4LabelTable;
	private TextView sensor4Name;
	private TableRow sensor4DataTable;
	private TextView sensor4Value;
	private TextView sensor4Type;
	private TableRow sensor6LabelTable;
	private TextView sensor6Name;
	private TableRow sensor6DataTable;
	private TextView sensor6Value;
	private TextView sensor6Type;
	private TableRow sensor8LabelTable;
	private TextView sensor8Name;
	private TableRow sensor8DataTable;
	private TextView sensor8Value;
	private TextView sensor8Type;

	private void assignViews(View view) {
		SamsDataTable = (LinearLayout) view.findViewById(R.id.Sams_data_table);
		lableHeader = (TableRow) view.findViewById(R.id.lable_Header);
		machineNo = (TextView) view.findViewById(R.id.machineNo);
		dataTable = (TableRow) view.findViewById(R.id.dataTable);
		sensor1LabelTable = (TableRow) view.findViewById(R.id.sensor1_Label_Table);
		sensor1Name = (TextView) view.findViewById(R.id.sensor1_Name);
		sensor1DataTable = (TableRow) view.findViewById(R.id.sensor1_Data_Table);
		sensor1Value = (TextView) view.findViewById(R.id.sensor1_Value);
		sensor1Type = (TextView) view.findViewById(R.id.sensor1_Type);
		sensor3LabelTable = (TableRow) view.findViewById(R.id.sensor3_Label_Table);
		sensor3Name = (TextView) view.findViewById(R.id.sensor3_Name);
		sensor3DataTable = (TableRow) view.findViewById(R.id.sensor3_Data_Table);
		sensor3Value = (TextView) view.findViewById(R.id.sensor3_Value);
		sensor3Type = (TextView) view.findViewById(R.id.sensor3_Type);
		sensor5LabelTable = (TableRow) view.findViewById(R.id.sensor5_Label_Table);
		sensor5Name = (TextView) view.findViewById(R.id.sensor5_Name);
		sensor5DataTable = (TableRow) view.findViewById(R.id.sensor5_Data_Table);
		sensor5Value = (TextView) view.findViewById(R.id.sensor5_Value);
		sensor5Type = (TextView) view.findViewById(R.id.sensor5_Type);
		sensor7LabelTable = (TableRow) view.findViewById(R.id.sensor7_Label_Table);
		sensor7Name = (TextView) view.findViewById(R.id.sensor7_Name);
		sensor7DataTable = (TableRow) view.findViewById(R.id.sensor7_Data_Table);
		sensor7Value = (TextView) view.findViewById(R.id.sensor7_Value);
		sensor7Type = (TextView) view.findViewById(R.id.sensor7_Type);
		sensor2LabelTable = (TableRow) view.findViewById(R.id.sensor2_Label_Table);
		sensor2Name = (TextView) view.findViewById(R.id.sensor2_Name);
		sensor2DataTable = (TableRow) view.findViewById(R.id.sensor2_Data_Table);
		sensor2Value = (TextView) view.findViewById(R.id.sensor2_Value);
		sensor2Type = (TextView) view.findViewById(R.id.sensor2_Type);
		sensor4LabelTable = (TableRow) view.findViewById(R.id.sensor4_Label_Table);
		sensor4Name = (TextView) view.findViewById(R.id.sensor4_Name);
		sensor4DataTable = (TableRow) view.findViewById(R.id.sensor4_Data_Table);
		sensor4Value = (TextView) view.findViewById(R.id.sensor4_Value);
		sensor4Type = (TextView) view.findViewById(R.id.sensor4_Type);
		sensor6LabelTable = (TableRow) view.findViewById(R.id.sensor6_Label_Table);
		sensor6Name = (TextView) view.findViewById(R.id.sensor6_Name);
		sensor6DataTable = (TableRow) view.findViewById(R.id.sensor6_Data_Table);
		sensor6Value = (TextView) view.findViewById(R.id.sensor6_Value);
		sensor6Type = (TextView) view.findViewById(R.id.sensor6_Type);
		sensor8LabelTable = (TableRow) view.findViewById(R.id.sensor8_Label_Table);
		sensor8Name = (TextView) view.findViewById(R.id.sensor8_Name);
		sensor8DataTable = (TableRow) view.findViewById(R.id.sensor8_Data_Table);
		sensor8Value = (TextView) view.findViewById(R.id.sensor8_Value);
		sensor8Type = (TextView) view.findViewById(R.id.sensor8_Type);

		sensorTypeTextViewEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, TextView>(NewSemsSmsSender.SensorNumber.class);
		sensorTypeTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._1, sensor1Type);
		sensorTypeTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._2, sensor2Type);
		sensorTypeTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._3, sensor3Type);
		sensorTypeTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._4, sensor4Type);
		sensorTypeTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._5, sensor5Type);
		sensorTypeTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._6, sensor6Type);
		sensorTypeTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._7, sensor7Type);
		sensorTypeTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._8, sensor8Type);

		sensorValueTextViewEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, TextView>(NewSemsSmsSender.SensorNumber.class);
		sensorValueTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._1, sensor1Value);
		sensorValueTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._2, sensor2Value);
		sensorValueTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._3, sensor3Value);
		sensorValueTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._4, sensor4Value);
		sensorValueTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._5, sensor5Value);
		sensorValueTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._6, sensor6Value);
		sensorValueTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._7, sensor7Value);
		sensorValueTextViewEnumMap.put(NewSemsSmsSender.SensorNumber._8, sensor8Value);
	}


	public static final NewSemsStateFragment newInstance(NewSemsData newSemsData) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_NEW_SEMS_DATA, newSemsData);

		NewSemsStateFragment newSemsStateFragment = new NewSemsStateFragment();
		newSemsStateFragment.setArguments(bundle);

		return newSemsStateFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		newSemsData = (NewSemsData) getArguments().getSerializable(EXTRA_NEW_SEMS_DATA);
		newSemsInfoData = newSemsData.newSemsInfoData;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.new_sems_state_fragment, null);
		assignViews(view);
		assignAttribute();
		return view;
	}

	private void assignAttribute() {
		machineNo.setText(newSemsInfoData.machineNumber.getDetailName());
		for (NewSemsSmsSender.SensorNumber sensorNumber : NewSemsSmsSender.SensorNumber.values()) {
			sensorTypeTextViewEnumMap.get(sensorNumber).setText(newSemsInfoData.sensorTypeEnumMap.get(sensorNumber).getSummary());
			sensorValueTextViewEnumMap.get(sensorNumber).setText(newSemsInfoData.stringValueEnumMap.get(sensorNumber));
		}
	}
}