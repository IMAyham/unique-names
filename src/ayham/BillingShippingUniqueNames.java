package ayham;
import java.io.BufferedReader;
import java.io.FileReader;

public class BillingShippingUniqueNames {

	public static int countUniqueNames(String billFirstName, String billLastName, String shipFirstName,
			String shipLastName, String billNameOnCard) {
		String lowerCaseBillFullName = billFirstName.toLowerCase() + " " + billLastName.toLowerCase();
		String lowerCaseShipFullName = shipFirstName.toLowerCase() + " " + shipLastName.toLowerCase();
		String lowerCaseCardName = billNameOnCard.toLowerCase();

		if (lowerCaseBillFullName.equals(lowerCaseShipFullName)) {
			return 1;
		}
		String file = "src\\names.csv";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				for (int i = 0; i < row.length; i++) {
					if (row[i].equals(shipFirstName.split(" ")[0].toLowerCase()) || typo(shipFirstName.split(" ")[0].toLowerCase(), row[i])) {
						for (int j = 0; j < row.length && !row[j].equals(shipFirstName); j++) {
							if (lowerCaseCardName.contains(row[j])
									&& (lowerCaseCardName.contains(shipLastName.toLowerCase())
											|| typoCheck(shipLastName.toLowerCase(), lowerCaseCardName))) {
								reader.close();
								return 1;
							}
						}
					}
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 2;
	}

	private static boolean typoCheck(String name, String cardName) {
		
		String[] cardNameParts = cardName.split(" ");
		for (int i = 0; i < cardNameParts.length; i++) {
			if (typo(name, cardNameParts[i])) {
				return true;
			}
		}
		return false;
	}

	private static boolean typo(String name1, String name2) {
		int count = 0;
		if (name1.length() != name2.length())
			return false;
		for (int i = 0; i < name1.length(); i++) {
			if (name1.charAt(i) != name2.charAt(i))
				count++;
		}
		return count == 1;
	}

	public static void main(String[] args) {
		System.out.println(countUniqueNames("Deborah", "Egli", "Deborah", "Egli", "Deborah Egli"));
		System.out.println(countUniqueNames("Deborah", "Egli", "Debbie", "Egni", "Debbie Egli"));
		System.out.println(countUniqueNames("Deborah", "Egni", "Deborah", "Egli", "Deborah Egli"));
		System.out.println(countUniqueNames("Deborah S", "Egli", "Deborah", "Egli", "Egli Deborah"));
		System.out.println(countUniqueNames("Michele", "Egli", "Deborah", "Egli", "Michele Egli"));

	}
}
