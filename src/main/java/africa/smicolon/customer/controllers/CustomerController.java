package africa.smicolon.customer.controllers;
import africa.smicolon.customer.dtos.requests.AddUserRequest;
import africa.smicolon.customer.dtos.requests.BillingRequest;
import africa.smicolon.customer.dtos.requests.FindACustomerRequest;
import africa.smicolon.customer.dtos.requests.InvoiceRequest;
import africa.smicolon.customer.dtos.responses.UserResponse;
import africa.smicolon.customer.exceptions.NoCustomerFoundException;
import africa.smicolon.customer.model.data.Customer;
import africa.smicolon.customer.model.data.Invoice;
import africa.smicolon.customer.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/addUser")
    public UserResponse addUser(@RequestBody AddUserRequest addUserRequest){
        return customerService.addUser(addUserRequest);

    }
    @PostMapping("/addBilling")
    public String addBillingToCustomer(@RequestBody BillingRequest request){
        customerService.addBillingToCustomer(request);
        return "Successful";
    }

    @GetMapping("/getInvoice")
    public Invoice generateInvoice(@RequestBody InvoiceRequest invoiceRequest) throws NoCustomerFoundException {
        return customerService.generateInvoice(invoiceRequest.getCustomerId());
    }

    @GetMapping("/findACustomer")
    public UserResponse findACustomer(@RequestBody FindACustomerRequest findACustomerRequest) throws NoCustomerFoundException {
        return customerService.findACustomer(findACustomerRequest.getEmail());
    }

    @GetMapping("/findAllCustomer")
    public List<Customer> findAllUser() {
        return customerService.findAllUser();
    }
}
