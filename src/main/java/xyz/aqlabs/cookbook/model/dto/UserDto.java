package xyz.aqlabs.cookbook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.aqlabs.cookbook.model.Role;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String password;
    private Role role;
}
