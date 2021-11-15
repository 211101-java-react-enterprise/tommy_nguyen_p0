package com.revature.banking.util.collections;

// Interface acts as a contract for implementing classes

// do not have constructors so cannot be instantiated beacuse they
public interface List<T> extends Collection<T> {

    T get(int index);

}
