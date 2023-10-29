package com.web2.RoundRobin.startup;

import com.web2.RoundRobin.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RemoveConstraintsListener {
    private final MatchRepository matchRepository;

    @EventListener
    public void addTSVectors(ContextRefreshedEvent event){
        int numOfUniqueConstraints = matchRepository.getUniqueKeys();
        for (int i = 0; i < numOfUniqueConstraints; i++)
            matchRepository.removeConstraint();
    }
}
