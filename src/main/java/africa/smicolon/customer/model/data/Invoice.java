package africa.smicolon.customer.model.data;

import lombok.Data;

@Data
public class Invoice {
    private String customerName;
    private String accountNumber;
    private String accountToPay;
}
