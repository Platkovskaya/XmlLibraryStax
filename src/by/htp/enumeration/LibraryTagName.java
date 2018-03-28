package by.htp.enumeration;

public enum LibraryTagName {
	EDITION, TYPE, NAME, AUTHOR, YEAR, COUNT, COUNTOFREADINGDAYS, READATHOME, LIBRARY;

	public static LibraryTagName getElementTagName(String element) {
		switch (element) {
		case "edition":
			return EDITION;

		case "type":
			return TYPE;

		case "name":
			return NAME;

		case "author":
			return AUTHOR;

		case "year":
			return YEAR;

		case "count":
			return COUNT;

		case "countOfReadingDays":
			return COUNTOFREADINGDAYS;

		case "readAtHome":
			return READATHOME;

		case "library":
			return LIBRARY;
		default:
			throw new EnumConstantNotPresentException(LibraryTagName.class, element);
		}

	}

}
