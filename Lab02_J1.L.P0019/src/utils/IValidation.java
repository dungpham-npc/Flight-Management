
package utils;

import java.util.Date;

/**
 *
 * @author canhd
 */
public interface IValidation {
    int getInteger(String inputMsg, String errorMsg, boolean canBeBlank);
    
    int getInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound);
    
    double getDouble(String inputMsg, String errorMsg);
    
    double getDouble(String inputMsg, String errorMsg, double lowerBound, double upperBound);
    
    String getID(String inputMsg, String errorMsg, String format, boolean canBeBlank);
    
    String getString(String inputMsg, String errorMsg, boolean canBeBlank);
    
    public Date getDate(String message, String errorMessage, String pattern, boolean allowNull);
    
    boolean confirmYesNo(String welcome);
    
    public boolean areDatesValid(Date manufacturingDate, Date expirationDate);
    
}
