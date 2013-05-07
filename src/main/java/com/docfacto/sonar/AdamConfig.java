package com.docfacto.sonar;

import lombok.Getter;
import lombok.Setter;

/**
 * A singleton class to store the config details for Adam which can be used by
 * other classes
 * <P>
 * This class stores the config details necessary for Adam to function, which
 * are the path of the doclet (the Docfacto jar), the path of the config (the
 * Docfacto config xml file) and the source path of the source files to be
 * analyzed
 * 
 * @author damonli - created Apr 23, 2013
 * @since 2.2.3
 */
public class AdamConfig {

    /**
     * The path to the doclet (the Docfacto jar)
     */
    @Getter @Setter private static String docletPath;
    
    /**
     * The path to the config (the Docfacto config xml file)
     */
    @Getter @Setter private static String configPath;
    
    /**
     * The source path of the sources which need to be analyzed
     */
    @Getter @Setter private static String sourcePath;

    /**
     * A private constructor as this is a singleton to prevent this class from
     * being instantiated by other classes.
     */
    private AdamConfig() {
        docletPath = "";
        configPath = "";
        sourcePath = "";
    }
}
