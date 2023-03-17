package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("mailService")
@Scope("prototype")// defaultta Bean Scope'u "Singleton" dır. Ama "@Scope("prototype")" yaptığımızda getBean() her çağrıldığında yeni obje üreterek gelir.
public class MailService implements MessageService{

    @Value("${app.email}")
    private String email;

    // Field Injection --> Bu çok fazla önerilmez çünkü private a set edildiği için aşağıdaki repository yi göremez
    // @Autowired // Dependency Injection
    // @Qualifier("fileRepository")
    // private Repository repository;

    // Setter Injection
    /*
    private Repository repository;

    @Autowired
    @Qualifier("fileRepository")
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    */

    // Constructor Injection --> Güvenlik ve kullanım sıklığı açısından bu daha çok tercih edilir.
    // Kod okunabilirliği ve Reflection(Advance Java bilgisi : private'ların bile okunabildiği bir kavram) önleme açısından daha güvenli

    private Repository repository;

    @Autowired // TRICK : Eğer class içinde 1 tane constructor varsa @Autowired a gerek yok
    public MailService(@Qualifier("fileRepository") Repository repository) {
        this.repository = repository;
    }

    public void sendMessage(Message message){

        System.out.println("Ben Mail Servisiyim, size mesaj gönderiyorum : " + message.getMessage());
        repository.saveMessage(message);


    }

}
