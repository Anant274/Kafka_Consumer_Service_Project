package com.nt.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nt.Entity.User;
import com.nt.Repo.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {

    private final UserRepository userRepository;
    public KafkaConsumerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "hello_topic", groupId = "my-group", containerFactory = "userKafkaListenerContainerFactory")
    public void consumeUserMessage(User user) {
        System.out.println("Received User: " + user);
        userRepository.save(user);
        System.out.println("Consumed message: " + user);
        System.out.println("Message Arrived");
    }
}
