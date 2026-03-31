package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Product;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductTest {
    
	static Product product1 ;
	Product product2 ;
	Product product3 =new Product("product3", 0);
	Product product4 =new Product("product4", 20000000);

	

	@BeforeAll
	static void setUp() {
	    product1 = new Product("product1", 10.5);
	    System.out.print("product created\n");
	}
	
	@Test
	void testNegativePrice() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Product("product2", -1);
	    });
	}
//***************************************************************
	
	@Order(1)
	@ParameterizedTest
    @ValueSource(doubles= {1,10,40})
	@DisplayName("Apply Discount Tset")
	void testApplyDiscount(double discount) {
		product1.applyDiscount(discount);
		
    }	
	
	@ParameterizedTest
	@ValueSource(doubles={-5, 60,0,50})
	@DisplayName("Apply invalid discount")
	void testApplyInvalidDiscount(double discount) {
	    assertThrows(IllegalArgumentException.class, () -> {
	        product1.applyDiscount(discount);
	    });
	}
//***************************************************************

	@Test
	@Order(2)
	void testGetFinalPrice() {
		System.out.print(product1.getFinalPrice());
		//price before discount should'nt be equal to after discount
		assertNotEquals(product1.getPrice(), product1.getFinalPrice());
	}
	

//***************************************************************
	@Test
	@Timeout(value=500,unit=TimeUnit.MILLISECONDS)
	void testGetName() throws InterruptedException{
		assertEquals(product1.getName(),product1.getName());
	}
//***************************************************************
	@Test
	void testGetPrice() {
		assertEquals(10.5, product1.getPrice());
	}
//***************************************************************
	@Test
	void testGetDiscount() {
		  product1.applyDiscount(25);
	        assertEquals(25, product1.getDiscount());
	}


} 
