package pl.qaaacademy.todo.list;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.qaaacademy.todo.item.TodoItem;

import static org.junit.jupiter.api.Assertions.*;

@Tag("list")
@Tag("unit")
public class BasicTodoListPropertiesTest extends BaseTodoListTest {

    protected static final Logger logger = LoggerFactory.getLogger(BasicTodoListPropertiesTest.class);

    @Tag("happy")
    @Test
    public void shouldCreateAnEmptyList() {
        assertEquals(0, itemList.getListSize());
    }

    @Tag("happy")
    @Test
    public void listShouldHaveTitleAndSizeAfterCreation() {
        assertAll(
                () -> assertEquals(titleList, itemList.getListTitle()),
                () -> assertEquals(0, itemList.getListSize())
        );
    }

    @Tag("happy")
    @Test
    public void listIsIncrementedAfterAddingItem() {
        TodoItem item1 = TodoItem.of("This is the title", "This is item description");
        TodoItem item2 = TodoItem.of("This is the title", "This is item description");
        TodoItem item3 = TodoItem.of("This is the title", "This is item description");

        itemList.addItem(item1);
        itemList.addItem(item2);
        assertEquals(2, itemList.getListSize());

        itemList.addItem(item3);
        assertEquals(3, itemList.getListSize());
    }

    @Tag("happy")
    @Test
    public void shouldCombineSeveralListWithNewTitle() {
        //TODO
        // Several lists can be combined to one with a new title
        TodoList itemList2 = TodoList.of("List 2");
        TodoList itemListPrime = TodoList.of("Sdf");

        itemList.addItem(TodoItem.of("This is item 1", "description of item 1"));
        itemList.addItem(TodoItem.of("This is item 2", "description of item 2"));
        itemList2.addItem(TodoItem.of("This is item1 from list 2", "this is description of item2"));
        itemList2.addItem(TodoItem.of("This is item 2 form list 2", "And this is its description"));

//        itemListPrime = ListUtils.union(itemList.getItemList(), itemList2.getItemList());


        assertEquals(4, itemListPrime.getListSize());
    }

    @Tag("happy")
    @Test
    public void shouldCombineSeveralListWithTitleFromFirstList() {
        //TODO
        // Several lists can be combined to one with a
        // 1st list title will be assigned by default
    }

    @Tag("exception")
    @Test
    public void shouldNotCreateListWithEmptyTitle() {

        String emptyTitle = "";

        assertThrows(TodoListValidTitleExceptionThrow.class,
                () -> TodoList.of(emptyTitle));
    }

    @Tag("happy")
    @Test
    public void shouldSuccessfullyAddItemToTheList() {
        TodoItem item1 = TodoItem.of("This is title of item1", "This is description of item1");
        itemList.addItem(item1);
        assertEquals(1, itemList.getListSize());
    }

    @Tag("happy")
    @Test
    public void shouldDeleteItemFromTheListByItemTitle() {
        //TODO
        // Items can be deleted from the list by their title
        TodoItem item1 = TodoItem.of("This is title of item1", "This is description of item1");
        itemList.addItem(item1);
        itemList.removeItem("This is title of item1");
        assertEquals(0, itemList.getListSize());
    }

    @Tag("happy")
    @Test
    public void shouldFilterItemsByItsStatus() {
        //TODO
        // Items can be filtered by status
    }

    @Tag("happy")
    @Test
    public void shouldSortItemsByTitle() {
        //TODO
        // Items can be sorted by title
    }

    @Tag("exception")
    @Test
    public void itemsInListShouldNotHaveDuplicateTitle() {
        //TODO
        // Items should not have duplicate titles
    }

    @Tag("optional")
    @Test
    public void severalItemsInListCanChangeItsStatus() {
        //TODO
        // Several items can be toggled or completed (optional)
    }
}
