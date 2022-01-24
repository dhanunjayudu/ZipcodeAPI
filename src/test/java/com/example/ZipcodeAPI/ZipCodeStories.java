package com.example.ZipcodeAPI;

import com.example.ZipcodeAPI.config.AppConfig;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestConfig.class})
@ComponentScan(basePackages = {"com.example.ZipcodeAPI"})
public class ZipCodeStories extends JUnitStories {

    @Autowired
    private ApplicationContext applicationContext;

    public ZipCodeStories() {
        initJBehaveConfiguration();
    }

    private void initJBehaveConfiguration() {
        Class<?> thisClass = this.getClass();
        useConfiguration(new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(thisClass.getClassLoader()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withDefaultFormats()
                        .withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML, Format.STATS)
                        .withFailureTrace(true)));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), applicationContext);
    }

    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", "**/excluded*.story");
    }
}
