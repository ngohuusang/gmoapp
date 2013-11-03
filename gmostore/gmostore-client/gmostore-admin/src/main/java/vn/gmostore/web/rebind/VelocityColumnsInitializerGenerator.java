package vn.gmostore.web.rebind;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

public class VelocityColumnsInitializerGenerator extends AbstractVelocityGenerator {
    private static final String VELOCITY_TEMPLATE = "com/gwtplatform/carstore/rebind/ColumnsInitializer.vm";
    private static final String SUFFIX = "Impl";

    @Inject
    protected VelocityColumnsInitializerGenerator(Provider<VelocityContext> velocityContextProvider, VelocityEngine velocityEngine, GeneratorUtil generatorUtil) {
        super(velocityContextProvider, velocityEngine, generatorUtil);
    }

    @Override
    protected void populateVelocityContext(VelocityContext velocityContext, JClassType type) throws UnableToCompleteException {
        ColumnsInitializerDefinitions columnsInitializerDefinitions = ColumnsInitializerDefinitions.createFrom(type);

        velocityContext.put("columnsInitializerDefinitions", columnsInitializerDefinitions);
    }

    public String generate(JClassType type) throws Exception {
        String packageName = type.getPackage().getName();
        String implName = type.getName() + SUFFIX;

        PrintWriter printWriter = getGeneratorUtil().tryCreatePrintWriter(packageName, implName);
        if (printWriter != null) {
            mergeTemplate(printWriter, VELOCITY_TEMPLATE, type, type.getName(), packageName);
        }

        return packageName + "." + implName;
    }
}
