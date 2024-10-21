package OtherCompetitions;

import Animals.Animal;
import Graphics.CompetitionFrame;
import Graphics.CompetitionPanel;

public abstract class Tournament {
    protected TournamentThread tournamentThread;

    public Tournament(Animal[][] animalGroups, String tournamentName, CompetitionFrame competitionFrame) {
        setup(animalGroups,tournamentName,competitionFrame);
    }

    protected abstract void setup(Animal[][] animalGroups, String tournamentName,CompetitionFrame competitionFrame);

    public TournamentThread getTournamentThread()
    {
        return tournamentThread;
    }

    public void removeReferee(Thread refereeThread) {
    }

}
