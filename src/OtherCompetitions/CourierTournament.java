package OtherCompetitions;

import Animals.Animal;
import Graphics.AnimalThread;
import Graphics.CompetitionFrame;
import Graphics.CompetitionPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CourierTournament extends Tournament {
    private AtomicBoolean startFlag;
    private Scores scores;
    private List<Referee> refereeThreads; // רשימת threads של שופטים
    private List<AnimalThread> animalThreads;

    public CourierTournament(Animal[][] animalGroups,String tournamentName,CompetitionFrame competitionFrame) {
        super(animalGroups,tournamentName,competitionFrame);



    }


    @Override
    protected void setup(Animal[][] animalGroups, String tournamentName, CompetitionFrame competitionFrame) {
        List<List<AtomicBoolean>> finishFlagsList = new ArrayList<>();
        startFlag = new AtomicBoolean(false);
        this.scores = new Scores();
        refereeThreads = new ArrayList<>();
        for (int indexGroup = 0; indexGroup < animalGroups.length; indexGroup++) {
            Animal[] animalGroup = animalGroups[indexGroup];
            int n = animalGroup.length;

            AtomicBoolean[] finishFlag = new AtomicBoolean[n];
            for (int i = 0; i < n; i++) {
                finishFlag[i] = new AtomicBoolean(false);
            }
            finishFlagsList.add(List.of(finishFlag));


            for (int i = 0; i < n; i++) {
                AtomicBoolean startFlagForAnimal;
                if (i==0)
                {
                    startFlagForAnimal = startFlag;
                }
                else
                {
                    startFlagForAnimal = finishFlagsList.get(indexGroup).get(i-1);
                }
                AtomicBoolean finishFlagForAnimal = finishFlagsList.get(indexGroup).get(i);
                double neededDistance = (double) (1430 * 2 + 730*2)/n;

                AnimalThread animalThread = new AnimalThread(animalGroup[i],neededDistance,startFlagForAnimal,finishFlagForAnimal,competitionFrame);
               // animalThreads.add(animalThread);

                Referee referee = new Referee("Team name:" + animalGroup[0].animalCategory(),scores,finishFlagsList.get(indexGroup).get(n - 1),this);
                refereeThreads.add(referee);

            }

        }
        startFlag.set(true);
    }

    public void removeReferee(Thread refereeThread){
        for (int i = 0; i < refereeThreads.size(); i++) {
            if (refereeThreads.get(i).equals(refereeThread)) {
                refereeThreads.remove(i);
                break;
            }
        }
    }
}
