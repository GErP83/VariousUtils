package com.gerp83.variousutils;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A simple log class for write to System.out.println()
 */
public class L {


    public static void l(Object... objects) {

        if(objects == null ){
            System.out.println("null");
            return;
        }

        for (Object object : objects) {
            printObject(object);
        }
    }

    private static void printObject(Object object) {

        if(object == null ){
            System.out.println("null");
            return;
        }

        Class clazz = object.getClass();

        //it is a primitive type so we can print it easily
        if(isPrimitive(clazz) || isListOrMap(clazz)) {
            System.out.println(object);

        //it is an array
        } else if(clazz.isArray()) {

            Class type = clazz.getComponentType();
            if(type != null && isPrimitive(type)) {

                if(type == int.class)
                    System.out.println(Arrays.toString((int[])object));
                else if(type == long.class)
                    System.out.println(Arrays.toString((Long[])object));
                else if(type == double.class)
                    System.out.println(Arrays.toString((double[])object));
                else if(type == float.class)
                    System.out.println(Arrays.toString((float[])object));
                else if(type == short.class)
                    System.out.println(Arrays.toString((short[])object));
                else if(type == char.class)
                    System.out.println(Arrays.toString((char[])object));
                else if(type == byte.class)
                    System.out.println(Arrays.toString((byte[])object));
                else if(type == boolean.class)
                    System.out.println(Arrays.toString((boolean[])object));
                else if(type == String.class)
                    System.out.println(Arrays.toString((String[])object));
                else
                    System.out.println(Arrays.toString((Object[])object));

            } else {
                System.out.println(Arrays.toString((Object[])object));
            }

        //try to call the Objects toString() method
        } else {
            Method method = null;
            try {
                method = object.getClass().getMethod("toString");
                if(method != null) {
                    System.out.println(method.invoke(object));
                } else {
                    System.out.println(object);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private static boolean isPrimitive(Class classType) {
        return classType.isPrimitive() ||
                classType == String.class ||
                classType == Integer.class ||
                classType == Long.class ||
                classType == Double.class ||
                classType == Float.class ||
                classType == Short.class ||
                classType == Character.class ||
                classType == Byte.class ||
                classType == Boolean.class;
    }

    private static boolean isListOrMap(Class classType) {
        return List.class.isAssignableFrom(classType) || Map.class.isAssignableFrom(classType);
    }

}