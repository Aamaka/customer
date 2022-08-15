package africa.smicolon.customer.dtos.requests;

import africa.smicolon.customer.model.data.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String homeAddress;
    private String phoneNumber;
    private Gender gender;
    private String password;
}
