package ru.kolotovkin.lastproject.service;

import org.springframework.stereotype.Service;
import ru.kolotovkin.lastproject.model.Message;
import ru.kolotovkin.lastproject.repository.MessageRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMassage(){
        return messageRepository.findAll();
    }

    public void addMassage(Message message){
        DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy, HH:mm");
        String date = dateFormat.format(new Date());
        message.setDate(date);
        messageRepository.save(message);
    }

    public List<Message> filterMessage(String filter){
        ArrayList<Message> messages = new ArrayList<>();
        if(filter.length() != 0){
            for(Message s : messageRepository.findByAuthorName(filter)){
                messages.add(s);
            }
        } else
            for(Message s : messageRepository.findAll()){
                messages.add(s);
            }
        return messages;
    }
}
