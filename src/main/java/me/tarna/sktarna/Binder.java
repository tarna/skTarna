package me.tarna.sktarna;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Binder extends AbstractModule {

    private final SkTarna plugin;

    public Binder(SkTarna plugin) {
        this.plugin = plugin;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        this.bind(SkTarna.class).toInstance(this.plugin);
    }

}
