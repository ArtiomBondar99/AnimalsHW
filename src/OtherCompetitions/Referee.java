package OtherCompetitions;

import java.util.concurrent.atomic.AtomicBoolean;

public class Referee implements Runnable {

    private Scores scores;
    private String name;
    private AtomicBoolean finishFlag;
    private Tournament tournament;
    //private RegularTournament regularTournament;
    Thread thread;

    public Referee(String name, Scores scores, AtomicBoolean finishFlag, Tournament tournament) {
        this.scores = scores;
        this.name=name;//שם של הקבוצה אליה הוא ממתין
        this.finishFlag=finishFlag;
        this.tournament=tournament;
        //this.regularTournament=regularTournament;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public AtomicBoolean getFinishFlag() {return finishFlag;}

    @Override
    public void run() {

        synchronized(finishFlag)
        {
            while(!finishFlag.get())
            {
                try {
                    finishFlag.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

        }
        scores.add(name);
        tournament.removeReferee(Thread.currentThread());

    }


    public String getGroupName() {return name;}
}
