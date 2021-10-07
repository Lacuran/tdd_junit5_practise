package pl.qaaacademy.todo.item;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.qaaacademy.todo.item.Exception.*;
import pl.qaaacademy.todo.item.enums.ItemStatus;
import pl.qaaacademy.todo.item.stringgenerator.RandomGeneratedString;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.qaaacademy.todo.matcher.ValidTodoItemMatcher.isValidTodoItemWith;

@Tag("item")
public class BasicTodoItemPropertiesTest extends BaseTest {

    Logger logger2 = LoggerFactory.getLogger(BasicTodoItemPropertiesTest.class);

    @Tag("happy")
    @Test
    public void shouldCreateTodoItemWithTitleAndDescription() {
        logger2.warn("This is the logger test string");
        assertAll(
                () -> assertEquals(title, item.getTitle()),
                () -> assertEquals(description, item.getDescription())
        );
    }

    @Tag("exception")
    @Test
    public void shouldTrowAnExceptionWhileItemCreationWithEmptyTitle() {
        String emptyTitle = "";
        assertThrows(TodoItemValidationException.class,
                () -> TodoItem.of(emptyTitle, description));
    }

    @Tag("exception")
    @Test
    public void shouldThrowAnExceptionWhileSettingAnEmptyTitle() {
        String emptyTitle = "";
        assertThrows(TodoItemValidationException.class,
                () -> item.setTitle(emptyTitle));
    }

    @Tag("happy")
    @Test
    public void shouldChangeStatusFromPendingToInProgress() {
        item.toggleStatus();
        assertEquals(ItemStatus.IN_PROGRESS, item.getStatus());
    }

    @Tag("happy")
    @Test
    public void shouldChangeStatusFromInProgressToInPending() {
        item.toggleStatus();
        item.toggleStatus();
        assertEquals(ItemStatus.PENDING, item.getStatus());
    }

    @Tag("happy")
    @Test
    public void shouldCompleteATaskRepresentByItem() {
        item.toggleStatus();
        item.complete();
        assertEquals(ItemStatus.COMPLETED, item.getStatus());
    }

    @Tag("happy")
    @Test
    public void shouldCreateItemWithPendingStatus() {
        assertEquals(ItemStatus.PENDING, item.getStatus());
    }

    @Tag("exception")
    @Test
    public void shouldNotToggleStatusFromCompletedToInProgress() {
        item.toggleStatus();
        item.complete();
        assertThrows(TodoItemCompleteStatusException.class,
                () -> item.toggleStatus());
    }

    @Tag("exception")
    @Test
    public void shouldNotCreateATodoItemWithDescriptionLongerThan250Chars() {
        String descriptionLongerThan250Chars = RandomGeneratedString.randomGeneratedString(251);
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> TodoItem.of(title, descriptionLongerThan250Chars));
    }

    @Tag("exception")
    @Test
    public void shouldNotCreateATodoItemWithDescriptionWithNull() {
        String emptyDescription = "";
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> TodoItem.of(title, emptyDescription));
    }

    @Tag("exception")
    @Test
    public void shouldNotSetANewDescriptionLongerThan250Chars() {
        String descriptionLongerThan250Chars = RandomGeneratedString.randomGeneratedString(251);
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> item.setDescription(descriptionLongerThan250Chars));
    }

    @Tag("exception")
    @Test
    public void shouldNotSetAnEmptyNewDescription() {
        String emptyDescription = "";
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> item.setDescription(emptyDescription));
    }

    @Tag("happy")
    @Test
    public void shouldChangeStatusFromCompleteToReOpen() {
        item.toggleStatus();
        item.complete();
        item.reOpen();
        assertEquals(ItemStatus.REOPEN, item.getStatus());
    }

    @Tag("happy")
    @Test
    public void shouldChangeStatusFromReOpenToPending() {
        item.toggleStatus();
        item.complete();
        item.reOpen();
        item.toggleStatus();
        assertEquals(ItemStatus.PENDING, item.getStatus());
    }

    @Tag("exception")
    @Test
    public void shouldTrowExceptionWhileChangingStatusFromInProgressToReOpen() {
        item.toggleStatus();
        assertThrows(TodoItemReOpenStatusExceptionThrow.class,
                () -> item.reOpen());
    }

    @Tag("exception")
    @Test
    public void shouldNotCompleteItemInPendingStatus() {
        assertThrows(TodoItemPendingStatusToCompleteExceptionThrow.class,
                () -> item.complete());
    }

    @Tag("exception")
    @Test
    public void shouldNotChangeTitleIfItemIsComplete() {
        String newTitle = "This is new title of a item";
        item.toggleStatus();
        item.complete();
        assertThrows(TodoItemCompleteChangeStatusException.class,
                () -> item.setTitle(newTitle));
    }

    @Tag("description")
    @Tag("exception")
    @Test
    public void shouldNotChangeDescriptionIfItemIsComplete() {
        String newDescription = "This is new description for an item";
        item.toggleStatus();
        item.complete();
        assertThrows(TodoItemCompleteChangeStatusException.class,
                () -> item.setDescription(newDescription));
    }

    @Tag("description")
    @Tag("happy")
    @Test
    public void shouldChangeDescriptionWhenItemIsInProgress() {
        String newDescription = "This is new item description";
        item.toggleStatus();
        item.setDescription(newDescription);
        assertEquals(newDescription, item.getDescription());
    }

    @ParameterizedTest
    @CsvFileSource(files = {"src/main/resources/todocsv.csv" }, numLinesToSkip = 1)
    public void shouldCreateValidateTodoItemCSVFileSource(String title, String description) {
        TodoItem newTodo = TodoItem.of(title, description);
        assertThat(newTodo, isValidTodoItemWith(title, description));
    }

    @ParameterizedTest
    @ArgumentsSource(TodoItemArgumentsProvider.class)
    public void shouldCreateValidTodoItemArgumentSource(String title, String description) {
        TodoItem newTodo = TodoItem.of(title, description);
        assertThat(newTodo, isValidTodoItemWith(title, description));
    }

    @Tag("exception")
    @Test
    public void shouldNotCreateAnItemWithTitleLowerThan5Chars() {
        String itemTitleLowerThan5Chars = "Asd";
        assertThrows(TodoItemValidationException.class,
                () -> TodoItem.of(itemTitleLowerThan5Chars, description));
    }

}
