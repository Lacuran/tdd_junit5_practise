package pl.qaaacademy.todo.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.qaaacademy.todo.item.Exception.TodoItemCompleteChangeStatusException;
import pl.qaaacademy.todo.item.Exception.TodoItemCompleteStatusException;
import pl.qaaacademy.todo.item.Exception.TodoItemPendingStatusToCompleteExceptionThrow;
import pl.qaaacademy.todo.item.Exception.TodoItemReOpenStatusExceptionThrow;
import pl.qaaacademy.todo.item.enums.ItemStatus;
import pl.qaaacademy.todo.item.interfaces.StatusChangeable;

import java.util.Objects;
import java.util.function.Predicate;

import static pl.qaaacademy.todo.item.core.TodoItemValidator.validateDescription;
import static pl.qaaacademy.todo.item.core.TodoItemValidator.validateTitle;

public class TodoItem implements StatusChangeable {
    private String title;
    private String description;
    private ItemStatus status;
    private static Predicate<String> descriptionCriteria;

    protected static final Logger logger;

    static {
        logger = LoggerFactory.getLogger(TodoItem.class);
        descriptionCriteria =
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem item = (TodoItem) o;
        return Objects.equals(title, item.title) && Objects.equals(description, item.description) && status == item.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, status);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
