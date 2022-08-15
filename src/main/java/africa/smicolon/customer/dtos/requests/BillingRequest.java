package africa.smicolon.customer.dtos.requests;

import lombok.Data;

@Data
public class BillingRequest {
    private Long userId;
    private String tariff;
}
