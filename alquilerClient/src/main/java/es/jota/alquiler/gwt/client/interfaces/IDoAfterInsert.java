package es.jota.alquiler.gwt.client.interfaces;

public interface IDoAfterInsert<T> {
	void exec( T id );
}