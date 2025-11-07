package com.project.service;

import com.project.Model.PlanType;
import com.project.Model.Subscription;
import com.project.Model.User;

public interface SubscriptionService {


    Subscription createSubscription(User user);

    Subscription getUsersSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
