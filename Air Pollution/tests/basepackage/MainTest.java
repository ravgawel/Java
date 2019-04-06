package basepackage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.IllformedLocaleException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    //private final String x[];

    @Test
    public void main() throws Exception {
        String x[] = new String[1];
        x[0] = "-f=1";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano nazwy stacji", e.getMessage());
        }

        x = new String[2];
        x[0] = "-f=1";
        x[1] = "-s=ZłaStacja";
        try
        {
            Main.main(x);
        }
        catch(NullPointerException e)
        {
            assertEquals("Nie znaleziono podanej stacji", e.getMessage());
        }
        x = new String[3];
        x[0] = "-f=2";
        x[1] = "-d=\"2019-01-20 00:00:00\"";
        x[2] = "-p=\"PM10\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano nazwy stacji", e.getMessage());
        }
        x[2] = "-s=\"Kraków, ul. Bujaka\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano parametru", e.getMessage());
        }
        x[1] = "-s=\"PM10\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano daty", e.getMessage());
        }


        x = new String[4];
        x[0] = "-f=3";
        x[1] = "-d1=\"2019-01-20 00:00:00\"";
        x[2] = "-d2=\"2019-01-20 00:00:00\"";
        x[3] = "-p=\"PM10\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano nazwy stacji", e.getMessage());
        }
        x[3] = "-s=\"Kraków, ul. Bujaka\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano parametru", e.getMessage());
        }
        x[1] = "-p=\"PM10\"";;
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano daty początkowej", e.getMessage());
        }
        x[2] = "-d1=\"2019-01-20 00:00:00\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano daty końcowej", e.getMessage());
        }
        x = new String[5];
        x[4] = "-s=Zla stacja";
        x[0] = "-f=3";
        x[1] = "-d1=2019-01-20 00:00:00";
        x[2] = "-d2=2019-01-21 00:00:00";
        x[3] = "-p=\"PM10\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano nazwy stacji", e.getMessage());
        }


        x = new String[2];
        x[0] = "-f=4";
        x[1] = "-d=\"2019-01-20 00:00:00\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano nazwy stacji", e.getMessage());
        }
        x[1] = "-s=\"Kraków, ul. Bujaka\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano daty", e.getMessage());
        }


        x = new String[2];
        x[0] = "-f=6";
        x[1] = "-d=\"2019-01-20 00:00:00\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano nazwy stacji", e.getMessage());
        }
        x[1] = "-s=\"Kraków, ul. Bujaka\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano daty", e.getMessage());
        }


        x = new String[2];
        x[0] = "-f=5";
        x[1] = "-s=\"Kraków, ul. Bujaka\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano daty", e.getMessage());
        }

        x = new String[2];
        x[0] = "-f=7";
        x[1] = "-s=\"Kraków, ul. Bujaka\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano parametru", e.getMessage());
        }


        x = new String[4];
        x[0] = "-f=8";
        x[1] = "-d1=\"2019-01-20 00:00:00\"";
        x[2] = "-d2=\"2019-01-20 00:00:00\"";
        x[3] = "-p=\"PM10\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano nazwy stacji", e.getMessage());
        }
        x[3] = "-s=\"Kraków, ul. Bujaka\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano parametru", e.getMessage());
        }
        x[1] = "-p=\"PM10\"";;
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano daty początkowej", e.getMessage());
        }
        x[2] = "-d1=\"2019-01-20 00:00:00\"";
        try
        {
            Main.main(x);
        }
        catch(IllegalArgumentException e )
        {
            assertEquals("Nie podano daty końcowej", e.getMessage());
        }

    }
}

