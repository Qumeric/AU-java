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
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
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


        List<BroadcastSender> senders = loadAllFromPathStream(sendersPaths);
        List<BroadcastReceiver> recievers = loadAllFromPathStream(receiversPaths);
        List<Filter> filters = loadAllFromPathStream(filtersPaths);
        List<Function> functions = loadAllFromPathStream(functionsPaths);

        Coordinator coordinator = new Coordinator();
        for (BroadcastSender sender: senders) {
            sender.setCoordinator(coordinator);
            new Thread(sender::run);
        }
    }

    static private List loadAllFromPathStream(Stream<Path> stream) {
        return stream.map(Coordinator::loadClassFromPath)
                .filter(cls -> !cls.isInterface())
                .map(Coordinator::createInstance)
                .collect(
                        ArrayList::new,
                        Collection::add,
                        Collection::addAll
                );
    }

    @SuppressWarnings("unchecked")
    static private <T> Class<T> loadClassFromPath(Path p) {
        try {
            String nameDotJava = "info.qumeric.hw." + p.toString().replace("/", ".");
            return (Class<T>) Class.forName(nameDotJava.substring(0, nameDotJava.length()-5));
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

    public void newMessage(Message message) {
    }
}
