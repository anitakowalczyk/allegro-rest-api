package pl.anitakowalczyk.surwejlansapi.service.email;

import lombok.Data;

@Data
public class Email {

    String to;
    String subject;
    String content;

    public Email() {
    }

    public Email(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }
}
