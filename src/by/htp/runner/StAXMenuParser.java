package by.htp.runner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.bean.Edition;
import by.htp.enumeration.LibraryTagName;

public class StAXMenuParser {
	public static void main(String[] args) throws FileNotFoundException {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream("resources/library.xml");
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			List<Edition> library = process(reader);
			for (Edition e : library) {
				System.out.println(e.getName());
				// System.out.println(e.getCalories());
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private static List<Edition> process(XMLStreamReader reader) throws XMLStreamException {
		List<Edition> library = new ArrayList<Edition>();
		Edition edition = null;
		LibraryTagName elementName = null;
		while (reader.hasNext()) {
			// определение типа "прочтённого" элемента (тега)
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = LibraryTagName.getElementTagName(reader.getLocalName());
				switch (elementName) {
				case EDITION:
					edition = new Edition();
					Integer id = Integer.parseInt(reader.getAttributeValue(null, "id"));
					edition.setId(id);
					break;
				}
				break;
			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();
				if (text.isEmpty()) {
					break;
				}
				switch (elementName) {

				case NAME:
					edition.setName(text);
					break;
				case TYPE:
					edition.setType(text);
					break;
				case AUTHOR:
					edition.setAuthor(text);
					break;
				case YEAR:
					Integer year = Integer.parseInt(text);
					edition.setYear(year);
					break;

				case COUNT:
					Integer count = Integer.parseInt(text);
					edition.setCount(count);
					break;

				case COUNTOFREADINGDAYS:
					Integer countOfReadingDays = Integer.parseInt(text);
					edition.setCountOfReadingDays(countOfReadingDays);
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				elementName = LibraryTagName.getElementTagName(reader.getLocalName());
				switch (elementName) {
				case EDITION:
					library.add(edition);
				}
			}
		}
		return library;
	}
}