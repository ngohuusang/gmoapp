package vn.gmostore.web.rebind;

import java.io.PrintWriter;

import javax.inject.Inject;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.uibinder.rebind.MortalLogger;

public class GeneratorUtil {
    private final TypeOracle typeOracle;
    private final MortalLogger logger;
    private final GeneratorContext generatorContext;

    @Inject
    public GeneratorUtil(TypeOracle typeOracle, MortalLogger logger, GeneratorContext generatorContext) {
        this.typeOracle = typeOracle;
        this.logger = logger;
        this.generatorContext = generatorContext;
    }

    public JClassType getType(String typeName) throws UnableToCompleteException {
        return getType(typeName, typeOracle, logger);
    }

    public void closeDefinition(PrintWriter printWriter) {
        generatorContext.commit(logger.getTreeLogger(), printWriter);
    }

    public PrintWriter tryCreatePrintWriter(String packageName, String className) throws UnableToCompleteException {
        return generatorContext.tryCreate(logger.getTreeLogger(), packageName, className);
    }

    public static JClassType getType(String typeName, TypeOracle typeOracle, MortalLogger logger) throws UnableToCompleteException {

        try {
            return typeOracle.getType(typeName);
        } catch (NotFoundException e) {
            logger.die("Cannot find " + typeName);
        }

        return null;
    }
}
