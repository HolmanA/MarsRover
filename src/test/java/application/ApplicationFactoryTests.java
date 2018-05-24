package application;

import marsrover.application.Application;
import marsrover.application.ApplicationFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * ApplicationFactoryTests
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class ApplicationFactoryTests {
    private ApplicationFactory applicationFactory;

    @Test
    public void createApplication_shouldReturnUserApplication_whenFileApplicationThrowsIOException() {
        applicationFactory = Mockito.spy(new ApplicationFactory_fileApplicationThrowsIOException());
        Application application = applicationFactory.createApplication("");
        Assert.assertNotNull(application);
        Mockito.verify(applicationFactory, times(1)).createUserApplication(Mockito.any(InputStream.class));
    }

    @Test
    public void createApplication_shouldReturnUserApplication_whenFilenameNull() {
        applicationFactory = Mockito.spy(new ApplicationFactory());
        Application application = applicationFactory.createApplication(null);
        Assert.assertNotNull(application);
        Mockito.verify(applicationFactory, times(1)).createUserApplication(Mockito.any());
    }

    private class ApplicationFactory_fileApplicationThrowsIOException extends ApplicationFactory {
        @Override
        public Application createFileApplication(String filename) throws IOException {
            throw new IOException();
        }
    }
}
