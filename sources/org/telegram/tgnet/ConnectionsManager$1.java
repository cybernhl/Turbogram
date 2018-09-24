package org.telegram.tgnet;

import java.util.HashMap;

class ConnectionsManager$1 extends ThreadLocal<HashMap<String, ConnectionsManager$ResolvedDomain>> {
    ConnectionsManager$1() {
    }

    protected HashMap<String, ConnectionsManager$ResolvedDomain> initialValue() {
        return new HashMap();
    }
}
