package com.docfacto.sonar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.Metric;
import org.sonar.api.resources.Project;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import com.docfacto.common.DocfactoException;
import com.docfacto.output.generated.Statistic;

/**
 * The Sensor which will retrieve Statistics from Adam and record them when
 * analysing a project.
 * 
 * @author damonli - created Apr 22, 2013
 * @since 2.2.3
 */
public class DocfactoSensor implements Sensor {

    /**
     * The extension of files which should be analysed
     */
    private static final String ACCEPTED_FILE_EXTENSION = ".java";

    /**
     * The file system of the project which will be analysed
     */
    private final ModuleFileSystem projectFileSystem;

    /**
     * The constructor for instantiating the Sensor.
     * 
     * @param moduleFileSystem the file system of the project which will be
     * analyzed
     * @param settings the settings of the
     * @since 2.2.3
     */
    public DocfactoSensor(ModuleFileSystem moduleFileSystem,Settings settings) {
        this.projectFileSystem = moduleFileSystem;

        AdamConfig.setConfigPath(settings.getString("sonar.docfacto.config"));
        AdamConfig.setDocletPath(settings.getString("sonar.docfacto.jar"));
        AdamConfig.setSourcePath(settings.getString("sonar.sources"));
    }

    /**
     * @see org.sonar.api.batch.CheckProject#shouldExecuteOnProject(org.sonar.api.resources.Project)
     */
    public boolean shouldExecuteOnProject(Project project) {
        return true;
    }

    /**
     * Retrieve statistics for a given project and then save them to the given
     * SensorContext
     * 
     * @see org.sonar.api.batch.Sensor#analyse(org.sonar.api.resources.Project,
     * org.sonar.api.batch.SensorContext)
     */
    public void analyse(Project project,SensorContext sensorContext) {
        setupMetricsProvider();

        try {
            List<Statistic> statisticsList = getStatisticsForProject();

            for (Statistic statistic:statisticsList) {
                String key = statistic.getRule().getKey();
                String valueString = statistic.getValue()+"";

                Metric metric = MetricsProvider.METRICS_PROVIDER.getMetricForKey(key);
                if (metric!=null)
                    saveMeasureToSensorContext(sensorContext,metric,Double.parseDouble(valueString));
            }
        }
        catch (DocfactoException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set up the Metrics Provider to have all Metrics
     * <p>
     * This method populates the MetricsProvider with all the metrics so that it
     * can be used elsewhere.
     * </p>
     * 
     * @since 2.2.3
     */
    private void setupMetricsProvider() {
        DocfactoMetrics docfactoMetrics = new DocfactoMetrics();

        for (Metric metric:docfactoMetrics.getMetrics())
            MetricsProvider.METRICS_PROVIDER.setNewKeyMetricPair(metric.getKey(),metric);
    }

    /**
     * Save a measure for a given Metric and double to a SensorContext
     * <p>
     * This method will save a given Metric and double value to a given
     * SensorContext.
     * </p>
     * 
     * @param sensorContext the sensor context to save the measure to
     * @param metric the metric of the measure
     * @param doubleValue the value of the measure
     * @since 2.2.3
     */
    private void saveMeasureToSensorContext(SensorContext sensorContext,Metric metric,Double doubleValue) {
        Measure measure = new Measure(metric,doubleValue);
        sensorContext.saveMeasure(measure);
    }

    /**
     * Get Statistics from Adam for a given Project
     * <p>
     * This method will invoke Adam to get Statistics for a given Project
     * </p>
     * 
     * @return the list of Statistics Adam returned for that Project
     * @throws IOException There was an error when dealing with files in
     * AdamOutputRetriever
     * @throws DocfactoException There was an error with Adam in
     * AdamOutputRetriever
     * @since 2.2.3
     */
    private List<Statistic> getStatisticsForProject() throws IOException, DocfactoException {
        List<File> files = getFilesFromFileSystem(projectFileSystem);
        return AdamOutputRetriever.ADAM_OUTPUT_RETRIEVER.getStatisticsForFiles(files);
    }

    /**
     * Gets the list of files from a ModuleFileSystem
     * <p>
     * This method traverses a given ModuleFileSystem and returns a list of all
     * files found.
     * </p>
     * 
     * @param fileSystem the file system which files are to be retrieved from
     * @return the list of files found in the file system given
     * @since 2.2.3
     */
    private List<File> getFilesFromFileSystem(ModuleFileSystem fileSystem) {
        List<File> fileList;

        fileList = getFilesFromDirs(fileSystem.sourceDirs());
        fileList.addAll(getFilesFromDirs(fileSystem.testDirs()));
        return fileList;
    }

    /**
     * Gets the list of files from a list of directories
     * <p>
     * This method will traverse through each directory given in a list and
     * returns a list of all files found in each directory.
     * </p>
     * 
     * @param dirs the list of directories which files are to be retrieved from
     * @return the list of files found in all the given directories
     * @since 2.2.3
     */
    private List<File> getFilesFromDirs(List<File> dirs) {
        List<File> files = new ArrayList<File>();
        for (File dir:dirs) {
            if (dir.isDirectory())
                files.addAll(getFilesFromDirs(Arrays.asList(dir.listFiles())));
            else {
                if (dir.getName().endsWith(ACCEPTED_FILE_EXTENSION))
                    files.add(dir);
            }
        }
        return files;
    }
}
