package pl.qaaacademy.todo.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.qaaacademy.todo.item.TodoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TodoList {
    private String title;
    private List<TodoItem> itemList;

    protected static final Logger logger = LoggerFactory.getLogger(TodoList.class);

    private TodoList() {
    }

    private TodoList(String title) {
        this.title = title;
        this.itemList = new ArrayList<>();
    }

    public static TodoList of(String title) {
        if (title.isBlank()) {
            logger.error("Cant create list with empty title");
            throw new TodoListValidTitleExceptionThrow("Cant create list with empty title");
        } else {
            return new TodoList(title);
        }

    }

    public String getListTitle() {
        return title;
    }

    public long getListSize() {
        return itemList.size();
    }

    public void setListTitle(String title) {
        this.title = title;
    }

    public List<TodoItem> getItemList() {
        return itemList;
    }

    public void addItem(TodoItem item) {
        itemList.add(item);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoList todoList = (TodoList) o;
        return Objects.equals(title, todoList.title) && Objects.equals(itemList, todoList.itemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, itemList);
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "title='" + title + '\'' +
                ", itemList=" + itemList +
                '}';
    }

    public TodoList removeItem(String itemTitle) {


        return new TodoList(this.title);
    }
}
