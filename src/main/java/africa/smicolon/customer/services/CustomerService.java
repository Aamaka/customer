package africa.smicolon.customer.services;

import africa.smicolon.customer.dtos.requests.AddUserRequest;
import africa.smicolon.customer.dtos.requests.BillingRequest;
import africa.smicolon.customer.dtos.responses.UserResponse;
import africa.smicolon.customer.exceptions.NoCustomerFoundException;
import africa.smicolon.customer.model.data.Customer;
import africa.smicolon.customer.model.data.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    UserResponse addUser(AddUserRequest addUserRequest);

    String addBillingToCustomer(BillingRequest customerId);

    Invoice generateInvoice (Long customerId) throws NoCustomerFoundException;
    UserResponse findACustomer(String email) throws NoCustomerFoundException;
    List<Customer> findAllUser();

    Customer findACustomer(Long id) throws NoCustomerFoundException;
}
