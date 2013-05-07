package com.docfacto.sonar;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import org.sonar.api.measures.Metric;

/**
 * An enum singleton for mapping Metric objects to its key and then providing
 * the Metric when the matching key is given.
 * <P>
 * Metric objects are stored in a map with a string key, and the Metric object
 * can then be retrieved with the key.
 * 
 * @author damonli - created Apr 22, 2013
 * @since 2.2.3
 */
public enum MetricsProvider {

    METRICS_PROVIDER;

    @Getter
    private Map<String,Metric> keyToMetricMap;

    /**
     * A private constructor as this is a singleton
     */
    private MetricsProvider() {
        keyToMetricMap = new HashMap<String,Metric>();
    }

    /**
     * Get the Metric which matches a given key if possible
     * 
     * @param key the key of the Metric to be retrieved
     * @return the Metric which has been stored with a key that matches the
     * given key
     * @since 2.2.3
     */
    public Metric getMetricForKey(String key) {
        return keyToMetricMap.get(key);
    }

    /**
     * Add a Metric and key pair to the provider map
     * <p>
     * Sets the given key to be mapped to the given Metric. If the key already
     * exists in the map, the previous Metric it was mapped to will be dropped,
     * and the key will be mapped to the new given Metric instead.
     * </p>
     * 
     * @param key the key the given Metric should be mapped to
     * @param metric the Metric to be stored into provider and mapped to the
     * given key
     * @since 2.2.3
     */
    public void setNewKeyMetricPair(String key,Metric metric) {
        keyToMetricMap.put(key,metric);
    }
}
