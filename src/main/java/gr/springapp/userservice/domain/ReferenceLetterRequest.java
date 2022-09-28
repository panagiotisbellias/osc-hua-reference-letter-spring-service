package gr.springapp.userservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceLetterRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id; 
    private String title;
    private String senderUsername;
    private String reciverUsername;
    private String carrierName;
    private String carrierEmail;
    private String state;
    private String text;
}
