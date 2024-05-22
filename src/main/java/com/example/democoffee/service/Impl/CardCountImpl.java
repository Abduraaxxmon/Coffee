package com.example.democoffee.service.Impl;

import com.example.democoffee.entity.CardCount;
import com.example.democoffee.entity.Coffee;
import com.example.democoffee.entity.User;
import com.example.democoffee.repository.CardCountRepository;
import com.example.democoffee.repository.CoffeeRepository;
import com.example.democoffee.repository.UserRepository;
import com.example.democoffee.service.CardCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardCountImpl implements CardCountService {

    private final UserRepository userRepository;
    private final CoffeeRepository coffeeRepository;
    private final CardCountRepository cardCountRepository;

    @Override
    public Long getCardCount(Long userId, Long coffeeId) {
        User user = userRepository.getReferenceById(userId);
        Coffee coffee = coffeeRepository.getReferenceById(coffeeId);
        CardCount cardCount = cardCountRepository.findCardCountByUserAndCoffee(user, coffee);

        return cardCount.getCount();
    }


    @Override
    public void addCard(Long userId, Long coffeeId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow(() -> new ResourceNotFoundException("Coffee not found"));
        CardCount cardCount = cardCountRepository.findCardCountByUserAndCoffee(user, coffee);

        if (cardCount == null) {
            cardCount = new CardCount();
            cardCount.setUser(user);
            cardCount.setCoffee(coffee);
            cardCount.setCount(1L);
            cardCountRepository.save(cardCount);
        } else {
            cardCount.setCount(cardCount.getCount() + 1);
            cardCountRepository.save(cardCount);
        }
    }

    @Override
    public void minusCard(Long userId, Long coffeeId) {

        User user = userRepository.getReferenceById(userId);
        Coffee coffee = coffeeRepository.getReferenceById(coffeeId);
        CardCount cardCount = cardCountRepository.findCardCountByUserAndCoffee(user, coffee);
        if (cardCount.getCount() <= 0) {
            cardCount.setCount(0L);
        } else {
            cardCount.setCount(cardCount.getCount() - 1);
        }

    }
}