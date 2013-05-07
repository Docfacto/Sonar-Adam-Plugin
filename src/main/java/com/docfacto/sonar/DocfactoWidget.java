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
@Description("Show reports on documentation from Adam")
@WidgetCategory("Docfacto")
public class DocfactoWidget extends AbstractRubyTemplate implements RubyRailsWidget {

    public static final String WIDGET_ID = "com.docfacto.sonar.DocfactoWidget";
    
    /**
     * The directory the Ruby on Rails widget file should be stored in
     */
    public static final String WIDGET_TEMPLATE_DIRECTORY = "/org/sonar/ror/docfactoAdamWidget/app/views/";
    
    /**
     * The file name of the Ruby on Rails widget file
     */
    public static final String WIDGET_TEMPLATE_NAME = "docfactoWidgetTemplate.html.erb";

    public String getId() {
        return WIDGET_ID;
    }

    public String getTitle() {
        return "Docfacto Adam";
    }

    @Override
    protected String getTemplatePath() {
        return WIDGET_TEMPLATE_DIRECTORY+WIDGET_TEMPLATE_NAME;
    }
}
