package com.habitsapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private GoalNotificationService goalNotificationService;

    @Scheduled(cron = "0 0 0 * * *")
    public void checkGoal(){
        goalNotificationService.checkGoals();
    }
}
