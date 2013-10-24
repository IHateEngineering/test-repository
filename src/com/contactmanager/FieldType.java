package com.contactmanager;

/**
 * enums for the different field types in a contact object
 *
 */
public enum FieldType {
	FirstName("First Name"), PreferredName("Preferred Name"),MiddleName("Middle Name"),
	LastName("Last Name"), MobileNumber("Mobile Number"), HomeNumber("Home Number"),
	Email("Email"), Adress("Adress"), DOB("DOB"), Age("Age"), Gender("Gender"), Notes("Notes") ;
	private String _text;
	private FieldType(String text){
		_text = text;
	}
	public String getText(){
		return _text;
	}
	public static FieldType fromString(String text) {
		    if (text != null) {
		      for (FieldType f : FieldType.values()) {
		        if (text.equalsIgnoreCase(f.getText())) {
		          return f;
		        }
		      }
		    }
		    return null;
		  }
}
