package de.homelabs.wiki.entity;

public class Result<T, U> {

	public T ok;
	public U value;
	
	public Result(T ok, U value) {
		this.ok = ok;
		this.value = value;
	}
}
