package com.docfacto.sonar;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;

/**
 * @docfacto.adam ignore
 */
public class DocfactoExtensionPlugin extends SonarPlugin {

    public List getExtensions() {
        List extensions =
            Arrays.asList(DocfactoMetrics.class,
                            DocfactoSensor.class,
                            CheckstyleExtensionRepository.class,
                            DocfactoWidget.class);
        return extensions;
    }
}
