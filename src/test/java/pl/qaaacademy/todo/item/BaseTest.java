package pl.qaaacademy.todo.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected String title;
    protected String description;
    protected TodoItem item;


    @AfterEach
    
    @BeforeEach
    public void setUpTest() {
        title = "Complete Java Udemy course";
        description = "Ivan set to do it quickly";
        item = TodoItem.of(title, description);
    }
}
