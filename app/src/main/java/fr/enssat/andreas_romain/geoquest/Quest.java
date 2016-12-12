package fr.enssat.andreas_romain.geoquest;

import java.util.List;

/**
 * Created by andreas on 12/12/16.
 */

public class Quest {
    String name;
    String description;
    String[][] coords;

    public Quest(String name, String description, String[][] coords){
        this.name = name;
        this.description = description;
        this.coords = coords;
    };
}
