package com.contactmanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * Depreciated implementation of the new contact screen. Now replaced by the new
 * contact activity. This class is kept here for documentation purposes.
 * 
 */
public class NewContactDialogFragment extends DialogFragment implements
		OnEditorActionListener, OnKeyListener, OnClickListener {

	public interface EditNameDialogListener {
		void onFinishEditDialog(Contact contact);
	}

	private LinearLayout _addContactButtonLayout;
	private LinearLayout _addFieldLayout;
	// private List<String> _addFieldNames = Arrays.asList(_addFieldNamesArray);
	private List<String> _addFieldNames = new ArrayList<String>();
	String[] _addFieldNamesArray = new String[] {
			FieldType.FirstName.getText(), "Preferred Name", "Middle Name",
			"Last Name", "Mobile Number", "D.O.B", "Home Number", "Email",
			" Adress", "DOB", "Age", "gender", "Notes" };
	private Spinner _addFieldSpinner;
	private ArrayAdapter<String> _arrayAdapter;
	String _currentSelectedAddFieldName;
	private EditText _editTextFirstName, _editTextPreferredName,
			_editTextMiddleName, _editTextLastName, _editTextMobileNumber,
			_editTextHomeNumber, _editTextEmail, _editTextAdress, _editTextDOB,
			_editTextAge, _editTextGender, _editTextNotes;
	private TextView _firstName, _preferredName, _middleName, _lastName,
			_mobileNumber, _homeNumber, _age, _notes, _email, _DOB, _adress,
			_gender;
	private LayoutInflater _inflater;
	private LinearLayout _mainLin;

	// private EditText[] _editTextFields = {_editTextFirstName,
	// _editTextPreferredName, _editTextMiddleName,
	// _editTextLastName, _editTextMobileNumber};
	private Button _newContactButton, _addFieldButton;

	public NewContactDialogFragment() {
		// Empty constructor required for DialogFragment
	}

	private void addFieldButton(View view) {
		// _addFieldButton = (Button)
	}

	// private void initEditTextFields(View view, LayoutInflater inflater){
	//
	// _editTextFirstName = defaultsEditText.
	// _editTextFirstName = (EditText)
	// view.findViewById(R.id.new_contact_firstname_edittext);
	// _editTextPreferredName = (EditText)
	// view.findViewById(R.id.preferredName);
	// _editTextMiddleName = (EditText) view.findViewById(R.id.middleName);
	// _editTextLastName = (EditText) view.findViewById(R.id.lastName);
	// _editTextMobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
	// _editTextFirstName.setFocusable(true);
	// // for(EditText editText : _editTextFields){
	// editText.setFocusable(true);
	// }
	// }
	// private void firstName(LayoutInflater inflater){
	// LinearLayout firstName = (LinearLayout) inflater.inflate(
	// R.layout.new_contact_text_field, null);
	// _editTextFirstName =
	// (EditText)firstName.findViewById(R.id.new_contact_field_edit);
	//
	// _firstName =
	// (TextView)firstName.findViewById(R.id.new_contact_field_name);
	// _firstName.append("First Name");
	// _mainLin.addView(firstName);
	//
	// LinearLayout lastName = (LinearLayout) inflater.inflate(
	// R.layout.new_contact_text_field, null);
	// _editTextLastName =
	// (EditText)lastName.findViewById(R.id.new_contact_field_edit);
	//
	// _lastName = (TextView)lastName.findViewById(R.id.new_contact_field_name);
	// _lastName.append("Last Name");
	// _mainLin.addView(lastName);
	// }

	private void addNewContactButton(View view) {
		_newContactButton = (Button) view.findViewById(R.id.newContact_button);
		_newContactButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getNewContact();
			}
		});
	}

	private void editTextFunction(EditText editText, LinearLayout linearLayout) {
		editText = (EditText) linearLayout
				.findViewById(R.id.new_contact_field_edit);
	}

	private void getNewContact() {
		EditNameDialogListener activity = (EditNameDialogListener) getActivity();
		String firstName, preferredName, middleName, lastName;
		int mobileNumber;
		Contact.Builder builder = new Contact.Builder();
		try {
			firstName = _editTextFirstName.getText().toString();
			builder = builder.firstName(firstName);
		} catch (NullPointerException e) {
		}
		try {
			// preferredName = _editTextPreferredName.getText().toString();
			builder = builder.preferedName(_editTextPreferredName.getText()
					.toString());
		} catch (NullPointerException e) {
		}
		try {
			// middleName = _editTextMiddleName.getText().toString();
			builder = builder.middleName(_editTextMiddleName.getText()
					.toString());
		} catch (NullPointerException e) {
		}
		try {
			// lastName = _editTextLastName.getText().toString();
			builder = builder.lastName(_editTextLastName.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			// mobileNumber =
			// Integer.parseInt(_editTextMobileNumber.getText().toString());
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

		firstName = _editTextFirstName.getText().toString();
		builder = builder.firstName(firstName);
		activity.onFinishEditDialog(builder.build());
		// activity.onFinishEditDialog(new
		// Contact.Builder().firstName(firstName).preferedName(preferredName).
		// middleName(middleName).lastName(lastName).mobileNumber(mobileNumber).build());
		this.dismiss();
	}

	private void initNewField(LayoutInflater inflater, FieldType fieldType,
			String title) {
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(
				R.layout.new_contact_text_field, null);
		EditText editText = null;
		TextView textView = null;
		switch (fieldType) {
		case FirstName:
			// editText = (EditText)_editTextFirstName;
			// textView = _firstName;
			_editTextFirstName = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_firstName = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_firstName.append(title);
			// _mainLin.addView(linearLayout, 0);
			break;
		case LastName:
			// editText = (EditText)_editTextLastName;
			// textView = _lastName;
			_editTextLastName = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_lastName = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_lastName.append(title);
			// _mainLin.addView(linearLayout, 0);
			break;
		case MiddleName:
			// editText = (EditText)_editTextMiddleName;
			// textView = _middleName;
			_editTextMiddleName = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_middleName = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_middleName.append(title);
			// _mainLin.addView(linearLayout, 0);
			break;
		case MobileNumber:
			// editText = (EditText) _editTextMobileNumber;
			// textView = _mobileNumber;
			_editTextMobileNumber = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_middleName = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_middleName.append(title);
			// _mainLin.addView(linearLayout, 0);
			break;
		case PreferredName:
			// editText = (EditText) _preferredName;
			// textView = _preferredName;
			_editTextPreferredName = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

			_preferredName = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_preferredName.append(title);
			// _mainLin.addView(linearLayout, 0);
			break;
		case Adress:
			_editTextAdress = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

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

			_DOB = (TextView) linearLayout
					.findViewById(R.id.new_contact_field_name);
			_DOB.append(title);
			break;
		case Email:
			_editTextEmail = (EditText) linearLayout
					.findViewById(R.id.new_contact_field_edit);

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
		// editText =
		// (EditText)linearLayout.findViewById(R.id.new_contact_field_edit);
		//
		// textView =
		// (TextView)linearLayout.findViewById(R.id.new_contact_field_name);
		// textView.append(title);
		_mainLin.addView(linearLayout, 0);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		// _addFieldNames.remove(_currentSelectedAddFieldName);
		LinearLayout newField = null;
		String string = getString(R.string.action_settings);
		FieldType fieldType = FieldType
				.fromString(_currentSelectedAddFieldName);

		// switch (fieldType){
		// case FirstName:
		// newField = (LinearLayout) initNewField(
		// inflater,
		// _editTextFirstName,_firstName,_currentSelectedAddFieldName);
		// // _addFieldNames.remove("First Name");
		// mainLin.addView(newField, 0);
		// break;
		// case PreferredName:
		// newField = (LinearLayout) initNewField(
		// inflater,
		// _editTextPreferredName,_preferredName,_currentSelectedAddFieldName);
		// mainLin.addView(newField, 0);
		// break;
		// case MiddleName:
		// newField = (LinearLayout) initNewField(
		// inflater,
		// _editTextMiddleName,_middleName,_currentSelectedAddFieldName);
		// mainLin.addView(newField, 0);
		// break;
		// case LastName:
		// newField = (LinearLayout) initNewField(
		// inflater, _editTextLastName, _lastName,_currentSelectedAddFieldName);
		// mainLin.addView(newField, 0);
		// break;
		// case MobileNumber:
		// newField = (LinearLayout) initNewField(
		// inflater, _editTextMobileNumber,
		// _mobileNumber,_currentSelectedAddFieldName);
		// // mainLin.addView(newField, 0);
		// break;
		// default:
		// break;
		// }
		String s = _currentSelectedAddFieldName;
		if (s.equals(FieldType.FirstName.getText())) {
			initNewField(_inflater, FieldType.FirstName, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.PreferredName.getText())) {
			initNewField(_inflater, FieldType.PreferredName, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.MiddleName.getText())) {
			initNewField(_inflater, FieldType.MiddleName, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.LastName.getText())) {
			initNewField(_inflater, FieldType.LastName, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.MobileNumber.getText())) {
			initNewField(_inflater, FieldType.MobileNumber, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.Adress.getText())) {
			initNewField(_inflater, FieldType.Adress, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.Gender.getText())) {
			initNewField(_inflater, FieldType.Gender, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.HomeNumber.getText())) {
			initNewField(_inflater, FieldType.HomeNumber, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.DOB.getText())) {
			initNewField(_inflater, FieldType.DOB, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.Email.getText())) {
			initNewField(_inflater, FieldType.Email, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.Age.getText())) {
			initNewField(_inflater, FieldType.Age, s);
			updateSpinner(s);
		} else if (s.equals(FieldType.Notes.getText())) {
			initNewField(_inflater, FieldType.Notes, s);
			updateSpinner(s);
		}
		try {
			// _mainLin.addView(newField, 0);
		} catch (Exception e) {
		}

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		_inflater = inflater;
		View view = _inflater.inflate(R.layout.new_contact, container);
		// _editTextFirstName = (EditText)
		// view.findViewById(R.id.new_contact_field_edit);
		getDialog().setTitle(R.string.new_contact_title);
		for (FieldType f : FieldType.values()) {
			_addFieldNames.add(f.getText());
		}
		_mainLin = (LinearLayout) view.findViewById(R.id.main_linear);

		_addFieldLayout = (LinearLayout) inflater.inflate(
				R.layout.new_contact_add_field, null);
		_addContactButtonLayout = (LinearLayout) inflater.inflate(
				R.layout.new_contact_add_contact_button, null);
		_newContactButton = (Button) _addContactButtonLayout
				.findViewById(R.id.newContact_button);
		_addFieldButton = (Button) _addFieldLayout
				.findViewById(R.id.new_contact_add_field_button);
		_addFieldSpinner = (Spinner) _addFieldLayout
				.findViewById(R.id.new_contact_add_field_spinner);

		_arrayAdapter = new ArrayAdapter<String>(getActivity(),
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
						// _addFieldNames.remove(_currentSelectedAddFieldName);
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

		_mainLin.addView(_addContactButtonLayout);
		_mainLin.addView(_addFieldLayout);
		// initNewField(_inflater,FieldType.FirstName,
		// "First Name");
		// _mainLin.addView(firstName);

		// firstName(_inflater);

		// Show soft keyboard automatically
		// _editTextFirstName.setFocusableInTouchMode(true);
		// initEditTextFields(view, inflater);
		// _editTextFirstName.requestFocus();
		// _editTextFirstName.setHint("firstName");
		// _editTextFirstName.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		getDialog().getWindow().setSoftInputMode(
				LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		// mEditText.setOnEditorActionListener(this);
		// mEditText.setOnKeyListener(this);
		addNewContactButton(view);
		return view;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		// if (EditorInfo.IME_ACTION_DONE == actionId) {
		// // Return input text to activity
		// EditNameDialogListener activity = (EditNameDialogListener)
		// getActivity();
		// activity.onFinishEditDialog(mEditText.getText().toString());
		// this.dismiss();
		// return true;
		// }
		return false;
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// If the event is a key-down event on the "enter" button
		// if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		// (keyCode == KeyEvent.KEYCODE_ENTER)) {
		// // Perform action on key press
		// EditNameDialogListener activity = (EditNameDialogListener)
		// getActivity();
		// activity.onFinishEditDialog(mEditText.getText().toString());
		// this.dismiss();
		//
		// return true;
		// }
		return false;
	}

	private void updateSpinner(String name) {
		if (_addFieldNames.size() == 1) {
			_addFieldNames.add("None Left");
		}
		_addFieldNames.remove(name);
		_addFieldSpinner.setSelection(0);
		_arrayAdapter.notifyDataSetChanged();
		_currentSelectedAddFieldName = _addFieldNames.get(0);
	}

	// @Override
	// public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
	// // TODO Auto-generated method stub
	// return false;
	// }

}
