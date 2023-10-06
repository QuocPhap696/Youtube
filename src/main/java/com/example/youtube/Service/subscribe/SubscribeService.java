package com.example.youtube.Service.subscribe;

import com.example.youtube.model.Subscribe;
import com.example.youtube.model.User;
import com.example.youtube.repository.SubscribeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;

    public SubscribeService(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }
    public void save (Subscribe subscribe){
        subscribeRepository.save(subscribe);
    }
    public Subscribe findSubscriptionByUserIdAndUserSubId(User user, User userSub){
      return   subscribeRepository.findSubscriptionByUserIdAndUserSubId(user.getId(),userSub.getId());
    }
    public void delete(Subscribe subscribe){
        subscribeRepository.delete(subscribe);
    }
    public List<Subscribe> findSubscriptionsByUser(User user){
        return subscribeRepository.findSubscriptionsByUserId(user.getId());
    }
}
