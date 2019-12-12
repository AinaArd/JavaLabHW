package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.models.User;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private String name;

    public static UserDto from(User user) {
        return UserDto.builder().name(user.getName()).build();
    }
}
