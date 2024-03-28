package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final AdyenLibraryAccessors laccForAdyenLibraryAccessors = new AdyenLibraryAccessors(owner);
    private final SpringLibraryAccessors laccForSpringLibraryAccessors = new SpringLibraryAccessors(owner);
    private final ThymeleafLibraryAccessors laccForThymeleafLibraryAccessors = new ThymeleafLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects) {
        super(config, providers, objects);
    }

    /**
     * Returns the group of libraries at adyen
     */
    public AdyenLibraryAccessors getAdyen() { return laccForAdyenLibraryAccessors; }

    /**
     * Returns the group of libraries at spring
     */
    public SpringLibraryAccessors getSpring() { return laccForSpringLibraryAccessors; }

    /**
     * Returns the group of libraries at thymeleaf
     */
    public ThymeleafLibraryAccessors getThymeleaf() { return laccForThymeleafLibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class AdyenLibraryAccessors extends SubDependencyFactory {

        public AdyenLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for java (com.adyen:adyen-java-api-library)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJava() { return create("adyen.java"); }

    }

    public static class SpringLibraryAccessors extends SubDependencyFactory {
        private final SpringBootLibraryAccessors laccForSpringBootLibraryAccessors = new SpringBootLibraryAccessors(owner);

        public SpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at spring.boot
         */
        public SpringBootLibraryAccessors getBoot() { return laccForSpringBootLibraryAccessors; }

    }

    public static class SpringBootLibraryAccessors extends SubDependencyFactory {
        private final SpringBootStarterLibraryAccessors laccForSpringBootStarterLibraryAccessors = new SpringBootStarterLibraryAccessors(owner);

        public SpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for devtools (org.springframework.boot:spring-boot-devtools)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDevtools() { return create("spring.boot.devtools"); }

        /**
         * Returns the group of libraries at spring.boot.starter
         */
        public SpringBootStarterLibraryAccessors getStarter() { return laccForSpringBootStarterLibraryAccessors; }

    }

    public static class SpringBootStarterLibraryAccessors extends SubDependencyFactory {

        public SpringBootStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for test (org.springframework.boot:spring-boot-starter-test)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() { return create("spring.boot.starter.test"); }

            /**
             * Creates a dependency provider for thymeleaf (org.springframework.boot:spring-boot-starter-thymeleaf)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getThymeleaf() { return create("spring.boot.starter.thymeleaf"); }

            /**
             * Creates a dependency provider for web (org.springframework.boot:spring-boot-starter-web)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getWeb() { return create("spring.boot.starter.web"); }

    }

    public static class ThymeleafLibraryAccessors extends SubDependencyFactory {
        private final ThymeleafLayoutLibraryAccessors laccForThymeleafLayoutLibraryAccessors = new ThymeleafLayoutLibraryAccessors(owner);

        public ThymeleafLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at thymeleaf.layout
         */
        public ThymeleafLayoutLibraryAccessors getLayout() { return laccForThymeleafLayoutLibraryAccessors; }

    }

    public static class ThymeleafLayoutLibraryAccessors extends SubDependencyFactory {

        public ThymeleafLayoutLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for dialect (nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDialect() { return create("thymeleaf.layout.dialect"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: adyenVersion (23.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAdyenVersion() { return getVersion("adyenVersion"); }

            /**
             * Returns the version associated to this alias: springDependendyManagementVersion (1.1.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSpringDependendyManagementVersion() { return getVersion("springDependendyManagementVersion"); }

            /**
             * Returns the version associated to this alias: springVersion (3.1.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSpringVersion() { return getVersion("springVersion"); }

    }

    public static class BundleAccessors extends BundleFactory {
        private final SpringBundleAccessors baccForSpringBundleAccessors = new SpringBundleAccessors(objects, providers, config);

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config) { super(objects, providers, config); }

        /**
         * Returns the group of bundles at bundles.spring
         */
        public SpringBundleAccessors getSpring() { return baccForSpringBundleAccessors; }

    }

    public static class SpringBundleAccessors extends BundleFactory  implements BundleNotationSupplier{

        public SpringBundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config) { super(objects, providers, config); }

            /**
             * Creates a dependency bundle provider for spring which is an aggregate for the following dependencies:
             * <ul>
             *    <li>org.springframework.boot:spring-boot-starter-web</li>
             *    <li>org.springframework.boot:spring-boot-starter-thymeleaf</li>
             *    <li>org.springframework.boot:spring-boot-devtools</li>
             *    <li>nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> asProvider() { return createBundle("spring"); }

            /**
             * Creates a dependency bundle provider for spring.test which is an aggregate for the following dependencies:
             * <ul>
             *    <li>org.springframework.boot:spring-boot-starter-test</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getTest() { return createBundle("spring.test"); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final SpringPluginAccessors paccForSpringPluginAccessors = new SpringPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of plugins at plugins.spring
         */
        public SpringPluginAccessors getSpring() { return paccForSpringPluginAccessors; }

    }

    public static class SpringPluginAccessors extends PluginFactory {

        public SpringPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for spring.dependency to the plugin id 'io.spring.dependency-management'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getDependency() { return createPlugin("spring.dependency"); }

            /**
             * Creates a plugin provider for spring.framework to the plugin id 'org.springframework.boot'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getFramework() { return createPlugin("spring.framework"); }

    }

}
