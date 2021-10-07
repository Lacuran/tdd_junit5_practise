package pl.qaaacademy.todo.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

public class TodoItem implements StatusChangeable {
    private String title;
    private String description;
    private ItemStatus status;

    protected static final Logger logger;

    static {
        logger = LoggerFactory.getLogger(TodoItem.class);

    }

    private TodoItem() {
    }

    private TodoItem(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = ItemStatus.PENDING;
    }

    public static TodoItem of(String title, String description) {
        validateTitle(title);
        validateDescription(description);
        return new TodoItem(title, description);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        if (this.status == ItemStatus.COMPLETED) {
            logger.error("Cant change title or description after item is completed");
            throw new TodoItemCompleteChangeStatusException("Cant change title or description after item is completed");
        } else {
            validateTitle(title);
            this.title = title;
        }
    }

    public void setDescription(String description) {
        if (this.status == ItemStatus.COMPLETED) {
            logger.error("Cant change title or description after item is completed");
            throw new TodoItemCompleteChangeStatusException("Cant change title or description after item is completed");
        } else {
            validateDescription(description);
            this.description = description;
        }
    }

    private static void validateTitle(String title) {
        if ((title == null || title.isBlank())) { //TODO add description check
            logger.error("Item title or description is null or blank");
            throw new TodoItemValidationException("Item title or description is null or blank");
        }
    }

    private static void validateDescription(String description) {
        if (description == null || description.isBlank() || description.length() > 250) {
            logger.error("Description length is longer than 250 or null");
            throw new TodoItemDescriptionValidationException("Description length is longer than 250 or null");
        }

    }

    public void validateTest(String title, List<Predicate<String>> criteria) {
        //TODO
        // stream pipeline for criteria
        // each criteria get a title to perform checks
        // collect to list of boolean
        // filter list to find false, to list and check if list size is > 0


    }

    @Override
    public void toggleStatus() {
        if (this.status == ItemStatus.COMPLETED) {
            logger.error("Can't change status from Completed");
            throw new TodoItemCompleteStatusException("Can't change status from Completed");
        }
        if (this.status == ItemStatus.PENDING) {
            this.status = ItemStatus.IN_PROGRESS;
        } else if (this.status == ItemStatus.REOPEN) {
            this.status = ItemStatus.PENDING;
        } else {
            this.status = ItemStatus.PENDING;
        }
    }

    @Override
    public void reOpen() {
        if (this.status != ItemStatus.COMPLETED) {
            logger.error("Can't change status from In_Progress or Pending");
            throw new TodoItemReOpenStatusExceptionThrow("Can't change status from In_Progress or Pending");
        } else {
            this.status = ItemStatus.REOPEN;
        }
    }

    @Override
    public void complete() {
        if (this.status != ItemStatus.IN_PROGRESS) {
            logger.error("Cant change status to complete from pending");
            throw new TodoItemPendingStatusToCompleteExceptionThrow("Cant change status to complete from pending");
        } else {
            this.status = ItemStatus.COMPLETED;
        }
    }
}
