package africa.smicolon.customer.services;

import africa.smicolon.customer.dtos.requests.AddUserRequest;
import africa.smicolon.customer.dtos.requests.BillingRequest;
import africa.smicolon.customer.dtos.responses.UserResponse;

import africa.smicolon.customer.exceptions.NoCustomerFoundException;
import africa.smicolon.customer.model.data.Billing;
import africa.smicolon.customer.model.data.Customer;
import africa.smicolon.customer.model.data.Invoice;
import africa.smicolon.customer.model.data.Message;
import africa.smicolon.customer.model.repositories.BillingRepository;
import africa.smicolon.customer.model.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final BillingRepository billingRepository;

    @Override
    public UserResponse addUser(AddUserRequest addUserRequest) {
        Customer customer = new Customer();
        customer.setFirstName(addUserRequest.getFirstName());
        customer.setLastName(addUserRequest.getLastName());
        customer.setEmail(addUserRequest.getEmail());
        customer.setHomeAddress(addUserRequest.getHomeAddress());
        customer.setPhoneNumber(addUserRequest.getPhoneNumber());
        customer.setGender(addUserRequest.getGender());
        customer.setPassword(addUserRequest.getPassword());


        Customer saved = customerRepository.save(customer);

        UserResponse response = new UserResponse();

        Message message = new Message();
        message.setFirstName(saved.getFirstName());
        message.setLastName(saved.getLastName());
        message.setEmail(saved.getEmail());
        message.setHomeAddress(saved.getHomeAddress());
        message.setGender(saved.getGender());
        response.setMessage(message);

        return response;
    }

    @Override
    public String addBillingToCustomer(BillingRequest request) {
        Optional<Customer> customerOptional = customerRepository.findById(request.getUserId());
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            Billing billing = new Billing(customer);
            billing.setTariff(request.getTariff());
            billingRepository.save(billing);
        }

        return "Billing Assigned";
    }

    @Override
    public Invoice generateInvoice(Long customerId) throws NoCustomerFoundException {
        Optional<Billing> billing = billingRepository.findByCustomerId(customerId);
        if(billing.isPresent()){
            Billing bill = billing.get();
            Optional<Customer> customer = customerRepository.findById(bill.getCustomer().getId());
            if(customer.isPresent()){
                Invoice invoice = new Invoice();
                invoice.setCustomerName(customer.get().getFirstName()+ " " + customer.get().getLastName());
                invoice.setAccountNumber(bill.getAccountNumber());
                invoice.setAccountToPay(bill.getTariff());
                return invoice;
            }else {
                throw new NoCustomerFoundException("No Customer Found");
            }

        }else {
            throw new NoCustomerFoundException("No Billing Found");
        }

    }

    @Override
    public UserResponse findACustomer(String email) throws NoCustomerFoundException {
        Optional<Customer> customer = customerRepository.findCustomerByEmail(email);
        if(customer.isPresent()){

            Message message = new Message();
            UserResponse response = new UserResponse();

            message.setFirstName(customer.get().getFirstName());
            message.setLastName(customer.get().getLastName());
            message.setEmail(customer.get().getEmail());
            message.setHomeAddress(customer.get().getHomeAddress());
            message.setGender(customer.get().getGender());

            Optional<Billing> billing = billingRepository.findByCustomerId(customer.get().getId());

            return getUserResponse(message, response, billing);
        }else {
            throw new NoCustomerFoundException("Email Not Found");
        }

    }

    private UserResponse getUserResponse(Message message, UserResponse response, Optional<Billing> billing) throws NoCustomerFoundException {
        if(billing.isPresent()){
            Billing bill = billing.get();
            bill.setTariff(bill.getTariff());
            message.setBilling(bill);
            response.setMessage(message);
            return response;
        }
        else {
            throw new NoCustomerFoundException("Billing Not Found");
        }
    }

    @Override
    public List<Customer> findAllUser() {
        return customerRepository.findAll();
    }

    @Override
    public UserResponse findACustomer(Long id) throws NoCustomerFoundException {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            Customer custom = customer.get();
            UserResponse response = new UserResponse();
            Message message = new Message();
            message.setFirstName(custom.getFirstName());
            message.setLastName(custom.getLastName());
            message.setHomeAddress(custom.getHomeAddress());
            message.setEmail(custom.getEmail());
            message.setGender(custom.getGender());

            Optional<Billing> billing = billingRepository.findByCustomerId(custom.getId());
            return getUserResponse(message, response, billing);

        }else {
            throw new NoCustomerFoundException("Id does not exist");
        }

    }
}
