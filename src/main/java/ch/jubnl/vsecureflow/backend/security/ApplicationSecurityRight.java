package ch.jubnl.vsecureflow.backend.security;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

@Component
public class ApplicationSecurityRight {
    private final Set<String> rights = new TreeSet<>();

    public void registerRight(final String right) {
        rights.add(right);
    }
}
