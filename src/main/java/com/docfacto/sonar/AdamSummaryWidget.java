package com.docfacto.sonar;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.Description;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.UserRole;
import org.sonar.api.web.WidgetCategory;

/**
 * @docfacto.adam ignore
 */
@UserRole(UserRole.USER)
@Description("Show a summary of the reports by Adam on documentation and the number of Adam Rules enabled which have been violated")
@WidgetCategory("Docfacto Adam")
public class AdamSummaryWidget extends AbstractRubyTemplate implements RubyRailsWidget {

    public static final String WIDGET_ID = "com.docfacto.sonar.AdamSummaryWidget";
    
    /**
     * The directory the Ruby on Rails widget file should be stored in
     */
    public static final String WIDGET_TEMPLATE_DIRECTORY = "/org/sonar/ror/docfactoAdamWidget/app/views/";
    
    /**
     * The file name of the Ruby on Rails widget file
     */
    public static final String WIDGET_TEMPLATE_NAME = "adamSummaryWidgetTemplate.html.erb";

    public String getId() {
        return WIDGET_ID;
    }

    public String getTitle() {
        return "Docfacto Adam Summary";
    }

    @Override
    protected String getTemplatePath() {
        return WIDGET_TEMPLATE_DIRECTORY+WIDGET_TEMPLATE_NAME;
    }
}
