package com.contactmanager;

import java.util.List;

import com.contactmanager.ContactDataBaseHandler.TableType;
import com.contactmanager.SelectSortDialogFragment.SortOption;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * creates a tab to be added to the main activity that contains a
 * ContactListFragment. This class is used twice, once for the contact list and
 * once for the ignore list.
 */
public class TabListener<T> implements android.app.ActionBar.TabListener {
	private ContactListFragment _fragment;
	private final Activity _activity;
	private final String _tag;
	private final Class<T> _class;
	private final TableType _tableType;
	private final ContactDataBaseHandler _dbHandler;

	public TabListener(Activity activity, String tag, Class<T> class1,
			TableType t) {
		_activity = activity;
		_tag = tag;
		_class = class1;
		_tableType = t;
		_dbHandler = new ContactDataBaseHandler(activity);
	}

	public void updateFragment(ContactListFragment frag) {
		_fragment = frag;
	}

	public ContactListFragment getFragment() {
		return _fragment;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if (_fragment == null) {
			_fragment = (ContactListFragment) Fragment.instantiate(_activity,
					_class.getName());
			_fragment.setTableType(_tableType);
			ft.add(R.id.Main_frame1, _fragment, _tag);
		} else {
			// ft.attach(_fragment);
			ft.show(_fragment);
			List<Contact> contacts = _dbHandler.getAllContacts(_tableType,
					SortOption.Default);
			_fragment.setList(contacts);

		}

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if (_fragment != null) {
			// ft.detach(_fragment);
			ft.hide(_fragment);
		}
	}

}
