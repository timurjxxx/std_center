package com.backend.studycenter.common.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;


public class CronDemo {

//  Note that in this example, we're scheduling a task to be executed at 10:15 AM on the 15th day of every month.

//  @Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris")

//  <minute> <hour> <day-of-month> <month> <day-of-week> <command>


//  @reboot – run once at the startup
//  @yearly or @annualy – run once a year
//  @monthly – run once a month
//  @weekly – run once a week
//  @daily or @midnight – run once a day
//  @hourly – run hourly


  @Scheduled(cron = "0 15 10 15 * ?")
  @Async
  public void scheduleTaskUsingCronExpression() {

    long now = System.currentTimeMillis() / 1000;
    System.out.println(
        "schedule tasks using cron jobs - " + now);
  }
}
