package com.habitsapp.confiq.emailNotifications;

import com.habitsapp.model.Goal;
import com.habitsapp.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalNotificationService {

    private GoalRepository goalRepository;

    private EmailService emailService;

    /**
     * The notification the user will receive when the goal is nearing completion
     * @param goal the purpose for which the notification relates
     */
    private void sendNotification(Goal goal){
        String subject = "Your goal is coming!";
        String message = String.format("Hi! We wanted to remind you " +
                "of your goal: %s, which ends in %s.",
                goal.getName(),goal.getEndDate());

        emailService.sendMessage(goal.getEmail(), subject, message);
    }

    /**
     *Determining when a given notification will be sent to the user
     */
    public void checkGoals(){
        List<Goal> goals = goalRepository.findAll();

        LocalDate now = LocalDate.now();
        LocalDate notification = now.plusDays(2);

        for (Goal goal:goals){
            if (goal.getEndDate()!=null && !goal
                    .getEndDate()
                    .isBefore(now)&& goal
                    .getEndDate()
                    .isBefore(notification)){
                sendNotification(goal);
            }
        }
    }
}
