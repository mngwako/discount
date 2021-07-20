package com.monthusi.discount.sales;

import com.monthusi.discount.sales.entity.SalesTransaction;
import com.monthusi.discount.sales.repository.ISalesTransactionRepository;
import com.monthusi.discount.sales.service.implementation.SalesTransactionServiceImpl;
import com.monthusi.discount.user.entity.User;
import com.monthusi.discount.user.entity.UserType;
import com.monthusi.discount.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SalesTransactionServiceBeanTest {
    @Mock
    private ISalesTransactionRepository iSalesTransactionRepository;

    @Mock
    private IUserService iUserService;

    @InjectMocks
    private SalesTransactionServiceImpl salesTransactionService;

    @Test
    public void shouldCreateSalesTransaction() {
        double bill = 200;
        String userType = "Employee";
        String service = "Services";//Any Service not "Grocery" for this test
        String dateString = "09/22/2006";
        Date registrationDate = formatDate(dateString);

        SalesTransaction salesTransaction = buildSalesTransaction(200, service, userType, registrationDate);

        when(iUserService.getUserByCustomerNumber(anyString())).thenReturn(this.buildUser(userType, registrationDate));

        when(iSalesTransactionRepository.save(ArgumentMatchers.any(SalesTransaction.class))).thenReturn(salesTransaction);

        SalesTransaction created = salesTransactionService.createSalesTransaction(salesTransaction);
        assertEquals(created, salesTransaction);
        verify(iSalesTransactionRepository).save(salesTransaction);
    }

    @Test
    public void shouldDiscount30ForEmployees() throws ParseException {
        double bill = 80.0; //Less than $100 so that only the user type category applies to this test
        String userType = "Employee";//Employee User
        String service = "Services";//Any Service not "Grocery" for this test
        String dateString = "09/22/2006";
        Date registrationDate = formatDate(dateString);

        SalesTransaction salesTransaction = buildSalesTransaction(bill, service, userType, registrationDate);
        double expectedDiscount = bill * (30.0/100.0);

        when(iUserService.getUserByCustomerNumber(anyString())).thenReturn(this.buildUser(userType, registrationDate));

        when(iSalesTransactionRepository.save(ArgumentMatchers.any(SalesTransaction.class))).thenReturn(salesTransaction);

        SalesTransaction created = salesTransactionService.createSalesTransaction(salesTransaction);
        double actualDiscount = created.getDiscountAmount();
        assertEquals(expectedDiscount, actualDiscount);
        verify(iSalesTransactionRepository).save(salesTransaction);
    }

    @Test
    public void shouldDiscount10ForAffiliates() throws ParseException {
        double bill = 80.0; //Less than $100 so that only the user type category applies to this test
        String userType = "Affiliate";//Affiliate User Type
        String service = "Services";//Any Service not "Grocery" for this test
        String dateString = "09/22/2006";
        Date registrationDate = formatDate(dateString);

        SalesTransaction salesTransaction = buildSalesTransaction(bill, service, userType, registrationDate);
        double expectedDiscount = bill * (10.0/100.0);

        when(iUserService.getUserByCustomerNumber(anyString())).thenReturn(this.buildUser(userType, registrationDate));

        when(iSalesTransactionRepository.save(ArgumentMatchers.any(SalesTransaction.class))).thenReturn(salesTransaction);

        SalesTransaction created = salesTransactionService.createSalesTransaction(salesTransaction);
        double actualDiscount = created.getDiscountAmount();
        assertEquals(expectedDiscount, actualDiscount);
        verify(iSalesTransactionRepository).save(salesTransaction);
    }

    @Test
    public void shouldDiscount5ForLongTermCustomers() throws ParseException {
        double bill = 80.0; //Less than $100 so that only the user type category applies to this test
        String userType = "Customer";//Customer User
        String service = "Services";//Any Service not "Grocery" for this test
        String dateString = "09/22/2006";
        Date registrationDate = formatDate(dateString);

        SalesTransaction salesTransaction = buildSalesTransaction(bill, service, userType, registrationDate);
        double expectedDiscount = bill * (5.0/100.0);

        when(iUserService.getUserByCustomerNumber(anyString())).thenReturn(this.buildUser(userType, registrationDate));

        when(iSalesTransactionRepository.save(ArgumentMatchers.any(SalesTransaction.class))).thenReturn(salesTransaction);

        SalesTransaction created = salesTransactionService.createSalesTransaction(salesTransaction);
        double actualDiscount = created.getDiscountAmount();
        assertEquals(expectedDiscount, actualDiscount);
        verify(iSalesTransactionRepository).save(salesTransaction);
    }

    @Test
    public void shouldNotDiscountForNewCustomers() throws ParseException {
        double bill = 80.0; //Less than $100 so that only the user type category applies to this test
        String userType = "Customer";//Customer User
        String service = "Services";//Any Service not "Grocery" for this test
        String dateString = "04/22/2021";
        Date registrationDate = formatDate(dateString);

        SalesTransaction salesTransaction = buildSalesTransaction(bill, service, userType, registrationDate);
        double expectedDiscount = 0;

        when(iUserService.getUserByCustomerNumber(anyString())).thenReturn(this.buildUser(userType, registrationDate));

        when(iSalesTransactionRepository.save(ArgumentMatchers.any(SalesTransaction.class))).thenReturn(salesTransaction);

        SalesTransaction created = salesTransactionService.createSalesTransaction(salesTransaction);
        double actualDiscount = created.getDiscountAmount();
        assertEquals(expectedDiscount, actualDiscount);
        verify(iSalesTransactionRepository).save(salesTransaction);

    }

    @Test
    public void shouldDiscountForEvery100OnTheBill(){
        double bill = 350.0; //More than $100
        String userType = "Customer";//Customer User (So Employee and Affiliate discount do not apply
        String service = "Services";//Any Service not "Grocery" for this test
        String dateString = "04/22/2021";//To Ensure that percentage discount does not apply
        Date registrationDate = formatDate(dateString);

        SalesTransaction salesTransaction = buildSalesTransaction(bill, service, userType, registrationDate);
        double expectedDiscount = ((int)(bill / 100)) * 5;

        when(iUserService.getUserByCustomerNumber(anyString())).thenReturn(this.buildUser(userType, registrationDate));

        when(iSalesTransactionRepository.save(ArgumentMatchers.any(SalesTransaction.class))).thenReturn(salesTransaction);

        SalesTransaction created = salesTransactionService.createSalesTransaction(salesTransaction);
        double actualDiscount = created.getDiscountAmount();
        assertEquals(expectedDiscount, actualDiscount);
        verify(iSalesTransactionRepository).save(salesTransaction);

    }

    @Test
    public void shouldNotDiscountForGroceries(){
        double bill = 800.0; //Less than $100
        String userType = "Employee";//Employee User
        String service = "Grocery";//To test if grocery does not get percentage discount
        String dateString = "09/22/2006";
        Date registrationDate = formatDate(dateString);

        SalesTransaction salesTransaction = buildSalesTransaction(bill, service, userType, registrationDate);
        double expectedDiscount = ((int)(bill / 100)) * 5;

        when(iUserService.getUserByCustomerNumber(anyString())).thenReturn(this.buildUser(userType, registrationDate));

        when(iSalesTransactionRepository.save(ArgumentMatchers.any(SalesTransaction.class))).thenReturn(salesTransaction);

        SalesTransaction created = salesTransactionService.createSalesTransaction(salesTransaction);
        double actualDiscount = created.getDiscountAmount();
        assertEquals(expectedDiscount, actualDiscount);
        verify(iSalesTransactionRepository).save(salesTransaction);
    }

    @Test
    public void shouldGetAllTransactions(){
        final List<SalesTransaction> expectedTransactions = generateListOfTransactions();

        when(iSalesTransactionRepository.findAll()).thenReturn(expectedTransactions);
        final List<SalesTransaction> actualTransactions = salesTransactionService.getAllSalesTransactions();

        verify(iSalesTransactionRepository, times(1)).findAll();
        assertEquals(expectedTransactions, actualTransactions);
    }

    private User buildUser(String userType, Date registrationDate){
        User user = new User();
        user.setId("1");
        user.setCustomerNumber("0000001");
        user.setFullName("Test User");
        user.setUserType(buildUserType(userType));
        user.setRegistrationDate(registrationDate);
        return user;
    }

    private UserType buildUserType(String typeName){
        UserType userType = new UserType();
        userType.setId("1");
        userType.setName(typeName);
        return userType;
    }

    private SalesTransaction buildSalesTransaction(double bill, String saleCategory, String userType, Date registrationDate){
        SalesTransaction salesTransaction = new SalesTransaction();
        salesTransaction.setId("1");
        salesTransaction.setUser(buildUser(userType, registrationDate));
        salesTransaction.setBill(bill);
        salesTransaction.setSaleCategory(saleCategory);
        return salesTransaction;
    }

    private List<SalesTransaction> generateListOfTransactions(){
        List<SalesTransaction> transactions = new ArrayList<>();

        SalesTransaction transaction1 = buildSalesTransaction(150, "Grocery", "Customer", formatDate("09/22/2006"));
        SalesTransaction transaction2 = buildSalesTransaction(150, "Furniture", "Employee", formatDate("09/22/2021"));
        transactions.add(transaction1);
        transactions.add(transaction2);

        return transactions;
    }

    private Date formatDate(String date){
        SimpleDateFormat dateStringFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date registrationDate =  new Date();
        try {
            registrationDate = dateStringFormat.parse(date);
        } catch(ParseException error) {
            System.out.println(error.toString());
        }
        return registrationDate;
    }
}
