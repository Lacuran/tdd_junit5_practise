package pl.qaaacademy.todo.list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTodoListTest {

    protected static final Logger logger = LoggerFactory.getLogger(BaseTodoListTest.class);

    protected String titleList;
    protected TodoList itemList;

    @BeforeEach
    public void todoListSetUp() {
        titleList = "This is List of a Title";
        itemList = new TodoList(titleList);
    }

    @AfterEach
    public void todoListCleanUp() {
        itemList = null;
    }
}
