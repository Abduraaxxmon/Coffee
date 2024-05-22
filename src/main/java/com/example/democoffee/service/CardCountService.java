package com.example.democoffee.service;



public interface CardCountService {
     Long getCardCount(Long userId,Long coffeeId);
     void addCard(Long userId,Long coffeeId);
     void minusCard(Long userId,Long coffeeId);
}
