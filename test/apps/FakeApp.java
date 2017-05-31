package apps;

import com.avaje.ebean.*;
import net.sf.ehcache.*;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import play.test.FakeApplication;
import java.io.IOException;

import static play.test.Helpers.*;

public class FakeApp {
    public static FakeApplication app;
    public static String createDdl = "";
    public static String dropDdl = "";

    @BeforeClass
    public static void startApp() throws IOException {
        app = fakeApplication(inMemoryDatabase(), new GlobalTest());
        start(app);
        String evolutionContent = FileUtils.readFileToString(app.getWrappedApplication()
                                            .getFile("conf/evolutions/default/1.sql"));
        String[] splitEvolutionContent = evolutionContent.split("# --- !Ups");
        String[] upsDowns = splitEvolutionContent[1].split("# --- !Downs");
        createDdl = upsDowns[0];
        dropDdl = upsDowns[1];
    }

    @Before
    public void createCleanDb() {
        initDb();
    }

    public static void initDb() {
        Ebean.execute(Ebean.createCallableSql(dropDdl));
        Ebean.execute(Ebean.createCallableSql(createDdl));

        // Ehcacheキャッシュのクリア
        CacheManager manager = CacheManager.create();
        Cache cache = manager.getCache("play");
        cache.removeAll();
    }

    public static void restartApp() {
        start(app);
    }

    @AfterClass
    public static void stopApp() {
        stop(app);
    }
}
