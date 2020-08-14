package com.tanuj.exceldownload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJpaExcelDownloaderApplication {

	/*@Autowired
	CustomerRepository repository;*/

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaExcelDownloaderApplication.class, args);
    }
	
  /*  @Override
    public void run(String... args) throws Exception {
    	
    	List<Customer> customers = Arrays.asList(
    			new Customer(Long.valueOf(1), "Jack Smith", "Massachusetts", 23),
    			new Customer(Long.valueOf(2), "Adam Johnson", "New York", 27),
    			new Customer(Long.valueOf(3), "Katherin Carter", "Washington DC", 26),
    			new Customer(Long.valueOf(4), "Jack London", "Nevada", 33), 
    			new Customer(Long.valueOf(5), "Jason Bourne", "California", 36));
    	
		// save a list of Customers
		repository.saveAll(customers);
    }	*/
}
