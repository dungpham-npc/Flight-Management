
package dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author canhd
 */
public class BoardingPassDaoImpl extends HashMap<String, List<String>> implements BoardingPassDao, Serializable{
    
    @Override
    public void addBoardingPass(String reservationID, List<String> boardingPassInformation){
        this.put(reservationID, boardingPassInformation);
    }
}
