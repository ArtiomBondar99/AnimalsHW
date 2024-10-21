package OtherCompetitions;

import Animals.Animal;
import Graphics.AnimalThread;
import Graphics.CompetitionFrame;
import Graphics.CompetitionPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegularTournament extends Tournament{
    AtomicBoolean startFlag ;
    Scores scores;
    int groups;
    double distanceNeeded;
    private AnimalThread animalThread;
    private Referee refereeThread;

    public RegularTournament(Animal[][] animalGroups, String tournamentName, double distanceNeeded, CompetitionFrame cp) {
        super(animalGroups,tournamentName,cp);



        this.distanceNeeded = distanceNeeded;
    }


    @Override
    protected void setup(Animal[][] animalGroups, String tournamentName,CompetitionFrame cf) {
        this.scores = new Scores();
        this.startFlag = new AtomicBoolean(false);
        this.groups = animalGroups.length;
        for (int indexGroup = 0; indexGroup < animalGroups.length; indexGroup++)
        {
            Animal animalGroup = animalGroups[indexGroup][0];

            AtomicBoolean finishFlag = new AtomicBoolean(false);

            animalThread = new AnimalThread(animalGroup,(double)distanceNeeded,startFlag,finishFlag,cf);

            refereeThread = new Referee("Team name:" + animalGroup.animalCategory(),scores,finishFlag,this);

        }

    }
}
