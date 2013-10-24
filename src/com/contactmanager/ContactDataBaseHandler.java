package com.contactmanager;

import java.util.ArrayList;
import java.util.List;

import com.contactmanager.SelectSortDialogFragment.SortOption;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper class used to manage the database. Can be instantiated as many times
 * as wanted, but only one database will every exist.
 * 
 */
public class ContactDataBaseHandler extends SQLiteOpenHelper {

	public static int DATABASE_VERSION = 1;
	private static String DATABASE_NAME = "MYDATABASE4";
	private static String TABLE_NAME_CONTACTS = "ContactListTable";
	private static String TABLE_NAME_IGNORE = "IgnoreListTable";

	private static final String KEY_ID = "_id", KEY_FIRST_NAME = "First_Name",
			KEY_MIDDLE_NAME = "Middle_Name",
			KEY_PREFERED_NAME = "Preferred_Name", KEY_LAST_NAME = "Last_Name",
			KEY_MOBILE_NUMBER = "Mobile_Number",
			KEY_HOME_NUMBER = "Home_Number", KEY_EMAIL = "Email",
			KEY_ADRESS = "Adress", KEY_DOB = "DOB", KEY_AGE = "Ages",
			KEY_GENDER = "Gender", KEY_NOTES = "Notes";

	private final String DATABASE_CREATE_CONTACT_LIST = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME_CONTACTS
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_FIRST_NAME
			+ " TEXT,"
			+ KEY_MIDDLE_NAME
			+ " TEXT,"
			+ KEY_PREFERED_NAME
			+ " TEXT,"
			+ KEY_LAST_NAME
			+ " TEXT,"
			+ KEY_MOBILE_NUMBER
			+ " INTEGER,"
			+ KEY_HOME_NUMBER
			+ " INTEGER,"
			+ KEY_EMAIL
			+ " TEXT,"
			+ KEY_ADRESS
			+ " TEXT,"
			+ KEY_DOB
			+ " TEXT,"
			+ KEY_AGE
			+ " INTEGER,"
			+ KEY_GENDER + " TEXT," + KEY_NOTES + " TEXT);";

	private final String DATABASE_CREATE_IGNORE_LIST = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME_IGNORE
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_FIRST_NAME
			+ " TEXT,"
			+ KEY_MIDDLE_NAME
			+ " TEXT,"
			+ KEY_PREFERED_NAME
			+ " TEXT,"
			+ KEY_LAST_NAME
			+ " TEXT,"
			+ KEY_MOBILE_NUMBER
			+ " INTEGER,"
			+ KEY_HOME_NUMBER
			+ " INTEGER,"
			+ KEY_EMAIL
			+ " TEXT,"
			+ KEY_ADRESS
			+ " TEXT,"
			+ KEY_DOB
			+ " TEXT,"
			+ KEY_AGE
			+ " INTEGER,"
			+ KEY_GENDER + " TEXT," + KEY_NOTES + " TEXT);";

	public ContactDataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_CONTACT_LIST);
		db.execSQL(DATABASE_CREATE_IGNORE_LIST);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CONTACTS);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_IGNORE_LIST);
		onCreate(db);
	}

	/**
	 * Add a new contact, return the auto assignmed id in the database
	 */
	public long addContact(Contact contact, TableType t) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FIRST_NAME, contact.getFirstName());
		values.put(KEY_PREFERED_NAME, contact.getPreferredName());
		values.put(KEY_MIDDLE_NAME, contact.getMiddleName());
		values.put(KEY_LAST_NAME, contact.getLastName());
		values.put(KEY_MOBILE_NUMBER, contact.getMobileNumber());
		values.put(KEY_HOME_NUMBER, contact.getHomeNumber());
		values.put(KEY_EMAIL, contact.getEmail());
		values.put(KEY_ADRESS, contact.getAdress());
		values.put(KEY_DOB, contact.getDOB());
		values.put(KEY_AGE, contact.getAge());
		values.put(KEY_GENDER, contact.getGender());
		values.put(KEY_NOTES, contact.getNotes());

		long id = 0;
		if (t == TableType.ContactList) {
			id = db.insert(TABLE_NAME_CONTACTS, null, values);
		} else if (t == TableType.IgnoreList) {
			id = db.insert(TABLE_NAME_IGNORE, null, values);
		}
		db.close();
		return id;
	}

	/*
	 * Gets a single contact
	 */
	public Contact getContact(int id, TableType t) {
		SQLiteDatabase db = this.getReadableDatabase();
		String tableName = null;
		if (t == TableType.ContactList) {
			tableName = TABLE_NAME_CONTACTS;
		} else if (t == TableType.IgnoreList) {
			tableName = DATABASE_CREATE_IGNORE_LIST;
		}
		Cursor cursor = db.query(tableName, new String[] { KEY_ID,
				KEY_FIRST_NAME, KEY_MIDDLE_NAME, KEY_PREFERED_NAME,
				KEY_LAST_NAME, KEY_MOBILE_NUMBER, KEY_HOME_NUMBER, KEY_EMAIL,
				KEY_ADRESS, KEY_DOB, KEY_AGE, KEY_GENDER, KEY_NOTES }, KEY_ID
				+ "=?", new String[] { String.valueOf(id) }, null, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		Contact contact = new Contact.Builder().firstName(cursor.getString(1))
				.middleName(cursor.getString(2))
				.preferedName(cursor.getString(3))
				.lastName(cursor.getString(4)).mobileNumber(cursor.getInt(5))
				.homeNumber(cursor.getInt(6)).email(cursor.getString(6))
				.adress(cursor.getString(7)).dob(cursor.getString(8))
				.age(cursor.getInt(9)).gender(cursor.getString(10))
				.notes(cursor.getString(11)).build();

		return contact;
	}

	/**
	 * returns a list of all contacts from either the ignore table or contacts
	 * table. The ording of the list can also be specified.
	 */
	public List<Contact> getAllContacts(TableType t, SortOption s) {
		List<Contact> contactList = new ArrayList<Contact>();
		String tableName = tableNameLogic(t);

		String selectQuery = "SELECT  * FROM " + tableName;

		switch (s) {
		case Age:
			selectQuery = selectQuery + " ORDER BY " + KEY_AGE;
			break;
		case Default:
			break;
		case FirstName:
			selectQuery = selectQuery + " ORDER BY " + KEY_FIRST_NAME;
			break;
		case LastName:
			selectQuery = selectQuery + " ORDER BY " + KEY_LAST_NAME;
			break;
		case PreferedName:
			selectQuery = selectQuery + " ORDER BY " + KEY_PREFERED_NAME;
			break;
		default:
			break;
		}

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact.Builder()
						.firstName(cursor.getString(1))
						.middleName(cursor.getString(2))
						.preferedName(cursor.getString(3))
						.lastName(cursor.getString(4))
						.mobileNumber(cursor.getInt(5))
						.homeNumber(cursor.getInt(6))
						.email(cursor.getString(6)).adress(cursor.getString(7))
						.dob(cursor.getString(8)).age(cursor.getInt(9))
						.gender(cursor.getString(10))
						.notes(cursor.getString(11)).build();
				contact.setId(cursor.getLong(0));
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		db.close();
		return contactList;
	}

	/*
	 * gets a count of the number of contacts
	 */
	public int getContactsCount(TableType t) {
		String tableName = tableNameLogic(t);
		String countQuery = "SELECT  * FROM " + tableName;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		return cursor.getCount();
	}

	/**
	 * updates a contact in the database
	 * 
	 * @param contact
	 * @return
	 */
	public long updateContact(Contact contact, TableType t) {
		SQLiteDatabase db = this.getWritableDatabase();
		String tableName = tableNameLogic(t);

		ContentValues values = new ContentValues();
		values.put(KEY_FIRST_NAME, contact.getFirstName());
		values.put(KEY_PREFERED_NAME, contact.getPreferredName());
		values.put(KEY_MIDDLE_NAME, contact.getMiddleName());
		values.put(KEY_LAST_NAME, contact.getLastName());
		values.put(KEY_MOBILE_NUMBER, contact.getMobileNumber());
		values.put(KEY_HOME_NUMBER, contact.getHomeNumber());
		values.put(KEY_EMAIL, contact.getEmail());
		values.put(KEY_ADRESS, contact.getAdress());
		values.put(KEY_DOB, contact.getDOB());
		values.put(KEY_AGE, contact.getAge());
		values.put(KEY_GENDER, contact.getGender());
		values.put(KEY_NOTES, contact.getNotes());

		return db.update(tableName, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getId()) });
	}

	/**
	 * deletes a contact in the database
	 * 
	 * @param contact
	 */
	public void deleteContact(Contact contact, TableType t) {
		SQLiteDatabase db = this.getWritableDatabase();
		String tableName = tableNameLogic(t);
		db.delete(tableName, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getId()) });
		db.close();
	}

	/**
	 * creates a string variable that references a table name in the database
	 * depending on the TableType enum;
	 * 
	 * @param t
	 * @return
	 */
	private String tableNameLogic(TableType t) {
		String tableName = null;
		if (t == TableType.ContactList) {
			tableName = TABLE_NAME_CONTACTS;
		} else if (t == TableType.IgnoreList) {
			tableName = TABLE_NAME_IGNORE;
		}
		return tableName;
	}

	public enum TableType {
		IgnoreList, ContactList
	}

}
