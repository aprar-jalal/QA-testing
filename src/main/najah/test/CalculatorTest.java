package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Calculator;



@Execution(value= ExecutionMode.CONCURRENT)
class CalculatorTest {
	Calculator calc= new Calculator();
	
	
	@ParameterizedTest
	@CsvSource({
		"1,1,2",
		"1,-2,4",
		"-1,1,3",
		"-4,-4,-8",
	})
	void testAdd(int x,int y,int expected) {
		System.out.print("Addtion\n");
		assertEquals( expected,calc.add(x,y));
		calc.add(-4,-4,-4);
		calc.add(4,4,-4);
		calc.add(-4,4,-4);
		calc.add(4,4,4);

		calc.add(-4,-4,-4,-4);
		calc.add(4,4,-4,-4);
		calc.add(-4,4,-4,-4);
		calc.add(4,4,4,4,4);
		
		calc.add(4,4,-4,-4,-4);
		calc.add(-4,-4,-4,-4,-4);
		
		calc.add(4,4,-4,-4,-4,4);
		calc.add(4,4,-4,-4,-4,-4);
		
		calc.add(4,4,-4,-4,-4,4,4);
		calc.add(4,4,-4,-4,-4,-4,-4);
		
		calc.add(4,4,-4,-4,-4,4,4,4);
		calc.add(4,4,-4,-4,-4,-4,-4,-4);
	}
	
	
//********************************************************************
	@DisplayName ("Divide") 
	@Timeout(value=500 , unit=TimeUnit.MILLISECONDS)
    @ParameterizedTest
    @CsvSource({
    	"10,2,5",
    	"-10,2,-5",
    	"10,-2,-5",
    	"-10,-2,5"
    })
   void testDivide(int x ,int y ,int exp) throws InterruptedException {
   System.out.print("Divide\n");
   //Thread.sleep(1000);
   assertEquals(exp,calc.divide(x,y));
	}

	
	
	@ParameterizedTest
    @CsvSource({"1,0","-1,0","0,1,0,-1"})
    @DisplayName("invaled values for Divied Test b=0 or a=0")
    void testDivide2(int x ,int y) {
       assertThrows(ArithmeticException.class,()->{ calc.divide(x, y);});
    }

//*********************************************************************
	@ParameterizedTest
	@ValueSource(ints={1,3,-3,0})
	void testFactorial(int x) {
		System.out.print("Fraction\n");
		assertTrue(calc.factorial(x) >2);
	}
	
	
	@Test
	@Disabled("the fraction dose not take nigative values")
	void testFactorialInvalidValue() {
		System.out.print("Fraction invalid values \n");
		calc.factorial(-1);
	}
//***************************************************************************
	@BeforeAll
	static  void start() {
	    System.out.println("Calculator setup start");
	}
	
    @BeforeEach
    void Debug() {
        System.out.println("Start Debugging");
    }
    
    @AfterEach
    void FinishedDebug() {
        System.out.println("Finished Debugging");
    }
    
    @AfterAll
    static void Finished() {
        System.out.println("Calculator Setup complete");
    }
}
