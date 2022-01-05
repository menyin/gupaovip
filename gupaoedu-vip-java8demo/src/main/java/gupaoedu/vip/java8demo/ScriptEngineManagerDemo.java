package gupaoedu.vip.java8demo;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * Java 调用 JavaScript
 * 学习 详见 https://www.jianshu.com/p/ba33c52c5b43
 */
public class ScriptEngineManagerDemo {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        //普通调用
        /*ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        engine.eval("print('hello word!!')");*/

        //js对象调用，并且可以得到返回值
        /*ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String script = "var obj = new Object();"+"obj.hello = function(name){print('hello, '+name);return '这是一个返回结果';}";
        engine.eval(script);
        // javax.script.Invocable 是一个可选的接口
        // 检查你的script engine 接口是否已实现!
        // 注意：JavaScript engine实现了Invocable接口
        Invocable inv = (Invocable) engine;
        // 获取我们想调用那个方法所属的js对象
        Object obj = engine.get("obj");
        // 执行obj对象的名为hello的方法
        Object result = inv.invokeMethod(obj, "hello", "Script Method !!");
        System.out.println(result);*/

        //通过js文件调用
        /*ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        engine.eval(new java.io.FileReader(new File("E:/test/test.js")));
        Invocable inv = (Invocable) engine;
        Object obj = engine.get("obj");
        inv.invokeMethod(obj, "name", "知道了" );*/
        TimeLine timeLine = new TimeLine();
        timeLine.begin("push-center-begin");
        timeLine.addTimePoint("p1");
        timeLine.addTimePoint("p2");
        timeLine.addTimePoint("p3");
        timeLine.end();
        System.out.println(timeLine);
    }




    public final static class TimeLine {
        private final TimePoint root = new TimePoint("root");
        private final String name;
        private int pointCount;
        private TimePoint current = root;

        public TimeLine() {
            name = "TimeLine";
        }

        public TimeLine(String name) {
            this.name = name;
        }

        public void begin(String name) {
            addTimePoint(name);
        }

        public void begin() {
            addTimePoint("begin");
        }

        public void addTimePoint(String name) {
            current = current.next = new TimePoint(name);
            pointCount++;
        }

        public void addTimePoints(Object[] points) {
            if (points != null) {
                for (int i = 0; i < points.length; i++) {
                    current = current.next = new TimePoint((String) points[i], ((Number) points[++i]).longValue());
                    pointCount++;
                }
            }
        }

        public TimeLine end(String name) {
            addTimePoint(name);
            return this;
        }

        public TimeLine end() {
            addTimePoint("end");
            return this;
        }

        public TimeLine successEnd() {
            addTimePoint("success-end");
            return this;
        }

        public TimeLine failureEnd() {
            addTimePoint("failure-end");
            return this;
        }

        public TimeLine timeoutEnd() {
            addTimePoint("timeout-end");
            return this;
        }

        public void clean() {
            root.next = null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(name);
            if (root.next != null) {
                sb.append('[').append(current.time - root.next.time).append(']').append("(ms)");
            }
            sb.append('{');
            TimePoint next = root;
            while ((next = next.next) != null) {
                sb.append(next.toString());
            }
            sb.append('}');
            return sb.toString();
        }

        public Object[] getTimePoints() {
            Object[] arrays = new Object[2 * pointCount];
            int i = 0;
            TimePoint next = root;
            while ((next = next.next) != null) {
                arrays[i++] = next.name;
                arrays[i++] = next.time;
            }
            return arrays;
        }

        private static class TimePoint { //？？这里为什么用static
            private final String name;
            private final long time;
            private transient TimePoint next;

            public TimePoint(String name) {
                this.name = name;
                this.time = System.currentTimeMillis();
            }

            public TimePoint(String name, long time) {
                this.name = name;
                this.time = time;
            }

            public void setNext(TimePoint next) {
                this.next = next;
            }

            @Override
            public String toString() {
                if (next == null) return name;
                return name + " --（" + (next.time - time) + "ms) --> ";
            }
        }
    }

}
