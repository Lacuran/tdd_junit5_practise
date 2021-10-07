package pl.qaaacademy.todo.item;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void shouldThrowAnExceptionWhileSettingAnEmptyTitle() {
        String emptyTitle = "";
        assertThrows(TodoItemValidationException.class,
                () -> item.setTitle(emptyTitle));
    }

    @Test
    public void shouldChangeStatusFromPendingToInProgress() {
        item.toggleStatus();
        assertEquals(ItemStatus.IN_PROGRESS, item.getStatus());
    }

    @Test
    public void shouldChangeStatusFromInProgressToInPending() {
        item.toggleStatus();
        item.toggleStatus();
        assertEquals(ItemStatus.PENDING, item.getStatus());
    }

    @Test
    public void shouldCompleteATaskRepresentByItem() {
        item.toggleStatus();
        item.complete();
        assertEquals(ItemStatus.COMPLETED, item.getStatus());
    }

    @Test
    public void shouldCreateItemWithPendingStatus() {
        assertEquals(ItemStatus.PENDING, item.getStatus());
    }

    @Test
    public void shouldNotToggleStatusFromCompletedToInProgress() {
        item.toggleStatus();
        item.complete();
        assertThrows(TodoItemCompleteStatusException.class,
                () -> item.toggleStatus());
    }

    @Test
    public void shouldNotCreateATodoItemWithDescriptionLongerThan250Chars() {
        String descriptionLongerThan250Chars = RandomGeneratedString.randomGeneratedString(251);
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> TodoItem.of(title, descriptionLongerThan250Chars));
    }

    @Test
    public void shouldNotCreateATodoItemWithDescriptionWithNull() {
        String emptyDescription = "";
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> TodoItem.of(title, emptyDescription));
    }

    @Test
    public void shouldNotSetANewDescriptionLongerThan250Chars() {
        String descriptionLongerThan250Chars = RandomGeneratedString.randomGeneratedString(251);
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> item.setDescription(descriptionLongerThan250Chars));
    }

    @Test
    public void shouldNotSetAnEmptyNewDescription() {
        String emptyDescription = "";
        assertThrows(TodoItemDescriptionValidationException.class,
                () -> item.setDescription(emptyDescription));
    }

    @Test
    public void shouldChangeStatusFromCompleteToReOpen() {
        item.toggleStatus();
        item.complete();
        item.reOpen();
        assertEquals(ItemStatus.REOPEN, item.getStatus());
    }

    @Test
    public void shouldChangeStatusFromReOpenToPending() {
        item.toggleStatus();
        item.complete();
        item.reOpen();
        item.toggleStatus();
        assertEquals(ItemStatus.PENDING, item.getStatus());
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

    @Test
    public void shouldNotChangeTitleIfItemIsComplete() {
        //TODO
        String newTitle = "This is new title of a item";
        item.toggleStatus();
        item.complete();
        assertThrows(TodoItemCompleteChangeStatusException.class,
                () -> item.setTitle(newTitle));
    }

    @Test
    public void shouldNotChangeDescriptionIfItemIsComplete() {
        //TODO
        String newDescription = "This is new description for an item";
        item.toggleStatus();
        item.complete();
        assertThrows(TodoItemCompleteChangeStatusException.class,
                () -> item.setDescription(newDescription));
    }

    @Test
    public void shouldChangeDescriptionWhenItemIsInProgress() {
        String newDescription = "This is new item description";
        item.toggleStatus();
        item.setDescription(newDescription);
        assertEquals(newDescription, item.getDescription());
    }

    @Test
    public void shouldGiveListOfBooleans() {
        String emptyTitle = "";
        String nullTitle = null;
        List<Predicate<String>> criteria = List.of(
                s -> {
                    if (s.isBlank()) {
                        return false;
                    }
                    return true;
                },
                s -> title.length() > 3
        );
        item.validateTest(emptyTitle, criteria);

    }

}
