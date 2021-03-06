package com.example.SemsApp.fragment.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.SemsApp.R;
import com.example.SemsApp.fragment.dialog.new_sems.function.*;
import com.example.SemsApp.preference.PreferenceKeys;
import com.example.SemsApp.utility.NewSemsSmsSender;

import java.util.EnumMap;

/**
 * Created by Administrator on 14. 3. 20.
 * 새로운 샘스기계를 사용하기 위한 다이얼로그
 */
public class NewSemsFunctionDialogFragment
	extends AbsFunctionDialog
	implements ExpandableListView.OnGroupClickListener
	, ExpandableListView.OnChildClickListener
	, ExpandableListView.OnGroupExpandListener
	, ExpandableListView.OnGroupCollapseListener {

	private ExpandableListView commandExpandableListView;
	private EnumMap<NewSemsSmsSender.CommandCategory, NewSemsSmsSender.CommandType[]> commandTypesEnumMap;

	private void assignExpandableListView(View view) {
		commandExpandableListView = (ExpandableListView) view.findViewById(R.id.commandExpandableListView);
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return super.onCreateDialog(savedInstanceState);
	}

	@Override
	protected String getTitle() {
		return "New Sems 기능";
	}

	@Override
	protected View getDetailView() {
		View view = getActivity().getLayoutInflater().inflate(R.layout.new_sems_function_dialog, null, false);
		assignExpandableListView(view);
		commandExpandableListView.setAdapter(new CustomExpandableListAdapter(getActivity()));
		commandExpandableListView.setOnGroupClickListener(this);
		commandExpandableListView.setOnChildClickListener(this);
		commandExpandableListView.setOnGroupExpandListener(this);
		commandExpandableListView.setOnGroupCollapseListener(this);
		return view;
	}

	@Override
	protected void setEventHandlers() {
		;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		String newSemsPhonNumber = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(PreferenceKeys.NEW_SEMS_PHONE_NUMBER, "");
		if ( newSemsPhonNumber.equals("") ) {
			Toast.makeText(getActivity(), "전화번호를 설정하세요..", Toast.LENGTH_SHORT).show();
			return false;
		}
		DialogFragment dialogFragment = null;
		NewSemsSmsSender.CommandCategory commandCategory = NewSemsSmsSender.CommandCategory.getValueOf(groupPosition);
		if ( commandCategory == NewSemsSmsSender.CommandCategory.SET ) {
			NewSemsSmsSender.CommandType commandType = NewSemsSmsSender.CommandType.getValueOf(childPosition);
			switch ( commandType ) {
				case EMPTY:
					break;
				case NUM: dialogFragment = new SetEmergencyContactDialogFragment();
					break;
				case TIME: dialogFragment = new SetRegularSmsTimePickerDialogFragment();
					break;
				case LIMT: dialogFragment = new SetWaringRangeDialogFragment();
					break;
				case KIND: dialogFragment = new SetSensorTypeDialogFragment();
					break;
				case USE: dialogFragment = new SetSensorAvailabilityDialogFragment();
					break;
			}

		}
		else if ( commandCategory == NewSemsSmsSender.CommandCategory.GET ) {
			NewSemsSmsSender.CommandType commandType = NewSemsSmsSender.CommandType.getValueOf(childPosition);
			switch ( commandType ) {
				case EMPTY:
					NewSemsSmsSender.sendSms(newSemsPhonNumber, commandCategory);
					break;
				case NUM:
					NewSemsSmsSender.sendSms(newSemsPhonNumber, commandCategory, commandType);
					break;
				case TIME:
					NewSemsSmsSender.sendSms(newSemsPhonNumber, commandCategory, commandType);
					break;
				case LIMT: dialogFragment = new GetWarningRangeDialogFragment();
					break;
				case KIND: dialogFragment = new GetSensorTypeDialogFragment();
					break;
				case USE: dialogFragment = new GetSensorAvailabilityDialogFragment();
					break;
			}
		}
		else if ( commandCategory == NewSemsSmsSender.CommandCategory.INFO ) {
			NewSemsSmsSender.sendSms(newSemsPhonNumber, NewSemsSmsSender.CommandCategory.INFO);
		}
		else {
			//이런 경우는 없다고 봐야 한다.
			return false;
		}

		if ( dialogFragment != null ) {
			Bundle bundle = new Bundle();
			bundle.putString(AbsFunctionDialogFragment.EXTRA_NEW_SEMS_PHONE_NUMBER, newSemsPhonNumber);
			dialogFragment.setArguments(bundle);
			dialogFragment.show(getFragmentManager(), "");
		}
		return true;
	}

	@Override
	public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
		TextView groupTextView = (TextView) v.findViewById(R.id.groupTextView);

		return false;
	}

	@Override
	public void onGroupCollapse(int groupPosition) {
		;
	}

	@Override
	public void onGroupExpand(int groupPosition) {
		for ( int i = 0; i < commandExpandableListView.getCount(); i++ ) {
			if ( i != groupPosition ) {
				commandExpandableListView.collapseGroup(i);
			}
		}
	}

	class CustomExpandableListAdapter extends BaseExpandableListAdapter {
		private Activity activity;

		CustomExpandableListAdapter(Activity activity) {
			this.activity = activity;
		}

		@Override
		public int getGroupCount() {
			return NewSemsSmsSender.CommandCategory.values().length;
		}

		//개발자, 서버관련 사항은 숨긴다.
		@Override
		public int getChildrenCount(int groupPosition) {
			if ( groupPosition == NewSemsSmsSender.CommandCategory.values().length - 1 ) {
				return 1;
			}
			else {
				return NewSemsSmsSender.CommandType.values().length - 2;
			}
		}

		@Override
		public Object getGroup(int groupPosition) {
			return NewSemsSmsSender.CommandCategory.values()[groupPosition];
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return NewSemsSmsSender.CommandType.values()[childPosition];
		}

		@Override
		public long getGroupId(int groupPosition) {
			return (long)groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return (long)childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			if ( convertView == null ) {
				convertView = activity.getLayoutInflater().inflate(R.layout.expandable_list_view_group_item, null, false);
			}
			TextView textView = (TextView) convertView.findViewById(R.id.groupTextView);
			textView.setText(NewSemsSmsSender.CommandCategory.values()[groupPosition].getSummary());
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
			if ( convertView == null ) {
				convertView = activity.getLayoutInflater().inflate(R.layout.expandable_list_view_child_item, null, false);
			}
			TextView textView = (TextView) convertView.findViewById(R.id.childTextView);
			if ( groupPosition == 0 && childPosition == 0 ) {
				textView.setText("SET 명령어 조회");
			}
			else if ( groupPosition == 1 && childPosition == 0 ) {
				textView.setText("GET 명령어 조회 및\n장비개수 조회");
			}
			else if ( groupPosition == 2 && childPosition == 0 ) {
				textView.setText("현재 센서값 조회");
			}
			else {
				textView.setText(NewSemsSmsSender.CommandType.values()[childPosition].getSummary().concat(" ").concat(NewSemsSmsSender.CommandCategory.values()[groupPosition].getSummary()));
			}
			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
}