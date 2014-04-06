package com.example.SemsApp.activity.tab;

import android.app.ActionBar;
import com.example.SemsApp.fragment.viewpager.ViewPagerFragment;

/**
 * Created by Administrator on 14. 3. 20.
 */
public class TabHandler implements ActionBar.TabListener {
	private ViewPagerFragment viewPagerFragment;

	public TabHandler(ViewPagerFragment viewPagerFragment) {
		this.viewPagerFragment = viewPagerFragment;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
		ft.show(viewPagerFragment);
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
		ft.hide(viewPagerFragment);
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
		//viewPagerFragment.showFirstPage();
	}
}
