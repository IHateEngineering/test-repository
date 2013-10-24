package com.contactmanager;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * Activity for the new contact screen. New contact details can be specified
 * here. Goes back to the main activity when finished
 * 
 */
@SuppressLint("CutPasteId")
public class NewContactActivity extends Activity implements OnClickListener {

	private EditText _editTextFirstName, _editTextPreferredName,
			_editTextMiddleName, _editTextLastName, _editTextMobileNumber,
			_editTextHomeNumber, _editTextEmail, _editTextAdress, _editTextDOB,
			_editTextAge, _editTextGender, _editTextNotes;
	private TextView _firstName, _preferredName, _middleName, _lastName,
			_mobileNumber, _homeNumber, _age, _notes, _email, _DOB, _adress,
			_gender;

	private Button _newContactButton, _addFieldButton;

	String _currentSelectedAddFieldName;
	private ArrayAdapter<String> _arrayAdapter;

	private List<String> _addFieldNames = new ArrayList<String>();

	private LayoutInflater _inflater;
	private LinearLayout _addFieldLayout;
	private LinearLayout _addContactButtonLayout;
	private Spinner _addFieldSpinner;

	private LinearLayout _mainLin;

	/**
	 * the onCreate method mainly sets up the add field, add contact buttons and
	 * the spinner to choose which field to add. calls the other methods that
	 * sets up the rest.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_contact);
		_inflater = getLayoutInflater();

		for (FieldType f : FieldType.values()) {
			_addFieldNames.add(f.getText());
		}
		_mainLin = (LinearLayout) findViewById(R.id.main_linear);

		_addFieldLayout = (LinearLayout) _inflater.inflate(
				R.layout.new_contact_add_field, null);
		_addContactButtonLayout = (LinearLayout) _inflater.inflate(
				R.layout.new_contact_add_contact_button, null);

		_newContactButton = (Button) _addContactButtonLayout
				.findViewById(R.id.newContact_button);
		_addFieldButton = (Button) _addFieldLayout
				.findViewById(R.id.new_contact_add_field_button);
		_addFieldSpinner = (Spinner) _addFieldLayout
				.findViewById(R.id.new_contact_add_field_spinner);

		_arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, _addFieldNames);

		_arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_addFieldSpinner.setAdapter(_arrayAdapter);

		_addFieldSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View selectedView, int position, long id) {
						// TODO Auto-generated method stub
						_currentSelectedAddFieldName = parent
								.getItemAtPosition(position).toString();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		_addFieldButton.setOnClickListener(this);

		_newContactButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getNewContact();
			}
		});

		_mainLin.addView(_addFieldLayout);
		_mainLin.addView(_addContactButtonLayout);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		String s = _currentSelectedAddFieldName;
		// for(FieldType fieldType : FieldType.values()){
		// if(s.equals(fieldType.getText())){
		// initNewField(fieldType, s);
		// updateSpinner(s);
		// }
		// }
		if (s.equals(FieldType.FirstName.getText())) {
			initNewField(FieldType.FirstName, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.PreferredName.getText())) {
			initNewField(FieldType.PreferredName, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.MiddleName.getText())) {
			initNewField(FieldType.MiddleName, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.LastName.getText())) {
			initNewField(FieldType.LastName, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.MobileNumber.getText())) {
			initNewField(FieldType.MobileNumber, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.Adress.getText())) {
			initNewField(FieldType.Adress, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.Gender.getText())) {
			initNewField(FieldType.Gender, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.HomeNumber.getText())) {
			initNewField(FieldType.HomeNumber, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.DOB.getText())) {
			initNewField(FieldType.DOB, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.Email.getText())) {
			initNewField(FieldType.Email, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.Age.getText())) {
			initNewField(FieldType.Age, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.Notes.getText())) {
			initNewField(FieldType.Notes, s);
			updateSpinner(s);
		}
	}

	/**
	 * creates a new contact form the data entered into the editText fields.
	 * This method will call finish() and send the contact object back to the
	 * main activity.
	 */
	private void getNewContact() {
		Contact.Builder builder = new Contact.Builder();
		try {
			builder = builder
					.firstName(_editTextFirstName.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			builder = builder.preferedName(_editTextPreferredName.getText()
					.toString());
		} catch (NullPointerException e) {
		}
		try {
			builder = builder.middleName(_editTextMiddleName.getText()
					.toString());
		} catch (NullPointerException e) {
		}
		try {
			builder = builder.lastName(_editTextLastName.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			builder.mobileNumber(Integer.parseInt(_editTextMobileNumber
					.getText().toString()));
		} catch (Exception e) {
		}
		try {
			builder.homeNumber(Integer.parseInt(_editTextHomeNumber.getText()
					.toString()));
		} catch (Exception e) {
		}
		try {
			builder.gender(_editTextGender.getText().toString());
		} catch (Exception e) {
		}
		try {
			builder.dob(_editTextDOB.getText().toString());
		} catch (Exception e) {
		}
		try {
			builder.adress(_editTextAdress.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			builder.notes(_editTextNotes.getText().toString());
		} catch (Exception e) {
		}
		try {
			builder.age(Integer.parseInt(_editTextAge.getText().toString()));
		} catch (Exception e) {
		}
		try {
			builder.email(_editTextEmail.getText().toString());
		} catch (NullPointerException e) {
		}

		Contact newContact = builder.build();
		Intent returnIntent = new Intent();
		returnIntent.putExtra("newContact", newContact);
		setResult(RESULT_OK, returnIntent);
		finish();
	}

	/**
	 * updates the spinner after a field has been added, this is to prevent more
	 * than one of the same field from being added.
	 * 
	 * @param name
	 */
	private void updateSpinner(String name) {
		if (_addFieldNames.size() == 1) {
			_addFieldNames.add("None Left");
		}
		_addFieldNames.remove(name);
		_addFieldSpinner.setSelection(0);
		_arrayAdapter.notifyDataSetChanged();
		_currentSelectedAddFieldName = _addFieldNames.get(0);
	}

	/**
	 * initialises a detail field when the add field button is pressed.
	 * 
	 * @param fieldType
	 * @param title
	 */
	@SuppressLint("CutPasteId")
	private void initNewField(FieldType fieldType, String title) {
		LinearLayout linearLayout = (LinearLayout) _inflater.inflate(
				R.layout.new_contact_text_field, null);
		EditText editText = null;
		TextView textView = null;
		switch (fieldType) {
		case FirstName:
			_editTextFirstName = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_firstName = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_firstName.append(title);
			break;
		case LastName:
			_editTextLastName = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_lastName = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_lastName.append(title);
			break;
		case MiddleName:
			_editTextMiddleName = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_middleName = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_middleName.append(title);
			break;
		case MobileNumber:
			_editTextMobileNumber = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);
			_editTextMobileNumber.setInputType(InputType.TYPE_CLASS_PHONE);
			_mobileNumber = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_mobileNumber.append(title);
			break;
		case PreferredName:
			_editTextPreferredName = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_preferredName = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_preferredName.append(title);
			break;
		case Adress:
			_editTextAdress = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);
			_editTextAdress.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
			_adress = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_adress.append(title);
			break;
		case Age:
			_editTextAge = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);
			_editTextAge.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_CLASS_NUMBER);
			_age = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_age.append(title);
			break;
		case DOB:
			_editTextDOB = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);
			_editTextDOB.setInputType(InputType.TYPE_CLASS_NUMBER);
			_DOB = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_DOB.append(title);
			break;
		case Email:
			_editTextEmail = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);
			_editTextEmail.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
			_email = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_email.append(title);
			break;
		case Gender:
			_editTextGender = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_gender = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_gender.append(title);
			break;
		case HomeNumber:
			_editTextHomeNumber = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);
			_editTextHomeNumber.setInputType(InputType.TYPE_CLASS_PHONE);
			_homeNumber = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_homeNumber.append(title);
			break;
		case Notes:
			_editTextNotes = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_notes = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_notes.append(title);
			break;
		default:
			break;
		}
		_mainLin.addView(linearLayout, 0);
	}
}
