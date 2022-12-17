package com.mycompany.message;

import com.mycompany.message.Message;
import org.springframework.data.repository.CrudRepository;


public interface MessageRepository extends CrudRepository<Message, Integer> {

    public Long countById(Integer id);
}
