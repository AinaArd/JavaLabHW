package ru.itis.longpolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.longpolling.models.Message;
import ru.itis.longpolling.models.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String text;
    private String author;
}
