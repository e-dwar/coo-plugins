package event;

public interface Observer<E extends Event> {

	public void update(E event);
}
