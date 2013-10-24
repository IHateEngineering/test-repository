package com.contactmanager;

import java.util.ArrayList;
import java.util.List;

import com.contactmanager.ContactDataBaseHandler.TableType;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity that shows the contact details. This is also the same activity for
 * editing contact details. Editing ome number, email, adress, DOB, age, gender
 * and notes is currently bugged. i.e. when editing home number, the data is put
 * into the email field instead and when editing adress, data is put into the
 * DOB field instead. The cause of this fault is unknown.
 */
public class ContactDetailsActivity extends Activity {

	private Contact _selectedContact;
	private LayoutInflater _inflater;

	ContactDataBaseHandler _dbHandler;

	public static final String _deleteContactReturnString = "ContactToDelete";
	public static final int _deleteContactResultCode = 10;

	private LinearLayout _mainLin;

	private List<EditText> _detailFields = new ArrayList<EditText>();

	private EditText _firstName, _middleName, _preferedName, _lastName,
			_mobileNumber, _homeNumber, _email, _adress, _DOB, _age, _gender,
			_notes;

	private boolean _editable = false;

	/**
	 * returns an EditText object from a parent linear layout object, if it has
	 * one of a hardcoded resource id.
	 * 
	 * @param lin
	 * @return
	 */
	private EditText fieldContents(LinearLayout lin) {
		return (EditText) lin
				.findViewById(R.id.activity_contact_details_field_contents);
	}

	/**
	 * returns a TextView object from a parent linear layout object, if it has
	 * one of a hardcoded resource id.
	 * 
	 * @param lin
	 * @return
	 */
	private TextView fieldName(LinearLayout lin) {
		return (TextView) lin
				.findViewById(R.id.activity_contact_details_field_name);
	}

	/**
	 * fills the main linear layout with the contact detail fields. The fields
	 * are actually themselve linear layouts with a child textView and EditText
	 */
	private void initDetails() {
		Intent intent = getIntent();
		_selectedContact = intent.getParcelableExtra("selectedContact");
		Log.d("inserted details", "" + _selectedContact.getId());

		for (FieldType f : FieldType.values()) {
			try {
				LinearLayout newField = newField();
				TextView fieldName = fieldName(newField);
				EditText fieldContents = null;
				fieldName.setText(f.toString());
				switch (f) {
				case FirstName:
					_firstName = fieldContents(newField);
					// fieldContents = _firstName;
					_firstName.setText(_selectedContact.getFirstName());
					_mainLin.addView(newField);
					_detailFields.add(_firstName);
					break;
				case Adress:
					_adress = fieldContents(newField);
					// fieldContents = _adress;
					_adress.setText(_selectedContact.getAdress());
					_mainLin.addView(newField);
					_detailFields.add(_adress);
					break;
				case Age:
					_age = fieldContents(newField);
					// fieldContents = _age;
					String age = Integer.toString(_selectedContact.getAge());
					_age.setText(age);
					_mainLin.addView(newField);
					_detailFields.add(_age);
					break;
				case DOB:
					_DOB = fieldContents(newField);
					// fieldContents = _DOB;
					_DOB.setText(_selectedContact.getDOB());
					_mainLin.addView(newField);
					_detailFields.add(_DOB);
					break;
				case Email:
					_email = fieldContents(newField);
					// fieldContents = _email;
					_email.setText(_selectedContact.getEmail());
					_mainLin.addView(newField);
					_detailFields.add(_email);
					break;
				case Gender:
					_gender = fieldContents(newField);
					// fieldContents = _gender;
					_gender.setText(_selectedContact.getGender());
					_mainLin.addView(newField);
					_detailFields.add(_gender);
					break;
				case HomeNumber:
					String homeNumber = Integer.toString(_selectedContact
							.getHomeNumber());
					_homeNumber = fieldContents(newField);
					// fieldContents = _homeNumber;
					_homeNumber.setText(homeNumber);
					_mainLin.addView(newField);
					_detailFields.add(_homeNumber);
					break;
				case LastName:
					_lastName = fieldContents(newField);
					// fieldContents = _lastName;
					_lastName.setText(_selectedContact.getLastName());
					_mainLin.addView(newField);
					_detailFields.add(_lastName);
					break;
				case MiddleName:
					_middleName = fieldContents(newField);
					// fieldContents = _middleName;
					_middleName.setText(_selectedContact.getMiddleName());
					_mainLin.addView(newField);
					_detailFields.add(_middleName);
					break;
				case MobileNumber:
					String mobileNumber = Integer.toString(_selectedContact
							.getMobileNumber());
					_mobileNumber = fieldContents(newField);
					// fieldContents = _mobileNumber;
					_mobileNumber.setText(mobileNumber);
					_mainLin.addView(newField);
					_detailFields.add(_mobileNumber);
					break;
				case Notes:
					_notes = fieldContents(newField);
					// fieldContents = _notes;
					_notes.setText(_selectedContact.getNotes());
					_mainLin.addView(newField);
					_detailFields.add(_notes);
					break;
				case PreferredName:
					_preferedName = fieldContents(newField);
					// fieldContents = _preferedName;
					_preferedName.setText(_selectedContact.getPreferredName());
					_mainLin.addView(newField);
					_detailFields.add(_preferedName);
					break;
				default:
					break;
				}
			} catch (NullPointerException e) {
			}
		}

	}

	/**
	 * instantiates a new linear layout with a child textview and editText from
	 * the layout resource.
	 * 
	 * @return
	 */
	private LinearLayout newField() {
		return (LinearLayout) _inflater.inflate(
				R.layout.activity_contact_details_text_field, null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		_inflater = getLayoutInflater();

		_mainLin = (LinearLayout) findViewById(R.id.contact_details_main_linear);

		initDetails();

		_dbHandler = new ContactDataBaseHandler(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_details, menu);
		return true;
	}

	/**
	 * callback handlers for the menu items.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_deleteContact:
			initDeleteAlertDialog();
			break;
		case R.id.action_editContact:
			setEditable(!_editable);
			Log.d("edit", "edit clicked");
			break;
		case R.id.action_acceptChanges:
			saveChanges();
			break;
		case R.id.action_blockContact:
			
			_selectedContact.ignored = true;
			_dbHandler.deleteContact(_selectedContact, TableType.ContactList);
			_dbHandler.addContact(_selectedContact, TableType.IgnoreList);
			Intent returnIntent = new Intent();
			setResult(RESULT_CANCELED, returnIntent);
			_dbHandler.close();
			finish();
			
			break;

		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * implements the logic for saving changes to a contact. The changes are
	 * saved to the database directly.
	 */
	private void saveChanges() {
		try {
			_selectedContact.setFirstName(_firstName.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact
					.setPreferedName(_preferedName.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact.setMiddleName(_middleName.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact.setLastName(_lastName.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact.setMobileNumber(Integer.parseInt(_mobileNumber
					.getText().toString()));
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact.setHomeNumber(Integer.parseInt(_homeNumber
					.getText().toString()));
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact.setGender(_gender.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact.setDOB(_DOB.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact.setAdress(_adress.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact.setNotes(_notes.getText().toString());
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact
					.setAge(Integer.parseInt(_age.getText().toString()));
		} catch (NullPointerException e) {
		}
		try {
			_selectedContact.setEmail(_email.getText().toString());
		} catch (NullPointerException e) {
		}
		if (_selectedContact.ignored) {
			_dbHandler.updateContact(_selectedContact, TableType.IgnoreList);
		} else {
			_dbHandler.updateContact(_selectedContact, TableType.ContactList);
		}
		Intent returnIntent = new Intent();
		setResult(RESULT_OK, returnIntent);
		_dbHandler.close();
		finish();
		// }

	}

	/**
	 * implements the logic for whether the editText objects for the contact's
	 * detail fields can be edited or not.
	 * 
	 * @param b
	 */
	private void setEditable(boolean b) {
		for (EditText editText : _detailFields) {
			editText.setFocusable(b);
			editText.setClickable(b);
			editText.setFocusableInTouchMode(b);
			// editText.setText("test");
			Log.d("edit", "in setEditable");
		}
		if (b) {
			_firstName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
			_preferedName
					.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
			_lastName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
			_middleName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

			_gender.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

			_mobileNumber.setInputType(InputType.TYPE_CLASS_PHONE);
			_homeNumber.setInputType(InputType.TYPE_CLASS_PHONE);
			_email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
			_DOB.setInputType(InputType.TYPE_CLASS_DATETIME);
			_age.setInputType(InputType.TYPE_CLASS_NUMBER);
		}

		if (b) {
			Toast.makeText(getBaseContext(), "Fields editable",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getBaseContext(), "Fields not editable",
					Toast.LENGTH_LONG).show();
		}
		_editable = !_editable;
	}

	/**
	 * implements the logic for the alert dialog that pops up when the delete
	 * menu item is pressed.
	 */
	private void initDeleteAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Delete this contact?");
		builder.setMessage("This cannot be undone.");

		builder.setNegativeButton("cancel", null);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent returnIntent = new Intent();
				returnIntent.putExtra(_deleteContactReturnString,
						_selectedContact);
				setResult(_deleteContactResultCode, returnIntent);
				finish();
			}
		});

		builder.setCancelable(true);
		builder.create().show();
	}

}
