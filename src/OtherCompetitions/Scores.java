package OtherCompetitions;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Scores {
    private Map<String, Date> scores;

    public void add(String name){
        scores.put(name, new Date());
    }

    public Map<String, Date> getAll() {
        return scores;
    }

    public Scores() {
        scores = Collections.synchronizedMap(new HashMap<>());
    }
}
