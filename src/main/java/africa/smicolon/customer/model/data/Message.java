package africa.smicolon.customer.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String firstName;
    private String lastName;
    private String email;
    private String homeAddress;
    private Gender gender;
    private List<Billing> billings;
    private Billing billing;



}
