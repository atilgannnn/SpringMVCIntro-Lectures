package com.tpe.repository;

import com.tpe.domain.Message;
import org.springframework.stereotype.Component;

@Component("fileRepository")
public class FileRepository implements Repository{
    @Override
    public void saveMessage(Message message) {

        System.out.println("Mesajınız Dosya olarak kaydedildi : " + message.getMessage());

    }
}
