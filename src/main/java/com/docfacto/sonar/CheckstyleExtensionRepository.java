package com.docfacto.sonar;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.sonar.api.resources.Java;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleRepository;
import org.sonar.api.rules.XMLRuleParser;

/**
 * @docfacto.adam ignore
 */
public class CheckstyleExtensionRepository extends RuleRepository {

    /**
     * The directory of the Checkstyle xml file
     */
    public static final String CHECKSTYLE_XML_DIRECTORY = "/com/docfacto/sonar/";
    
    /**
     * The name of the Checkstyle xml file
     */
    public static final String CHECKSTYLE_XML_NAME = "checkstyleExtensions.xml";
    
    private XMLRuleParser xmlRuleParser;

    public CheckstyleExtensionRepository(XMLRuleParser xmlRuleParser) {
        super("checkstyle",Java.KEY);
        this.setName("Checkstyle");
        this.xmlRuleParser = xmlRuleParser;
    }

    @Override
    public List<Rule> createRules() {
        InputStream input = getClass().getResourceAsStream(CHECKSTYLE_XML_DIRECTORY+CHECKSTYLE_XML_NAME);
        List<Rule> rules = new ArrayList<Rule>();

        try {
            rules = xmlRuleParser.parse(input);
        }
        finally {
            IOUtils.closeQuietly(input);
        }

        return rules;
    }
}
