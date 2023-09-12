package za.co.demo.bookstore.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "firstName cannot be null or empty")
    private String firstName;

    @NotBlank(message = "lastName cannot be null or empty")
    private String lastName;

    @NotBlank(message = "phoneNumber cannot be null or empty")
    private String phoneNumber;

    @NotBlank(message = "address cannot be null or empty")
    private String address;
}