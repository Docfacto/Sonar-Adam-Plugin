package com.docfacto.sonar;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.docfacto.common.DocfactoException;
import com.docfacto.output.generated.Result;

/**
 * An enum singleton for getting Results produced by Adam.
 * <P>
 * This class is used for retrieving the Results produced by Adam for given
 * files. To prevent Adam being called multiple times for identical files, files
 * which have been requested for before are stored with their results, so the
 * same Results are returned the next time those Results are requested.
 * 
 * @author damonli - created Apr 19, 2013
 * @since 2.2.3
 */
public enum ResultsStore {

    RESULTS_STORE;

    private Map<String,List<Result>> fileToResultsMap;

    private ResultsStore() {
        fileToResultsMap = new HashMap<String,List<Result>>();
    }

    /**
     * Retrieve Results for a file
     * <p>
     * If the file has already been requested before, then return the Result
     * previously stored for that file. Otherwise retrieve the results which
     * Adam outputs for that file.
     * </p>
     * 
     * @param file the file Results are to be retrieved for
     * @return a list of Results from Adam for the given file
     * @since 2.2.3
     */
    public List<Result> getResultsForFile(File file) {
        if (fileToResultsMap.keySet().contains(file.getPath()))
            return fileToResultsMap.get(file.getPath());

        List<Result> resultsList = getResultsListForFileFromAdam(file);

        fileToResultsMap.put(file.getPath(),resultsList);
        return resultsList;
    }

    /**
     * Retrieve Results from Adam for a file
     * <p>
     * Retrieves Results which are produced by Adam for the given file.
     * </p>
     * 
     * @param file the file which Results are to be retrieved for
     * @return a list of Results from Adam for the given file
     * @since 2.2.3
     */
    private List<Result> getResultsListForFileFromAdam(File file) {
        try {
            return AdamOutputRetriever.ADAM_OUTPUT_RETRIEVER.getResultsForFiles(Arrays.asList(file));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (DocfactoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
