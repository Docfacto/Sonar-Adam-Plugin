package com.docfacto.sonar;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Decorator;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.MeasureUtils;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;
import org.sonar.api.resources.ResourceUtils;
import org.sonar.api.rules.Violation;

/**
 * The Decorator whichw ill retrieve the total number of Docfacto Adam Violations
 *
 * @author damonli - created May 17, 2013
 * @since 2.3.0
 */
public class AdamViolationsDecorator implements Decorator {

    private static final Logger logger = LoggerFactory.getLogger(AdamViolationsDecorator.class);

    /**
     * @see org.sonar.api.batch.CheckProject#shouldExecuteOnProject(org.sonar.api.resources.Project)
     */
    public boolean shouldExecuteOnProject(Project project) {
        return ResourceUtils.isRootProject(project);
    }

    /**
     * @see org.sonar.api.batch.Decorator#decorate(org.sonar.api.resources.Resource,
     * org.sonar.api.batch.DecoratorContext)
     */
    public void decorate(Resource resource,DecoratorContext decoratorContext) {
        double count = getNumberOfAdamViolationsFromChildrenMeasures(decoratorContext);
        count += getNumberOfAdamViolationsForDecoratorContext(decoratorContext);
        
        decoratorContext.saveMeasure(SummaryMetrics.ADAM_VIOLATIONS,count);
    }
    
    private double getNumberOfAdamViolationsFromChildrenMeasures(DecoratorContext decoratorContext) {
        Collection<Measure> childrenViolations = decoratorContext.getChildrenMeasures(SummaryMetrics.ADAM_VIOLATIONS);

        Double count = MeasureUtils.sum(true,childrenViolations);

        if (count==null)
            count = 0.0;
        
        return count;
    }

    private double getNumberOfAdamViolationsForDecoratorContext(DecoratorContext decoratorContext) {
        double count = 0.0;
        
        List<Violation> violations = decoratorContext.getViolations();
        for (Violation violation:violations) {
            String key = violation.getRule().getKey();
            if (key.startsWith("com.docfacto.sonar.checks."))
                count++;
        }
        
        return count;
    }
}
