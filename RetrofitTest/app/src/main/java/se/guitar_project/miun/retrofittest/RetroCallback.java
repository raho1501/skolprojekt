package se.guitar_project.miun.retrofittest;

/**
 * Created by linus on 2017-10-12.
 */

public interface RetroCallback<T> {
    public void onResponse(T entity);
}
