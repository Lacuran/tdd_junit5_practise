package pl.qaaacademy.todo.item;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@Tag("item")
public class BasicTodoItemPropertiesTest extends BaseTest {


    Logger logger2 = LoggerFactory.getLogger(BasicTodoItemPropertiesTest.class);

    @Tag("happy")
    @Test
    public void shouldCreateTodoItemWithTitleAndDescription() {
        //TODO assertAll
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

    @Test
    public void shouldThrowAnExceptionWhileSettingAnEmptyTitle() {
        String emptyTitle = "";
        assertThrows(TodoItemValidationException.class,
                () -> item.setTitle(emptyTitle));
    }

    @Test
    public void shouldChangeStatusFromPendingToInProgress() {
        item.toggleStatus();
        assertEquals(item.getStatus(), ItemStatus.IN_PROGRESS);
    }

    @Test
    public void shouldChangeStatusFromInProgressToInPending() {
        item.toggleStatus();
        item.toggleStatus();
        assertEquals(item.getStatus(), ItemStatus.PENDING);
    }

    @Test
    public void shouldCompleteATaskRepresentByItem() {
        item.toggleStatus();
        item.complete();
        assertEquals(item.getStatus(), ItemStatus.COMPLETED);
    }

    @Test
    public void shouldCreateItemWithPendingStatus() {
        //TODO
        assertEquals(item.getStatus(), ItemStatus.PENDING);
    }

    @Test
    public void shouldNotToggleStatusFromCompletedToInProgress() {
        //TODO
        item.toggleStatus();
        item.complete();
        assertThrows(TodoItemCompleteStatusException.class,
                () -> item.toggleStatus());
    }

    @Test
    public void shouldNotCreateATodoItemWithDescriptionLongerThan250Chars() {
        //TODO
        String descriptionLongerThan250Chars = RandomGeneratedString.randomGeneratedString(251);
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> TodoItem.of(title, descriptionLongerThan250Chars));
    }

    @Test
    public void shouldNotCreateATodoItemWithDescriptionWithNull() {
        //TODO
        String emptyDescription = "";
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> TodoItem.of(title, emptyDescription));
    }

    @Test
    public void shouldNotSetANewDescriptionLongerThan250Chars() {
        //TODO
        String descriptionLongerThan250Chars = RandomGeneratedString.randomGeneratedString(251);
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> item.setDescription(descriptionLongerThan250Chars));
    }

    @Test
    public void shouldNotSetAnEmptyNewDescription() {
        //TODO
        String emptyDescription = "";
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> item.setDescription(emptyDescription));
    }

    @Test
    public void shouldChangeStatusFromCompleteToReOpen() {
        item.toggleStatus();
        item.complete();
        item.reOpen();
        assertEquals(item.getStatus(), ItemStatus.REOPEN);
    }

    @Test
    public void shouldChangeStatusFromReOpenToPending() {
        item.toggleStatus();
        item.complete();
        item.reOpen();
        item.toggleStatus();
        assertEquals(item.getStatus(), ItemStatus.PENDING);
    }

    @Test
    public void shouldTrowExceptionWhileChangingStatusFromInProgressToReOpen() {
        item.toggleStatus();
        assertThrows(TodoItemReOpenStatusExceptionThrow.class,
                () -> item.reOpen());
    }

    @Test
    public void shouldNotCompleteItemInPendingStatus() {
        assertThrows(TodoItemPendingStatusToCompleteExceptionThrow.class,
                () -> item.complete());
    }

}
