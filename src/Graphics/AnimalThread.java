package Graphics;

import Animals.Animal;
import Mobility.Point;

import java.util.concurrent.atomic.AtomicBoolean;

public class AnimalThread implements Runnable{
    Animal participant ;
    double neededDistance;
    AtomicBoolean startFlag;
    AtomicBoolean finishFlag;
    Thread thread;
    public static int sleepValue = 200;
    CompetitionFrame cp;

    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean startFlag,AtomicBoolean finishFlag, CompetitionFrame cp)
    {
        this.cp = cp;
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.thread = new Thread(this);
        this.thread.start();
    }


    @Override
    public void run() {

        synchronized(startFlag)
         {
            while(!startFlag.get() )
            {
                //stuck here until competition starts
                try {
                    startFlag.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        while(!finishFlag.get() && !Thread.interrupted())
        {
            synchronized(participant)
            {
                //move animal
//                participant.move(new Point(0, 0));
//                neededDistance -= participant.getSpeed();
                Point currentLocation = participant.getLocation();
                Point newLocation = new Point(currentLocation.getX() + (int)participant.getSpeed(), currentLocation.getY());
                participant.move(newLocation);
                neededDistance -= participant.getSpeed();
                cp.revalidate();
                cp.repaint();
            }

            if(neededDistance <= 0)
            {
                synchronized(finishFlag)
                {
                    finishFlag.set(true);
                    finishFlag.notifyAll();
                    return;
                }
            }

            try
            {
                Thread.sleep(sleepValue);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }


    }
}
