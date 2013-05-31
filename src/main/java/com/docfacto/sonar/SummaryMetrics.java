package com.docfacto.sonar;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
* @docfacto.adam ignore
*/
public class SummaryMetrics implements Metrics {

    public static final Metric CLASSES_WITH_JAVADOC_PERCENTAGE = new Metric.Builder("classes-with-javadoc-percentage",
        "Classes With Javadoc Percentage",
        Metric.ValueType.INT)
        .setDescription("A metric for the number of classes with Javadoc")
        .setDirection(Metric.DIRECTION_BETTER)
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric INTERFACES_WITH_JAVADOC_PERCENTAGE = new Metric.Builder(
        "interfaces-with-javadoc-percentage","Interfaces With Javadoc Percentage",
        Metric.ValueType.INT)
        .setDescription("A metric for the number of interfaces with Javadoc")
        .setDirection(Metric.DIRECTION_BETTER)
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric ENUMS_WITH_JAVADOC_PERCENTAGE = new Metric.Builder("enums-with-javadoc-percentage",
        "Enums With Javadoc Percentage",
        Metric.ValueType.INT)
        .setDescription("A metric for the number of enums with Javadoc")
        .setDirection(Metric.DIRECTION_BETTER)
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric METHODS_WITH_JAVADOC_PERCENTAGE = new Metric.Builder("methods-with-javadoc-percentage",
        "Methods With Javadoc Percentage",
        Metric.ValueType.INT)
        .setDescription("A metric for the number of methods with Javadoc")
        .setDirection(Metric.DIRECTION_BETTER)
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric CONSTRUCTORS_WITH_JAVADOC_PERCENTAGE = new Metric.Builder(
        "constructors-with-javadoc-percentage","Constructors With Javadoc Percentage",
        Metric.ValueType.INT)
        .setDescription("A metric for the number of constructors with Javadoc")
        .setDirection(Metric.DIRECTION_BETTER)
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric FIELDS_WITH_JAVADOC_PERCENTAGE = new Metric.Builder("fields-with-javadoc-percentage",
        "Fields With Javadoc Percentage",
        Metric.ValueType.INT)
        .setDescription("A metric for the number of fields with Javadoc")
        .setDirection(Metric.DIRECTION_BETTER)
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric BARCHART = new Metric.Builder("summary-barchart","Sumamry Bar Chart",
        Metric.ValueType.DISTRIB)
        .setDescription("A metric for storing the distribution to be used by the summary bar chart")
        .setDirection(Metric.DIRECTION_NONE)
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric ADAM_VIOLATIONS = new Metric.Builder("number-of-adam-violations",
        "Number of Adam Violations",
        Metric.ValueType.INT)
        .setDescription("A metric for the number of Adam Rules violated")
        .setDirection(Metric.DIRECTION_WORST)
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public List<Metric> getMetrics() {
        return Arrays.asList(CLASSES_WITH_JAVADOC_PERCENTAGE,
            INTERFACES_WITH_JAVADOC_PERCENTAGE,
            ENUMS_WITH_JAVADOC_PERCENTAGE,
            METHODS_WITH_JAVADOC_PERCENTAGE,
            CONSTRUCTORS_WITH_JAVADOC_PERCENTAGE,
            FIELDS_WITH_JAVADOC_PERCENTAGE,BARCHART,ADAM_VIOLATIONS);
    }
}
