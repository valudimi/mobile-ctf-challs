package com.example.hermes;

import java.security.Provider;
import java.util.List;
import java.util.Map;

class TcpServerService extends Provider.Service {

    /**
     * Construct a new service.
     *
     * @param provider   the provider that offers this service
     * @param type       the type of this service
     * @param algorithm  the algorithm name
     * @param className  the name of the class implementing this service
     * @param aliases    List of aliases or null if algorithm has no aliases
     * @param attributes Map of attributes or null if this implementation
     *                   has no attributes
     * @throws NullPointerException if provider, type, algorithm, or
     *                              className is null
     */
    public TcpServerService(Provider provider, String type, String algorithm, String className, List<String> aliases, Map<String, String> attributes) {
        super(provider, type, algorithm, className, aliases, attributes);
    }
}