package com.contactmanager;

import java.util.ArrayList;
import java.util.List;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * dialog for picking the sort order of the contact list or ignore list.
 * 
 */
public class SelectSortDialogFragment extends DialogFragment implements
		AdapterView.OnItemClickListener {

	private LayoutInflater _inflater;
	private List<SortDialogListener> _listeners = new ArrayList<SortDialogListener>();
	String[] _sortOptions = { "temp", "temp", "temp", "temp" };

	private String _firstNameOption = "First Name",
			_lastNameOption = "Last Name",
			_preferedNameOption = "Prefered Name", _ageOption = "Age";

	/**
	 * nested interface to be implemented by classes that want to be notified
	 * when a sort option is picked.
	 * 
	 */
	public interface SortDialogListener {
		public void onSortSelection(SortOption s);
	}

	public enum SortOption {
		Default, FirstName, LastName, PreferedName, Age;
	}

	public void addSortListener(SortDialogListener l) {
		_listeners.add(l);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		_inflater = inflater;

		final String[] sortOptions = getResources().getStringArray(
				R.array.contact_sort_list);

		for (int i = 0; i < sortOptions.length; i++) {
			_sortOptions[i] = sortOptions[i];
		}

		ListView view = (ListView) _inflater.inflate(R.layout.contact_list,
				container);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1, sortOptions);
		view.setAdapter(arrayAdapter);
		view.setOnItemClickListener(this);
		return view;
	}

	/**
	 * sets up even handlers for when a sort option is picked.
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1,
			int clickedViewPosition, long arg3) {
		String sortOption = _sortOptions[clickedViewPosition];
		if (sortOption.equals(_firstNameOption)) {
			for (SortDialogListener l : _listeners) {
				Log.d("listener", _listeners.get(0).toString());
				l.onSortSelection(SortOption.FirstName);
			}
		} else if (sortOption.equals(_lastNameOption)) {
			for (SortDialogListener l : _listeners) {
				l.onSortSelection(SortOption.LastName);
			}
		} else if (sortOption.equals(_preferedNameOption)) {
			for (SortDialogListener l : _listeners) {
				l.onSortSelection(SortOption.PreferedName);
			}
		} else if (sortOption.equals(_ageOption)) {
			for (SortDialogListener l : _listeners) {
				l.onSortSelection(SortOption.Age);
			}
		}
		dismiss();
	}

}
