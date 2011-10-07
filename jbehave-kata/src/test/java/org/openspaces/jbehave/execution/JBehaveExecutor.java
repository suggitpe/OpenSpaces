package org.openspaces.jbehave.execution;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openspaces.jbehave.steps.JbehaveSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.jbehave.core.reporters.Format.*;

/**
 * Class that manages the jbehave story execution.
 * <p/>
 * User: suggitpe
 * Date: 27/07/11
 * Time: 10:04
 */
@RunWith(AnnotatedEmbedderRunner.class)
@UsingEmbedder(embedder = Embedder.class)
@Configure(storyLoader = LoadFromURL.class,
        stepPatternParser = RegexPrefixCapturingPatternParser.class,
        parameterConverters = { JBehaveExecutor.JbehaveDateConverter.class },
        storyReporterBuilder = JBehaveExecutor.JbehaveStoryReporterBuilder.class)
@UsingSteps(instances = { JbehaveSteps.class })
public final class JBehaveExecutor extends InjectableEmbedder {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( JBehaveExecutor.class );

    private final String STORY_LOCATION = CodeLocations.codeLocationFromClass( this.getClass() ).getFile();

    @Test
    @Override
    public void run() {
        StoryFinder finder = new StoryFinder();
        injectedEmbedder().runStoriesAsPaths( finder.findPaths( STORY_LOCATION,
                Arrays.asList( "**/*.story" ),
                Arrays.asList( "" ),
                "file:" + STORY_LOCATION ) );
    }

    public static final class JbehaveDateConverter extends ParameterConverters.DateConverter {

        public JbehaveDateConverter() {
            super( new SimpleDateFormat( "dd-MM-yyyy" ) );
        }
    }

    public static final class JbehaveStoryReporterBuilder extends StoryReporterBuilder {

        public JbehaveStoryReporterBuilder() {
            Properties viewResources = new Properties();
            viewResources.put( "decorateNonHtml", "true" );
            this.withPathResolver( new FilePrintStreamFactory.ResolveToSimpleName() );
            this.withFormats( HTML, TXT, XML, STATS );
            this.withViewResources( viewResources );
            this.withFailureTrace( true );
            this.withCodeLocation( CodeLocations.codeLocationFromClass( this.getClass() ) );
        }
    }
}