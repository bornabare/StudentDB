package hr.fer.oop.week9.db;

/**
 * Created by borna on 1/10/15.
 */
public class LastNameFilter implements IFilter {

    String lastNameWildcard;

    public LastNameFilter(String lastNameWildCard) {
        this.lastNameWildcard = lastNameWildCard;
    }

    @Override
    public boolean accepts(StudentRecord record) {
        String[] string = lastNameWildcard.split("\\*");

        /*
        if string length is 2, that means asterisk is in between beginning and end of lastName
         */
        if (string.length == 2) {
            if (!isBeginningSame(string, record)) {
                return false;
            }
            if (!isEdingSame(string, record)) {
                return false;
            }
            return true;
        }
        /*
        if only 1 string that means asterisk is given either on the beginning or at the end of the lastName

        1.1 define where is asterisk? start or end
        1.2 calculate first or second for loop
         */

        else if (string.length == 1) {

            if (string[0].charAt(0) == '*') {
                if (isEdingSame(string, record)) {
                    return true;
                }
            } else {
                if (isBeginningSame(string, record)) {
                    return true;
                }
            }
        }

        else {
            System.out.println("Wrong asterisk consumation !");
            System.exit(1);
        }

        return false; //nebitno
    }

    public boolean isBeginningSame(String[] string, StudentRecord record) {
        for (int i = 0; i < string[0].length(); i++) {
            if (!(string[0].charAt(i) == record.getLastName().charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isEdingSame(String[] string, StudentRecord record) {
        for (int i = 0; i < string[1].length(); i++) {
            if (!(string[1].charAt(string[1].length() - i - 1) == record.getLastName().charAt(record.getLastName().length() - i - 1))) {
                return false;
            }
        }
        return true;
    }
}