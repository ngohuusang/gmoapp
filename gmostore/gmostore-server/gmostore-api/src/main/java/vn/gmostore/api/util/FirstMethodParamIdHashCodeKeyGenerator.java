package vn.gmostore.api.util;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInvocation;

import com.googlecode.ehcache.annotations.key.CacheKeyGenerator;

public class FirstMethodParamIdHashCodeKeyGenerator implements CacheKeyGenerator<Serializable> {

    @Override
    public Serializable generateKey(MethodInvocation methodInvocation) {
        Long id = (Long) methodInvocation.getArguments()[0];
        return id;
    }

    @Override
    public Serializable generateKey(Object... objects) {
        if (objects.length != 0)
            return objects[0].hashCode();

        return null;
    }

}