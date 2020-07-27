package ru.itis.rabbit_spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomMessage implements Serializable {
    private String text;
    private Timestamp date;
    private String sender;
}
 
