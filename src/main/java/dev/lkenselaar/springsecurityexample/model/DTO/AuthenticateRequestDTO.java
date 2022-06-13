package dev.lkenselaar.springsecurityexample.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequestDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
