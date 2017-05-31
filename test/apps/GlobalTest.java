package apps;

import play.*;
import play.api.mvc.EssentialFilter;
import play.filters.csrf.CSRFFilter;

public class GlobalTest extends GlobalSettings {


    @Override
    public <T extends EssentialFilter> Class<T>[] filters() {
        return new Class[]{
            CSRFFilter.class
        };
    }
}
