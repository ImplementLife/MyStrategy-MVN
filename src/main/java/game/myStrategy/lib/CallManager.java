package game.myStrategy.lib;

import game.myStrategy.lib.noConcurrent.NoConcurrentList;

import java.util.Objects;
import java.util.function.Consumer;

// TODO: 27.10.2021 Отправить в нужный пакет
public class CallManager<T> {
    private final NoConcurrentList<Call<T>> list = new NoConcurrentList<>();

    public void iterate(Consumer<? super T> action) {
        list.forEach(call -> call.call(action));
    }
    private void put(Call<T> o) {
        list.add(o);
    }
    private void remove(Call<T> o) {
        list.remove(o);
    }
    public Call<T> get(T t) {
        return new Call<>(t, this);
    }

    //==============================================//
    public static class Call<T> {
        private final CallManager<T> callManager;
        private final T target;
        private boolean canCall;

        private Call(T target, CallManager<T> callManager) {
            this.callManager = callManager;
            this.target = target;
            this.callManager.put(this);
        }

        public void setCanCall(boolean canCall) {
            this.canCall = canCall;
        }

        public void call(Consumer<? super T> action) {
            if (canCall) action.accept(target);
        }

        public void remove() {
            callManager.remove(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Call<?> call = (Call<?>) o;
            return  canCall == call.canCall
                    && Objects.equals(callManager, call.callManager)
                    && Objects.equals(target, call.target);
        }

        @Override
        public int hashCode() {
            return target.hashCode();
        }
    }
}
