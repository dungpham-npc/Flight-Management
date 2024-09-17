
package dao;

import java.util.List;
import model.Crew;
import model.Crew.Position;


public interface CrewDao {

    void addCrewMember(Crew crewMember);

    void updateCrewMember(Crew crewMember);

    void removeCrewMember(Crew crewMember);

    List<Crew> getAllCrewMembers();

    List<Crew> getCrewsWithSpecificPosition(Position position);
}
