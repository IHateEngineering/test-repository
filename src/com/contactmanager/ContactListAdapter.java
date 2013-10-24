package com.contactmanager;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Custom adapter class for a list of contact objects to a listView.
 */
public class ContactListAdapter extends ArrayAdapter<Contact> {
	
	private  final Context _context;
	private final List<Contact> _contactList;

	public ContactListAdapter(Context context, List<Contact> contactList) {
		super(context, android.R.layout.simple_list_item_1, contactList);
		// TODO Auto-generated constructor stub
		
		_context = context;
		_contactList = contactList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		return super.getView(position, convertView, parent);
		
		LayoutInflater inflater = (LayoutInflater) _context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		View contactListView = inflater.inflate(R.layout.contact_list_item_1, null);
		
		TextView firstName = (TextView) contactListView.findViewById(R.id.contact_text_firstName);
		TextView lastName = (TextView) contactListView.findViewById(R.id.contact_text_lastName);
		
		firstName.setText(_contactList.get(position).getFirstName());
		lastName.setText(_contactList.get(position).getLastName());
		
		return contactListView;
	}
	
	

}
