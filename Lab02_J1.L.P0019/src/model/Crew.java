
package model;

import java.io.Serializable;

/**
 *
 * @author canhd
 */
public class Crew implements Serializable{

    private String name;
    private Position position;

    public enum Position implements Serializable{
        PILOT,
        ATTENDANT,
        GROUNDSTAFF
    }

    public Crew(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    // Getters and setters for name and position
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+-----------------------+------------------+\n");
        sb.append("|         Field         |      Value       |\n");
        sb.append("+-----------------------+------------------+\n");
        sb.append(String.format("| Name                  | %-16s |\n", name));
        sb.append(String.format("| Position              | %-16s |\n", position));
        sb.append("+-----------------------+------------------+\n");
        return sb.toString();
    }
}
