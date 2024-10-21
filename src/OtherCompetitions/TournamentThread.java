package OtherCompetitions;

import java.util.ArrayList;
import java.util.List;

public class TournamentThread implements Runnable {
    private Scores scores;
    private Boolean startSignal;
    private int groups;
    private List<Referee> referees;
    Thread thread;


    public TournamentThread(Scores scores, Boolean startSignal, int groups, List<Referee> referees) {
        this.scores = scores;
        this.startSignal = startSignal;
        this.groups = groups;
        this.referees = referees;
        this.thread = new Thread(this);
        this.thread.start();
    }


    @Override
    public void run() {
        synchronized (startSignal) {
            startSignal = true;
            startSignal.notifyAll();
        }

        List<Integer> idList = new ArrayList<>();
        int finishedGroups = 0;
        while(finishedGroups < groups)
        {
            for (int i = 0; i< referees.size(); i++)
            {
                if(referees.get(i).getFinishFlag().get() && !idList.contains(i))
                {
                    finishedGroups++;
                    idList.add(i);
                    //                JLabel animalLabel = new JLabel("Select animal:");
                    //                f.add(animalLabel);
//                    if (!scores.getAll().containsKey(referees.get(i).getGroupName())) {
//                        scores.add(referees.get(i).getGroupName());
//                    }
                }
            }

            try {
                Thread.sleep(1000); // Sleep for 1 second (adjust as needed)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
