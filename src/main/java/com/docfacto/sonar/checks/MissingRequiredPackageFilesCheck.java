package com.docfacto.sonar.checks;

import java.io.File;
import java.util.List;
import com.docfacto.output.generated.Result;
import com.puppycrawl.tools.checkstyle.api.AbstractFileSetCheck;
import com.docfacto.sonar.ResultsStore;

/**
 * @docfacto.adam ignore
 */
public class MissingRequiredPackageFilesCheck extends AbstractFileSetCheck {
    private String resultKey = "missing-package-required-file";

    @Override
    protected void processFiltered(File file,List<String> aLines) {
        List<Result> resultsList = ResultsStore.RESULTS_STORE.getResultsForFile(file);
        for (Result result:resultsList) {
            if (result.getRule().getKey().equals(getResultKey()))
                this.addViolation(result.getPosition().getLine(),result.getRule().getMessage(),result.getSeverity());
        }
    }

    private String getResultKey() {
        return resultKey;
    }

    private void addViolation(int lineNo,String message,Object detailsOfMessage) {
        this.log(lineNo,message,detailsOfMessage);
    }
}
