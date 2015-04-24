package no.sands.kodeverk.index.tasks;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * @author Øyvind Strømmen
 */
public class GroupedTask implements Task {

    private String argument;

    private List<Task> tasks = new ArrayList<>();

    @Override
    public void execute() {
        for (Task task : tasks) {
            task.execute();
        }
    }

    public GroupedTask with(Task task) {
        tasks.add(task);
        return this;
    }

    @Override
    public Task withArgument(String argument) {
        this.argument = argument;
        return this;
    }

    @Override
    public String getArgument() {
        return argument;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object == this) {
            return true;
        }

        if (object.getClass() != getClass()) {
            return false;
        }

        GroupedTask groupedTask = (GroupedTask) object;
        return new EqualsBuilder()
                .append(argument, groupedTask.getArgument())
                .append(tasks.toArray(), groupedTask.getTasks().toArray())
                .isEquals();
    }
}
