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
 * The Decorator which will retrieve the total number of Docfacto Adam Violations
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
        double numberOfAdamViolations = getTotalNumberOfAdamViolationsSoFar(decoratorContext);
        decoratorContext.saveMeasure(SummaryMetrics.ADAM_VIOLATIONS,numberOfAdamViolations);
    }
    
    /**
     * Get the total number of Adam violations found so far
     * <p>
     * Get the total number of Adam violations up to this point in the analysis, including the violations for the
     * current decorator context and the children measures.
     * </p>
     * @param decoratorContext the decorator context to get the Adam violations from
     * @return the total number of Adam violations so far.
     * @since 2.3.0
     */
    private double getTotalNumberOfAdamViolationsSoFar(DecoratorContext decoratorContext) {
        double count = getNumberOfAdamViolationsFromChildrenMeasures(decoratorContext);
        count += getNumberOfAdamViolationsForDecoratorContext(decoratorContext);
        return count;
    }
    
    /**
     * Get the number of Adam violations from children measures
     * <p>
     * Get the number of Adam violations which have already been found in the children measures from
     * a given decorator context.
     * </p>
     * @param decoratorContext the decorator context to get children measure violations for 
     * @return the number of Adam violations from the children measures.
     * @since 2.3.0
     */
    private double getNumberOfAdamViolationsFromChildrenMeasures(DecoratorContext decoratorContext) {
        Collection<Measure> childrenViolations = decoratorContext.getChildrenMeasures(SummaryMetrics.ADAM_VIOLATIONS);

        Double count = MeasureUtils.sum(true,childrenViolations);

        if (count==null)
            count = 0.0;
        
        return count;
    }

    /**
     * Get the number of Adam Violations for a decorator context
     * <p>
     * Search through the violations for a given decorator context. All Docfacto Adam violations have a key which
     * begins with 'com.docfacto.sonar.checks', so this method will return the number of violations who's key begins
     * with this string.
     * </p>
     * @param decoratorContext the decorator context to get the number of Adam violations from
     * @return the number of Adam violations from the given decorator context
     * @since 2.3.0
     */
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
