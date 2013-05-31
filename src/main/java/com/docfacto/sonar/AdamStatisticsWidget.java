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
@Description("Show a list of all the statistics reported by Adam on the documentation")
@WidgetCategory("Docfacto Adam")
public class AdamStatisticsWidget extends AbstractRubyTemplate implements RubyRailsWidget {

    public static final String WIDGET_ID = "com.docfacto.sonar.AdamStatisticsWidget";
    
    /**
     * The directory the Ruby on Rails widget file should be stored in
     */
    public static final String WIDGET_TEMPLATE_DIRECTORY = "/org/sonar/ror/docfactoAdamWidget/app/views/";
    
    /**
     * The file name of the Ruby on Rails widget file
     */
    public static final String WIDGET_TEMPLATE_NAME = "adamStatisticsWidgetTemplate.html.erb";

    public String getId() {
        return WIDGET_ID;
    }

    public String getTitle() {
        return "Docfacto Adam Statistics";
    }

    @Override
    protected String getTemplatePath() {
        return WIDGET_TEMPLATE_DIRECTORY+WIDGET_TEMPLATE_NAME;
    }
}
