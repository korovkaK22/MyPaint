package com.example.mypaint.controllers.dialogwindows;

@FunctionalInterface
public interface FourArgsConsumer<A, B, C, D> {
    void accept(A a, B b, C c, D d);
}
