package com.neathorium.thorium.java.extensions.namespaces.concurrent;

import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;
import com.neathorium.thorium.java.extensions.time.records.TimeEntryData;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public interface CompletableFutureExtensions {
    private static CompletableFuture<?> allOfTerminateOnFailure(Function<CompletableFuture<?>[], CompletableFuture<?>> handler, CompletableFuture<?>... tasks) {
        final var failure = new CompletableFuture<>();
        for (var task : tasks) {
            task.exceptionally(ex -> {
                failure.completeExceptionally(ex);
                return null;
            });
        }

        return CompletableFuture.anyOf(failure, handler.apply(tasks));
    }

    private static CompletableFuture<?> anyOfTerminateOnFailureCore(Function<CompletableFuture<?>[], CompletableFuture<?>> handler, CompletableFuture<?>... tasks) {
        final var failure = new CompletableFuture<>();
        for (var task : tasks) {
            task.whenComplete((a, ex) -> {
                failure.complete(null);
                for (var task2: tasks) {
                    if (BooleanUtilities.isFalse(task2.isDone())) {
                        task2.complete(null);
                    }
                }
                return;
            });
        }

        return CompletableFuture.anyOf(failure, handler.apply(tasks));
    }

    static CompletableFuture<?> taskWithTimeout(CompletableFuture<?> task, TimeEntryData timeData) {
        return task.orTimeout(timeData.LENGTH(), timeData.TIME_UNIT());
    }
    static Function<CompletableFuture<?>, CompletableFuture<?>> taskWithTimeout(TimeEntryData timeData) {
        return task -> CompletableFutureExtensions.taskWithTimeout(task, timeData);
    }

    static Function<CompletableFuture<?>[], CompletableFuture<?>> taskWithTimeout(Function<CompletableFuture<?>[], CompletableFuture<?>> function, TimeEntryData timeData) {
        return function.andThen(CompletableFutureExtensions.taskWithTimeout(timeData));
    }

    static CompletableFuture<?> allOfTerminateOnFailureTimed(TimeEntryData data, CompletableFuture<?>... tasks) {
        return CompletableFutureExtensions.allOfTerminateOnFailure(CompletableFutureExtensions.taskWithTimeout(CompletableFuture::allOf, data), tasks);
    }

    static CompletableFuture<?> anyOfTerminateOnFailureTimed(TimeEntryData data, CompletableFuture<?>... tasks) {
        return CompletableFutureExtensions.anyOfTerminateOnFailureCore(CompletableFutureExtensions.taskWithTimeout(CompletableFuture::anyOf, data), tasks);
    }

    static CompletableFuture<?> allOfTerminateOnFailureTimed(int duration, CompletableFuture<?>... tasks) {
        final var data = new TimeEntryData(duration, TimeUnit.MILLISECONDS);
        return CompletableFutureExtensions.allOfTerminateOnFailureTimed(data, tasks);
    }

    static CompletableFuture<?> anyOfTerminateOnFailureTimed(int duration, CompletableFuture<?>... tasks) {
        final var data = new TimeEntryData(duration, TimeUnit.MILLISECONDS);
        return CompletableFutureExtensions.anyOfTerminateOnFailureTimed(data, tasks);
    }

    static CompletableFuture<?> allOfTerminateOnFailure(CompletableFuture<?>... tasks) {
        return CompletableFutureExtensions.allOfTerminateOnFailure(CompletableFuture::allOf, tasks);
    }

    static CompletableFuture<?> anyOfTerminateOnFailure(CompletableFuture<?>... tasks) {
        return CompletableFutureExtensions.anyOfTerminateOnFailureCore(CompletableFuture::anyOf, tasks);
    }
}
