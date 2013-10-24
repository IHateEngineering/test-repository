package com.contactmanager;

import java.util.List;

import com.contactmanager.ContactDataBaseHandler.TableType;
import com.contactmanager.SelectSortDialogFragment.SortOption;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Fragment that holds a list of contacts in a ListView.
 *
 */
public class ContactListFragment extends Fragment {

	private List<Contact> _contactList;
	private ContactListAdapter _contactListAdapter;
	private ListView _listView;
	private TableType _tableType;

	private ContactDataBaseHandler _dbHandler = new ContactDataBaseHandler(
			getActivity());

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.contact_list, container, false);
		setUpListView(v);
		_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parentView,
					View clickedView, int position, long id) {
				// TODO Auto-generated method stub
				Contact selectedContact = _contactList.get(position);
				Intent i = new Intent();
				i.putExtra("selectedContact", selectedContact);
				i.setClass(getActivity(), ContactDetailsActivity.class);
				startActivityForResult(i, 1);
			}
		});
		return v;
	}

	/**
	 * changes the model of the list view and updates the list view on screen.
	 * 
	 * @param list
	 */
	public void setList(List<Contact> list) {
		_contactList = list;
		_contactListAdapter = new ContactListAdapter(getActivity(),
				_contactList);
		_listView.setAdapter(_contactListAdapter);

	}

	public List<Contact> getList() {
		return _contactList;
	}

	public TableType getTableType() {
		return _tableType;
	}

	public Contact getContactFromList(int index) {
		return _contactList.get(index);
	}

	public void setTableType(TableType t) {
		_tableType = t;
	}

	private void setUpListView(View v) {
		_dbHandler = new ContactDataBaseHandler(getActivity());
	
		_contactList = _dbHandler
				.getAllContacts(_tableType, SortOption.Default);
		_contactListAdapter = new ContactListAdapter(getActivity(),
				_contactList);

		_listView = (ListView) v.findViewById(R.id.contactList_list);
		_listView.setAdapter(_contactListAdapter);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 1:
			switch (resultCode) {
			case Activity.RESULT_OK:
				// ContactDataBaseHandler dbHandler = new
				// ContactDataBaseHandler(getActivity());
//				Contact updatedContact = data.getParcelableExtra("updatedContact");
//				_dbHandler.deleteContact(updatedContact, _tableType);
//				updatedContact.setId(0);
//				_dbHandler.addContact(updatedContact, _tableType);
				setList(_dbHandler.getAllContacts(TableType.ContactList,
						SortOption.Default));

				break;
			case Activity.RESULT_CANCELED:
				setList(_dbHandler.getAllContacts(_tableType,
						SortOption.Default));
				break;
			case ContactDetailsActivity._deleteContactResultCode:
				ContactDataBaseHandler _dbHandler = new ContactDataBaseHandler(
						getActivity());
				Contact contact = data
						.getParcelableExtra(ContactDetailsActivity._deleteContactReturnString);
				_dbHandler.deleteContact(contact, _tableType);
				setList(_dbHandler.getAllContacts(_tableType,
						SortOption.Default));
				break;
			}
		case 2:
			switch (resultCode) {
			case Activity.RESULT_OK:
				break;
			}
		}
	}

}
