package africa.smicolon.customer.services;

import africa.smicolon.customer.dtos.requests.AddUserRequest;
import africa.smicolon.customer.dtos.requests.BillingRequest;
import africa.smicolon.customer.dtos.responses.UserResponse;
import africa.smicolon.customer.exceptions.NoCustomerFoundException;
import africa.smicolon.customer.model.data.Customer;
import africa.smicolon.customer.model.data.Gender;
import africa.smicolon.customer.model.data.Invoice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceTest {



    @Autowired
    CustomerService customerService;

    @Test
    @DisplayName("Test to add User")
    void testThatUserCanBeAdded(){
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("James");
        addUserRequest.setLastName("John");
        addUserRequest.setEmail("james@yahoo.com");
        addUserRequest.setGender(Gender.MALE);
        addUserRequest.setHomeAddress("51, Newton road,USA");
        addUserRequest.setPassword("1234");
        addUserRequest.setPhoneNumber("09127XXXXX6");
        UserResponse response = customerService.addUser(addUserRequest);
        assertNotNull(response);
        assertEquals("James",response.getMessage().getFirstName());
    }
    @Test
    @DisplayName("Add billing to Customer")
    void testThatBillingCanBeAddedToCustomer(){
        BillingRequest billingRequest = new BillingRequest();
        billingRequest.setUserId(4L);
        billingRequest.setTariff("6000");
        String response = customerService.addBillingToCustomer(billingRequest);
        assertEquals("Billing Assigned",response);

        BillingRequest billingRequest2 = new BillingRequest();
        billingRequest2.setUserId(1L);
        billingRequest2.setTariff("52000");
        String response2 = customerService.addBillingToCustomer(billingRequest2);
        assertEquals("Billing Assigned",response2);
    }

    @Test
    @DisplayName("Find Customer by Email")
    void testThatCustomerCanBeFoundByEmail() throws NoCustomerFoundException {
       UserResponse response =  customerService.findACustomer("kelvin.okoro.e@yahoo.com");
       assertEquals(5,response.getMessage().getBillings().size());
       assertNotNull(response);
    }


    @Test
    @DisplayName("generate invoice")
    void testThatInvoiceCanBeGenerated() throws NoCustomerFoundException {
        Invoice invoice = customerService.generateInvoice(1L);
        assertEquals("Kelvin Okoro", invoice.getCustomerName());
        assertNotNull(invoice);
        Invoice invoice1 = customerService.generateInvoice(1L);
        assertEquals(4, invoice1.getAmountToPay().length());
    }

    @Test
    @DisplayName("find all user")
    void testToFindAllUser(){
       List<Customer> customer =  customerService.findAllUser();
        assertEquals(3, customer.size());
    }

    @Test
    @DisplayName("FInd a user by id")
    void findAUserById() throws NoCustomerFoundException {
       Customer customers =  customerService.findACustomer(1L);
       assertEquals("Kelvin", customers.getFirstName());
    }
}