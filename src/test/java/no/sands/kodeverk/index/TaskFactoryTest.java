package no.sands.kodeverk.index;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import no.sands.kodeverk.commandline.KodeverkOption;
import no.sands.kodeverk.index.tasks.Generate;
import no.sands.kodeverk.index.tasks.GroupedTask;
import no.sands.kodeverk.index.tasks.Help;
import no.sands.kodeverk.index.tasks.Leveranse;
import no.sands.kodeverk.index.tasks.Task;
import no.sands.kodeverk.index.tasks.Validate;

/**
 * Unit tests for {@link no.sands.kodeverk.index.TaskFactory}
 *
 * @author Øyvind Strømmen
 */
public class TaskFactoryTest {

    @BeforeClass
    public static void setUp() throws ClassNotFoundException {
        TaskFactory.registerTask(KodeverkOption.LEVERANSE, new Leveranse());
        TaskFactory.registerTask(KodeverkOption.HELP, new Help());
        TaskFactory.registerTask(KodeverkOption.GENERATE, new GroupedTask().with(new Validate()).with(new Generate()));
    }

    @Test
    public void shouldReturnInstanceOfHelpWhenInstanceIsTheOnlyOption() {
        Map<KodeverkOption, String> map = new EnumMap<>(KodeverkOption.class);
        map.put(KodeverkOption.HELP, null);

        Set<Task> tasks = TaskFactory.createTasks(map);
        assertThat(tasks, is(notNullValue()));
        assertThat(tasks, hasSize(1));
        assertThat(tasks, hasItem(new Help().withArgument(null)));
    }

    @Test
    public void shouldReturnInstanceOfLeveranseWhenLeveranseIsTheOnlyOption() {
        Map<KodeverkOption, String> map = new EnumMap<>(KodeverkOption.class);
        map.put(KodeverkOption.LEVERANSE, "argument");

        Set<Task> tasks = TaskFactory.createTasks(map);
        assertThat(tasks, is(notNullValue()));
        assertThat(tasks, hasSize(1));
        assertThat(tasks, hasItem(new Leveranse().withArgument("argument")));
    }

    @Test
    public void shouldReturnNullWhenMapIsEmpty() {
        Set<Task> tasks = TaskFactory.createTasks(new EnumMap<KodeverkOption, String>(KodeverkOption.class));
        assertThat(tasks, is(emptyCollectionOf(Task.class)));
    }

    @Test
    public void shouldReturnInstanceOfLeveranseWhenLeveranseIsTheFirstOption() {
        Map<KodeverkOption, String> map = new EnumMap<>(KodeverkOption.class);
        map.put(KodeverkOption.LEVERANSE, "argument");
        map.put(KodeverkOption.HELP, "heeelp");

        Set<Task> tasks = TaskFactory.createTasks(map);
        assertThat(tasks, is(notNullValue()));
        assertThat(tasks, hasSize(2));
        assertThat(tasks, hasItem(new Help().withArgument("heeelp")));
        assertThat(tasks, hasItem(new Leveranse().withArgument("argument")));
    }

    @Test
    public void shouldReturnNullIfMapIsNull() {
        Set<Task> tasks = TaskFactory.createTasks(null);
        assertThat(tasks, is(emptyCollectionOf(Task.class)));
    }

    @Test
    public void shouldReturnGroupedTaskWithValidateAndGenerate() {
        Map<KodeverkOption, String> map = new EnumMap<>(KodeverkOption.class);
        map.put(KodeverkOption.GENERATE, null);
        map.put(KodeverkOption.HELP, "heeelp");

        Set<Task> tasks = TaskFactory.createTasks(map);
        assertThat(tasks, is(Matchers.notNullValue()));
        assertThat(tasks, hasSize(2));
        assertThat(tasks, hasItem(new Help().withArgument("heeelp")));
        assertThat(tasks, hasItem(
                new GroupedTask()
                .with(new Validate())
                .with(new Generate())
                .withArgument(null))
        );
    }

    @Test
    public void shouldNotContainDuplicates() {
        Map<KodeverkOption, String> map = new EnumMap<>(KodeverkOption.class);
        map.put(KodeverkOption.HELP, "heeelp");
        map.put(KodeverkOption.GENERATE, null);
        map.put(KodeverkOption.HELP, "heeelp");

        Set<Task> tasks = TaskFactory.createTasks(map);
        assertThat(tasks, is(Matchers.notNullValue()));
        assertThat(tasks, hasSize(2));
        assertThat(tasks, hasItem(new Help().withArgument("heeelp")));
        assertThat(tasks, hasItem(
                new GroupedTask()
                        .with(new Validate())
                        .with(new Generate())
                        .withArgument(null))
        );
    }
}