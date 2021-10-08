package pl.qaaacademy.todo.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.qaaacademy.todo.item.TodoItem;
import pl.qaaacademy.todo.item.enums.ItemStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TodoList {
    private String title;
    private List<TodoItem> itemList;

    protected static final Logger logger = LoggerFactory.getLogger(TodoList.class);


    public TodoList(String title) {
        if (title.isBlank()) {
            logger.error("Cant create list with empty title");
            throw new TodoListValidTitleExceptionThrow("Cant create list with empty title");
        } else {
            this.title = title;
        }

        this.itemList = new ArrayList<>();
    }

    /*public static TodoList of(String title) {
        if (title.isBlank()) {
            logger.error("Cant create list with empty title");
            throw new TodoListValidTitleExceptionThrow("Cant create list with empty title");
        } else {
            return new TodoList(title);
        }

    }*/

    public String getListTitle() {
        return title;
    }

    public int getListSize() {
        return itemList.size();
    }

    public void setListTitle(String title) {
        this.title = title;
    }

    public List<TodoItem> getItemList() {
        return itemList;
    }

    public void addItem(TodoItem item) {
        for (TodoItem t : this.itemList) {
            if (t.getTitle().equals(item.getTitle())) {
                logger.error("Same item already exists");
                throw new TodoListSameItemTitleExceptionThrow("Same item already exists");
            }
        }
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

    public void removeItem(String itemTitle) {
        for (TodoItem t : itemList.stream().toList()) {
            if (t.getTitle().equals(itemTitle)) {
                removeItem(t);
            }
        }
        /*TodoList newlist = TodoList.of(this.title);
        itemList.forEach(t -> {
            if (t.getTitle().equals(itemTitle)) {
                this.itemList.remove(t);
            }

            newlist.addItem(t);
        });
        return newlist;*/

    }

    public void removeItem(TodoItem item) {
        if (this.itemList.contains(item)) {
            this.itemList.remove(item);
        }
    }


    public static TodoList fuzeList(TodoList firstList, TodoList secondList, String title) {
        TodoList newList = new TodoList(title);

        for (TodoItem t : firstList.getItemList()) {
            newList.addItem(t);
            System.out.println("Added item form first list");

        }
        for (TodoItem t : secondList.getItemList()) {
            newList.addItem(t);
            System.out.println("Added item form second list");
        }

        return newList;

    }


    public void filterByStatus(ItemStatus status) {
//        TodoList newList = new TodoList(title);
        for (TodoItem s : itemList.stream().toList()) {
            if (!s.getStatus().equals(status)) {
                removeItem(s);
            }
        }
//        return newList;
    }

    public void sortList() {
        this.itemList = getItemList().stream()
                .sorted(Comparator.comparing(TodoItem::getTitle))
                .collect(Collectors.toList());
    }
}
