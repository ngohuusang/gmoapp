package vn.gmostore.web.rebind;

import java.io.InputStream;
import java.util.Properties;

import javax.inject.Singleton;

import org.apache.velocity.app.VelocityEngine;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.uibinder.rebind.MortalLogger;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class RebindModule extends AbstractModule {
    private static final String VELOCITY_PROPERTIES = "vn/gmostore/web/rebind/velocity.properties";

    private final MortalLogger logger;
    private final GeneratorContext generatorContext;

    public RebindModule(MortalLogger logger, GeneratorContext generatorContext) {
        super();

        this.logger = logger;
        this.generatorContext = generatorContext;
    }

    @Override
    protected void configure() {
        bindConstant().annotatedWith(VelocityProperties.class).to(VELOCITY_PROPERTIES);

        bind(GeneratorUtil.class).in(Singleton.class);
    }

    @Provides
    public MortalLogger getLogger() {
        return logger;
    }

    @Provides
    public TypeOracle getTypeOracle() {
        return generatorContext.getTypeOracle();
    }

    @Provides
    public GeneratorContext getGeneratorContext() {
        return generatorContext;
    }

    @Provides
    @Singleton
    public VelocityEngine getVelocityEngine(@VelocityProperties
    String velocityProperties, MortalLogger logger) throws UnableToCompleteException {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(velocityProperties);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            return new VelocityEngine(properties);
        } catch (Exception e) {
            logger.die("Cannot load velocity properties from " + velocityProperties);
        }

        return null;
    }
}
