package info.qumeric.hw;

import info.qumeric.hw.filters.Filter;
import info.qumeric.hw.functions.Function;
import info.qumeric.hw.receivers.BroadcastReceiver;
import info.qumeric.hw.senders.BroadcastSender;

import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Coordinator {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: ./Coordinator senders_path receivers_path filters_path functions_path");
            return;
        }
        Stream<Path> sendersPaths = null,
                receiversPaths = null,
                filtersPaths = null,
                functionsPaths = null;
        try {
            sendersPaths = Files.list(Paths.get(args[0]));
            receiversPaths = Files.list(Paths.get(args[1]));
            filtersPaths = Files.list(Paths.get(args[2]));
            functionsPaths = Files.list(Paths.get(args[3]));
        } catch (IOException e) {
            System.err.println("Cannot open at least one of given folders");
            e.printStackTrace();
        }
        List<? super BroadcastSender> senders = sendersPaths.map(Coordinator::loadClassFromPath)
                .filter(cls -> !cls.isInterface())
                .collect(Collectors.toList());
        List<? super BroadcastReceiver> receivers = receiversPaths.map(Coordinator::loadClassFromPath)
                .filter(cls -> !cls.isInterface())
                .map(Coordinator::createInstance)
                .collect(Collectors.toList());
        List<? super Filter> filters = filtersPaths.map(Coordinator::loadClassFromPath)
                .filter(cls -> !cls.isInterface())
                .collect(Collectors.toList());
        List<? super Function> functions = functionsPaths.map(Coordinator::loadClassFromPath)
                .filter(cls -> !cls.isInterface())
                .collect(Collectors.toList());
    }

    static private Class<?> loadClassFromPath(Path p) {
        try {
            return Class.forName(p.toString());
        } catch (ClassNotFoundException e) {
            System.err.println("Cannot load some files in at least one of given folders");
            e.printStackTrace();
            return null;
        }
    }

    static private <T> T createInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("Cannot create an instance of some class");
            e.printStackTrace();
            return null;
        }
    }
}
