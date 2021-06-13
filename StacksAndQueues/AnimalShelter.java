package StacksAndQueues;

import java.util.LinkedList;

/*
 * Animal Shelter: An animal shelter, which holds only dogs and cats,
 * operates on a strictly FIFO basis. People must adopt either the
 * oldest (based on arrival time) of all animals at the shelter, or
 * they can select whether they would prefer a dog or a cat (and will
 * receive the oldest animal of that type). They cannot select which
 * specific animal they would like. Create the data structures to
 * maintain this system and implement operations such as enqueue,
 * dequeueAny, dequeueDog, and dequeueCat. You may use the built-in
 * LinkedList data structure.
 */
abstract class Animal {
    private int order;
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public int getOrder() { return this.order; }
    public void setOrder(int order) { this.order = order; }
}

class Dog extends Animal {
    public Dog(String n) { super(n); }
};

class Cat extends Animal {
    public Cat(String n) { super(n); }
}

public class AnimalShelter {
    private LinkedList<Dog> dogs = new LinkedList<>();
    private LinkedList<Cat> cats = new LinkedList<>();
    private int order = 0;

    public void enqueue(Animal animal) {
        animal.setOrder(order++);
        if (animal instanceof Dog) dogs.add((Dog) animal);
        if (animal instanceof Cat) cats.add((Cat) animal);
    }

    public Animal dequeueAny() {
        if (dogs.isEmpty()) return dequeueCat();
        if (cats.isEmpty()) return dequeueDog();
        if (dogs.peek().getOrder() < cats.peek().getOrder()) {
            return dequeueDog();
        } else {
            return dequeueCat();
        }
    }

    public Dog dequeueDog() {
        return dogs.poll();
    }

    public Cat dequeueCat() {
        return cats.poll();
    }

    public int size() {
        return dogs.size() + cats.size();
    }

    public static void main(String[] args) {
        AnimalShelter animals = new AnimalShelter();
        animals.enqueue(new Cat("Callie"));
        animals.enqueue(new Cat("Kiki"));
        animals.enqueue(new Dog("Fido"));
        animals.enqueue(new Dog("Dora"));
        animals.enqueue(new Cat("Kari"));
        animals.enqueue(new Dog("Dexter"));
        animals.enqueue(new Dog("Dobo"));
        animals.enqueue(new Cat("Copa"));

        System.out.println(animals.dequeueAny().name);
        System.out.println(animals.dequeueAny().name);
        System.out.println(animals.dequeueAny().name);

        animals.enqueue(new Dog("Dapa"));
        animals.enqueue(new Cat("Kilo"));

        while (animals.size() != 0) System.out.println(animals.dequeueAny().name);
    }
}