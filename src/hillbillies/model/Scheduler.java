//TODO	comments (formal!)
package hillbillies.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

import be.kuleuven.cs.som.annotate.*;

/**
 * @invar Each scheduler must have proper tasks. | hasProperTasks()
 * @invar Each scheduler can have its faction as faction. |
 *        canHaveAsFaction(this.getFaction()
 */
public class Scheduler {

	/**
	 * Initialize this new scheduler with given faction.
	 * 
	 * @param faction
	 *            The faction for this new scheduler.
	 * @post The faction of this new scheduler is equal to the given faction. |
	 *       new.getFaction() == faction
	 * @throws IllegalArgumentException
	 *             This new scheduler cannot have the given faction as its
	 *             faction. | ! canHaveAsFaction(this.getFaction()))
	 */
	public Scheduler(Faction faction) throws IllegalArgumentException {
		if (!canHaveAsFaction(faction))
			throw new IllegalArgumentException();
		this.faction = faction;
	}

	/**
	 * Check whether this scheduler has the given task as one of its tasks.
	 * 
	 * @param task
	 *            The task to check.
	 */
	@Basic
	@Raw
	public boolean hasAsTask(@Raw Task task) {
		return tasks.contains(task);
	}

	/**
	 * Check whether this scheduler can have the given task as one of its tasks.
	 * 
	 * @param task
	 *            The task to check.
	 * @return True if and only if the given task is effective and that task is
	 *         a valid task for a scheduler. | result == | (task != null) && |
	 *         Task.isValidScheduler(this)
	 */
	@Raw
	public boolean canHaveAsTask(Task task) {
		return (task != null);
	}

	/**
	 * Check whether this scheduler has proper tasks attached to it.
	 * 
	 * @return True if and only if this scheduler can have each of the tasks
	 *         attached to it as one of its tasks, and if each of these tasks
	 *         references this scheduler as the scheduler to which they are
	 *         attached. | for each task in Task: | if (hasAsTask(task)) | then
	 *         canHaveAsTask(task) && | (task.getScheduler() == this)
	 */
	public boolean hasProperTasks() {
		for (Task task : tasks) {
			if (!canHaveAsTask(task))
				return false;
		}
		return true;
	}

	/**
	 * Return the number of tasks associated with this scheduler.
	 *
	 * @return The total number of tasks collected in this scheduler. | result
	 *         == | card({task:Task | hasAsTask({task)})
	 */
	public int getNbTasks() {
		return tasks.size();
	}

	/**
	 * Add the given task to the set of tasks of this scheduler.
	 * 
	 * @param task
	 *            The task to be added.
	 * @pre The given task is effective and already references this scheduler. |
	 *      (task != null) && (task.getScheduler() == this)
	 * @post This scheduler has the given task as one of its tasks. |
	 *       new.hasAsTask(task)
	 */
	public void addTask(@Raw Task task) {
		assert canHaveAsTask(task);
		tasks.add(task);
		task.getAllSchedulers().add(this);
	}

	/**
	 * Remove the given task from the set of tasks of this scheduler.
	 * 
	 * @param task
	 *            The task to be removed.
	 * @pre This scheduler has the given task as one of its tasks, and the given
	 *      task does not reference any scheduler. | this.hasAsTask(task) && |
	 *      (task.getScheduler() == null)
	 * @post This scheduler no longer has the given task as one of its tasks. |
	 *       ! new.hasAsTask(task)
	 */
	@Raw
	public void removeTask(Task task) {
		assert this.hasAsTask(task);
		if (task.isBeingExecuted())
			// TODO: unit begint aan volgende task
			System.out.println(); // lijn code zodat eclipse niet moeilijk gaat
									// doen over if
		tasks.remove(task);
		task.getAllSchedulers().remove(this);
	}

	public Set<Task> getAllTasks() {
		return this.tasks;
	}

	public void replaceTask(Task original, Task replacement) {
		if (original.isBeingExecuted())
			// TODO: unit begint aan volgende task
			System.out.println(); // lijn code zodat eclipse niet moeilijk gaat
									// doen over if
		this.removeTask(original);
		this.addTask(replacement);

	}

	public boolean areTasksPartOf(Collection<Task> tasks) {
		for (Task task : tasks) {
			if (!this.hasAsTask(task))
				return false;
		}
		return true;
	}

	public Iterator<Task> getAllTasksIterator() {
		return new Iterator<Task>() {
			public boolean hasNext() {
				return (index < sortedTasks.size() - 1);
			}

			public Task next() {
				index += 1;
				return sortedTasks.get(index);
			}

			private ArrayList<Task> sortedTasks = getSortedTasks();
			private int index = -1;
		};
	}

	public ArrayList<Task> getSortedTasks() {
		ArrayList<Task> result = new ArrayList<Task>(this.tasks);
		result.sort((a, b) -> a.compareTo(b));
		return result;
	}

	public Task getHighestPriorityTaskNotExecuted() {
		for (Task task : getSortedTasks()) {
			if (!task.isBeingExecuted())
				return task;
		}
		return null;
	}

	public void assignTaskToUnit(Task task, Unit unit) {
		if (task.isBeingExecuted() && task.getUnit() != unit) {
			// TODO: stop executing task
		}
		if (unit.getTask() != null) {
			this.addTask(unit.getTask());
		}
		unit.setTask(task);
		task.setUnit(unit);
	}

	public void resetTaskToUnit(Task task, Unit unit) throws IllegalStateException {
		if (task.getUnit() != unit || unit.getTask() != task)
			throw new IllegalStateException();
		unit.setTask(null); // TODO: of unit.nextTask ofzo?
		task.setUnit(null);
	}

	public Set<Task> getAllTasksThatSatisfy(Predicate<Task> condition) {
		Set<Task> tasksSoFar = new HashSet<Task>(getAllTasks());
		tasksSoFar.stream().filter(condition);
		return tasksSoFar;
	}

	/**
	 * Variable referencing a set collecting all the tasks of this scheduler.
	 * 
	 * @invar The referenced set is effective. | tasks != null
	 * @invar Each task registered in the referenced list is effective and not
	 *        yet terminated. | for each task in tasks: | ( (task != null) && |
	 *        (! task.isTerminated()) )
	 */
	private final Set<Task> tasks = new HashSet<Task>();

	/**
	 * Return the faction of this scheduler.
	 */
	@Basic
	@Raw
	@Immutable
	public Faction getFaction() {
		return this.faction;
	}

	/**
	 * Check whether this scheduler can have the given faction as its faction.
	 * 
	 * @param faction
	 *            The faction to check.
	 * @return | result == (faction != null)
	 */
	@Raw
	public boolean canHaveAsFaction(Faction faction) {
		return faction != null;
	}

	/**
	 * Variable registering the faction of this scheduler.
	 */
	private final Faction faction;

}
