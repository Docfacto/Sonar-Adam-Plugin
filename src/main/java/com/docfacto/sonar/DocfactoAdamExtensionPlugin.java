package com.docfacto.sonar;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;

/**
 * @docfacto.adam ignore
 */
public class DocfactoAdamExtensionPlugin extends SonarPlugin {

    public List getExtensions() {
        List extensions =
            Arrays.asList(AdamMetrics.class,
                            SummaryMetrics.class,
                            AdamSensor.class,
                            AdamViolationsDecorator.class,
                            CheckstyleExtensionRepository.class,
                            AdamStatisticsWidget.class,
                            AdamSummaryWidget.class,
                            BarChart.class);
        return extensions;
    }
}
