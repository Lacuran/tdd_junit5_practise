package pl.qaaacademy.todo.list;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.qaaacademy.todo.item.TodoItem;
import pl.qaaacademy.todo.item.enums.ItemStatus;

import static org.junit.jupiter.api.Assertions.*;
import static pl.qaaacademy.todo.list.TodoList.fuzeList;

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
        TodoItem item1 = TodoItem.of("This is the title1", "This is item description");
        TodoItem item2 = TodoItem.of("This is the title2", "This is item description");
        TodoItem item3 = TodoItem.of("This is the title3", "This is item description");

        itemList.addItem(item1);
        itemList.addItem(item2);
        assertEquals(2, itemList.getListSize());

        itemList.addItem(item3);
        assertEquals(3, itemList.getListSize());
    }

    @Tag("happy")
    @Test
    public void shouldCombineSeveralListWithNewTitle() {
        TodoList itemList2 = new TodoList("List 2");
        TodoList itemListPrime = new TodoList("List Prime");

        itemList.addItem(TodoItem.of("This is item 1", "description of item 1"));
        itemList.addItem(TodoItem.of("This is item 2", "description of item 2"));
        itemList2.addItem(TodoItem.of("This is item1 from list 2", "this is description of item2"));
        itemList2.addItem(TodoItem.of("This is item 2 form list 2", "And this is its description"));

        itemListPrime = fuzeList(itemList, itemList2, itemListPrime.getListTitle());


        TodoList finalItemListPrime = itemListPrime;
        TodoList finalItemListPrime1 = itemListPrime;
        assertAll(
                () -> assertEquals(4, finalItemListPrime.getListSize()),
                () -> assertEquals("List Prime", finalItemListPrime1.getListTitle())
        );
    }


    @Tag("exception")
    @Test
    public void shouldNotCreateListWithEmptyTitle() {

        String emptyTitle = "";
        assertThrows(TodoListValidTitleExceptionThrow.class,
                () -> new TodoList(emptyTitle));
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
        String titleToRemove = "This is title of item1";
        TodoItem item1 = TodoItem.of(titleToRemove, "This is description of item1");
//        TodoItem item2 = TodoItem.of("This is title of item2", "This is description of item1");
        itemList.addItem(item1);
//        itemList.addItem(item2);
        itemList.removeItem(titleToRemove);

        assertEquals(0, itemList.getListSize());
    }

    @Tag("happy")
    @Test
    public void shouldFilterItemsByItsStatus() {
        //TODO
        // Items can be filtered by status
        TodoItem item1 = TodoItem.of("This is the title1", "This is item description");
        TodoItem item2 = TodoItem.of("This is the title2", "This is item description");
        TodoItem item3 = TodoItem.of("This is the title3", "This is item description");
        TodoItem item4 = TodoItem.of("This is the title4", "This is item description");

        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);
        itemList.addItem(item4);
        item4.toggleStatus();
        item4.complete();
        item3.toggleStatus();

        itemList.filterByStatus(ItemStatus.COMPLETED);
        assertEquals(1, itemList.getListSize());

    }

    @Tag("happy")
    @Test
    public void shouldSortItemsByTitle() {
        //TODO
        // Items can be sorted by title
        TodoList itemList2 = new TodoList(itemList.getListTitle());
        TodoItem item1 = TodoItem.of("This is the title4", "This is item description");
        TodoItem item2 = TodoItem.of("This is the title3", "This is item description");
        TodoItem item3 = TodoItem.of("This is the title2", "This is item description");
        TodoItem item4 = TodoItem.of("This is the title1", "This is item description");

        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);
        itemList.addItem(item4);

        itemList2.addItem(item4);
        itemList2.addItem(item3);
        itemList2.addItem(item2);
        itemList2.addItem(item1);

        itemList.sortList();
        assertEquals(itemList, itemList2);
    }

    @Tag("exception")
    @Test
    public void itemsInListShouldNotHaveDuplicateTitle() {
        //TODO
        // Items should not have duplicate titles
        TodoItem item1 = TodoItem.of("This is the title", "This is item description");
        TodoItem item2 = TodoItem.of("This is the title", "This is item description");
        itemList.addItem(item1);
//        itemList.addItem(item2);
//        assertEquals(1, itemList.getListSize());

        assertThrows(TodoListSameItemTitleExceptionThrow.class,
                () -> itemList.addItem(item2));
    }

}
