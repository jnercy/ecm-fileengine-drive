package com.nextcont.drive.utils;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Try monad
 * Created by wangxudong on 16/3/2.
 */
abstract public class Try<V> {

    private Try(){}

    abstract public boolean isSuccess();
    abstract public boolean isFailure();
    abstract public void throwException();

    public <U> Try<U> map(Function<V,U> mapper) {
        Objects.requireNonNull(mapper);
        if (isFailure())
            return failure(failureException());
        else {
            V v  = successValue();
            try{
                return Try.success(Objects.requireNonNull(mapper.apply(v)));
            }catch(Throwable e){
                return Try.failure(e);
            }
        }
    }

    public <U> Try<U> flatMap(Function<V,Try<U>> mapper){
        Objects.requireNonNull(mapper);
        if (isFailure())
            return failure(failureException());
        else {
            V v  = successValue();
            try{
                return Objects.requireNonNull(mapper.apply(v));
            }catch(Throwable e){
                return Try.failure(e);
            }
        }
    }

    public Try<V> ifPresent(Consumer<V> c) {
        if (isSuccess()) {
            c.accept(successValue());
        }
        return this;
    }

    public void ifPresentOrThrow(Consumer<V> c) {
        if (isSuccess()) {
            c.accept(successValue());
        } else {
            throwException();
        }
    }

    public Try<V> ifThrowable(Consumer<Throwable> c){
        if(isFailure()){
            c.accept(failureException());
        }
        return this;
    }

    public V getOrThrow(){
        if (isSuccess()) {
            return successValue();
        } else {
            throw failureException();
        }
    }

    public Optional<V> optional(){
        if (isSuccess()) {
            return Optional.of(successValue());
        } else {
            return Optional.empty();
        }
    }

    public static <V> Try<V> failure(String message) {
        return new Failure<>(message);
    }

    public static <V> Try<V> failure(String message, Throwable e) {
        return new Failure<>(message, e);
    }

    public static <V> Try<V> failure(Throwable e) {
        return new Failure<>(e);
    }

    public static <V> Try<V> lift(V value) {
        if(value==null)
            return failure(new NullPointerException("value must not be empty!"));
        return success(value);
    }

    public static <V> Try<V> success(V value) {
        return new Success<>(value);
    }

    public static <V> Try<V> supplier(Supplier<V> supplier){
        Objects.requireNonNull(supplier);
        try{
            return Try.success(supplier.get());
        }catch(Exception e){
            return Try.failure(e);
        }
    }

    public static <V extends Collection<?>> Try<V> collMustHaveSomeOne(V coll){
        if(coll==null || coll.size()==0)
            return Try.failure("size of collection must be > 0");
        return Try.success(coll);
    }

    public static <V> Try<V> test(V value, Predicate<V> tester){
        if(tester.test(value))
            return success(value);
        else
            return failure("no passed the test");
    }

    public static <V> Try<V> test(V value, Predicate<V> tester,String message){
        if(tester.test(value))
            return success(value);
        else
            return failure(message);
    }

    public static <V,U> Try<U> tried(V v,Function<V,U> mapper){
        Objects.requireNonNull(mapper);
        try{
            return Objects.requireNonNull(Try.success(mapper.apply(v)));
        }catch(Throwable e){
            return Try.failure(e);
        }
    }

    public static <V> void tried(V v  ,Consumer<V> mapper){
        Objects.requireNonNull(mapper);
        try{
            mapper.accept(v);
        }catch(Throwable e){
           e.printStackTrace();
        }
    }

    public V successValue(){
        return ((Success<V>)this).value;
    }

    public RuntimeException failureException(){
        return ((Failure<V>)this).exception;
    }


    private static class Success<V> extends Try<V>{

        private V value;

        public Success(V v){
            super();
            this.value = v;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public void throwException() {

        }
    }

    private static class Failure<V> extends Try<V>{

        private RuntimeException exception;

        public Failure(String message) {
            super();
            this.exception = new IllegalStateException(message);
        }
        public Failure(String message, Throwable e) {
            super();
            this.exception = new IllegalStateException(message, e);
        }
        public Failure(Throwable e) {
            super();
            this.exception = new IllegalStateException(e);
        }


        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public void throwException() {
            throw this.exception;
        }
    }

}


