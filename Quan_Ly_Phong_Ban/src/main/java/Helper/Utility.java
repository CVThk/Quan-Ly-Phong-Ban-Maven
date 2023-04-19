
package Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Chau Thinh
 */
public class Utility {

    private static Utility instance;

    public static Utility getInstance() {
        if (instance == null) {
            instance = new Utility();
        }
        return instance;
    }

    private Utility() {
    }

    public int createID(String query, Object[] params) throws Exception {
        int id = JDBCHelper.getInstance().ExecuteScalar(query, params);
        return id + 1;
    }

    public boolean checkNumber(String number) {
        if (number.matches("^[1-9][0-9]+$")) {
            return true;
        }
        return false;
    }
    
    public String formatNumber(Object value) {
        return String.format("%.0f", value);
    }
    
    public String formatMoney(float value) {
        return String.format("%,.0f", value);
    }

    public String convertDateToString(Date value, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        String dateString = df.format(value);
        return dateString;
    }

    public Date convertStringToDate(String value) throws ParseException {
        if (value.contains(" ")) {
            value = value.split(" ")[0];
        }
        SimpleDateFormat format = null;
        if (value.contains("-")) {
            if (value.split("-")[0].length() > 2) {
                format = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                format = new SimpleDateFormat("dd-MM-yyyy");
            }
        }
        else if(value.contains("/")) {
            if (value.split("-")[0].length() > 2) {
                format = new SimpleDateFormat("yyyy/MM/dd");
            } else {
                format = new SimpleDateFormat("dd/MM/yyyy");
            }
        }

        Date date = null;
        try {
            date = format.parse(value);
        } catch (Exception ex) {
            return null;
        }
        return date;
    }

    public String convertDateToString(String value, String pattern) throws ParseException {
        Date valueDate = convertStringToDate(value);
        String dateString = convertDateToString(valueDate, pattern);
        return dateString;
    }
}
