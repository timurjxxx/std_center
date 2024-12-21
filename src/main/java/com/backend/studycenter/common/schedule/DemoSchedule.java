package com.backend.studycenter.common.schedule;

import org.springframework.stereotype.Component;

@Component
public class DemoSchedule {

// fixedRate: This is used to run the scheduled jobs in every n milliseconds.
// It does not matter whether the job has already finished its previous turn or not.

// fixedDelay: It is used to run the scheduled job sequentially with the given n milliseconds delay time between turns.
// Which means, the time spent on the job will affect the start time of the next run of scheduled job.


//  @Scheduled(fixedDelay = 5000)
  public void updateEmployeeInventory(){
    System.out.println("employee inventory will be updated once only the last updated finished ");
    /**
     * add your scheduled job logic here
     */
  }


//  @Scheduled(fixedRate=5000)
  public void updateEmployeeInventory2(){
    System.out.println("employee inventory will be updated every 5 seconds from prior updated has stared, regardless it is finished or not");
    /**
     * add your scheduled job logic here
     */
  }


//  private static int i = 0;

//  @Scheduled(initialDelay=1000, fixedRate=1000)
//  public void testScheduling() throws InterruptedException {
//    System.out.println("Started : "+ ++i);
//    Thread.sleep(4000);
//    System.out.println("Finished : "+ i);
//  }

}
