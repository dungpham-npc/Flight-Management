package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Crew;
import model.Crew.Position;


public class CrewDaoImpl extends ArrayList<Crew> implements CrewDao,Serializable {

    @Override
    public void addCrewMember(Crew crewMember) {
        this.add(crewMember);
    }

    @Override
    public void updateCrewMember(Crew crewMember) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(crewMember)) {
                this.set(i, crewMember);
                return;
            }
        }
    }

    @Override
    public void removeCrewMember(Crew crewMember) {
        this.remove(crewMember);
    }

    @Override
    public List<Crew> getAllCrewMembers() {
        return this;
    }

    @Override
    public List<Crew> getCrewsWithSpecificPosition(Position position) {
        List<Crew> filteredCrewList = new ArrayList<>();

        for (Crew crewMember : this) {
            if (crewMember.getPosition() == position) {
                filteredCrewList.add(crewMember);
            }
        }

        return filteredCrewList;
    }
}
