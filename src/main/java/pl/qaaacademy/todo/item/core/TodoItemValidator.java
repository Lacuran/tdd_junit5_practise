package pl.qaaacademy.todo.item.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.qaaacademy.todo.item.Exception.TodoItemDescriptionValidationException;
import pl.qaaacademy.todo.item.Exception.TodoItemValidationException;
import pl.qaaacademy.todo.item.TodoItem;

import java.util.List;
import java.util.function.Predicate;

public class TodoItemValidator {
    protected static final Logger logger = LoggerFactory.getLogger(TodoItem.class);

    private static List<Predicate<String>> titleValidationCriteria = List.of(
            t -> !t.isBlank(),
            t -> t.length() > 5
    );

    private static boolean validateItemProperty(String title, List<Predicate<String>> criteria) {
        return criteria.stream().anyMatch(crit -> crit.test(title));
    }

    public static void validateTitle(String title) {
        if (!validateItemProperty(title, titleValidationCriteria)) {
            logger.error("Item title or description is null or blank");
            throw new TodoItemValidationException("Item title or description is null or blank");
        }
    }

    public static void validateDescription(String description, Predicate<String> criterion) {
        if (criterion.test(description)) {
            logger.error("Description length is longer than 250 or null");
            throw new TodoItemDescriptionValidationException("Description length is longer than 250 or null");
        }
    }
}
