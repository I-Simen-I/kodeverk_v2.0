package no.sands.kodeverk.index;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

import java.util.EnumMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import no.sands.kodeverk.commandline.KodeverkOption;
import no.sands.kodeverk.index.tasks.Help;
import no.sands.kodeverk.index.tasks.Leveranse;
import no.sands.kodeverk.index.tasks.Task;

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
    }

    @Test
    public void shouldReturnInstanceOfHelpWhenInstanceIsTheOnlyOption() {
        Map<KodeverkOption, String> map = new EnumMap<KodeverkOption, String>(KodeverkOption.class);
        map.put(KodeverkOption.HELP, null);

        Task task = TaskFactory.createTask(map);
        assertThat(task, is(notNullValue()));
        assertThat(task, is(instanceOf(Help.class)));
        assertThat(task.getArgument(), is(nullValue()));
    }

    @Test
    public void shouldReturnInstanceOfLeveranseWhenLeveranseIsTheOnlyOption() {
        Map<KodeverkOption, String> map = new EnumMap<KodeverkOption, String>(KodeverkOption.class);
        map.put(KodeverkOption.LEVERANSE, "argument");

        Task task = TaskFactory.createTask(map);
        assertThat(task, is(notNullValue()));
        assertThat(task, is(instanceOf(Leveranse.class)));
        assertThat(task.getArgument(), is("argument"));
    }

    @Test
    public void shouldReturnNullWhenMapIsEmpty() {
        Task task = TaskFactory.createTask(new EnumMap<KodeverkOption, String>(KodeverkOption.class));
        assertThat(task, is(nullValue()));
    }

    @Test
    public void shouldReturnInstanceOfLeveranseWhenLeveranseIsTheFirstOption() {
        Map<KodeverkOption, String> map = new EnumMap<KodeverkOption, String>(KodeverkOption.class);
        map.put(KodeverkOption.LEVERANSE, "argument");
        map.put(KodeverkOption.HELP, "heeelp");

        Task task = TaskFactory.createTask(map);
        assertThat(task, is(notNullValue()));
        assertThat(task, is(instanceOf(Leveranse.class)));
        assertThat(task.getArgument(), is("argument"));
    }

    @Test
    public void shouldReturnNullIfMapIsNull() {
        Task task = TaskFactory.createTask(null);
        assertThat(task, is(nullValue()));
    }

}