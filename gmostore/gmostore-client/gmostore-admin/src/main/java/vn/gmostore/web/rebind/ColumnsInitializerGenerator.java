package vn.gmostore.web.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.rebind.MortalLogger;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ColumnsInitializerGenerator extends Generator {
    private MortalLogger logger;
    private JClassType type;
    private Injector injector;

    @Override
    public String generate(TreeLogger treeLogger, GeneratorContext generatorContext, String typeName) throws UnableToCompleteException {
        logger = new MortalLogger(treeLogger);
        type = GeneratorUtil.getType(typeName, generatorContext.getTypeOracle(), logger);

        injector = Guice.createInjector(new RebindModule(logger, generatorContext));

        return generateColumnsIntializer();
    }

    private String generateColumnsIntializer() throws UnableToCompleteException {
        VelocityColumnsInitializerGenerator velocityColumnsInitializerGenerator = injector.getInstance(VelocityColumnsInitializerGenerator.class);
        try {
            return velocityColumnsInitializerGenerator.generate(type);
        } catch (Exception e) {
            logger.die(e.getMessage());
        }

        return null;
    }
}
