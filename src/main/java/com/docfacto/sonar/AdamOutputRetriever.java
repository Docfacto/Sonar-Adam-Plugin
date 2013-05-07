package com.docfacto.sonar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.docfacto.adam.AdamOutputProcessor;
import com.docfacto.common.DocfactoException;
import com.docfacto.output.generated.Result;
import com.docfacto.output.generated.Statistic;

/**
 * An enum singleton for getting statistics and results from Adam.
 * <P>
 * This class uses AdamOutputProcessor to retrieve statistics or results for a
 * list of files.
 * 
 * @author damonli - created Apr 19, 2013
 * @since 2.2.3
 */
public enum AdamOutputRetriever {

    ADAM_OUTPUT_RETRIEVER;

    /**
     * Returns a list of Statistics produced by Adam for a given list of files.
     * <p>
     * This method will configure Adam to process the list of given files. After
     * processing the files, the Statistics that Adam outputs will be returned
     * as a list.
     * </p>
     * 
     * @param fileList the list of files that Statistics are to be retrieved for
     * @return the list of Statistics produced by Adam
     * @throws DocfactoException if the AdamOutputProcessor could not be created
     * @throws IOException if a temporary file could not be created
     * @since 2.2.3
     */
    public List<Statistic> getStatisticsForFiles(List<File> fileList) throws IOException, DocfactoException {
        AdamOutputProcessor processor = getAdamOutputProcessorConfiguredForFiles(fileList);
        return processor.getStatistics();
    }

    /**
     * Returns a list of Results produced by Adam for a given list of files
     * <p>
     * This method will configure Adam to process the list of given files. After
     * processing the files, the Results that Adam then outputs will be returned
     * as a list.
     * </p>
     * 
     * @param fileList the list of files that Results are to be retrieved for
     * @return the list of Results produced by Adam
     * @throws DocfactoException if the AdamOutputProcessor could not be created
     * @throws IOException if a temporary file could not be created
     * @since 2.2.3
     */
    public List<Result> getResultsForFiles(List<File> fileList) throws IOException, DocfactoException {
        AdamOutputProcessor processor = getAdamOutputProcessorConfiguredForFiles(fileList);
        return processor.getResults();
    }

    /**
     * Returns an AdamOutputProcessor which has been configured with the output
     * from Adam for a given list of files
     * <p>
     * This method executes javadoc with the arguments required for Adam to
     * execute including the list of files. A temporary file is given for Adam
     * to write its results onto. An AdamOutputProcessor is created and
     * configured from this temporary file.
     * </p>
     * 
     * @param fileList
     * @return a new AdamOutputProcessor with the output from Adam for the given
     * list of files
     * @throws IOException if the temporary file could not be created
     * @throws DocfactoException if the AdamOutputProcessor could not be created
     * @since 2.2.3
     */
    private AdamOutputProcessor getAdamOutputProcessorConfiguredForFiles(List<File> fileList) throws IOException,
    DocfactoException {

        File tempFile = File.createTempFile("tempFile",".xml");
        String[] argsArray = generateAdamArgumentArrayForFiles(tempFile,fileList);
        com.sun.tools.javadoc.Main.execute(argsArray);

        return new AdamOutputProcessor(tempFile.toString());
    }

    /**
     * Generates the string arguments array needed to run Adam via javadoc for a
     * list of files
     * <p>
     * This method generates a string array for the arguments needed to run
     * Adam.
     * </p>
     * <p>
     * The first set of arguments are for configuring javadoc to invoke the Adam
     * doclet with the doclet, config file.
     * <p>
     * <p>
     * The next argument is the temporary file which Adam will write its output
     * onto.
     * </p>
     * <p>
     * The next set of arguments is the source path of the project and the list
     * of files to be analyzed by Adam.
     * </p>
     * 
     * @param tempFile the temporary file Adam is expected to write its output
     * onto
     * @param fileList the list of files is to be processed by Adam
     * @return a string array of the arguments to configure Adam so that it will
     * process given list of files and output onto the given temporary file
     * @since 2.2.3
     */
    private String[] generateAdamArgumentArrayForFiles(File tempFile,List<File> fileList) {
        List<String> argList = new ArrayList<String>();
        List<String> fileStringList = new ArrayList<String>();

        argList.add("-doclet");
        argList.add("com.docfacto.doclets.adam.AdamDoclet");
        argList.add("-docletpath");
        argList.add(AdamConfig.getDocletPath());
        argList.add("-config");
        argList.add(AdamConfig.getConfigPath());
        argList.add("-output");
        argList.add(tempFile.toString());
        argList.add("-sourcepath");
        argList.add(AdamConfig.getSourcePath());

        for (File file:fileList)
            fileStringList.add(file.toString());

        argList.addAll(fileStringList);

        String[] argsArray = argList.toArray(new String[argList.size()]);

        return argsArray;
    }

    /**
     * Returns a list of Statistics (which do not have values) produced by an
     * AdamOutputProcessor which does not have output from Adam
     * <p>
     * Creates an AdamOutputProcessor without a configuration file which has
     * output from Adam. The list of statistics which is returned from this
     * AdamOutputProcessor will therefore not have any concrete values, but each
     * Statistic will still have the Rule for that statistic and it's attributes
     * such as name and description.
     * </p>
     * 
     * @return a list of Statistics (with no values) from AdamOutputProcessor
     * which does not have output from Adam
     * @throws DocfactoException if the AdamOutputProcessor could not be created
     * @since 2.2.3
     */
    public List<Statistic> getEmptyStatistics() throws DocfactoException {
        AdamOutputProcessor adamOutputProcessor = new AdamOutputProcessor();
        return adamOutputProcessor.getStatistics();
    }
}
