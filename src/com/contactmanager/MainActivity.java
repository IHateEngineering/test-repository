package com.contactmanager;

import java.util.ArrayList;
import java.util.List;

import com.contactmanager.ContactDataBaseHandler.TableType;
import com.contactmanager.SelectSortDialogFragment.SortOption;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Activity that is first shown when the application is launched. This activity
 * holds two fragments, one showing a list of contacts, and the other showing a
 * list of blocked contacts. Contact details can be accessed by touching a
 * contact in either list. The new contact activity can be accessed from this
 * screen.
 * 
 * @author Matthew
 * 
 */
public class MainActivity extends Activity implements
		SelectSortDialogFragment.SortDialogListener {

	public static final int _contactDetailsRequestCode = 0,
			_newContactRequestCode = 1;

	private TabListener<ContactListFragment> _contactListTabListener = new TabListener<ContactListFragment>(
			this, "Contacts", ContactListFragment.class, TableType.ContactList);
	private TabListener<ContactListFragment> _ignoreListTablListener = new TabListener<ContactListFragment>(
			this, "Ignore list", ContactListFragment.class,
			TableType.IgnoreList);

	private ContactDataBaseHandler _dbHandler = new ContactDataBaseHandler(this);

	/**
	 * Adds a contact to the database. Can only add to the contact list table
	 * and not the ignore list table.
	 * 
	 * @param contact
	 */
	private void addContact(Contact contact) {
		ContactListFragment frag = (ContactListFragment) getFragmentManager()
				.findFragmentByTag("Contacts");

		long id = _dbHandler.addContact(contact, TableType.ContactList);
		contact.setId(id);

		ListView lv = (ListView) findViewById(R.id.contactList_list);
		ArrayList<Contact> list = (ArrayList<Contact>) frag.getList();
		list.add(contact);
		ContactListAdapter ad = (ContactListAdapter) lv.getAdapter();
		ad.notifyDataSetChanged();

		_contactListTabListener.updateFragment(frag);
	}

	/**
	 * deletes a contact from the contacts database.
	 * 
	 * @param contact
	 */
	private void deleteContact(Contact contact) {
		ContactListFragment frag = (ContactListFragment) getFragmentManager()
				.findFragmentByTag("Contacts");

		_dbHandler.deleteContact(contact, TableType.ContactList);

		ListView lv = (ListView) findViewById(R.id.contactList_list);

		List<Contact> list = _dbHandler.getAllContacts(TableType.ContactList,
				SortOption.Default);

		// list.remove(contact);

		ContactListAdapter ad = (ContactListAdapter) lv.getAdapter();

		ad.notifyDataSetChanged();

		_contactListTabListener.updateFragment(frag);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == _newContactRequestCode) {
			try {
				Contact newContact = data.getParcelableExtra("newContact");
				addContact(newContact);
			} catch (NullPointerException e) {
			}
		} else if (requestCode == _contactDetailsRequestCode) {
			// switch(resultCode){
			// case ContactDetailsActivity._deleteContactResultCode:
			Toast.makeText(getBaseContext(), "test", Toast.LENGTH_LONG).show();
			Contact contactToDelete = data
					.getParcelableExtra(ContactDetailsActivity._deleteContactReturnString);
			deleteContact(contactToDelete);
			// break;
			// }
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ActionBar ab = getActionBar();
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		Tab tab = ab.newTab().setText("Contacts")
				.setTabListener(_contactListTabListener);
		ab.addTab(tab);

		tab = ab.newTab().setText("Ignore list")
				.setTabListener(_ignoreListTablListener);
		ab.addTab(tab);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_newContact:
			// showEditDialog();
			Intent newContactIntent = new Intent(this, NewContactActivity.class);
			startActivityForResult(newContactIntent, _newContactRequestCode);
			break;
		case R.id.action_sort:
			FragmentManager fm = getFragmentManager();
			SelectSortDialogFragment sortDialog = new SelectSortDialogFragment();
			sortDialog.addSortListener(this);
			sortDialog.show(fm, "sort dialog");
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * implementation of the callback method in the nested interface in the sort
	 * dialog class. This method is responsible for sorting either the contact
	 * list or ignore list.
	 */
	@Override
	public void onSortSelection(SortOption s) {
		ContactListFragment frag;
		ContactListFragment contactFrag = _contactListTabListener.getFragment();
		ContactListFragment ignoreFrag = _ignoreListTablListener.getFragment();
		if (contactFrag.isVisible()) {
			frag = contactFrag;
		} else {
			frag = ignoreFrag;
		}
		List<Contact> list = _dbHandler.getAllContacts(frag.getTableType(), s);
		frag.setList(list);

	}

}
