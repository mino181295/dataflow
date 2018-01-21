package datamodel;

import org.junit.Test;
import it.matteo.datamodel.Person;
import it.matteo.datamodel.Phone;
import it.matteo.datamodel.Item;
import it.matteo.datamodel.BankAccount;
import it.matteo.datamodel.Notebook;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 *
 * @author matteo.minardi
 */
public class DataModelTest {
    
    @Test
    public void personTest() {
        Person matteo = new Person("Matteo", "Minardi", 22);
        assertNotNull(matteo);
        
        assertThat(matteo.getName(), equalTo("Matteo"));
        assertThat(matteo.getSurname(), equalTo("Minardi"));
        assertThat(matteo.getAge(), equalTo(22));
    }
    
    @Test
    public void itemTest() {
        Item samsung = new Phone("Samsung Galaxy S2");
        assertNotNull(samsung);
        
        assertThat(samsung.printName(), equalTo("Samsung Galaxy S2"));
        assertTrue(samsung instanceof Phone);
        
        Item acer = new Notebook("Acer Predator");
        assertNotNull(acer);
        
        assertThat(acer.printName(), equalTo("Acer Predator"));
        assertTrue(acer instanceof Notebook);
    }
    
    @Test
    public void bankAccountTest() {
        BankAccount account = new BankAccount(1000f);
        assertNotNull(account);
        
        account.add(1000);
        assertThat(account.printBalance(), equalTo(2000f));
        
        try {
            account.add(-1);
            account.get(3000);
            fail();
        } catch (IllegalArgumentException ex) {}
     }
}
