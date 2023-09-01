package xyz.aqlabs.cookbook.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.aqlabs.cookbook.model.User;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private User user;
    private String token;
}
