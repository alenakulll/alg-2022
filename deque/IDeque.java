package ru.bgpu.task.deque;

public interface IDeque<T> {
	boolean isEmpty();

	boolean isFull();

	void pushBack(T value);

	T popBack();

	T peekBack();

	void pushFront(T value);

	T popFront();

	T peekFront();
}