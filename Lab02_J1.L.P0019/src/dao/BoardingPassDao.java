
package dao;

import java.util.List;

/**
 *
 * @author canhd
 */
public interface BoardingPassDao {
    void addBoardingPass(String reservationID, List<String> boardingPassInformation);
}
