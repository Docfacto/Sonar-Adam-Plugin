package com.docfacto.sonar;

import java.util.Arrays;
import java.util.List;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * @docfacto.adam ignore
 */

public final class DocfactoMetrics implements Metrics {

    public static final Metric PACKAGES_PROCESSED = new Metric.Builder("packages-processed","Packages Processed",
        Metric.ValueType.INT)
        .setDescription("Number of packages processed")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric PACKAGES_IGNORED = new Metric.Builder("packages-ignored","Packages Ignored",
        Metric.ValueType.INT)
        .setDescription("Number of packages ignored")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric MISSING_PACKAGE_DOC = new Metric.Builder("missing-package-doc",
        "Missing package documentation",Metric.ValueType.INT)
        .setDescription("Number of missing package documentation")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric MISSING_PACKAGE_REQUIRED_FILE = new Metric.Builder("missing-package-required-file",
        "Missing required package files",Metric.ValueType.INT)
        .setDescription("Number of missing required package files / directories")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric CLASSES_PROCESSED = new Metric.Builder("classes-processed","Classes processed",
        Metric.ValueType.INT)
        .setDescription("Number of classes processed")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric CLASSES_IGNORED = new Metric.Builder("classes-ignored","Classes ignored",
        Metric.ValueType.INT)
        .setDescription("Number of classes ignored")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric CLASSES_MISSING_JAVADOC = new Metric.Builder("classes-missing-javadoc",
        "Classes without Javadoc",Metric.ValueType.INT)
        .setDescription("Number of classes with no comments")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric INTERFACES_PROCESSED = new Metric.Builder("interfaces-processed","Interfaces processed",
        Metric.ValueType.INT)
        .setDescription("Number of interfaces processed")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric INTERFACES_IGNORED = new Metric.Builder("interfaces-ignored","Interfaces ignored",
        Metric.ValueType.INT)
        .setDescription("Number of interfaces ignored")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric INTERFACES_MISSING_JAVADOC = new Metric.Builder("interfaces-missing-javadoc",
        "Interfaces without Javadoc",Metric.ValueType.INT)
        .setDescription("Number of interfaces with no javadoc comments")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric ENUMS_PROCESSED = new Metric.Builder("enums-processed","Enums processed",
        Metric.ValueType.INT)
        .setDescription("Number of enums processed")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric ENUMS_IGNORED = new Metric.Builder("enums-ignored","Enums ignored",Metric.ValueType.INT)
        .setDescription("Number of enums ignored")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric ENUMS_MISSING_JAVADOC = new Metric.Builder("enums-missing-javadoc",
        "Enums without Javadoc",Metric.ValueType.INT)
        .setDescription("Number of enum classes with no javadoc comments")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric ENUMS_CONSTANT_MISSING_JAVADOC = new Metric.Builder("enums-constant-missing-javadoc",
        "Enums constants without Javadoc",Metric.ValueType.INT)
        .setDescription("Number of enums constants with no javadoc comments")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric METHODS_PROCESSED = new Metric.Builder("methods-processed","Methods processed",
        Metric.ValueType.INT)
        .setDescription("Number of methods processed")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric METHODS_IGNORED = new Metric.Builder("methods-ignored","Methods ignored",
        Metric.ValueType.INT)
        .setDescription("Number of methods ignored")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric METHODS_MISSING_JAVADOC = new Metric.Builder("methods-missing-javadoc",
        "Methods missing javadoc",Metric.ValueType.INT)
        .setDescription("Number of methods with no Javadoc")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric METHODS_MISSING_COMMENT = new Metric.Builder("methods-missing-comment",
        "Missing comment (empty javadoc)",Metric.ValueType.INT)
        .setDescription("Number of methods with no comments")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric INVALID_THROWS = new Metric.Builder("invalid-throws","Invalid throw tags",
        Metric.ValueType.INT)
        .setDescription("Number of invalid throw tags")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric INVALID_PARAMS = new Metric.Builder("invalid-params","Invalid param tags",
        Metric.ValueType.INT)
        .setDescription("Number of invalid param tags")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric INVALID_RETURN = new Metric.Builder("invalid-return","Invalid return tags",
        Metric.ValueType.INT)
        .setDescription("Number of invalid return tags")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric CONSTRUCTORS_PROCESSED = new Metric.Builder("constructors-processed",
        "Constructors processed",Metric.ValueType.INT)
        .setDescription("Number of constructors processed")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric CONSTRUCTORS_IGNORED = new Metric.Builder("constructors-ignored","Constructors ignored",
        Metric.ValueType.INT)
        .setDescription("Number of constructors ignored")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric CONSTRUCTOR_MISSING_JAVADOC = new Metric.Builder("constructor-missing-javadoc",
        "Missing constructor javadoc",Metric.ValueType.INT)
        .setDescription("Number of constructors with no javadoc")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric FIELD_PROCESSED = new Metric.Builder("field-processed","Fields processed",
        Metric.ValueType.INT)
        .setDescription("Number of fields processed")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric FIELD_IGNORED =
        new Metric.Builder("field-ignored","Fields ignored",Metric.ValueType.INT)
            .setDescription("Number of fields ignored")
            .setQualitative(false)
            .setDomain(CoreMetrics.DOMAIN_GENERAL)
            .create();

    public static final Metric FIELD_MISSING_JAVADOC = new Metric.Builder("field-missing-javadoc",
        "Fields without Javadoc",Metric.ValueType.INT)
        .setDescription("Number of fields with no javadoc")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric MISSING_REQUIRED_TAG = new Metric.Builder("missing-required-tag",
        "Missing required tags",Metric.ValueType.INT)
        .setDescription("Number of missing required javadoc tags")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric MISSING_TAG_DESCRIPTIONS = new Metric.Builder("missing-tag-descriptions",
        "Missing tag description",Metric.ValueType.INT)
        .setDescription("Number of missing required tags without descriptions")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric MEANINGLESS_COMMENT = new Metric.Builder("meaningless-comment","Meaningless comment",
        Metric.ValueType.INT)
        .setDescription("Number of meaningless comments")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric MISSING_THROWS_DESCRIPTION = new Metric.Builder("missing-throws-description",
        "Missing throws description",Metric.ValueType.INT)
        .setDescription("Number of missing throw tag descriptions")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric MISSING_PARAM_DESCRIPTION = new Metric.Builder("missing-param-description",
        "Missing param description",Metric.ValueType.INT)
        .setDescription("Number of missing param tag descriptions")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric MISSING_RETURN_DESCRIPTION = new Metric.Builder("missing-return-description",
        "Missing return description",Metric.ValueType.INT)
        .setDescription("Number of missing return tag descriptions")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric WARNING_IF_CONTAINS = new Metric.Builder("warning-if-contains",
        "Warning if comment contains",Metric.ValueType.INT)
        .setDescription("Number of warning if contains")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric MISSING_DEPRECATED_DESCRIPTION = new Metric.Builder("missing-deprecated-description",
        "Missing deprecated description",Metric.ValueType.INT)
        .setDescription("Number of deprecated tags without a description")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric MISSING_DEPRECATED_TAG = new Metric.Builder("missing-deprecated-tag",
        "Missing deprecated tag",Metric.ValueType.INT)
        .setDescription("Number of deprecated annotations without a deprecated tag")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric ADAMLET_WARNING = new Metric.Builder("adamlet-warning","Adamlet warnings",
        Metric.ValueType.INT)
        .setDescription("Number of Adamlet warnings")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric ERRORS = new Metric.Builder("errors","null",Metric.ValueType.INT)
        .setDescription("Number of errors")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric WARNINGS = new Metric.Builder("warnings","null",Metric.ValueType.INT)
        .setDescription("Number of warnings")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public static final Metric INFO = new Metric.Builder("info","null",Metric.ValueType.INT)
        .setDescription("Number of infos")
        .setQualitative(false)
        .setDomain(CoreMetrics.DOMAIN_GENERAL)
        .create();

    public List<Metric> getMetrics() {
        return Arrays.asList(PACKAGES_PROCESSED,PACKAGES_IGNORED,MISSING_PACKAGE_DOC,MISSING_PACKAGE_REQUIRED_FILE,
            CLASSES_PROCESSED,CLASSES_IGNORED,CLASSES_MISSING_JAVADOC,INTERFACES_PROCESSED,INTERFACES_IGNORED,
            INTERFACES_MISSING_JAVADOC,ENUMS_PROCESSED,ENUMS_IGNORED,ENUMS_MISSING_JAVADOC,
            ENUMS_CONSTANT_MISSING_JAVADOC,METHODS_PROCESSED,METHODS_IGNORED,METHODS_MISSING_JAVADOC,
            METHODS_MISSING_COMMENT,INVALID_THROWS,INVALID_PARAMS,INVALID_RETURN,CONSTRUCTORS_PROCESSED,
            CONSTRUCTORS_IGNORED,CONSTRUCTOR_MISSING_JAVADOC,FIELD_PROCESSED,FIELD_IGNORED,FIELD_MISSING_JAVADOC,
            MISSING_REQUIRED_TAG,MISSING_TAG_DESCRIPTIONS,MEANINGLESS_COMMENT,MISSING_THROWS_DESCRIPTION,
            MISSING_PARAM_DESCRIPTION,MISSING_RETURN_DESCRIPTION,WARNING_IF_CONTAINS,MISSING_DEPRECATED_DESCRIPTION,
            MISSING_DEPRECATED_TAG,ADAMLET_WARNING,ERRORS,WARNINGS,INFO);
    }
}
