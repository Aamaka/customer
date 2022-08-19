package africa.smicolon.customer.model.data;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    @NonNull
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NonNull
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @NonNull
    private String email;


    @NonNull
    @Column(name = "gender", nullable = false )
    private Gender gender;

    @NonNull
    @Column(name = "homeAddress", nullable = false)
    private String homeAddress;

    @NonNull
    @Column(name = "phoneNumber", nullable = false, unique = true)
    private String phoneNumber;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;



}
